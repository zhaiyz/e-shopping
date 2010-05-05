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
import com.shopping.util.GetTotalPrice;
import com.shopping.vo.CartVo;
import com.shopping.vo.ContactVo;
import com.shopping.vo.MyOrderVo;
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
		// 取得命令参数
		String action = request.getParameter("action");

		// 定义跳转路径
		String path = "";

		// 根据命令参数进行相应的操作
		if ("add".equals(action)) {
			// 首先取得付款方式
			int payment = Integer.parseInt(request.getParameter("payment"));

			// 根据付款方式进行一些判断
			if (payment == 0) {
				// 如果是先付款，后发货，那么要先判断用户的余额是否足够

				// 取得用户选择的邮递方式
				int post = Integer.parseInt(request.getParameter("post"));

				// 从session里面取得当前用户主键
				int userId = (Integer) request.getSession().getAttribute(
						"userId");

				// 计算出用户所有商品的总价
				float total = GetTotalPrice.getTotalPrice(userId);

				// 通过邮递方式判断出邮费，再加上商品的总价就是用户要付的费用
				if (post == 0) {
					total += 10;
				} else {
					total += 15;
				}

				// 通过userId取得用户信息
				UserVo user = new UserVo();
				user = ServiceFactory.getUserServiceInstance().findUserById(
						userId);

				// 现在要判断用户的余额是否足够支付所有的费用，如果足够就跳转到付费页，如果不足就跳转到一个提示页
				if (user.getBalance() > total) {
					// 有足够的余额
					path = "user/pay.jsp";

					// 把上个表单所填写的所有信息放到session里面
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
					// 把总费用也放在session里面
					request.getSession().setAttribute("total", total);
				} else {
					// 如果余额不足，就跳转到一个提示页面
					path = "error.jsp";

					// 把错误信息放入request中
					request.setAttribute("op_error", "余额不足!");
				}
			} else {
				// 现在是货到付款的那种情况，这下要直接生成订单了

				MyOrderVo order = new MyOrderVo();

				// 从session里面获得各种信息
				int userId = (Integer) request.getSession().getAttribute(
						"userId");
				String orderNum = "" + System.currentTimeMillis();
				payment = Integer.parseInt(request.getParameter("payment"));
				int post = Integer.parseInt(request.getParameter("post"));
				float totalPrice = GetTotalPrice.getTotalPrice(userId);

				// 通过邮递方式判断出邮费，再加上商品的总价就是用户要付的费用
				if (post == 0) {
					totalPrice += 10;
				} else {
					totalPrice += 15;
				}
				// 未发货
				int orderState = 0;

				order.setUserId(userId);
				order.setOrderNum(orderNum);
				order.setPayment(payment);
				order.setPost(post);
				order.setTotalPrice(totalPrice);
				order.setOrderState(orderState);

				// 先保存订单，这个本应该是一个事务的，但能力所限，现在只这样写吧，而且有一个更大的悲剧就是，订单和寄送信息是一个表的，唉！
				ServiceFactory.getOrderServiceInstance().addMyOrder(order);

				// 通过订单号再查询出订单
				order = ServiceFactory.getOrderServiceInstance()
						.findOrderByOrderNum(orderNum);

				// 寄送信息内容
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

				// 保存订单寄送信息
				ServiceFactory.getContactServiceInstance().addContact(contact);

				// 通过订单主键查询订单寄送信息
				contact = ServiceFactory.getContactServiceInstance()
						.findContactByOrderId(orderId);

				// 把寄送信息的主键放入到订单里面
				order.setConId(contact.getConId());

				// 再更新订单内容，主要是因为设置了寄送信息的外键，这就是悲剧啊！！！！
				ServiceFactory.getOrderServiceInstance().modifyMyOrder(order);

				// 现在要做订单详细内容的操作啦

				// 先要从购物车里面取出当前用户所有的商品
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

					// 添加订单详细内容
					ServiceFactory.getOrderInfoServiceInstance().addOrderInfo(
							info);

					// 更新商品的库存和销量
					product.setStock(product.getStock() - cart.getProAmount());
					product.setSales(product.getSales() + cart.getProAmount());
					ServiceFactory.getProductServiceInstance().modifyProduct(
							product);
				}

				// 更新用户的余额和消费量
				UserVo user = new UserVo();
				user = ServiceFactory.getUserServiceInstance().findUserById(
						userId);

				user.setBalance(user.getBalance() - totalPrice);
				user.setPayed(user.getPayed() + totalPrice);

				ServiceFactory.getUserServiceInstance().modifyUser(user);

				// 还要把购物车里面的商品全部清空
				ServiceFactory.getCartServiceInstance().removeByUserId(userId);

				// 跳转页面
				path = "error.jsp";

				// 操作成功
				request.setAttribute("op_error", "货到付款的方式，操作成功啦！");

			}
		} else if ("pay".equals(action)) {
			// 取得用户所输入的密码
			String password = request.getParameter("password");

			// 取得用户主键
			int userId = (Integer) request.getSession().getAttribute("userId");

			// 通过主键取得用户信息
			UserVo user = new UserVo();
			user = ServiceFactory.getUserServiceInstance().findUserById(userId);

			// 判断用户输出的密码是否正确
			if (password.equals(user.getUserPassword())) {
				// 如果一样，则生成订单，并进行一系列的操作

				MyOrderVo order = new MyOrderVo();

				// 从session里面获得各种信息
				userId = (Integer) request.getSession().getAttribute("userId");
				String orderNum = "" + System.currentTimeMillis();
				int payment = Integer.parseInt(request.getSession()
						.getAttribute("payment").toString());
				int post = Integer.parseInt(request.getSession().getAttribute(
						"post").toString());
				float totalPrice = GetTotalPrice.getTotalPrice(userId);

				// 通过邮递方式判断出邮费，再加上商品的总价就是用户要付的费用
				if (post == 0) {
					totalPrice += 10;
				} else {
					totalPrice += 15;
				}
				// 未发货
				int orderState = 0;

				order.setUserId(userId);
				order.setOrderNum(orderNum);
				order.setPayment(payment);
				order.setPost(post);
				order.setTotalPrice(totalPrice);
				order.setOrderState(orderState);

				// 先保存订单，这个本应该是一个事务的，但能力所限，现在只这样写吧，而且有一个更大的悲剧就是，订单和寄送信息是一个表的，唉！
				ServiceFactory.getOrderServiceInstance().addMyOrder(order);

				// 通过订单号再查询出订单
				order = ServiceFactory.getOrderServiceInstance()
						.findOrderByOrderNum(orderNum);

				// 寄送信息内容
				ContactVo contact = new ContactVo();
				int orderId = order.getOrderId();
				String name = request.getSession().getAttribute("name").toString();
				String address = request.getSession().getAttribute("address").toString();
				String postcode = request.getSession().getAttribute("post").toString();
				String telphone = request.getSession().getAttribute("telphone").toString();
				int freetime = Integer.parseInt(request.getSession().getAttribute("freetime").toString());

				contact.setOrderId(orderId);
				contact.setName(name);
				contact.setAddress(address);
				contact.setPostcode(postcode);
				contact.setTelphone(telphone);
				contact.setFreetime(freetime);

				// 保存订单寄送信息
				ServiceFactory.getContactServiceInstance().addContact(contact);

				// 通过订单主键查询订单寄送信息
				contact = ServiceFactory.getContactServiceInstance()
						.findContactByOrderId(orderId);

				// 把寄送信息的主键放入到订单里面
				order.setConId(contact.getConId());

				// 再更新订单内容，主要是因为设置了寄送信息的外键，这就是悲剧啊！！！！
				ServiceFactory.getOrderServiceInstance().modifyMyOrder(order);

				// 现在要做订单详细内容的操作啦

				// 先要从购物车里面取出当前用户所有的商品
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

					// 添加订单详细内容
					ServiceFactory.getOrderInfoServiceInstance().addOrderInfo(
							info);

					// 更新商品的库存和销量
					product.setStock(product.getStock() - cart.getProAmount());
					product.setSales(product.getSales() + cart.getProAmount());
					ServiceFactory.getProductServiceInstance().modifyProduct(
							product);
				}

				// 更新用户的余额和消费量
				user.setBalance(user.getBalance() - totalPrice);
				user.setPayed(user.getPayed() + totalPrice);

				ServiceFactory.getUserServiceInstance().modifyUser(user);

				// 还要把购物车里面的商品全部清空
				ServiceFactory.getCartServiceInstance().removeByUserId(userId);

				// 跳转页面
				path = "error.jsp";

				// 操作成功
				request.setAttribute("op_error", "货到付款的方式，操作成功啦！");

			} else {
				// 如果密码输出错误，跳转到提示页面
				path = "error.jsp";

				// 把错误信息放入到request中
				request.setAttribute("op_error", "密码输入错误!");
			}

		}

		// 根据path进行跳转
		request.getRequestDispatcher(path).forward(request, response);
	}
}
