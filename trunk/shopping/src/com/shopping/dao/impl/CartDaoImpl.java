package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.CartDao;
import com.shopping.util.DBUtil;
import com.shopping.vo.CartVo;
import com.shopping.vo.ProductVo;

public class CartDaoImpl implements CartDao {

	public boolean addCart(CartVo cart) {
		boolean flag = false;
		String sql = "INSERT INTO cart (user_id, pro_id, pro_amount, add_datetime) "
				+ "VALUES (?, ?, ?, NOW())";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, cart.getUserId());
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

	public boolean findProIdByUserIdAndProId(int userId, int proId) {
		boolean flag = false;
		String sql = "SELECT * FROM cart WHERE user_id = ? AND pro_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, proId);
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

	public CartVo findCartByUserIdAndProId(int userId, int proId) {
		CartVo cart = new CartVo();
		String sql = "SELECT * FROM cart WHERE user_id = ? AND pro_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, proId);
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

	public ProductVo findProductByCatId(int id) {
		ProductVo product = new ProductVo();
		String sql = "SELECT * FROM product WHERE pro_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				product.setProId(rs.getInt("pro_id"));
				product.setItemId(rs.getInt("item_id"));
				product.setProName(rs.getString("pro_name"));
				product.setImageUrl(rs.getString("imageurl"));
				product.setProDesc(rs.getString("pro_desc"));
				product.setProDatetime(rs.getString("pro_datetime").substring(0, 19));
				product.setPurPrice(rs.getFloat("pur_price"));
				product.setOriPrice(rs.getFloat("ori_price"));
				product.setDisPrice(rs.getFloat("dis_price"));
				product.setStock(rs.getInt("stock"));
				product.setSales(rs.getInt("sales"));
				product.setRecommendation(rs.getInt("recommendation"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return product;
	}
}
