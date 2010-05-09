package com.shopping.factory;

import com.shopping.service.CardService;
import com.shopping.service.CartService;
import com.shopping.service.CategoryService;
import com.shopping.service.ContactService;
import com.shopping.service.ItemService;
import com.shopping.service.MyOrderService;
import com.shopping.service.OrderInfoService;
import com.shopping.service.ProductService;
import com.shopping.service.UserService;
import com.shopping.service.impl.CardServiceImpl;
import com.shopping.service.impl.CartServiceImpl;
import com.shopping.service.impl.CategoryServiceImpl;
import com.shopping.service.impl.ContactServiceImpl;
import com.shopping.service.impl.ItemServiceImpl;
import com.shopping.service.impl.MyOrderServiceImpl;
import com.shopping.service.impl.OrderInfoServiceImpl;
import com.shopping.service.impl.ProductServiceImpl;
import com.shopping.service.impl.UserServiceImpl;

public class ServiceFactory {

	/**
	 * ȡ��UserService�ӿڵ�ʵ��
	 * 
	 * @return UserService ʵ��
	 */
	public static UserService getUserServiceInstance() {
		return new UserServiceImpl();
	}

	/**
	 * ȡ��CategoryService�ӿڵ�ʵ��
	 * 
	 * @return
	 */
	public static CategoryService getCategoryServiceInstance() {
		return new CategoryServiceImpl();
	}

	/**
	 * ȡ��ItemService�ӿ�ʵ��
	 * 
	 * @return
	 */
	public static ItemService getItemServiceInstance() {
		return new ItemServiceImpl();
	}

	/**
	 * ȡ��ProductService�ӿ�ʵ��
	 * 
	 * @return
	 */
	public static ProductService getProductServiceInstance() {
		return new ProductServiceImpl();
	}

	/**
	 * ȡ��CartService�ӿ�ʵ��
	 * 
	 * @return
	 */
	public static CartService getCartServiceInstance() {
		return new CartServiceImpl();
	}

	/**
	 * ȡ��OrderService�ӿ�ʵ��
	 * 
	 * @return
	 */
	public static MyOrderService getOrderServiceInstance() {
		return new MyOrderServiceImpl();
	}

	/**
	 * ȡ��ContactService�ӿ�ʵ��
	 * 
	 * @return
	 */
	public static ContactService getContactServiceInstance() {
		return new ContactServiceImpl();
	}

	/**
	 * ȡ��OrderInfoService�ӿ�ʵ��
	 * 
	 * @return
	 */
	public static OrderInfoService getOrderInfoServiceInstance() {
		return new OrderInfoServiceImpl();
	}

	/**
	 * ȡ��CardService�ӿ�ʵ��
	 * 
	 * @return
	 */
	public static CardService getCardServiceInstance() {
		return new CardServiceImpl();
	}
}
