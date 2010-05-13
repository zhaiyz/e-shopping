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
import com.shopping.util.InfoUtil;
import com.shopping.util.JSONUtil;
import com.shopping.vo.MyOrderVo;
import com.shopping.vo.NewInfoVo;
import com.shopping.vo.OrderInfoVo;

public class OrderInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		// 获得命令参数
		String action = request.getParameter("action");

		// 定义转发路径
		String path = "";

		String json = "";

		// 定义一个变量，标志是否跳转，默认为跳转，这样会好一点
		boolean flag = true;

		if ("show".equals(action)) {
			// 显示订单明细
			path = "user/orderinfo.jsp";

			// 取得订单主键
			int id = Integer.parseInt(request.getParameter("id"));

			// 根据订单主键查询出订单明细
			List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
			list = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(id);

			// 在前台显示的时候还要显示出订单号，所有在这里要查询出订单信息
			MyOrderVo order = new MyOrderVo();
			order = ServiceFactory.getOrderServiceInstance()
					.findMyOrderById(id);

			request.setAttribute("num", order.getOrderNum());
			request.setAttribute("post", order.getPost());
			request.setAttribute("info", list);
		} else if ("list".equals(action)) {
			// 按订单主键查询出所有订单明细

			// 先取得订单主键
			int orderId = 0;

			orderId = Integer.parseInt(request.getParameter("orderId"));

			List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
			
			List<NewInfoVo> l = new ArrayList<NewInfoVo>();

			list = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(orderId);
			
			Iterator<OrderInfoVo> iter = list.iterator();
			while(iter.hasNext()) {
				OrderInfoVo info = new OrderInfoVo();
				NewInfoVo n = new NewInfoVo();
				
				info = iter.next();
				
				n = InfoUtil.toNewInfo(info);
				
				l.add(n);
			}

			json += "{success:true,list:" + JSONUtil.list2json(l) + "}";

			flag = false;
		}

		// 根据path进行跳转
		if (flag) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			out.println(json);
		}
	}

}
