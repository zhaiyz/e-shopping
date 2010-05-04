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

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 取得命令参数
		String action = request.getParameter("action");

		// 定义转发路径
		String path = "";

		// 根据命令参数进行相应的操作
		if ("show".equals(action)) {
			// 取得要显示商品的id
			int id = Integer.parseInt(request.getParameter("id"));

			ProductVo product = new ProductVo();
			product = ServiceFactory.getProductServiceInstance()
					.findProductById(id);

			request.setAttribute("pro", product);
			path = "user/product.jsp";
		} else if ("buy".equals(action)) {
			// 取得商品id
			int id = Integer.parseInt(request.getParameter("id"));
			// 取得商品数量
			int amount = Integer.parseInt(request.getParameter("amount"));
			// 取得用户主键
			int userId = (Integer) request.getSession().getAttribute("userId");

			CartVo cart = new CartVo();
			cart.setUserId(userId);
			cart.setProId(id);
			cart.setProAmount(amount);

			if (ServiceFactory.getCartServiceInstance().addCart(cart)) {

				// 查询出当前用户购物车里面的内容
				List<CartVo> list = new ArrayList<CartVo>();
				list = ServiceFactory.getCartServiceInstance()
						.findCartByUserId(userId);
				request.setAttribute("cart", list);
				path = "user/cart.jsp";
			}
		}

		// 根据path进行跳转
		request.getRequestDispatcher(path).forward(request, response);
	}

}
