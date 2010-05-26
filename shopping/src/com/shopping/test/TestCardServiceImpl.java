package com.shopping.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.shopping.service.CardService;
import com.shopping.service.impl.CardServiceImpl;
import com.shopping.vo.CardVo;

public class TestCardServiceImpl {

	private CardService service = new CardServiceImpl();
	@Test
	public void testAddCard() {
		CardVo card = new CardVo();
		card.setCardNo("6");
		card.setCardPassword("6");
		card.setCardValue(6000000.0f);
		card.setCardFlag(1);
		
		assertTrue(service.addCard(card));
	}

	@Test
	public void testFindAllCard() {
		List<CardVo> list = new ArrayList<CardVo>();
		list = service.findAllCard(1, 2);
		
		assertNotNull(list);
	}

	@Test
	public void testFindCadrById() {
		CardVo card = new CardVo();
		card = service.findCadrById(1);
		assertNotNull(card);
	}

	@Test
	public void testModifyCard() {
		CardVo card = new CardVo();
		
		card.setCardNo("card123");
		card.setCardPassword("123");
		card.setCardValue(10.0f);
		card.setCardFlag(1);
		
		assertTrue(service.modifyCard(card));
	}
	
	@Test
	public void testAccountManage(){
		CardVo card = new CardVo();
		card.setCardNo("card123");
		card.setCardPassword("123");
		
		assertTrue(service.accountManage(card));
	}

	@Test
	public void testFindCardByCardName(){
		CardVo card = new CardVo();
		card = service.findCardByCardName("card123", "123");
		assertNotNull(card);
	}
	@Test
	public void testRemoveCard() {
		assertTrue(service.removeCard(2));
	}

}
