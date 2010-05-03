package com.shopping.factory;

import com.shopping.service.CategoryService;
import com.shopping.service.ItemService;
import com.shopping.service.ProductService;
import com.shopping.service.UserService;
import com.shopping.service.impl.CategoryServiceImpl;
import com.shopping.service.impl.ItemServiceImpl;
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
}
