package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.AdminDao;
import com.shopping.dao.impl.AdminDaoImpl;
import com.shopping.service.AdminService;
import com.shopping.vo.AdminVo;

public class AdminServiceImpl implements AdminService {
	
	private AdminDao dao = new AdminDaoImpl();

	public boolean addAdmin(AdminVo admin) {
		return dao.addAdmin(admin);
	}

	public AdminVo findAdminById(int id) {
		return dao.findAdminById(id);
	}

	public List<AdminVo> findAllAdmin(int start, int limit) {
		return dao.findAllAdmin(start, limit);
	}

	public boolean isLogin(AdminVo admin) {
		return dao.isLogin(admin);
	}

	public boolean modifyAdmin(AdminVo admin) {
		return dao.modifyAdmin(admin);
	}

	public boolean removeAdminById(int id) {
		return dao.removeAdminById(id);
	}

}
