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

public class AdminFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// Session����HTTP���룬����ServletRequest������Ҫ��ת����HttpServletRequest����
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if (session.getAttribute("adminName") == null) {
			// ����Ա��û�е�¼
			request.setAttribute("op_error", "��û�����Ȩ��");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		} else {
			chain.doFilter(request, response);
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
