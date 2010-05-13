package com.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.util.GetTotalPrice;
import com.shopping.util.JSONUtil;
import com.shopping.util.OrderUtil;
import com.shopping.vo.CartVo;
import com.shopping.vo.ContactVo;
import com.shopping.vo.MyOrderVo;
import com.shopping.vo.NewOrderVo;
import com.shopping.vo.OrderInfoVo;
import com.shopping.vo.ProductVo;
import com.shopping.vo.UserVo;

public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����ַ�����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		// ȡ���������
		String action = request.getParameter("action");

		// ������ת·��
		String path = "";

		// ����һ�����ص�����Աҳ������ݱ���
		String json = "";

		// ����һ�������ж��Ƿ����ҳ����ת�ı���������ͱ�Ĳ�һ�������Ĭ��Ϊ��ת����Ҫ����Ϊ���ҳ�棬Ҫ������ת���е��
		boolean flag = true;

		// �����������������Ӧ�Ĳ���
		if ("add".equals(action)) {
			// ����ȡ�ø��ʽ
			int payment = Integer.parseInt(request.getParameter("payment"));

			// ���ݸ��ʽ����һЩ�ж�
			if (payment == 0) {
				// ������ȸ���󷢻�����ôҪ���ж��û�������Ƿ��㹻

				// ȡ���û�ѡ����ʵݷ�ʽ
				int post = Integer.parseInt(request.getParameter("post"));

				// ��session����ȡ�õ�ǰ�û�����
				int userId = (Integer) request.getSession().getAttribute(
						"userId");

				// ������û�������Ʒ���ܼ�
				float total = GetTotalPrice.getTotalPrice(userId);

				// ͨ���ʵݷ�ʽ�жϳ��ʷѣ��ټ�����Ʒ���ܼ۾����û�Ҫ���ķ���
				if (post == 0) {
					total += 10;
				} else {
					total += 15;
				}

				// ͨ��userIdȡ���û���Ϣ
				UserVo user = new UserVo();
				user = ServiceFactory.getUserServiceInstance().findUserById(
						userId);

				// ����Ҫ�ж��û�������Ƿ��㹻֧�����еķ��ã�����㹻����ת������ҳ������������ת��һ����ʾҳ
				if (user.getBalance() > total) {
					// ���㹻�����
					path = "user/pay.jsp";

					// ���ϸ�������д��������Ϣ�ŵ�session����
					request.getSession().setAttribute("name",
							request.getParameter("name"));
					request.getSession().setAttribute("address",
							request.getParameter("address"));
					request.getSession().setAttribute("postcode",
							request.getParameter("postcode"));
					request.getSession().setAttribute("telphone",
							request.getParameter("telphone"));
					request.getSession().setAttribute("freetime",
							request.getParameter("freetime"));
					request.getSession().setAttribute("payment",
							request.getParameter("payment"));
					request.getSession().setAttribute("post",
							request.getParameter("post"));
					// ���ܷ���Ҳ����session����
					request.getSession().setAttribute("total", total);
				} else {
					// ������㣬����ת��һ����ʾҳ��
					path = "error.jsp";

					// �Ѵ�����Ϣ����request��
					request.setAttribute("op_error", "����!");
				}
			} else {
				// �����ǻ���������������������Ҫֱ�����ɶ�����

				MyOrderVo order = new MyOrderVo();

				// ��session�����ø�����Ϣ
				int userId = (Integer) request.getSession().getAttribute(
						"userId");
				String orderNum = "" + System.currentTimeMillis();
				payment = Integer.parseInt(request.getParameter("payment"));
				int post = Integer.parseInt(request.getParameter("post"));
				float totalPrice = GetTotalPrice.getTotalPrice(userId);

				// ͨ���ʵݷ�ʽ�жϳ��ʷѣ��ټ�����Ʒ���ܼ۾����û�Ҫ���ķ���
				if (post == 0) {
					totalPrice += 10;
				} else {
					totalPrice += 15;
				}
				// δ����
				int orderState = 0;

				order.setUserId(userId);
				order.setOrderNum(orderNum);
				order.setPayment(payment);
				order.setPost(post);
				order.setTotalPrice(totalPrice);
				order.setOrderState(orderState);

				// �ȱ��涩���������Ӧ����һ������ģ����������ޣ�����ֻ����д�ɣ�������һ������ı�����ǣ������ͼ�����Ϣ��һ����ģ�����
				ServiceFactory.getOrderServiceInstance().addMyOrder(order);

				// ͨ���������ٲ�ѯ������
				order = ServiceFactory.getOrderServiceInstance()
						.findOrderByOrderNum(orderNum);

				// ������Ϣ����
				ContactVo contact = new ContactVo();
				int orderId = order.getOrderId();
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String postcode = request.getParameter("postcode");
				String telphone = request.getParameter("telphone");
				int freetime = Integer.parseInt(request
						.getParameter("freetime"));

				contact.setOrderId(orderId);
				contact.setName(name);
				contact.setAddress(address);
				contact.setPostcode(postcode);
				contact.setTelphone(telphone);
				contact.setFreetime(freetime);

				// ���涩��������Ϣ
				ServiceFactory.getContactServiceInstance().addContact(contact);

				// ͨ������������ѯ����������Ϣ
				contact = ServiceFactory.getContactServiceInstance()
						.findContactByOrderId(orderId);

				// �Ѽ�����Ϣ���������뵽��������
				order.setConId(contact.getConId());

				// �ٸ��¶������ݣ���Ҫ����Ϊ�����˼�����Ϣ�����������Ǳ��簡��������
				ServiceFactory.getOrderServiceInstance().modifyMyOrder(order);

				// ����Ҫ��������ϸ���ݵĲ�����

				// ��Ҫ�ӹ��ﳵ����ȡ����ǰ�û����е���Ʒ
				List<CartVo> list = new ArrayList<CartVo>();
				list = ServiceFactory.getCartServiceInstance()
						.findCartByUserId(userId);
				Iterator<CartVo> iterator = list.iterator();
				while (iterator.hasNext()) {
					CartVo cart = new CartVo();
					OrderInfoVo info = new OrderInfoVo();
					ProductVo product = new ProductVo();

					cart = iterator.next();

					product = ServiceFactory.getProductServiceInstance()
							.findProductById(cart.getProId());

					info.setOrderId(orderId);
					info.setProId(cart.getProId());
					info.setAmount(cart.getProAmount());
					info.setPrice(product.getDisPrice() * cart.getProAmount());

					// ��Ӷ�����ϸ����
					ServiceFactory.getOrderInfoServiceInstance().addOrderInfo(
							info);

					// ������Ʒ�Ŀ�������
					product.setStock(product.getStock() - cart.getProAmount());
					product.setSales(product.getSales() + cart.getProAmount());
					ServiceFactory.getProductServiceInstance().modifyProduct(
							product);
				}

				// �����û�������������
				UserVo user = new UserVo();
				user = ServiceFactory.getUserServiceInstance().findUserById(
						userId);

				user.setBalance(user.getBalance() - totalPrice);
				user.setPayed(user.getPayed() + totalPrice);

				ServiceFactory.getUserServiceInstance().modifyUser(user);

				// ��Ҫ�ѹ��ﳵ�������Ʒȫ�����
				ServiceFactory.getCartServiceInstance().removeByUserId(userId);

				// ��תҳ��
				path = "error.jsp";

				// �����ɹ�
				request.setAttribute("op_error", "���ɶ����������ɹ�����");

			}
		} else if ("pay".equals(action)) {
			// ȡ���û������������
			String password = request.getParameter("password");

			// ȡ���û�����
			int userId = (Integer) request.getSession().getAttribute("userId");

			// ͨ������ȡ���û���Ϣ
			UserVo user = new UserVo();
			user = ServiceFactory.getUserServiceInstance().findUserById(userId);

			// �ж��û�����������Ƿ���ȷ
			if (password.equals(user.getUserPassword())) {
				// ���һ���������ɶ�����������һϵ�еĲ���

				MyOrderVo order = new MyOrderVo();

				// ��session�����ø�����Ϣ
				userId = (Integer) request.getSession().getAttribute("userId");
				String orderNum = "" + System.currentTimeMillis();
				int payment = Integer.parseInt(request.getSession()
						.getAttribute("payment").toString());
				int post = Integer.parseInt(request.getSession().getAttribute(
						"post").toString());
				float totalPrice = GetTotalPrice.getTotalPrice(userId);

				// ͨ���ʵݷ�ʽ�жϳ��ʷѣ��ټ�����Ʒ���ܼ۾����û�Ҫ���ķ���
				if (post == 0) {
					totalPrice += 10;
				} else {
					totalPrice += 15;
				}
				// δ����
				int orderState = 0;

				order.setUserId(userId);
				order.setOrderNum(orderNum);
				order.setPayment(payment);
				order.setPost(post);
				order.setTotalPrice(totalPrice);
				order.setOrderState(orderState);

				// �ȱ��涩���������Ӧ����һ������ģ����������ޣ�����ֻ����д�ɣ�������һ������ı�����ǣ������ͼ�����Ϣ��һ����ģ�����
				ServiceFactory.getOrderServiceInstance().addMyOrder(order);

				// ͨ���������ٲ�ѯ������
				order = ServiceFactory.getOrderServiceInstance()
						.findOrderByOrderNum(orderNum);

				// ������Ϣ����
				ContactVo contact = new ContactVo();
				int orderId = order.getOrderId();
				String name = request.getSession().getAttribute("name")
						.toString();
				String address = request.getSession().getAttribute("address")
						.toString();
				String postcode = request.getSession().getAttribute("post")
						.toString();
				String telphone = request.getSession().getAttribute("telphone")
						.toString();
				int freetime = Integer.parseInt(request.getSession()
						.getAttribute("freetime").toString());

				contact.setOrderId(orderId);
				contact.setName(name);
				contact.setAddress(address);
				contact.setPostcode(postcode);
				contact.setTelphone(telphone);
				contact.setFreetime(freetime);

				// ���涩��������Ϣ
				ServiceFactory.getContactServiceInstance().addContact(contact);

				// ͨ������������ѯ����������Ϣ
				contact = ServiceFactory.getContactServiceInstance()
						.findContactByOrderId(orderId);

				// �Ѽ�����Ϣ���������뵽��������
				order.setConId(contact.getConId());

				// �ٸ��¶������ݣ���Ҫ����Ϊ�����˼�����Ϣ�����������Ǳ��簡��������
				ServiceFactory.getOrderServiceInstance().modifyMyOrder(order);

				// ����Ҫ��������ϸ���ݵĲ�����

				// ��Ҫ�ӹ��ﳵ����ȡ����ǰ�û����е���Ʒ
				List<CartVo> list = new ArrayList<CartVo>();
				list = ServiceFactory.getCartServiceInstance()
						.findCartByUserId(userId);
				Iterator<CartVo> iterator = list.iterator();
				while (iterator.hasNext()) {
					CartVo cart = new CartVo();
					OrderInfoVo info = new OrderInfoVo();
					ProductVo product = new ProductVo();

					cart = iterator.next();

					product = ServiceFactory.getProductServiceInstance()
							.findProductById(cart.getProId());

					info.setOrderId(orderId);
					info.setProId(cart.getProId());
					info.setAmount(cart.getProAmount());
					info.setPrice(product.getDisPrice() * cart.getProAmount());

					// ��Ӷ�����ϸ����
					ServiceFactory.getOrderInfoServiceInstance().addOrderInfo(
							info);

					// ������Ʒ�Ŀ�������
					product.setStock(product.getStock() - cart.getProAmount());
					product.setSales(product.getSales() + cart.getProAmount());
					ServiceFactory.getProductServiceInstance().modifyProduct(
							product);
				}

				// �����û�������������
				user.setBalance(user.getBalance() - totalPrice);
				user.setPayed(user.getPayed() + totalPrice);

				ServiceFactory.getUserServiceInstance().modifyUser(user);

				// ��Ҫ�ѹ��ﳵ�������Ʒȫ�����
				ServiceFactory.getCartServiceInstance().removeByUserId(userId);

				// ��תҳ��
				path = "error.jsp";

				// �����ɹ�
				request.setAttribute("op_error", "���ɶ����������ɹ�����");

			} else {
				// ����������������ת����ʾҳ��
				path = "error.jsp";

				// �Ѵ�����Ϣ���뵽request��
				request.setAttribute("op_error", "�����������!");
			}

		} else if ("show".equals(action)) {
			// ��ѯ����ǰ�û����еĶ�������ʾ
			List<MyOrderVo> list = new ArrayList<MyOrderVo>();

			// ȡ�õ�ǰ�û�����
			int userId = (Integer) request.getSession().getAttribute("userId");

			list = ServiceFactory.getOrderServiceInstance().findOrderByUserId(
					userId);

			// �ѽ��ȫ�����뵽request��
			request.setAttribute("order", list);
			path = "user/order.jsp";
		} else if ("confirm".equals(action)) {
			path = "/order?action=show";

			// ȡ�ö�������
			int id = Integer.parseInt(request.getParameter("id"));

			// ͨ������ȡ�ö�������
			MyOrderVo order = new MyOrderVo();
			order = ServiceFactory.getOrderServiceInstance()
					.findMyOrderById(id);

			// ���ó����ջ�
			order.setOrderState(2);

			// ���´˶���
			ServiceFactory.getOrderServiceInstance().modifyMyOrder(order);
		} else if ("all".equals(action)) {
			// �����еĶ������з�ҳ��ѯ���������̨����ҳ��
			// ��÷�ҳ����
			int start = 0;
			int limit = 10;

			// ��������
			int total = 0;

			try {
				start = Integer.parseInt(request.getParameter("start"));
			} catch (NumberFormatException e) {
				start = 0;
			}

			try {
				limit = Integer.parseInt(request.getParameter("limit"));
			} catch (NumberFormatException e) {
				limit = 10;
			}

			// ����Ǹ�С�ؼ������嶩��״̬
			int state = -1;

			try {
				state = Integer.parseInt(request.getParameter("state"));
			} catch (NumberFormatException e) {
				state = -1;
			}

			List<MyOrderVo> list = new ArrayList<MyOrderVo>();

			if (state == -1) {
				// ���stateΪ-1����ô���Ƿ�ҳ��ѯ�����еĶ���

				total = ServiceFactory.getOrderServiceInstance().getTotalNum();

				list = ServiceFactory.getOrderServiceInstance().findAllMyOrder(
						start, limit);
			} else {
				// �������״̬����Ϊ-1����ô�Ͱ�����״̬���в�ѯ
				total = ServiceFactory.getOrderServiceInstance().getTotalNum(
						state);

				list = ServiceFactory.getOrderServiceInstance()
						.findOrderByState(state, start, limit);
			}

			List<NewOrderVo> l = new ArrayList<NewOrderVo>();

			Iterator<MyOrderVo> iter = list.iterator();
			while (iter.hasNext()) {
				MyOrderVo order = new MyOrderVo();
				NewOrderVo n = new NewOrderVo();
				order = iter.next();

				n = OrderUtil.toNewOrder(order);

				l.add(n);
			}

			// ��֯Ҫ���ص�json
			json += "{total:" + total + ",list:" + JSONUtil.list2json(l) + "}";

			// �������Ҫ����ҳ����ת
			flag = false;
		}

		// ����path������ת
		if (flag) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			out.println(json);
		}
	}
}
