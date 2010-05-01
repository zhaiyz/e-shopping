package com.shopping.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.vo.UserVo;
import com.shopping.factory.ServiceFactory;

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
					request.getSession().setAttribute("userName", userName);
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
		}

		// 把error放入到request入
		request.setAttribute("error", error);

		// 根据path进行跳转
		request.getRequestDispatcher(path).forward(request, response);
	}

}
