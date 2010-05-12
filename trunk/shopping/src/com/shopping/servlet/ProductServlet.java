package com.shopping.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.shopping.factory.ServiceFactory;
import com.shopping.util.JSONUtil;
import com.shopping.util.PageModel;
import com.shopping.vo.CartVo;
import com.shopping.vo.ProductVo;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		String path = "";

		String json = "";

		boolean flag = false;

		if ("show".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));

			ProductVo product = new ProductVo();
			product = ServiceFactory.getProductServiceInstance()
					.findProductById(id);

			request.setAttribute("pro", product);
			path = "user/product.jsp";

			flag = true;
		} else if ("buy".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			int userId = (Integer) request.getSession().getAttribute("userId");

			CartVo cart = new CartVo();
			cart.setUserId(userId);
			cart.setProId(id);
			cart.setProAmount(amount);

			if (ServiceFactory.getCartServiceInstance().addCart(cart)) {

				List<CartVo> list = new ArrayList<CartVo>();
				list = ServiceFactory.getCartServiceInstance()
						.findCartByUserId(userId);
				request.setAttribute("cart", list);
				path = "user/cart.jsp";

				flag = true;
			}
		} else if ("search".equals(action)) {
			String key = "";
			try {
				key = URLDecoder.decode(request.getParameter("keyword"),
						"utf-8");
			} catch (UnsupportedEncodingException e) {
				key = request.getParameter("keyword");
			}

			List<ProductVo> list = new ArrayList<ProductVo>();

			int offset = 0;
			int limit = 5;
			int total = 0;

			try {
				offset = Integer.parseInt(request.getParameter("pager.offset"));
			} catch (NumberFormatException e) {
			}

			list = ServiceFactory.getProductServiceInstance()
					.findProductByLike(key, offset, limit);
			total = ServiceFactory.getProductServiceInstance()
					.getTotalProductByLike(key);

			PageModel pm = new PageModel();
			pm.setTotal(total);
			pm.setDatas(list);

			path = "user/search.jsp";

			request.setAttribute("key", key);
			request.setAttribute("pm", pm);

			flag = true;
		} else if ("all".equals(action)) {

			int start = 0;
			int limit = 10;

			int total = 0;

			List<ProductVo> list = new ArrayList<ProductVo>();

			start = Integer.parseInt(request.getParameter("start"));
			limit = Integer.parseInt(request.getParameter("limit"));

			int itemId = 0;
			try {
				itemId = Integer.parseInt(request.getParameter("itemId"));
			} catch (NumberFormatException e) {
				itemId = 0;
			}

			String key = request.getParameter("query");
			if (key == null && itemId == 0) {
				total = ServiceFactory.getProductServiceInstance()
						.getTotalNum();

				list = ServiceFactory.getProductServiceInstance()
						.findAllProduct(start, limit);
			} else if (key != null && itemId == 0) {
				total = ServiceFactory.getProductServiceInstance()
						.getTotalProductByLike(key);

				list = ServiceFactory.getProductServiceInstance()
						.findProductByLike(key, start, limit);

				request.setAttribute("query", null);
			} else if (key == null && itemId > 0) {
				total = ServiceFactory.getProductServiceInstance()
						.getTotalNumber(itemId);

				list = ServiceFactory.getProductServiceInstance()
						.findAllProduct(itemId, start, limit);
			}

			json += "{total:" + total + ",list:" + JSONUtil.list2json(list)
					+ "}";

			flag = false;
		} else if ("add".equals(action)) {

			String basePath = getServletContext().getRealPath("/") + "images";

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);

			ProductVo product = new ProductVo();

			// 默认设置商品的销售量为0，不推荐
			product.setSales(0);
			product.setRecommendation(0);

			if (isMultipart == true) {
				try {
					FileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);

					List<FileItem> fileItems = upload.parseRequest(request);
					Iterator<FileItem> iter = fileItems.iterator();

					while (iter.hasNext()) {
						FileItem item = (FileItem) iter.next();

						if (item.isFormField()) {
							String name = item.getFieldName();
							String value = item.getString("utf-8");

							if (name.equals("itemId")) {
								// 设置商品小类
								int itemId = ServiceFactory
										.getItemServiceInstance()
										.findItemByName(value).getItemId();

								product.setItemId(itemId);
							} else if ("proName".equals(name)) {
								// 设置商品名称
								product.setProName(value);
							} else if ("proDesc".equals(name)) {
								// 设置商品描述
								product.setProDesc(value);
							} else if ("purPrice".equals(name)) {
								// 设置商品进价
								product.setPurPrice(Float.parseFloat(value));
							} else if ("oriPrice".equals(name)) {
								// 设置商品原价
								product.setOriPrice(Float.parseFloat(value));
							} else if ("disPrice".equals(name)) {
								// 设置会员价
								product.setDisPrice(Float.parseFloat(value));
							} else if ("stock".equals(name)) {
								// 设置库存
								product.setStock(Integer.parseInt(value));
							} else if ("recommendation".equals(name)) {
								// 设置成推荐
								product.setRecommendation(1);
							}

						} else {

							String fileName = item.getName();

							// 取得文件的扩展名
							String ext = "";

							if ((fileName != null) && (fileName.length() > 0)) {
								int i = fileName.lastIndexOf('.');

								if ((i > -1) && (i < (fileName.length() - 1))) {
									ext = fileName.substring(i + 1);
								}
							}

							if (fileName != null) {
								File fullFile = new File(System
										.currentTimeMillis()
										+ "." + ext);

								File fileOnServer = new File(basePath, fullFile
										.getName());

								product.setImageUrl(fullFile.getName());

								item.write(fileOnServer);
							}
						}
					}

					if (ServiceFactory.getProductServiceInstance().addProduct(
							product)) {
						json += "{success:true}";
					} else {
						json += "{success:false}";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			flag = false;
		} else if ("update".equals(action)) {
			// 修改商品信息
			int proId = Integer.parseInt(request.getParameter("proId"));
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			String proName = request.getParameter("proName");
			String proDesc = request.getParameter("proDesc");
			float purPrice = Float.parseFloat(request.getParameter("purPrice"));
			float oriPrice = Float.parseFloat(request.getParameter("oriPrice"));
			float disPrice = Float.parseFloat(request.getParameter("disPrice"));
			int stock = Integer.parseInt(request.getParameter("stock"));

			int recommendation = Boolean.parseBoolean(request
					.getParameter("recommendation")) ? 1 : 0;

			ProductVo product = new ProductVo();

			product = ServiceFactory.getProductServiceInstance()
					.findProductById(proId);

			product.setItemId(itemId);
			product.setProName(proName);
			product.setProDesc(proDesc);
			product.setPurPrice(purPrice);
			product.setOriPrice(oriPrice);
			product.setDisPrice(disPrice);
			product.setStock(stock);
			product.setRecommendation(recommendation);

			if (ServiceFactory.getProductServiceInstance().modifyProduct(
					product)) {
				json += "{success:true}";
			} else {
				json += "{success:false}";
			}

			flag = false;
		} else if ("del".equals(action)) {
			// 删除一个商品
			int proId = Integer.parseInt(request.getParameter("proId"));

			if (ServiceFactory.getProductServiceInstance().removeProductById(
					proId)) {
				json += "{success:true}";
			} else {
				json += "{success:false}";
			}
			
			System.out.println(json);

			// 不进行页面跳转
			flag = false;
		}

		if (flag) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			out.print(json);
		}
	}
}
