package com.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.util.JSONUtil;
import com.shopping.vo.UserVo;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 用来解决中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 取得操作码
		String action = request.getParameter("action");

		String path = "";
		String error = "";
		String json = "";

		PrintWriter out = response.getWriter();

		// 定义一个变量，来标志是否进行跳转，这次默认为跳转
		boolean flag = true;

		// 根据操作码进行相应的操作
		if ("login".equals(action)) {
			String userName = request.getParameter("username");
			String userPassword = request.getParameter("password");
			String checkCode1 = request.getParameter("checkCode");
			String checkCode2 = (String) request.getSession().getAttribute(
					"checkCode");

			if (checkCode1.equals(checkCode2)) {
				UserVo user = new UserVo();
				user.setUserName(userName);
				user.setUserPassword(userPassword);

				if (ServiceFactory.getUserServiceInstance().isLogin(user)) {
					// 登录成功，把userName放入session
					int userId = ServiceFactory.getUserServiceInstance().findUserByName(userName).getUserId();
					
					request.getSession().setAttribute("userName", userName);
					request.getSession().setAttribute("userId",	userId);

					path = "index.jsp";
				} else {
					// 登录失败
					path = "user/login.jsp";
					error = "用户名或密码错误";
				}
			} else {
				// 验证码错误
				path = "user/login.jsp";
				error = "验证码错误";
			}
		} else if ("register".equals(action)) {
			UserVo user = new UserVo();

			String userName = request.getParameter("username");
			String password = request.getParameter("password");

			user.setUserName(userName);
			user.setUserPassword(password);

			if (!"none".equals(request.getParameter("prompt"))) {
				String prompt = request.getParameter("prompt");
				String answer = request.getParameter("answer");

				user.setPrompt(prompt);
				user.setAnswer(answer);
			}

			if (request.getParameter("email") != null) {
				String email = request.getParameter("eamil");

				user.setEmail(email);
			}
			if (request.getParameter("phone") != null) {
				String phone = request.getParameter("phone");

				user.setPhone(phone);
			}

			int gender = Integer.parseInt(request.getParameter("gender"));

			user.setGender(gender);

			user.setGrade(0);
			user.setBalance(0.0f);
			user.setPayed(0.0f);
			user.setUserState(0);

			if (ServiceFactory.getUserServiceInstance().addUser(user)) {
				request.getSession().setAttribute("userName", userName);
				// UserVo userV = new UserVo();
				user = ServiceFactory.getUserServiceInstance().findUserByName(
						userName);
				request.getSession().setAttribute("userId", user.getUserId());
				path = "index.jsp";
			} else {
				error = "注册失败!";
				path = "/user/register.jsp";
			}
		} else if ("userUpdate".equals(action)) {
			int id = (Integer) request.getSession().getAttribute("userId");
			UserVo user = ServiceFactory.getUserServiceInstance().findUserById(
					id);
			String userPassword = request.getParameter("password");
			int gender = Integer.parseInt(request.getParameter("gender"));
			String prompt = request.getParameter("prompt");
			String answer = request.getParameter("answer");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			user.setAnswer(answer);
			user.setEmail(email);
			user.setGender(gender);
			user.setPhone(phone);
			user.setPrompt(prompt);
			user.setUserPassword(userPassword);
			if (ServiceFactory.getUserServiceInstance().modifyUser(user)) {
				path = "index.jsp";
			} else {
				error = "个人信息修改失败！";
				path = "/user/personal.jsp";
			}
		} else if ("name".equals(action)) {
			// 这个有点晕啊，设计复杂了点，按用户主键查询出用户名
			int userId = Integer.parseInt(request.getParameter("userId"));

			UserVo user = new UserVo();

			user = ServiceFactory.getUserServiceInstance().findUserById(userId);

			json += "{success:true,userName:" + user.getUserName() + "}";

			flag = false;
		} else if ("all".equals(action)) {
			// 关键字
			String key = request.getParameter("query");

			int total = 0;
			int start = 0;
			int limit = 10;

			List<UserVo> list = new ArrayList<UserVo>();

			start = Integer.parseInt(request.getParameter("start"));
			limit = Integer.parseInt(request.getParameter("limit"));

			if (key == null) {
				// 如果为空那么就查询出全部
				total = ServiceFactory.getUserServiceInstance().getTotalNum();

				list = ServiceFactory.getUserServiceInstance().findAllUser(
						start, limit);
			} else {
				// 如果不为空就按条件进行查询
				total = ServiceFactory.getUserServiceInstance()
						.getTotalNum(key);
				list = ServiceFactory.getUserServiceInstance().findByLike(key,
						start, limit);
			}

			json += "{total:" + total + ",list:" + JSONUtil.list2json(list)
					+ "}";

			// 不进行跳转
			flag = false;
		} else if ("update".equals(action)) {
			// 主要是对用户禁言，解禁进行操作
			int userId = Integer.parseInt(request.getParameter("userId"));

			// 根据用户主键查询出用户
			UserVo user = new UserVo();
			user = ServiceFactory.getUserServiceInstance().findUserById(userId);

			if (user.getUserState() == 0) {
				user.setUserState(1);
			} else {
				user.setUserState(0);
			}

			if (ServiceFactory.getUserServiceInstance().modifyUser(user)) {
				json += "{success:true}";
			} else {
				json += "{success:false}";
			}

			flag = false;
		}

		if (flag) {
			// 把error放入到request入
			request.setAttribute("error", error);

			// 根据path进行跳转
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			out.print(json);
		}
	}
}
