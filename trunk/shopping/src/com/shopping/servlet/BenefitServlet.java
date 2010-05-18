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
import com.shopping.util.BenefitUtil;
import com.shopping.util.JSONUtil;
import com.shopping.vo.BenefitAllVo;
import com.shopping.vo.BenefitItemVo;
import com.shopping.vo.BenefitVo;
import com.shopping.vo.MyOrderVo;

public class BenefitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ͳһ�ַ�������ֹ����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		String json = "";

		if ("show".equals(action)) {
			List<MyOrderVo> list = new ArrayList<MyOrderVo>();

			int state = 0;

			try {
				state = Integer.parseInt(request.getParameter("state"));
			} catch (NumberFormatException e) {
				state = 0;
			}

			if (state == 0) {
				// ��ѯ�����еĶ���
				list = ServiceFactory.getOrderServiceInstance().findAllOrder();
			} else {
				// ��ʱ���ѯ����
				String start = request.getParameter("start");
				String end = request.getParameter("end");

				list = ServiceFactory.getOrderServiceInstance().findByTime(
						start, end);
			}

			List<BenefitVo> l = new ArrayList<BenefitVo>();
			l = BenefitUtil.getBenefitByCategory(list);

			json += "{list:" + JSONUtil.list2json(l) + "}";
		} else if ("show2".equals(action)) {
			List<MyOrderVo> list = new ArrayList<MyOrderVo>();

			int state = 0;
			int catId = 0;
			// true,��ѯȫ��,false�������ѯ
			boolean flag = true;

			try {
				catId = Integer.parseInt(request.getParameter("catId"));
			} catch (NumberFormatException e) {
				// �����������û�д��������������������Ҳ������ʾ�������е�С��
				flag = false;
			}

			try {
				state = Integer.parseInt(request.getParameter("state"));
			} catch (NumberFormatException e) {
				state = 0;
			}

			if ((request.getParameter("start") != null && !"".equals(request
					.getParameter("start")))
					|| state == 1) {
				// ��ʱ���ѯ����
				String start = request.getParameter("start");
				String end = request.getParameter("end");
				list = ServiceFactory.getOrderServiceInstance().findByTime(
						start, end);
			} else {
				// ��ѯ�����еĶ���
				list = ServiceFactory.getOrderServiceInstance().findAllOrder();
			}

			List<BenefitItemVo> l = new ArrayList<BenefitItemVo>();

			if (flag) {
				l = BenefitUtil.getBenefitByItem(catId, list);
			} else {
				l = BenefitUtil.getBenefitByItem(list);
			}

			json += "{list:" + JSONUtil.list2json(l) + "}";
		} else if ("show3".equals(action)) {
			List<MyOrderVo> list = new ArrayList<MyOrderVo>();

			int state = 0;

			try {
				state = Integer.parseInt(request.getParameter("state"));
			} catch (NumberFormatException e) {
				state = 0;
			}

			if (state == 0) {
				// ��ѯ�����еĶ���
				list = ServiceFactory.getOrderServiceInstance().findAllOrder();
			} else {
				// ��ʱ���ѯ����
				String start = request.getParameter("start");
				String end = request.getParameter("end");

				list = ServiceFactory.getOrderServiceInstance().findByTime(
						start, end);
			}

			List<BenefitAllVo> l = new ArrayList<BenefitAllVo>();
			l = BenefitUtil.getBenefitAll(list);

			json += "{list:" + JSONUtil.list2json(l) + "}";
		}

		out.println(json);
	}

}
