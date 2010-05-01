package com.shopping.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.shopping.service.OrderInfoService;
import com.shopping.service.impl.OrderInfoServiceImpl;
import com.shopping.vo.OrderInfoVo;

public class TestOrderInfoServiceImpl {

	private OrderInfoService service = new OrderInfoServiceImpl();
	@Test
	public void testAddOrderInfo() {
		OrderInfoVo orderInfo = new OrderInfoVo();
		orderInfo.setOrderId(1);
		orderInfo.setPrice(10.0f);
		orderInfo.setProId(1);
		orderInfo.setAmount(1);
		
		assertTrue(service.addOrderInfo(orderInfo));
	}

	@Test
	public void testFindAllOrderInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOrderInfoById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOrderInfoByOrderId() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyOrderInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveOrderInfoById() {
		fail("Not yet implemented");
	}

}
