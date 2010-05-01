package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.AdminDao;
import com.shopping.util.DBUtil;
import com.shopping.vo.AdminVo;

public class AdminDaoImpl implements AdminDao {

	public boolean addAdmin(AdminVo admin) {
		boolean flag = false;
		String sql = "INSERT INTO admin (admin_name, admin_password, admin_datetime) "
				+ "VALUES (?, ?, NOW())";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, admin.getAdminName());
			pstmt.setString(2, admin.getAdminPassword());
			if (pstmt.executeUpdate() == 1) {
				flag = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return flag;
	}

	public AdminVo findAdminById(int id) {
		AdminVo admin = new AdminVo();
		String sql = "SELECT * FROM admin WHERE admin_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setAdminName(rs.getString("admin_name"));
				admin.setAdminPassword(rs.getString("admin_password"));
				admin.setAdminDatetime(rs.getDate("admin_datetime"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return admin;
	}

	public List<AdminVo> findAllAdmin(int start, int limit) {
		List<AdminVo> list = new ArrayList<AdminVo>();
		String sql = "SELECT * FROM admin WHERE 1 = 1 LIMIT " + start + ", "
				+ limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AdminVo admin = new AdminVo();
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setAdminName(rs.getString("admin_name"));
				admin.setAdminPassword(rs.getString("admin_password"));
				admin.setAdminDatetime(rs.getDate("admin_datetime"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return list;
	}

	public boolean isLogin(AdminVo admin) {
		boolean flag = false;
		String sql = "SELECT * FROM admin WHERE admin_name = ? AND admin_password = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, admin.getAdminName());
			pstmt.setString(2, admin.getAdminPassword());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				flag = true;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return flag;
	}

	public boolean modifyAdmin(AdminVo admin) {
		boolean flag = false;
		String sql = "UPDATE admin SET admin_name = ?, admin_password = ? "
				+ "WHERE admin_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, admin.getAdminName());
			pstmt.setString(2, admin.getAdminPassword());
			pstmt.setInt(3, admin.getAdminId());
			if (pstmt.executeUpdate() == 1 || pstmt.executeUpdate() == 0) {
				flag = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return flag;
	}

	public boolean removeAdminById(int id) {
		boolean flag = false;
		String sql = "DELETE FROM admin WHERE admin_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			if (pstmt.executeUpdate() == 1) {
				flag = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return flag;
	}

}
