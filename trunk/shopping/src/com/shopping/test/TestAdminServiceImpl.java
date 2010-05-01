package com.shopping.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.shopping.service.AdminService;
import com.shopping.service.impl.AdminServiceImpl;
import com.shopping.vo.AdminVo;

public class TestAdminServiceImpl {

	private AdminService service = new AdminServiceImpl();
	@Test
	public void testAddAdmin() {
		AdminVo admin = new AdminVo();
		admin.setAdminName("aaa");
		admin.setAdminPassword("123");
		
		assertTrue(service.addAdmin(admin));
	}

	@Test
	public void testFindAdminById() {
		AdminVo admin = new AdminVo();
		admin = service.findAdminById(1);
		assertNotNull(admin);
	}

	@Test
	public void testFindAllAdmin() {
		List<AdminVo> list = new ArrayList<AdminVo>();
		list = service.findAllAdmin(1, 2);
		assertNotNull(list);
	}

	@Test
	public void testIsLogin() {
		AdminVo admin = new AdminVo();
		admin.setAdminName("admin");
		admin.setAdminPassword("123");
		assertFalse(service.isLogin(admin));
	}

	@Test
	public void testModifyAdmin() {
		AdminVo admin = new AdminVo();
		admin.setAdminName("adm");
		admin.setAdminPassword("456");
		assertTrue(service.modifyAdmin(admin));
	}

	@Test
	public void testRemoveAdminById() {
		assertTrue(service.removeAdminById(1));
	}

}
