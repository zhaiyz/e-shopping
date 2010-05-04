package com.shopping.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.CartVo;
import com.shopping.vo.ProductVo;

/**
 * ���û���id�������ǰ�û�������Ʒ���ܼ�
 * 
 * @author wolf
 * 
 */
public class GetTotalPrice {
    public static float getTotalPrice (int userId) {
    	float total = 0.0f;
    	//����Ҫͨ��userId��ѯ�����û��Ĺ��ﳵ�����������Ʒ
    	List<CartVo> list = new ArrayList<CartVo>();
    	list = ServiceFactory.getCartServiceInstance().findCartByUserId(userId);
    	
    	Iterator<CartVo> iterator = list.iterator();
    	while (iterator.hasNext()) {
    		CartVo cart = new CartVo();
    		cart = iterator.next();
    		
    		//������Ʒ������ѯ����Ʒ
    		ProductVo product = new ProductVo();
    		product = ServiceFactory.getProductServiceInstance().findProductById(cart.getProId());
    		
    		//�����ܼ�
    		total += product.getDisPrice() * cart.getProAmount();
    	}
    	return total;
    }
}
