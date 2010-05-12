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
		// �����ַ�����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		// ȡ���������
		String action = request.getParameter("action");

		// ����ת��·��
		String path = "";

		String json = "";

		// ����һ�����������ж��Ƿ���תҳ�棬Ĭ�ϲ���ת
		boolean flag = false;

		// �����������������Ӧ�Ĳ���
		if ("show".equals(action)) {
			// ȡ��Ҫ��ʾ��Ʒ��id
			int id = Integer.parseInt(request.getParameter("id"));

			ProductVo product = new ProductVo();
			product = ServiceFactory.getProductServiceInstance()
					.findProductById(id);

			request.setAttribute("pro", product);
			path = "user/product.jsp";

			flag = true;
		} else if ("buy".equals(action)) {
			// ȡ����Ʒid
			int id = Integer.parseInt(request.getParameter("id"));
			// ȡ����Ʒ����
			int amount = Integer.parseInt(request.getParameter("amount"));
			// ȡ���û�����
			int userId = (Integer) request.getSession().getAttribute("userId");

			CartVo cart = new CartVo();
			cart.setUserId(userId);
			cart.setProId(id);
			cart.setProAmount(amount);

			if (ServiceFactory.getCartServiceInstance().addCart(cart)) {

				// ��ѯ����ǰ�û����ﳵ���������
				List<CartVo> list = new ArrayList<CartVo>();
				list = ServiceFactory.getCartServiceInstance()
						.findCartByUserId(userId);
				request.setAttribute("cart", list);
				path = "user/cart.jsp";

				flag = true;
			}
		} else if ("search".equals(action)) {
			// ȡ�ùؼ���
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
				// e.printStackTrace();
			}

			list = ServiceFactory.getProductServiceInstance()
					.findProductByLike(key, offset, limit);
			total = ServiceFactory.getProductServiceInstance()
					.getTotalProductByLike(key);

			PageModel pm = new PageModel();
			pm.setTotal(total);
			pm.setDatas(list);

			path = "user/search.jsp";

			// �ѹؼ���Ҳ����request����
			request.setAttribute("key", key);
			request.setAttribute("pm", pm);

			flag = true;
		} else if ("all".equals(action)) {

			// ��ҳ����
			int start = 0;
			int limit = 10;

			// ��Ʒ����
			int total = 0;

			// ��Ʒ
			List<ProductVo> list = new ArrayList<ProductVo>();

			start = Integer.parseInt(request.getParameter("start"));
			limit = Integer.parseInt(request.getParameter("limit"));

			// ȡ����ƷС������
			int itemId = 0;
			try {
				itemId = Integer.parseInt(request.getParameter("itemId"));
			} catch (NumberFormatException e) {
				itemId = 0;
				// e.printStackTrace();
			}

			// ȡ�ò�ѯ���õĹؼ���
			String key = request.getParameter("query");
			if (key == null && itemId == 0) {
				// û�в�ѯ���������ǲ�ѯ��ȫ��

				// ��Ʒ����
				total = ServiceFactory.getProductServiceInstance()
						.getTotalNum();

				list = ServiceFactory.getProductServiceInstance()
						.findAllProduct(start, limit);
			} else if (key != null && itemId == 0) {
				// �в�ѯ���������ǰ�������ѯ���˴��ǰ���Ʒ���ƽ���ģ����ѯ
				total = ServiceFactory.getProductServiceInstance()
						.getTotalProductByLike(key);

				list = ServiceFactory.getProductServiceInstance()
						.findProductByLike(key, start, limit);

				request.setAttribute("query", null);
			} else if (key == null && itemId > 0) {
				// ����ƷС����в�ѯ
				total = ServiceFactory.getProductServiceInstance()
						.getTotalNumber(itemId);

				list = ServiceFactory.getProductServiceInstance()
						.findAllProduct(itemId, start, limit);
			}

			// ��֯Ҫ���ص�json
			json += "{total:" + total + ",list:" + JSONUtil.list2json(list)
					+ "}";

			// ����ǲ�������ת��
			flag = false;
		} else if ("add".equals(action)) {
			// �����Ʒ��Ϣ��������Ѷ���Ҫ���и�ͼƬ�ϴ������������ܲ��ܸ㶨

			String basePath = getServletContext().getRealPath("/") + "images";

			// �ж��Ƿ����ļ��ϴ�������е����⣬��Ϊ����һ��FormPanel
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);

			// ���ֶ����ó�true������
			isMultipart = true;

			if (isMultipart == true) {
				try {
					FileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);

					// �õ����еı�������Ŀǰ��������FileItem
					List<FileItem> fileItems = upload.parseRequest(request);
					Iterator<FileItem> iter = fileItems.iterator();

					// ���δ���ÿ������
					while (iter.hasNext()) {
						FileItem item = (FileItem) iter.next();

						if (item.isFormField()) {
							// ����������ı���
							String name = item.getFieldName();
							String value = item.getString();

							System.out.println("������Ϊ:" + name + ",��ֵΪ:"
									+ value);
						} else {
							// ����Ǹ��ļ��ϴ���

							// ����ļ�����·��
							String fileName = item.getName();

							if (fileName != null) {
								File fullFile = new File(item.getName());

								File fileOnServer = new File(basePath, fullFile
										.getName());

								item.write(fileOnServer);

								System.out.println("ͼƬ�ϴ��ɹ�");
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// ����ǲ�������ת��
			flag = false;
		}

		// ����path������ת
		if (flag) {
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			out.println(json);
		}
	}

}
