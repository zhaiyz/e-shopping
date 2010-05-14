package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.MyOrderDao;
import com.shopping.dao.impl.MyOrderDaoImpl;
import com.shopping.service.MyOrderService;
import com.shopping.vo.MyOrderVo;

public class MyOrderServiceImpl implements MyOrderService {
	
	private MyOrderDao dao = new MyOrderDaoImpl();

	public boolean addMyOrder(MyOrderVo order) {
		return dao.addMyOrder(order);
	}

	public List<MyOrderVo> findAllMyOrder(int start, int limit) {
		return dao.findAllMyOrder(start, limit);
	}

	public MyOrderVo findMyOrderById(int id) {
		return dao.findMyOrderById(id);
	}

	public boolean modifyMyOrder(MyOrderVo order) {
		return dao.modifyMyOrder(order);
	}

	public boolean removeMyOrderById(int id) {
		return dao.removeMyOrderById(id);
	}

	public MyOrderVo findOrderByOrderNum(String orderNum) {
		return dao.findOrderByOrderNum(orderNum);
	}

	public List<MyOrderVo> findOrderByUserId(int id) {
		return dao.findOrderByUserId(id);
	}

	public int getTotalNum() {
		return dao.getTotalNum();
	}

	public List<MyOrderVo> findOrderByState(int state, int start, int limit) {
		return dao.findOrderByState(state, start, limit);
	}

	public int getTotalNum(int state) {
		return dao.getTotalNum(state);
	}

	public List<MyOrderVo> findByTime(String start, String end) {
		return dao.findByTime(start, end);
	}

	public List<MyOrderVo> findAllOrder() {
		return dao.findAllOrder();
	}

}
