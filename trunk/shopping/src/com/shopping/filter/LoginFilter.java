package com.shopping.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// Session����HTTP���룬����ServletRequest������Ҫ��ת����HttpServletRequest����
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		// ȡ����ת�������
		String action = request.getParameter("action");

		// ���actionΪbuy��ô���ж��Ƿ��¼��û�е�¼��û�й���Ȩ��
		if ("buy".equals(action)) {
			//�����buy
			if (session.getAttribute("userName") != null) {
				chain.doFilter(request, response);
			} else {
				// ͨ��requestDispatcher��ת����½ҳ
				request.setAttribute("error", "ֻ�е�¼�˲��ܹ���");
				request.getRequestDispatcher("/user/login.jsp").forward(
						request, response);
			}
		} else {
			//�������buy����ô�Ϳ�������ȥ
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
