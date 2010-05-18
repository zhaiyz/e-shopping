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
import com.shopping.vo.CardAnalysisVo;
import com.shopping.vo.CardVo;
import com.shopping.vo.UserVo;

public class CardServelt extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ͳһ�ַ�������ֹ����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		String path = "";
		String error = "";

		String json = "";

		boolean flag = true;

		if ("show".equals(action)) {
			int id = (Integer) request.getSession().getAttribute("userId");
			UserVo user = ServiceFactory.getUserServiceInstance().findUserById(
					id);

			String cardNo = request.getParameter("cardNo");
			String cardPassword = request.getParameter("cardPassword");

			CardVo card = new CardVo();
			card.setCardNo(cardNo);
			card.setCardPassword(cardPassword);
			if (ServiceFactory.getCardServiceInstance().accountManage(card)) {
				CardVo newCard = ServiceFactory.getCardServiceInstance()
						.findCardByCardName(cardNo, cardPassword);
				user.setBalance(user.getBalance() + newCard.getCardValue());
				ServiceFactory.getUserServiceInstance().modifyUser(user);
				newCard.setCardFlag(1);
				ServiceFactory.getCardServiceInstance().modifyCard(newCard);
				path = "error.jsp";
				request.setAttribute("op_error", "��ϲ������ֵ�ɹ�!");
			} else {
				error = "��ֵʧ��";
				path = "error.jsp";
				request.setAttribute("op_error", "������˼������ֵʧ����!");
			}
		} else if ("list".equals(action)) {
			// ��ѯ��ȫ���Ŀ�
			int start = 0;
			int limit = 10;

			// ��stateΪ-1ʱ����ѯ��ȫ����
			int state = -1;

			int total = 0;

			try {
				state = Integer.parseInt(request.getParameter("state"));
			} catch (NumberFormatException e) {
				state = -1;
			}

			start = Integer.parseInt(request.getParameter("start"));
			limit = Integer.parseInt(request.getParameter("limit"));

			List<CardVo> list = new ArrayList<CardVo>();

			if (state == -1) {
				total = ServiceFactory.getCardServiceInstance().getTotalNum();

				list = ServiceFactory.getCardServiceInstance().findAllCard(
						start, limit);
			} else {
				// ��״̬���в�ѯ
				total = ServiceFactory.getCardServiceInstance().getTotalNum(
						state);

				list = ServiceFactory.getCardServiceInstance().findAllCard(
						state, start, limit);
			}

			json += "{total:" + total + ",list:" + JSONUtil.list2json(list)
					+ "}";

			flag = false;
		} else if ("add".equals(action)) {
			// ��ӳ�ֵ��
			String cardNo = request.getParameter("cardNo");
			String cardPassword = request.getParameter("cardPassword");
			float cardValue = Float.parseFloat(request
					.getParameter("cardValue"));

			CardVo card = new CardVo();

			card.setCardNo(cardNo);
			card.setCardPassword(cardPassword);
			card.setCardValue(cardValue);
			card.setCardFlag(0);

			if (ServiceFactory.getCardServiceInstance().addCard(card)) {
				json += "{success:true}";
			} else {
				json += "{success:false}";
			}

			flag = false;
		} else if ("analysis".equals(action)) {
			// ��ֵ��ͳ��

			// δʹ�õ�
			int state0 = 0;
			// ��ʹ��
			int state1 = 1;

			CardAnalysisVo ca0 = new CardAnalysisVo();
			CardAnalysisVo ca1 = new CardAnalysisVo();

			// δʹ�õ�
			ca0 = ServiceFactory.getCardServiceInstance().CardAnalysis(state0);

			// ��ʹ�õ�
			ca1 = ServiceFactory.getCardServiceInstance().CardAnalysis(state1);

			List<CardAnalysisVo> list = new ArrayList<CardAnalysisVo>();

			list.add(ca0);
			list.add(ca1);

			// ���ص��ַ���
			json += "{total:" + 2 + ",list:" + JSONUtil.list2json(list) + "}";

			flag = false;
		}

		if (flag) {
			request.setAttribute("error", error);
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			out.println(json);
		}
	}
}
