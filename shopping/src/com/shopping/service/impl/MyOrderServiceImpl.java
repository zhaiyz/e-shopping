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

}