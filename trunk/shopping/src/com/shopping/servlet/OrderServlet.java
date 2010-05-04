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
		// 取得命令参数
		String action = request.getParameter("action");

		// 定义跳转路径
		String path = "";

		// 根据命令参数进行相应的操作
		if ("add".equals(action)) {
			// 首先取得付款方式
			int payment = Integer.parseInt(request.getParameter("payment"));

			// 根据付款方式进行一些判断
			if (payment == 0) {
				// 如果是先付款，后发货，那么要先判断用户的余额是否足够

				// 取得用户选择的邮递方式
				int post = Integer.parseInt(request.getParameter("post"));

				// 从session里面取得当前用户主键
				int userId = (Integer) request.getSession().getAttribute(
						"userId");

				// 计算出用户所有商品的总价
				float total = GetTotalPrice.getTotalPrice(userId);

				// 通过邮递方式判断出邮费，再加上商品的总价就是用户要付的费用
				if (post == 0) {
					total += 10;
				} else {
					total += 15;
				}

				// 通过userId取得用户信息
				UserVo user = new UserVo();
				user = ServiceFactory.getUserServiceInstance().findUserById(
						userId);

				// 现在要判断用户的余额是否足够支付所有的费用，如果足够就跳转到付费页，如果不足就跳转到一个提示页
				if (user.getBalance() > total) {
					// 有足够的余额
					path = "user/pay.jsp";

					// 把上个表单所填写的所有信息放到session里面
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
					// 把总费用也放在session里面
					request.getSession().setAttribute("total", total);
				} else {
					//如果余额不足，就跳转到一个提示页面
					path = "error.jsp";
					
					//把错误信息放入request中
					request.setAttribute("op_error", "余额不足!");
				}
			}
		} else if ("pay".equals(action)) {
			//取得用户所输入的密码
			String password = request.getParameter("password");
			
			//取得用户主键
			int userId = (Integer) request.getSession().getAttribute("userId");
			
			//通过主键取得用户信息
			UserVo user = new UserVo();
			user = ServiceFactory.getUserServiceInstance().findUserById(userId);
			
			//判断用户输出的密码是否正确
			if (password.equals(user.getUserPassword())) {
				//如果一样，则生成订单，并进行一系列的操作
				
			} else {
				//如果密码输出错误，跳转到提示页面
				path = "error.jsp";
				
				//把错误信息放入到request中
				request.setAttribute("op_error", "密码输入错误!");
			}
			
		}

		// 根据path进行跳转
		request.getRequestDispatcher(path).forward(request, response);
	}

}
