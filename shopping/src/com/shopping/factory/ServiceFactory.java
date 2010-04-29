package com.shopping.factory;

import com.shopping.service.UserService;
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
}
