package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.MyOrderDao;
import com.shopping.util.DBUtil;
import com.shopping.vo.MyOrderVo;

public class MyOrderDaoImpl implements MyOrderDao {

	public boolean addMyOrder(MyOrderVo order) {
		boolean flag = false;
		String sql = "INSERT INTO myorder (user_id, con_id, order_num, order_datetime, payment,"
				+ "post, total_price, order_state) VALUES (?, NULL, ?, NOW(), ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, order.getUserId());
			// pstmt.setInt(2, order.getConId());
			pstmt.setString(2, order.getOrderNum());
			pstmt.setInt(3, order.getPayment());
			pstmt.setInt(4, order.getPost());
			pstmt.setFloat(5, order.getTotalPrice());
			pstmt.setInt(6, order.getOrderState());
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

	public List<MyOrderVo> findAllMyOrder(int start, int limit) {
		List<MyOrderVo> list = new ArrayList<MyOrderVo>();
		String sql = "SELECT * FROM myorder WHERE 1 = 1 LIMIT " + start + ", "
				+ limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MyOrderVo order = new MyOrderVo();
				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setConId(rs.getInt("con_id"));
				order.setOrderNum(rs.getString("order_num"));
				order.setOrderDatetime(rs.getDate("order_datetime"));
				order.setPayment(rs.getInt("payment"));
				order.setPost(rs.getInt("post"));
				order.setTotalPrice(rs.getFloat("total_price"));
				order.setOrderState(rs.getInt("order_state"));
				list.add(order);
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

	public MyOrderVo findMyOrderById(int id) {
		MyOrderVo order = new MyOrderVo();
		String sql = "SELECT * FROM myorder WHERE order_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setConId(rs.getInt("con_id"));
				order.setOrderNum(rs.getString("order_num"));
				order.setOrderDatetime(rs.getDate("order_datetime"));
				order.setPayment(rs.getInt("payment"));
				order.setPost(rs.getInt("post"));
				order.setTotalPrice(rs.getFloat("total_price"));
				order.setOrderState(rs.getInt("order_state"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return order;
	}

	public boolean modifyMyOrder(MyOrderVo order) {
		boolean flag = false;
		String sql = "UPDATE myorder SET user_id = ?, con_id = ?, order_num = ?, payment = ?,"
				+ "post = ?, total_price = ?, order_state = ? WHERE order_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, order.getUserId());
			pstmt.setInt(2, order.getConId());
			pstmt.setString(3, order.getOrderNum());
			pstmt.setInt(4, order.getPayment());
			pstmt.setInt(5, order.getPost());
			pstmt.setFloat(6, order.getTotalPrice());
			pstmt.setInt(7, order.getOrderState());
			pstmt.setInt(8, order.getOrderId());
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

	public boolean removeMyOrderById(int id) {
		boolean flag = false;
		String sql = "DELETE FROM myorder WHERE order_id = ?";
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

	public MyOrderVo findOrderByOrderNum(String orderNum) {
		MyOrderVo order = new MyOrderVo();
		String sql = "SELECT * FROM myorder WHERE order_num = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, orderNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setConId(rs.getInt("con_id"));
				order.setOrderNum(rs.getString("order_num"));
				order.setOrderDatetime(rs.getDate("order_datetime"));
				order.setPayment(rs.getInt("payment"));
				order.setPost(rs.getInt("post"));
				order.setTotalPrice(rs.getFloat("total_price"));
				order.setOrderState(rs.getInt("order_state"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return order;
	}

	public List<MyOrderVo> findOrderByUserId(int id) {
		List<MyOrderVo> list = new ArrayList<MyOrderVo>();
		String sql = "SELECT * FROM myorder WHERE user_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MyOrderVo order = new MyOrderVo();
				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setConId(rs.getInt("con_id"));
				order.setOrderNum(rs.getString("order_num"));
				try {
					order.setOrderDatetime(new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("order_datetime")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				order.setPayment(rs.getInt("payment"));
				order.setPost(rs.getInt("post"));
				order.setTotalPrice(rs.getFloat("total_price"));
				order.setOrderState(rs.getInt("order_state"));
				list.add(order);
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

}
