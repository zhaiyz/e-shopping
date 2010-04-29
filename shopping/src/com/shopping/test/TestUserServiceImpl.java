package com.shopping.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.shopping.service.UserService;
import com.shopping.service.impl.UserServiceImpl;
import com.shopping.vo.UserVo;

public class TestUserServiceImpl extends TestCase {
	private UserService userService = new UserServiceImpl();

	 @Test
	public void testAddUser() {
		UserVo user = new UserVo();
		String userName = "userName";
		String userPassword = "userPassword";
		int gender = 0;
		int grade = 0;
		float balance = 100.0f;
		String prompt = "prompt";
		String answer = "answer";
		String favor = "favor";
		float payed = 100.0f;
		int userState = 0;
		String email = "hanxue_lang@foxmail.com";
		String phone = "13658366487";

		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setGender(gender);
		user.setGrade(grade);
		user.setBalance(balance);
		user.setPrompt(prompt);
		user.setAnswer(answer);
		user.setFavor(favor);
		user.setPayed(payed);
		user.setUserState(userState);
		user.setEmail(email);
		user.setPhone(phone);

		assertTrue(userService.addUser(user));
	}

	@Test
	public void testFindAllUser() {
		List<UserVo> list = new ArrayList<UserVo>();
		int start = 1;
		int limit = 2;
		list = userService.findAllUser(start, limit);
		assertEquals(2, list.size());
	}

	@Test
	public void testFindUserById() {
		UserVo user = new UserVo();
		int id = 2;
		user = userService.findUserById(id);
		assertNotNull(user);
	}

	@Test
	public void testModifyUser() {
		UserVo user = new UserVo();
		int userId = 3;
		String userName = "userName2";
		String userPassword = "userPassword2";
		int gender = 1;
		int grade = 1;
		float balance = 120.0f;
		String prompt = "prompt3";
		String answer = "answer3";
		String favor = "favor3";
		float payed = 300.0f;
		int userState = 3;
		String email = "hanxue_lang@foxmail.com3";
		String phone = "136583664873";
		
		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setGender(gender);
		user.setGrade(grade);
		user.setBalance(balance);
		user.setPrompt(prompt);
		user.setAnswer(answer);
		user.setFavor(favor);
		user.setPayed(payed);
		user.setUserState(userState);
		user.setEmail(email);
		user.setPhone(phone);
		
		assertTrue(userService.modifyUser(user));
	}

	@Test
	public void testRemoveUserById() {
		int id = 2;
		assertFalse(userService.removeUserById(id));
	}
	
	@Test
	public void testIsLogin() {
		UserVo user = new UserVo();
		String userName = "userName2";
		String userPassword = "userPassword";
		
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		
		assertFalse(userService.isLogin(user));
	}
    
	@Test
	public void testFindByName() {
		String name = "userName";
		
		assertTrue(userService.findByName(name));
	}
}
