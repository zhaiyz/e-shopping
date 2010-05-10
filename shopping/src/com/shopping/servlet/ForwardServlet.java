package com.shopping.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.CartVo;
import com.shopping.vo.ProductVo;
import com.shopping.vo.UserVo;

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
			path = "/index.jsp";
		} else if ("index".equals(page)) {
			List<ProductVo> recommenList = new ArrayList<ProductVo>();
			List<ProductVo> newProductList = new ArrayList<ProductVo>();
			recommenList = ServiceFactory.getProductServiceInstance().getRecommenProduct();
			newProductList = ServiceFactory.getProductServiceInstance().getNewProduct();
			request.setAttribute("recommenList", recommenList);
			request.setAttribute("newProductList", newProductList);
			path = "/homepage.jsp";

			// 查询出所有的大类，这是一个悲剧，而且只是开始
			// List<CategoryVo> listc = new ArrayList<CategoryVo>();
			// listc =
			// ServiceFactory.getCategoryServiceInstance().findAllCategory();
			//			
			// request.setAttribute("category", listc);
		} else if ("cart".equals(page)) {
			// 获得用户主键
			int userId = (Integer) request.getSession().getAttribute("userId");

			List<CartVo> list = new ArrayList<CartVo>();
			list = ServiceFactory.getCartServiceInstance().findCartByUserId(
					userId);
			request.setAttribute("cart", list);
			path = "user/cart.jsp";
		} else if ("order".equals(page)) {
			path = "/user/info.jsp";
		}else if("personal".equals(page)){
			int id = (Integer)request.getSession().getAttribute("userId");
			UserVo user = ServiceFactory.getUserServiceInstance().findUserById(id);
			request.setAttribute("user", user);
			path = "/user/personal.jsp";
		}else if("account".equals(page)){
			int id = (Integer)request.getSession().getAttribute("userId");
			UserVo user = ServiceFactory.getUserServiceInstance().findUserById(id);
			request.setAttribute("user", user);
			path = "/user/account.jsp";
		}

		request.getRequestDispatcher(path).forward(request, response);
	}
}
