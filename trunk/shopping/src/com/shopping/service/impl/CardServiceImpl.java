package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.CardDao;
import com.shopping.dao.impl.CardDaoImpl;
import com.shopping.service.CardService;
import com.shopping.vo.CardVo;

public class CardServiceImpl implements CardService{

	private CardDao dao = new CardDaoImpl();
	public boolean addCard(CardVo card) {
		return dao.addCard(card);
	}

	public List<CardVo> findAllCard(int start, int limit) {
		return dao.findAllCard(start, limit);
	}

	public CardVo findCadrById(int cardId) {
		return dao.findCadrById(cardId);
	}

	public boolean modifyCard(CardVo card) {
		return dao.modifyCard(card);
	}

	public boolean removeCard(int cardId) {
		return dao.removeCard(cardId);
	}

	public boolean accountManage(CardVo card) {
		return dao.accountManage(card);
	}

	public CardVo findCardByCardName(String cardNo, String cardPassword) {
		return dao.findCardByCardName(cardNo, cardPassword);
	}

	public int getTotalNum() {
		return dao.getTotalNum();
	}

}
