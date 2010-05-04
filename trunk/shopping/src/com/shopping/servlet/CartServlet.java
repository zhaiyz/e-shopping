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
		// ȡ���������
		String action = request.getParameter("action");

		// ������ת·��
		String path = "";

		// �����������������Ӧ�Ĳ���
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
				// ��ǰ̨ȡ����Ӧ��Ʒ����Ʒ����
				amount = Integer.parseInt(request.getParameter(""
						+ cart.getCartId()));

				// ���amount����0��ô�͸�����Ʒ����
				if (amount > 0) {
					cart.setProAmount(amount);
					ServiceFactory.getCartServiceInstance().modifyCart(cart);
				} else {
					// ���amount������0����ô��ɾ��������¼
					ServiceFactory.getCartServiceInstance().removeCartById(
							cart.getCartId());
				}

				// ��ѯ����ǰ�û����ﳵ���������
				list = ServiceFactory.getCartServiceInstance()
						.findCartByUserId(userId);
				request.setAttribute("cart", list);
				path = "user/cart.jsp";
			}
		} else if("delete".equals(action)) {
			//ȡ�ù��ﳵid
			int id = Integer.parseInt(request.getParameter("id"));
			//ȡ���û�id
			int userId = (Integer)request.getSession().getAttribute("userId");
			
			ServiceFactory.getCartServiceInstance().removeCartById(id);
			
			List<CartVo> list = new ArrayList<CartVo>();
			
			// ��ѯ����ǰ�û����ﳵ���������
			list = ServiceFactory.getCartServiceInstance()
					.findCartByUserId(userId);
			request.setAttribute("cart", list);
			path = "user/cart.jsp";
		}

		// ����path������ת
		request.getRequestDispatcher(path).forward(request, response);
	}

}
