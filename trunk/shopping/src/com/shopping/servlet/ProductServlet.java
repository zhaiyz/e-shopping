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
		// ȡ���������
		String action = request.getParameter("action");

		// ����ת��·��
		String path = "";

		// �����������������Ӧ�Ĳ���
		if ("show".equals(action)) {
			// ȡ��Ҫ��ʾ��Ʒ��id
			int id = Integer.parseInt(request.getParameter("id"));

			ProductVo product = new ProductVo();
			product = ServiceFactory.getProductServiceInstance()
					.findProductById(id);

			request.setAttribute("pro", product);
			path = "user/product.jsp";
		} else if ("buy".equals(action)) {
			// ȡ����Ʒid
			int id = Integer.parseInt(request.getParameter("id"));
			// ȡ����Ʒ����
			int amount = Integer.parseInt(request.getParameter("amount"));
			// ȡ���û�����
			int userId = (Integer) request.getSession().getAttribute("userId");

			CartVo cart = new CartVo();
			cart.setUserId(userId);
			cart.setProId(id);
			cart.setProAmount(amount);

			if (ServiceFactory.getCartServiceInstance().addCart(cart)) {

				// ��ѯ����ǰ�û����ﳵ���������
				List<CartVo> list = new ArrayList<CartVo>();
				list = ServiceFactory.getCartServiceInstance()
						.findCartByUserId(userId);
				request.setAttribute("cart", list);
				path = "user/cart.jsp";
			}
		}

		// ����path������ת
		request.getRequestDispatcher(path).forward(request, response);
	}

}
