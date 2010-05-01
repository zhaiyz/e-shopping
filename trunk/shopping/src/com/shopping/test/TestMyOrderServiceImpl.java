package com.shopping.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.shopping.service.MyOrderService;
import com.shopping.service.impl.MyOrderServiceImpl;
import com.shopping.vo.MyOrderVo;

public class TestMyOrderServiceImpl extends TestCase {

	private MyOrderService service = new MyOrderServiceImpl();

	@Test
	public void testAddMyOrder() {
		MyOrderVo order = new MyOrderVo();
		order.setUserId(1);
		order.setOrderNum("abc");
		order.setPayment(1);
		order.setPost(1);
		order.setTotalPrice(10.0f);
		order.setOrderState(1);

		assertTrue(service.addMyOrder(order));
	}

	@Test
	public void testFindAllMyOrder() {
		List<MyOrderVo> list = new ArrayList<MyOrderVo>();
		int start = 1;
		int limit = 2;
		list = service.findAllMyOrder(start, limit);
		assertEquals(2, list.size());
	}

	@Test
	public void testFindMyOrderById() {
		MyOrderVo order = new MyOrderVo();
		int order_id = 1;
		order = service.findMyOrderById(order_id);
		assertNotNull(order);
	}

	@Test
	public void testModifyMyOrder() {
		MyOrderVo order = new MyOrderVo();
		order.setOrderId(1);
		order.setUserId(1);
		order.setOrderNum("abc");
		order.setPayment(1);
		order.setPost(1);
		order.setTotalPrice(10.0f);
		order.setOrderState(1);

		assertTrue(service.modifyMyOrder(order));
	}

	@Test
	public void testRemoveMyOrderById() {
		assertFalse(service.removeMyOrderById(1));
	}

}
