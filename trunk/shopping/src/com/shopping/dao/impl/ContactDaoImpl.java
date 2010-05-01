package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.ContactDao;
import com.shopping.util.DBUtil;
import com.shopping.vo.ContactVo;

public class ContactDaoImpl implements ContactDao {

	public boolean addContact(ContactVo contact) {
		boolean flag = false;
		String sql = "INSERT INTO contact (order_id, name, postcode, address, telphone, freetime) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, contact.getOrderId());
			pstmt.setString(2, contact.getName());
			pstmt.setString(3, contact.getPostcode());
			pstmt.setString(4, contact.getAddress());
			pstmt.setString(5, contact.getTelphone());
			pstmt.setInt(6, contact.getFreetime());
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

	public List<ContactVo> findAllContact(int start, int limit) {
		List<ContactVo> list = new ArrayList<ContactVo>();
		String sql = "SELECT * FROM contact WHERE 1 = 1 LIMIT " + start + ", "
				+ limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ContactVo contact = new ContactVo();
				contact.setConId(rs.getInt("con_id"));
				contact.setOrderId(rs.getInt("order_id"));
				contact.setName(rs.getString("name"));
				contact.setPostcode(rs.getString("postcode"));
				contact.setAddress(rs.getString("address"));
				contact.setTelphone(rs.getString("telphone"));
				contact.setFreetime(rs.getInt("freetime"));
				list.add(contact);
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

	public ContactVo findContactById(int id) {
		ContactVo contact = new ContactVo();
		String sql = "SELECT * FROM contact WHERE con_id = id";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				contact.setConId(rs.getInt("con_id"));
				contact.setOrderId(rs.getInt("order_id"));
				contact.setName(rs.getString("name"));
				contact.setPostcode(rs.getString("postcode"));
				contact.setAddress(rs.getString("address"));
				contact.setTelphone(rs.getString("telphone"));
				contact.setFreetime(rs.getInt("freetime"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return contact;
	}

	public ContactVo findContactByOrderId(int id) {
		ContactVo contact = new ContactVo();
		String sql = "SELECT * FROM contact WHERE order_id = id";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				contact.setConId(rs.getInt("con_id"));
				contact.setOrderId(rs.getInt("order_id"));
				contact.setName(rs.getString("name"));
				contact.setPostcode(rs.getString("postcode"));
				contact.setAddress(rs.getString("address"));
				contact.setTelphone(rs.getString("telphone"));
				contact.setFreetime(rs.getInt("freetime"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return contact;
	}

	public boolean modifyContact(ContactVo contact) {
		boolean flag = false;
		String sql = "UPDATE contact SET order_id = ?, name = ?, postcode = ?, address= ?,"
				+ "telphone = ?, freetime = ? WHERE con_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, contact.getOrderId());
			pstmt.setString(2, contact.getName());
			pstmt.setString(3, contact.getPostcode());
			pstmt.setString(4, contact.getAddress());
			pstmt.setString(5, contact.getTelphone());
			pstmt.setInt(6, contact.getFreetime());
			pstmt.setInt(7, contact.getConId());
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

	public boolean removeContactById(int id) {
		boolean flag = false;
		String sql = "DELETE FROM contact WHERE con_id = ?";
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
