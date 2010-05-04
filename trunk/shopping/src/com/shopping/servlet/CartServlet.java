package com.shopping.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.CartVo;

public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 取得命令参数
		String action = request.getParameter("action");

		// 定义跳转路径
		String path = "";

		// 根据命令参数进行相应的操作
		if ("update".equals(action)) {
			int userId = (Integer) request.getSession().getAttribute("userId");

			List<CartVo> list = new ArrayList<CartVo>();
			list = ServiceFactory.getCartServiceInstance().findCartByUserId(
					userId);
			Iterator<CartVo> iterator = list.iterator();

			int amount = -1;

			while (iterator.hasNext()) {
				amount = -1;
				CartVo cart = new CartVo();
				cart = iterator.next();
				// 从前台取得相应商品的商品数量
				amount = Integer.parseInt(request.getParameter(""
						+ cart.getCartId()));

				// 如果amount大于0那么就更新商品数量
				if (amount > 0) {
					cart.setProAmount(amount);
					ServiceFactory.getCartServiceInstance().modifyCart(cart);
				} else {
					// 如果amount不大于0，那么就删除此条记录
					ServiceFactory.getCartServiceInstance().removeCartById(
							cart.getCartId());
				}

				// 查询出当前用户购物车里面的内容
				list = ServiceFactory.getCartServiceInstance()
						.findCartByUserId(userId);
				request.setAttribute("cart", list);
				path = "user/cart.jsp";
			}
		} else if("delete".equals(action)) {
			//取得购物车id
			int id = Integer.parseInt(request.getParameter("id"));
			//取得用户id
			int userId = (Integer)request.getSession().getAttribute("userId");
			
			ServiceFactory.getCartServiceInstance().removeCartById(id);
			
			List<CartVo> list = new ArrayList<CartVo>();
			
			// 查询出当前用户购物车里面的内容
			list = ServiceFactory.getCartServiceInstance()
					.findCartByUserId(userId);
			request.setAttribute("cart", list);
			path = "user/cart.jsp";
		}

		// 根据path进行跳转
		request.getRequestDispatcher(path).forward(request, response);
	}

}
