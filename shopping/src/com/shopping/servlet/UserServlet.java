package com.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

		PrintWriter out = response.getWriter();

		// 取得操作码
		String action = request.getParameter("action");

		String json = "";

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
					json += "{success:true,info:'登录成功!'}";
				} else {
					json += "{success:false,info:'用户名或密码错误!'}";
				}
			} else {
				json += "{success:false,info:'验证码错误!'}";
			}
		}

		out.print(json);
	}

}
