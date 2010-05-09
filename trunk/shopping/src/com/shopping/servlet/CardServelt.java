package com.shopping.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
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
		
		//Í³Ò»×Ö·û¼¯£¬·ÀÖ¹ÂÒÂë
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		String path = "";
		String error = "";
		
		if("show".equals(action)){
			int id = (Integer)request.getSession().getAttribute("userId");
			UserVo user = ServiceFactory.getUserServiceInstance().findUserById(id);
			
			String cardNo = request.getParameter("cardNo");
			String cardPassword = request.getParameter("cardPassword");
			
			System.out.println("" + id);
			System.out.println(cardNo);
			System.out.println(cardPassword);
			
			CardVo card = new CardVo();
			card.setCardNo(cardNo);
			card.setCardPassword(cardPassword);
			if(ServiceFactory.getCardServiceInstance().accountManage(card)){
				CardVo newCard = ServiceFactory.getCardServiceInstance().findCardByCardName(cardNo, cardPassword);
				user.setBalance(user.getBalance() + newCard.getCardValue());
				ServiceFactory.getUserServiceInstance().modifyUser(user);
				newCard.setCardFlag(1);
				ServiceFactory.getCardServiceInstance().modifyCard(newCard);
				path = "index.jsp";
			}else{
				error = "³äÖµÊ§°Ü";
				path = "/user/account.jsp";
			}
		}
		
		request.setAttribute("error", error);
		request.getRequestDispatcher(path).forward(request, response);
	}

}
