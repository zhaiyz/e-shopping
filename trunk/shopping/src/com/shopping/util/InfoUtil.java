package com.shopping.util;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.NewInfoVo;
import com.shopping.vo.OrderInfoVo;
import com.shopping.vo.ProductVo;

/**
 * ����Ʒ����������ƵĹ�����
 * 
 * @author wolf
 * 
 */
public class InfoUtil {
	public static NewInfoVo toNewInfo(OrderInfoVo info) {
		NewInfoVo n = new NewInfoVo();

		ProductVo product = new ProductVo();
		product = ServiceFactory.getProductServiceInstance().findProductById(
				info.getProId());
		
		n.setInfoId(info.getInfoId());
		n.setOrderId(info.getOrderId());
		n.setProName(product.getProName());
		n.setPrice(info.getPrice());
		n.setAmount(info.getAmount());

		return n;
	}
}
