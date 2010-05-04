package com.shopping.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.util.GetTotalPrice;
import com.shopping.vo.UserVo;
import com.shopping.factory.ServiceFactory;

public class OrderServlet extends HttpServlet {

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
					//������㣬����ת��һ����ʾҳ��
					path = "error.jsp";
					
					//�Ѵ�����Ϣ����request��
					request.setAttribute("op_error", "����!");
				}
			}
		} else if ("pay".equals(action)) {
			//ȡ���û������������
			String password = request.getParameter("password");
			
			//ȡ���û�����
			int userId = (Integer) request.getSession().getAttribute("userId");
			
			//ͨ������ȡ���û���Ϣ
			UserVo user = new UserVo();
			user = ServiceFactory.getUserServiceInstance().findUserById(userId);
			
			//�ж��û�����������Ƿ���ȷ
			if (password.equals(user.getUserPassword())) {
				//���һ���������ɶ�����������һϵ�еĲ���
				
			} else {
				//����������������ת����ʾҳ��
				path = "error.jsp";
				
				//�Ѵ�����Ϣ���뵽request��
				request.setAttribute("op_error", "�����������!");
			}
			
		}

		// ����path������ת
		request.getRequestDispatcher(path).forward(request, response);
	}

}
