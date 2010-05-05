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
		//����������
		String action = request.getParameter("action");
		
		//����ת��·��
		String path = "";
		
		if("show".equals(action)) {
			//��ʾ������ϸ
			path = "user/orderinfo.jsp";
			
			//ȡ�ö�������
			int id = Integer.parseInt(request.getParameter("id"));
			
			//���ݶ���������ѯ��������ϸ
			List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
			list = ServiceFactory.getOrderInfoServiceInstance().findOrderInfoByOrderId(id);
			
			//��ǰ̨��ʾ��ʱ��Ҫ��ʾ�������ţ�����������Ҫ��ѯ��������Ϣ
			MyOrderVo order = new MyOrderVo();
			order = ServiceFactory.getOrderServiceInstance().findMyOrderById(id);
			
			request.setAttribute("num", order.getOrderNum());
			request.setAttribute("post", order.getPost());
			request.setAttribute("info", list);
		}
		
		// ����path������ת
		request.getRequestDispatcher(path).forward(request, response);
	}

}
