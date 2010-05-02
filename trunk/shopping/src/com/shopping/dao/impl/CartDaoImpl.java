package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.CartDao;
import com.shopping.util.DBUtil;
import com.shopping.vo.CartVo;

public class CartDaoImpl implements CartDao {

	public boolean addCart(CartVo cart) {
		boolean flag = false;
		String sql = "INSERT INTO cart (user_id, pro_id, pro_amount, add_datetime) "
				+ "VALUES (?, ?, ?, NOW())";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, cart.getCartId());
			pstmt.setInt(2, cart.getProId());
			pstmt.setInt(3, cart.getProAmount());
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

	public List<CartVo> findAllCart(int start, int limit) {
		List<CartVo> list = new ArrayList<CartVo>();
		String sql = "SELECT * FROM cart WHERE 1 = 1 LIMIT " + start + ", "
				+ limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CartVo cart = new CartVo();
				cart.setCartId(rs.getInt("cart_id"));
				cart.setUserId(rs.getInt("user_id"));
				cart.setProId(rs.getInt("pro_id"));
				cart.setProAmount(rs.getInt("pro_amount"));
				cart.setAddDatetime(rs.getDate("add_datetime"));
				list.add(cart);
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

	public CartVo findCartById(int id) {
		CartVo cart = new CartVo();
		String sql = "SELECT * FROM cart WHERE cart_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cart.setCartId(rs.getInt("cart_id"));
				cart.setUserId(rs.getInt("user_id"));
				cart.setProId(rs.getInt("pro_id"));
				cart.setProAmount(rs.getInt("pro_amount"));
				cart.setAddDatetime(rs.getDate("add_datetime"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return cart;
	}

	public List<CartVo> findCartByUserId(int id) {
		List<CartVo> list = new ArrayList<CartVo>();
		String sql = "SELECT * FROM cart WHERE user_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CartVo cart = new CartVo();
				cart.setCartId(rs.getInt("cart_id"));
				cart.setUserId(rs.getInt("user_id"));
				cart.setProId(rs.getInt("pro_id"));
				cart.setProAmount(rs.getInt("pro_amount"));
				cart.setAddDatetime(rs.getDate("add_datetime"));
				list.add(cart);
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

	public boolean modifyCart(CartVo cart) {
		boolean flag = false;
		String sql = "UPDATE cart SET user_id = ?, pro_id = ?, pro_amount = ? "
				+ "WHERE cart_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, cart.getUserId());
			pstmt.setInt(2, cart.getProId());
			pstmt.setInt(3, cart.getProAmount());
			pstmt.setInt(4, cart.getCartId());
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

	public boolean removeCartById(int id) {
		boolean flag = false;
		String sql = "DELETE FROM cart WHERE cart_id = ?";
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

	public boolean removeByUserId(int id) {
		boolean flag = false;
		String sql = "DELETE FROM cart WHERE user_id = ?";
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
