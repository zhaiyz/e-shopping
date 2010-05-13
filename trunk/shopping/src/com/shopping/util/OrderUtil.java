package com.shopping.util;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.MyOrderVo;
import com.shopping.vo.NewOrderVo;
import com.shopping.vo.UserVo;

/**
 * ������Ŀ����Ҫ������������û����������û���
 * 
 * @author wolf
 * 
 */
public class OrderUtil {
	public static NewOrderVo toNewOrder(MyOrderVo order) {
		NewOrderVo n = new NewOrderVo();

		// �����û�������ѯ���û�
		UserVo user = new UserVo();
		user = ServiceFactory.getUserServiceInstance().findUserById(
				order.getUserId());

		n.setOrderId(order.getOrderId());
		n.setUserName(user.getUserName());
		n.setConId(order.getConId());
		n.setOrderNum(order.getOrderNum());
		n.setOrderDatetime(order.getOrderDatetime());
		n.setPayment(order.getPayment());
		n.setPost(order.getPost());
		n.setTotalPrice(order.getTotalPrice());
		n.setOrderState(order.getOrderState());

		return n;
	}
}
