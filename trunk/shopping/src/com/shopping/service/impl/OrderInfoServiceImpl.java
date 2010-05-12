package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.OrderInfoDao;
import com.shopping.dao.impl.OrderInfoDaoImpl;
import com.shopping.service.OrderInfoService;
import com.shopping.vo.OrderInfoVo;

public class OrderInfoServiceImpl implements OrderInfoService {
	
	private OrderInfoDao dao = new OrderInfoDaoImpl();

	public boolean addOrderInfo(OrderInfoVo info) {
		return dao.addOrderInfo(info);
	}

	public List<OrderInfoVo> findAllOrderInfo(int start, int limit) {
		return dao.findAllOrderInfo(start, limit);
	}

	public OrderInfoVo findOrderInfoById(int id) {
		return dao.findOrderInfoById(id);
	}

	public List<OrderInfoVo> findOrderInfoByOrderId(int id) {
		return dao.findOrderInfoByOrderId(id);
	}

	public boolean modifyOrderInfo(OrderInfoVo info) {
		return dao.modifyOrderInfo(info);
	}

	public boolean removeOrderInfoById(int id) {
		return dao.removeOrderInfoById(id);
	}

	public boolean findOrderInfoByProId(int proId) {
		return dao.findOrderInfoByProId(proId);
	}

}
