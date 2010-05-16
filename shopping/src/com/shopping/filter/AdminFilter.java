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

		// Session属于HTTP范畴，所以ServletRequest对象需要先转换成HttpServletRequest对象
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if (session.getAttribute("adminName") == null) {
			// 管理员还没有登录
			request.setAttribute("op_error", "你没有这个权限");
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
