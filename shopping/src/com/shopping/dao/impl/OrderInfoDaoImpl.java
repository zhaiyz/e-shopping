package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.OrderInfoDao;
import com.shopping.util.DBUtil;
import com.shopping.vo.OrderInfoVo;

public class OrderInfoDaoImpl implements OrderInfoDao {

	public boolean addOrderInfo(OrderInfoVo info) {
		boolean flag = false;
		String sql = "INSERT INTO orderinfo (order_id, pro_id, amount, price) VALUES "
				+ "(?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, info.getOrderId());
			pstmt.setInt(2, info.getProId());
			pstmt.setInt(3, info.getAmount());
			pstmt.setFloat(4, info.getPrice());
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

	public List<OrderInfoVo> findAllOrderInfo(int start, int limit) {
		List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		String sql = "SELECT * FROM orderinfo WHERE 1 = 1 LIMIT " + start
				+ ", " + limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderInfoVo info = new OrderInfoVo();
				info.setInfoId(rs.getInt("info_id"));
				info.setOrderId(rs.getInt("order_id"));
				info.setProId(rs.getInt("pro_id"));
				info.setAmount(rs.getInt("amount"));
				info.setPrice(rs.getFloat("price"));
				list.add(info);
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

	public OrderInfoVo findOrderInfoById(int id) {
		OrderInfoVo info = new OrderInfoVo();
		String sql = "SELECT * FROM user WHERE info_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				info.setInfoId(rs.getInt("info_id"));
				info.setOrderId(rs.getInt("order_id"));
				info.setProId(rs.getInt("pro_id"));
				info.setAmount(rs.getInt("amount"));
				info.setPrice(rs.getFloat("price"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return info;
	}

	public List<OrderInfoVo> findOrderInfoByOrderId(int id) {
		List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		String sql = "SELECT * FROM orderinfo WHERE order_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderInfoVo info = new OrderInfoVo();
				info.setInfoId(rs.getInt("info_id"));
				info.setOrderId(rs.getInt("order_id"));
				info.setProId(rs.getInt("pro_id"));
				info.setAmount(rs.getInt("amount"));
				info.setPrice(rs.getFloat("price"));
				list.add(info);
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

	public boolean modifyOrderInfo(OrderInfoVo info) {
		boolean flag = false;
		String sql = "UPDATE orderinfo SET (order_id = ?, pro_id = ?, amount = ?, price = ?) "
				+ "WHERE info_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, info.getOrderId());
			pstmt.setInt(2, info.getProId());
			pstmt.setInt(3, info.getAmount());
			pstmt.setFloat(4, info.getPrice());
			pstmt.setInt(5, info.getInfoId());
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

	public boolean removeOrderInfoById(int id) {
		boolean flag = false;
		String sql = "DELETE FROM orderinfo WHERE info_id = ?";
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
