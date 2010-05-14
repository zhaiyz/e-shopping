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
import com.shopping.vo.MyOrderVo;
import com.shopping.vo.OrderInfoVo;
import com.shopping.vo.ProductVo;

public class BenefitServlet extends HttpServlet {

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

		String json = "";

		if ("show".equals(action)) {
			float sales = 0.0f;
			float benefit = 0.0f;

			List<MyOrderVo> list = new ArrayList<MyOrderVo>();

			int state = 0;

			try {
				state = Integer.parseInt(request.getParameter("state"));
			} catch (NumberFormatException e) {
				state = 0;
			}

			if (state == 0) {
				// 查询出所有的订单
				list = ServiceFactory.getOrderServiceInstance().findAllOrder();
			} else {
				// 按时间查询订单
				String start = request.getParameter("start");
				String end = request.getParameter("end");

				list = ServiceFactory.getOrderServiceInstance().findByTime(
						start, end);
			}

			Iterator<MyOrderVo> iter = list.iterator();
			while (iter.hasNext()) {
				MyOrderVo order = new MyOrderVo();
				order = iter.next();

				// 根据订单主键查询出订单明细
				List<OrderInfoVo> l = new ArrayList<OrderInfoVo>();

				l = ServiceFactory.getOrderInfoServiceInstance()
						.findOrderInfoByOrderId(order.getOrderId());

				Iterator<OrderInfoVo> iterator = l.iterator();
				while (iterator.hasNext()) {
					OrderInfoVo info = new OrderInfoVo();
					info = iterator.next();

					// 按商品主键查询出相应的商品
					ProductVo product = new ProductVo();

					product = ServiceFactory.getProductServiceInstance()
							.findProductById(info.getProId());

					// 总收入
					sales += info.getPrice();

					benefit += (product.getDisPrice() - product.getPurPrice())
							* info.getAmount();
				}
			}

			json += "{list:[{sales:" + sales + ",benefit:" + benefit + "}]}";
		}

		out.println(json);
	}

}
