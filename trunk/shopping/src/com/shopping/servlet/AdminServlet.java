package com.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.AdminVo;

public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ͳһ�ַ�������ֹ����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		String path = "";
		String json = "";

		// �����ж��Ƿ������ת
		boolean flag = false;

		// �����������������Ӧ�Ĳ���
		if ("login".equals(action)) {
			// ����Ա��¼����
			String name = request.getParameter("user");
			String password = request.getParameter("pass");
			String checkCode = request.getParameter("checkcode");

			// �ж���֤���Ƿ���ȷ
			String code = (String) request.getSession().getAttribute(
					"checkCode");

			if (code.equals(checkCode)) {
				// ��֤����ȷ
				AdminVo admin = new AdminVo();

				admin.setAdminName(name);
				admin.setAdminPassword(password);

				if (ServiceFactory.getAdminServiceInstance().isLogin(admin)) {
					// ��¼�ɹ�
				//	path = "admin/adminhome.jsp";
					request.getSession().setAttribute("adminName", name);
					json += "{success:true,url:\"/shopping/admin/adminhome.jsp\"}";
					flag = false;
				} else {
					// ��¼ʧ��
					flag = false;
					json += "{success:false,error:\"�û������������\"}";
				}
			} else {
				// ��֤�����
				flag = false;
				json += "{success:false,error:\"��֤�����\"}";
			}
		} else if ("logout".equals(action)) {
			//����Ա�˳�
			request.getSession().removeAttribute("adminName");
			json = "{success:true, url:\"/shopping/forward?page=index\"}";
			flag = false;
		}

		if (flag) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			out.println(json);
		}
	}

}
