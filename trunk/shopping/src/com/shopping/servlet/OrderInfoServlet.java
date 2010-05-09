package com.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.util.JSONUtil;
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
		// �����ַ�����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		// ����������
		String action = request.getParameter("action");

		// ����ת��·��
		String path = "";

		String json = "";

		// ����һ����������־�Ƿ���ת��Ĭ��Ϊ��ת���������һ��
		boolean flag = true;

		if ("show".equals(action)) {
			// ��ʾ������ϸ
			path = "user/orderinfo.jsp";

			// ȡ�ö�������
			int id = Integer.parseInt(request.getParameter("id"));

			// ���ݶ���������ѯ��������ϸ
			List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
			list = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(id);

			// ��ǰ̨��ʾ��ʱ��Ҫ��ʾ�������ţ�����������Ҫ��ѯ��������Ϣ
			MyOrderVo order = new MyOrderVo();
			order = ServiceFactory.getOrderServiceInstance()
					.findMyOrderById(id);

			request.setAttribute("num", order.getOrderNum());
			request.setAttribute("post", order.getPost());
			request.setAttribute("info", list);
		} else if ("list".equals(action)) {
			// ������������ѯ�����ж�����ϸ

			// ��ȡ�ö�������
			int orderId = 0;

			orderId = Integer.parseInt(request.getParameter("orderId"));

			List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();

			list = ServiceFactory.getOrderInfoServiceInstance()
					.findOrderInfoByOrderId(orderId);

			json += "{success:true,list:" + JSONUtil.list2json(list) + "}";

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
