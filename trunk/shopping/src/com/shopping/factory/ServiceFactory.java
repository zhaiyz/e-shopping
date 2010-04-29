package com.shopping.factory;

import com.shopping.service.UserService;
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
}
