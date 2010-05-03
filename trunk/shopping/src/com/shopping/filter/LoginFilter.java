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

		// Session属于HTTP范畴，所以ServletRequest对象需要先转换成HttpServletRequest对象
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		// 取得跳转命令参数
		String action = request.getParameter("action");

		// 如果action为buy那么就判断是否登录，没有登录就没有购买权限
		if ("buy".equals(action)) {
			//如果是buy
			if (session.getAttribute("userName") != null) {
				chain.doFilter(request, response);
			} else {
				// 通过requestDispatcher跳转到登陆页
				request.setAttribute("error", "只有登录了才能购买");
				request.getRequestDispatcher("/user/login.jsp").forward(
						request, response);
			}
		} else {
			//如果不是buy，那么就可以走下去
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
