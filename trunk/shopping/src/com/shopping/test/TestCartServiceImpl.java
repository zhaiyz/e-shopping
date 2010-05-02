package com.shopping.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.shopping.service.CartService;
import com.shopping.service.impl.CartServiceImpl;
import com.shopping.vo.CartVo;

public class TestCartServiceImpl {

	private CartService service = new CartServiceImpl();
	@Test
	public void testAddCart() {
		CartVo cart = new CartVo();
		cart.setUserId(1);
		cart.setProId(1);
		cart.setProAmount(1);
		
		assertTrue(service.addCart(cart));
	}

	@Test
	public void testFindAllCart() {
		List<CartVo> list = new ArrayList<CartVo>();
		list = service.findAllCart(1, 2);
		assertNotNull(list);
	}

	@Test
	public void testFindCartById() {
		CartVo cart = new CartVo();
		cart = service.findCartById(1);
		assertNotNull(cart);
	}

	@Test
	public void testFindCartByUserId() {
		List<CartVo> list = new ArrayList<CartVo>();
		list = service.findCartByUserId(1);
		assertNotNull(list);
		
	}

	@Test
	public void testModifyCart() {
		CartVo cart = new CartVo();
		cart.setUserId(1);
		cart.setProId(1);
		cart.setProAmount(1);
		
		assertTrue(service.modifyCart(cart));
	}

	@Test
	public void testRemoveByUserId() {
		assertTrue(service.removeByUserId(1));
	}

	@Test
	public void testRemoveCartById() {
		assertTrue(service.removeCartById(1));
	}

}
