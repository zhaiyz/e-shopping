package com.shopping.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.CartVo;
import com.shopping.vo.ProductVo;

/**
 * 按用户的id计算出当前用户所有商品的总价
 * 
 * @author wolf
 * 
 */
public class GetTotalPrice {
    public static float getTotalPrice (int userId) {
    	float total = 0.0f;
    	//首先要通过userId查询出此用户的购物车里面的所有商品
    	List<CartVo> list = new ArrayList<CartVo>();
    	list = ServiceFactory.getCartServiceInstance().findCartByUserId(userId);
    	
    	Iterator<CartVo> iterator = list.iterator();
    	while (iterator.hasNext()) {
    		CartVo cart = new CartVo();
    		cart = iterator.next();
    		
    		//根据商品主键查询出商品
    		ProductVo product = new ProductVo();
    		product = ServiceFactory.getProductServiceInstance().findProductById(cart.getProId());
    		
    		//计算总价
    		total += product.getDisPrice() * cart.getProAmount();
    	}
    	return total;
    }
}
