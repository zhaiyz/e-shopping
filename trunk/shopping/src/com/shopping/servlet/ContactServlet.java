package com.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.ContactVo;
import com.shopping.util.JSONUtil;

public class ContactServlet extends HttpServlet {

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

		// 首先要取得命令参数
		String action = request.getParameter("action");

		// 定义json
		String json = "";

		if ("show".equals(action)) {
			// 按订单主键显示出寄送信息
			// 先取得订单主键
			int orderId = 0;

			orderId = Integer.parseInt(request.getParameter("orderId"));

			ContactVo contact = new ContactVo();

			contact = ServiceFactory.getContactServiceInstance()
					.findContactByOrderId(orderId);

			json += "{success:true,list:" + JSONUtil.object2json(contact) + "}";
		}

		out.println(json);
	}

}
