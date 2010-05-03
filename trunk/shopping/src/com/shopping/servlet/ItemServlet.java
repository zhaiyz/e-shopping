package com.shopping.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.util.PageModel;
import com.shopping.vo.ProductVo;

public class ItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 首先要取得命令参数
		String action = request.getParameter("action");

		// 定义跳转页面
		String path = "";

		// 根据命令进行相应的操作
		if ("list".equals(action)) {
			//取得商品小类id
			int id = Integer.parseInt(request.getParameter("id"));
			//再把小类id放在request内
			request.setAttribute("id", id);
			
			List<ProductVo> list = new ArrayList<ProductVo>();

			int offset = 0;
			int limit = 5;
			int total = 0;

			try {
				offset = Integer.parseInt(request.getParameter("pager.offset"));
			} catch (NumberFormatException e) {
			//	e.printStackTrace();
			}

			list = ServiceFactory.getProductServiceInstance().findAllProduct(id, offset, limit);
			total = ServiceFactory.getProductServiceInstance().getTotalNumber(id);
			
			PageModel pm = new PageModel();
			pm.setTotal(total);
			pm.setDatas(list);
			
			path = "user/item.jsp";
			
			request.setAttribute("pm", pm);
		}
		
		//根据path进行跳转
		request.getRequestDispatcher(path).forward(request, response);
	}

}
