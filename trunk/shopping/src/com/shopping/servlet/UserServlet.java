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
		// ���������������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// ȡ�ò�����
		String action = request.getParameter("action");

		String path = "";
		String error = "";
		String json = "";

		PrintWriter out = response.getWriter();

		// ����һ������������־�Ƿ������ת�����Ĭ��Ϊ��ת
		boolean flag = true;

		// ���ݲ����������Ӧ�Ĳ���
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
					// ��¼�ɹ�����userName����session
					int userId = ServiceFactory.getUserServiceInstance().findUserByName(userName).getUserId();
					
					request.getSession().setAttribute("userName", userName);
					request.getSession().setAttribute("userId",	userId);

					path = "index.jsp";
				} else {
					// ��¼ʧ��
					path = "user/login.jsp";
					error = "�û������������";
				}
			} else {
				// ��֤�����
				path = "user/login.jsp";
				error = "��֤�����";
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
				error = "ע��ʧ��!";
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
				error = "������Ϣ�޸�ʧ�ܣ�";
				path = "/user/personal.jsp";
			}
		} else if ("name".equals(action)) {
			// ����е��ΰ�����Ƹ����˵㣬���û�������ѯ���û���
			int userId = Integer.parseInt(request.getParameter("userId"));

			UserVo user = new UserVo();

			user = ServiceFactory.getUserServiceInstance().findUserById(userId);

			json += "{success:true,userName:" + user.getUserName() + "}";

			flag = false;
		} else if ("all".equals(action)) {
			// �ؼ���
			String key = request.getParameter("query");

			int total = 0;
			int start = 0;
			int limit = 10;

			List<UserVo> list = new ArrayList<UserVo>();

			start = Integer.parseInt(request.getParameter("start"));
			limit = Integer.parseInt(request.getParameter("limit"));

			if (key == null) {
				// ���Ϊ����ô�Ͳ�ѯ��ȫ��
				total = ServiceFactory.getUserServiceInstance().getTotalNum();

				list = ServiceFactory.getUserServiceInstance().findAllUser(
						start, limit);
			} else {
				// �����Ϊ�վͰ��������в�ѯ
				total = ServiceFactory.getUserServiceInstance()
						.getTotalNum(key);
				list = ServiceFactory.getUserServiceInstance().findByLike(key,
						start, limit);
			}

			json += "{total:" + total + ",list:" + JSONUtil.list2json(list)
					+ "}";

			// ��������ת
			flag = false;
		} else if ("update".equals(action)) {
			// ��Ҫ�Ƕ��û����ԣ�������в���
			int userId = Integer.parseInt(request.getParameter("userId"));

			// �����û�������ѯ���û�
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
			// ��error���뵽request��
			request.setAttribute("error", error);

			// ����path������ת
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			out.print(json);
		}
	}
}
