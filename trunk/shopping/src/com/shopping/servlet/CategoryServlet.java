package com.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.util.JSONUtil;
import com.shopping.vo.CategoryVo;

public class CategoryServlet extends HttpServlet {

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

		// 获得操作命令
		String action = request.getParameter("action");

		// 定义要返回的json数据
		String json = "";

		// 商品大类数量
		int total = 0;

		// 根据操作命令进行相应的操作
		if ("list".equals(action)) {
			// 获得分页参数
			int start = 0;
			int limit = 10;

			start = Integer.parseInt(request.getParameter("start"));
			limit = Integer.parseInt(request.getParameter("limit"));

			// 取得查询所用的关键字
			String key = request.getParameter("query");

			List<CategoryVo> list = new ArrayList<CategoryVo>();

			// 如果为空则查询全部
			if (key == null) {
				// 分页查询所有的商品大类
				list = ServiceFactory.getCategoryServiceInstance()
						.findAllCategory(start, limit);

				total = ServiceFactory.getCategoryServiceInstance()
						.getTotalNum();

			} else {
				// 如果不为空，那么按条件进行查询
				list = ServiceFactory.getCategoryServiceInstance()
						.findCategoryByLike(key, start, limit);

				total = ServiceFactory.getCategoryServiceInstance()
						.getTotalNumByLike(key);
			}

			// 返回的字符串
			json += "{total:" + total + ",list:" + JSONUtil.list2json(list)
					+ "}";
		} else if ("all".equals(action)) {
			List<CategoryVo> list = new ArrayList<CategoryVo>();
			list = ServiceFactory.getCategoryServiceInstance()
					.findAllCategory();

			json += "{list:" + JSONUtil.list2json(list) + "}";
		}

		out.println(json);
	}
}
