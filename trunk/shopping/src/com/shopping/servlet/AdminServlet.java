package com.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.AdminVo;

public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 统一字符集，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		String path = "";
		String json = "";

		// 用来判断是否进行跳转
		boolean flag = false;

		// 根据命令参数进行相应的操作
		if ("login".equals(action)) {
			// 管理员登录操作
			String name = request.getParameter("user");
			String password = request.getParameter("pass");
			String checkCode = request.getParameter("checkcode");

			// 判断验证码是否正确
			String code = (String) request.getSession().getAttribute(
					"checkCode");

			if (code.equals(checkCode)) {
				// 验证码正确
				AdminVo admin = new AdminVo();

				admin.setAdminName(name);
				admin.setAdminPassword(password);

				if (ServiceFactory.getAdminServiceInstance().isLogin(admin)) {
					// 登录成功
				//	path = "admin/adminhome.jsp";
					request.getSession().setAttribute("adminName", name);
					json += "{success:true,url:\"/shopping/admin/adminhome.jsp\"}";
					flag = false;
				} else {
					// 登录失败
					flag = false;
					json += "{success:false,error:\"用户名或密码错误\"}";
				}
			} else {
				// 验证码错误
				flag = false;
				json += "{success:false,error:\"验证码错误\"}";
			}
		} else if ("logout".equals(action)) {
			//管理员退出
			request.getSession().removeAttribute("adminName");
			json = "{success:true, url:\"/shopping/forward?page=index\"}";
			flag = false;
		}

		if (flag) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			out.println(json);
		}
	}

}
