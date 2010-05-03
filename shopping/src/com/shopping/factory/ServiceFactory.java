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
	 * 取得UserService接口的实例
	 * 
	 * @return UserService 实例
	 */
	public static UserService getUserServiceInstance() {
		return new UserServiceImpl();
	}

	/**
	 * 取得CategoryService接口的实例
	 * 
	 * @return
	 */
	public static CategoryService getCategoryServiceInstance() {
		return new CategoryServiceImpl();
	}

	/**
	 * 取得ItemService接口实例
	 * 
	 * @return
	 */
	public static ItemService getItemServiceInstance() {
		return new ItemServiceImpl();
	}

	/**
	 * 取得ProductService接口实例
	 * 
	 * @return
	 */
	public static ProductService getProductServiceInstance() {
		return new ProductServiceImpl();
	}
}
