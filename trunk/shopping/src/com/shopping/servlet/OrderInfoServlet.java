package com.shopping.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.MyOrderVo;
import com.shopping.vo.OrderInfoVo;

public class OrderInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得命令参数
		String action = request.getParameter("action");
		
		//定义转发路径
		String path = "";
		
		if("show".equals(action)) {
			//显示订单明细
			path = "user/orderinfo.jsp";
			
			//取得订单主键
			int id = Integer.parseInt(request.getParameter("id"));
			
			//根据订单主键查询出订单明细
			List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
			list = ServiceFactory.getOrderInfoServiceInstance().findOrderInfoByOrderId(id);
			
			//在前台显示的时候还要显示出订单号，所有在这里要查询出订单信息
			MyOrderVo order = new MyOrderVo();
			order = ServiceFactory.getOrderServiceInstance().findMyOrderById(id);
			
			request.setAttribute("num", order.getOrderNum());
			request.setAttribute("post", order.getPost());
			request.setAttribute("info", list);
		}
		
		// 根据path进行跳转
		request.getRequestDispatcher(path).forward(request, response);
	}

}
