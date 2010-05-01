package com.shopping.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		list = service.findAllOrderInfo(1, 2);
		
		assertNotNull(list);
	}

	@Test
	public void testFindOrderInfoById() {
		OrderInfoVo info = new OrderInfoVo();
		info = service.findOrderInfoById(1);
		assertNotNull(info);
	}

	@Test
	public void testFindOrderInfoByOrderId() {
		OrderInfoVo info = new OrderInfoVo();
		info = service.findOrderInfoById(1);
		assertNotNull(info);		
	}

	@Test
	public void testModifyOrderInfo() {
		OrderInfoVo orderInfo = new OrderInfoVo();
		orderInfo.setOrderId(1);
		orderInfo.setPrice(10.0f);
		orderInfo.setProId(1);
		orderInfo.setAmount(1);
		
		assertTrue(service.modifyOrderInfo(orderInfo));
	}

	@Test
	public void testRemoveOrderInfoById() {
		assertFalse(service.removeOrderInfoById(1));
	}

}
