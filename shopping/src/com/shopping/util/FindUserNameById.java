package com.shopping.util;

import com.shopping.factory.ServiceFactory;

public class FindUserNameById {
	public String getUserName(int userId) {
		String userName = "";

		userName = ServiceFactory.getUserServiceInstance().findUserById(userId)
				.getUserName();

		return userName;
	}
}
