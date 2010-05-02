package com.shopping.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.vo.UserVo;
import com.shopping.factory.ServiceFactory;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���������������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// ȡ�ò�����
		String action = request.getParameter("action");

		String path = "";
		String error = "";
		// ���ݲ����������Ӧ�Ĳ���
		if ("login".equals(action)) {
			String userName = request.getParameter("username");
			String userPassword = request.getParameter("password");
			String checkCode1 = request.getParameter("checkCode");
			String checkCode2 = (String) request.getSession().getAttribute(
					"checkCode");

			if (checkCode1.equals(checkCode2)) {
				UserVo user = new UserVo();
				user.setUserName(userName);
				user.setUserPassword(userPassword);

				if (ServiceFactory.getUserServiceInstance().isLogin(user)) {
					// ��¼�ɹ�����userName����session
					request.getSession().setAttribute("userName", userName);
					path = "index.jsp";
				} else {
					// ��¼ʧ��
					path = "user/login.jsp";
					error = "�û������������";
				}
			} else {
				// ��֤�����
				path = "user/login.jsp";
				error = "��֤�����";
			}
		} else if ("register".equals(action)) {
			UserVo user = new UserVo();

			String userName = request.getParameter("username");
			String password = request.getParameter("password");

			user.setUserName(userName);
			user.setUserPassword(password);

			if (!"none".equals(request.getParameter("prompt"))) {
				String prompt = request.getParameter("prompt");
				String answer = request.getParameter("answer");

				user.setPrompt(prompt);
				user.setAnswer(answer);
			}

			if (request.getParameter("email") != null) {
				String email = request.getParameter("eamil");

				user.setEmail(email);
			}
			if (request.getParameter("phone") != null) {
				String phone = request.getParameter("phone");

				user.setPhone(phone);
			}
			
			int gender = Integer.parseInt(request.getParameter("gender"));
			
			user.setGender(gender);
			
			user.setGrade(0);
			user.setBalance(0.0f);
			user.setPayed(0.0f);
			user.setUserState(0);
			
			if (ServiceFactory.getUserServiceInstance().addUser(user)) {
				request.getSession().setAttribute("userName", userName);
				path="index.jsp";
			} else {
				error = "ע��ʧ��!";
				path="/user/register.jsp";
			}
		}

		// ��error���뵽request��
		request.setAttribute("error", error);

		// ����path������ת
		request.getRequestDispatcher(path).forward(request, response);
	}

}
