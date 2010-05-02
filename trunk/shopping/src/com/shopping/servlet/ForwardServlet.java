package com.shopping.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.vo.CategoryVo;
import com.shopping.factory.ServiceFactory;

public class ForwardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得页面转发命令
		String page = request.getParameter("page");
		
		// 跳转到的页面地址
		String path = "";

		if ("login".equals(page)) {
			path = "user/login.jsp";
		} else if ("register".equals(page)) {
			path = "user/register.jsp";
		} else if ("logout".equals(page)) {
			request.getSession().removeAttribute("userName");
			path ="/index.jsp";
		} else if ("index".equals(page)) {
			path = "/homepage.jsp";
			
			//查询出所有的大类
			List<CategoryVo> listc = new ArrayList<CategoryVo>();
			listc = ServiceFactory.getCategoryServiceInstance().findAllCategory();
			
			request.setAttribute("category", listc);
		}

		request.getRequestDispatcher(path).forward(request, response);
	}
}
