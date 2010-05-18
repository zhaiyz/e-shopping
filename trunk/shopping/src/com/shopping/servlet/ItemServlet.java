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
import com.shopping.util.PageModel;
import com.shopping.vo.ItemVo;
import com.shopping.vo.ProductVo;

public class ItemServlet extends HttpServlet {

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

		// 首先要取得命令参数
		String action = request.getParameter("action");

		// 定义跳转页面
		String path = "";

		// 定义json
		String json = "";

		// 定义一个标志，看是否要进行页面跳转,默认不跳转
		boolean flag = false;

		// 根据命令进行相应的操作
		if ("list".equals(action)) {
			// 取得商品小类id
			int id = Integer.parseInt(request.getParameter("id"));
			// 再把小类id放在request内
			request.setAttribute("id", id);

			List<ProductVo> list = new ArrayList<ProductVo>();

			int offset = 0;
			int limit = 5;
			int total = 0;

			try {
				offset = Integer.parseInt(request.getParameter("pager.offset"));
			} catch (NumberFormatException e) {
				// e.printStackTrace();
			}

			list = ServiceFactory.getProductServiceInstance().findAllProduct(
					id, 0, offset, limit);
			total = ServiceFactory.getProductServiceInstance().getTotalNum(
					id, 0);

			PageModel pm = new PageModel();
			pm.setTotal(total);
			pm.setDatas(list);

			path = "user/item.jsp";

			request.setAttribute("pm", pm);

			// 页面要进行跳转
			flag = true;
		} else if ("all".equals(action)) {
			// 些方法要在管理员后台传输数据

			// 获得分页参数
			int start = 0;
			int limit = 10;

			// 小类总数
			int total = 0;

			// 小类
			List<ItemVo> list = new ArrayList<ItemVo>();
			try {
				start = Integer.parseInt(request.getParameter("start"));
			} catch (NumberFormatException e) {
				start = 0;
			}

			try {
				limit = Integer.parseInt(request.getParameter("limit"));
			} catch (NumberFormatException e) {
				limit = 10;
			}

			// 取得商品大类主键
			int catId = 0;
			try {
				catId = Integer.parseInt(request.getParameter("catId"));
			} catch (NumberFormatException e) {
				catId = 0;
				// e.printStackTrace();
			}

			// 取得查询所用的关键字
			String key = request.getParameter("query");
			if (key == null && catId == 0) {
				// 没有查询条件，就是查询出全部

				// 小类总数
				total = ServiceFactory.getItemServiceInstance().getTotalNum();

				list = ServiceFactory.getItemServiceInstance().findAllItem(
						start, limit);
			} else if (key != null && catId == 0) {
				// 有查询条件，就是按条件查询，此处是按小类名称进行模糊查询
				total = ServiceFactory.getItemServiceInstance()
						.getTotalNum(key);

				list = ServiceFactory.getItemServiceInstance().findItemByLike(
						key, start, limit);

				request.setAttribute("query", null);
			} else if (key == null && catId > 0) {
				// 按商品大类进行查询
				total = ServiceFactory.getItemServiceInstance().getTotalNum(
						catId);

				list = ServiceFactory.getItemServiceInstance().findAllItem(
						catId, start, limit);
			}

			// 组织要返回的json
			json += "{total:" + total + ",list:" + JSONUtil.list2json(list)
					+ "}";

			// 这个是不进行跳转的
			flag = false;
		} else if ("display".equals(action)) {
			int total = 0;

			List<ItemVo> list = new ArrayList<ItemVo>();

			total = ServiceFactory.getItemServiceInstance().getTotalNum();

			list = ServiceFactory.getItemServiceInstance().findAllItem();

			// 组织要返回的json
			json += "{total:" + total + ",list:" + JSONUtil.list2json(list)
					+ "}";

			// 这个是不进行跳转的
			flag = false;
		} else if ("add".equals(action)) {
			int catId = Integer.parseInt(request.getParameter("catId"));
			String itemName = request.getParameter("itemName");
			String itemDesc = request.getParameter("itemDesc");

			ItemVo item = new ItemVo();
			item.setCatId(catId);
			item.setItemName(itemName);
			item.setItemDesc(itemDesc);

			if (ServiceFactory.getItemServiceInstance().addItem(item)) {
				json += "{success:true}";
			} else {
				json += "{success:false}";
			}

			// 这个也是不进行跳转的
			flag = false;
		} else if ("update".equals(action)) {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			int catId = Integer.parseInt(request.getParameter("catId"));
			String itemName = request.getParameter("itemName");
			String itemDesc = request.getParameter("itemDesc");

			ItemVo item = new ItemVo();
			item.setItemId(itemId);
			item.setCatId(catId);
			item.setItemName(itemName);
			item.setItemDesc(itemDesc);

			if (ServiceFactory.getItemServiceInstance().modifyItem(item)) {
				json += "{success:true}";
			} else {
				json += "{success:false}";
			}

			// 这个也是不进行跳转的
			flag = false;
		} else if ("del".equals(action)) {
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			if (ServiceFactory.getItemServiceInstance().removeItemById(itemId)) {
				json += "{success:true}";
			} else {
				json += "{success:false}";
			}

			// 这个也是不进行跳转的
			flag = false;
		}

		// 根据path进行跳转
		if (flag) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			// 页面不跳转就输出前台json
			out.println(json);
		}
	}

}
