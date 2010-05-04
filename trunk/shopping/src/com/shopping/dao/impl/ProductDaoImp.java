package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.ProductDao;
import com.shopping.util.DBUtil;
import com.shopping.vo.ProductVo;

public class ProductDaoImp implements ProductDao {

	public boolean addProduct(ProductVo product) {
		boolean flag = false;
		String sql = "INSERT INTO product (item_id, pro_name, imageurl, pro_desc, "
				+ "pro_datetime, pur_price, ori_price, dis_price, stock, sales, "
				+ "recommendation) VALUES (?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, product.getItemId());
			pstmt.setString(2, product.getProName());
			pstmt.setString(3, product.getImageUrl());
			pstmt.setString(4, product.getProDesc());
			pstmt.setFloat(5, product.getPurPrice());
			pstmt.setFloat(6, product.getOriPrice());
			pstmt.setFloat(7, product.getDisPrice());
			pstmt.setInt(8, product.getStock());
			pstmt.setInt(9, product.getSales());
			pstmt.setInt(10, product.getRecommendation());
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

	public List<ProductVo> findAllProduct(int itemId, int start, int limit) {
		List<ProductVo> list = new ArrayList<ProductVo>();
		String sql = "SELECT * FROM product WHERE item_id = ? and 1 = 1 LIMIT "
				+ start + "," + limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, itemId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVo product = new ProductVo();
				product.setProId(rs.getInt("pro_id"));
				product.setItemId(rs.getInt("item_id"));
				product.setProName(rs.getString("pro_name"));
				product.setImageUrl(rs.getString("imageurl"));
				product.setProDesc(rs.getString("pro_desc"));
				product.setProDatetime(rs.getDate("pro_datetime"));
				product.setPurPrice(rs.getFloat("pur_price"));
				product.setOriPrice(rs.getFloat("ori_price"));
				product.setDisPrice(rs.getFloat("dis_price"));
				product.setStock(rs.getInt("stock"));
				product.setSales(rs.getInt("sales"));
				product.setRecommendation(rs.getInt("recommendation"));
				list.add(product);
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

	public List<ProductVo> findAllProduct(int start, int limit) {
		List<ProductVo> list = new ArrayList<ProductVo>();
		String sql = "SELECT * FROM product WHERE 1 = 1 LIMIT " + start + ","
				+ limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVo product = new ProductVo();
				product.setProId(rs.getInt("pro_id"));
				product.setItemId(rs.getInt("item_id"));
				product.setProName(rs.getString("pro_name"));
				product.setImageUrl(rs.getString("imageurl"));
				product.setProDesc(rs.getString("pro_desc"));
				product.setProDatetime(rs.getDate("pro_datetime"));
				product.setPurPrice(rs.getFloat("pur_price"));
				product.setOriPrice(rs.getFloat("ori_price"));
				product.setDisPrice(rs.getFloat("dis_price"));
				product.setStock(rs.getInt("stock"));
				product.setSales(rs.getInt("sales"));
				product.setRecommendation(rs.getInt("recommendation"));
				list.add(product);
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

	public ProductVo findProductById(int proId) {
		ProductVo product = new ProductVo();
		String sql = "SELECT * FROM product WHERE pro_id = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		DBUtil dbc = new DBUtil();
		try {
			pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, proId);
			rs = pst.executeQuery();
			while (rs.next()) {
				product.setProId(rs.getInt("pro_id"));
				product.setItemId(rs.getInt("item_id"));
				product.setProName(rs.getString("pro_name"));
				product.setImageUrl(rs.getString("imageurl"));
				product.setProDesc(rs.getString("pro_desc"));
				product.setProDatetime(rs.getDate("pro_datetime"));
				product.setPurPrice(rs.getFloat("pur_price"));
				product.setOriPrice(rs.getFloat("ori_price"));
				product.setDisPrice(rs.getFloat("dis_price"));
				product.setStock(rs.getInt("stock"));
				product.setSales(rs.getInt("sales"));
				product.setRecommendation(rs.getInt("recommendation"));
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}

		return product;
	}

	public ProductVo findProductByName(String proName) {
		ProductVo product = new ProductVo();
		String sql = "SELECT * FROM product WHERE pro_name = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		DBUtil dbc = new DBUtil();
		try {
			pst = dbc.getConnection().prepareStatement(sql);
			pst.setString(1, proName);
			rs = pst.executeQuery();
			while (rs.next()) {
				product.setProId(rs.getInt("pro_id"));
				product.setItemId(rs.getInt("item_id"));
				product.setProName(rs.getString("pro_name"));
				product.setImageUrl(rs.getString("imageurl"));
				product.setProDesc(rs.getString("pro_desc"));
				product.setProDatetime(rs.getDate("pro_datetime"));
				product.setPurPrice(rs.getFloat("pur_price"));
				product.setOriPrice(rs.getFloat("ori_price"));
				product.setDisPrice(rs.getFloat("dis_price"));
				product.setStock(rs.getInt("stock"));
				product.setSales(rs.getInt("sales"));
				product.setRecommendation(rs.getInt("recommendation"));
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return product;
	}

	public boolean modifyProduct(ProductVo product) {
		boolean flag = false;
		String sql = "UPDATE product SET item_id = ?, pro_name = ?, imageurl = ?, "
				+ "pro_desc = ?, pur_price = ?, ori_price = ?, dis_price = ?,stock = ?,"
				+ " sales = ?,recommendation = ? WHERE pro_id = ?";
		PreparedStatement pst = null;
		DBUtil dbc = new DBUtil();
		try {
			pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, product.getItemId());
			pst.setString(2, product.getProName());
			pst.setString(3, product.getImageUrl());
			pst.setString(4, product.getProDesc());
			pst.setFloat(5, product.getPurPrice());
			pst.setFloat(6, product.getOriPrice());
			pst.setFloat(7, product.getDisPrice());
			pst.setInt(8, product.getStock());
			pst.setInt(9, product.getSales());
			pst.setInt(10, product.getRecommendation());
			pst.setInt(11, product.getProId());
			if (pst.executeUpdate() == 0 || pst.executeUpdate() == 1) {
				flag = true;
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return flag;
	}

	public boolean removeProductById(int proId) {
		boolean flag = false;
		String sql = "DELETE FROM product WHERE pro_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, proId);
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

	public int getTotalNumber(int id) {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM product WHERE item_id = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		DBUtil dbc = new DBUtil();
		try {
			pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return total;
	}

	public List<ProductVo> findProductByLike(String name, int start, int limit) {
		List<ProductVo> list = new ArrayList<ProductVo>();
		String sql = "SELECT * FROM product WHERE pro_name LIKE ? LIMIT "
				+ start + "," + limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVo product = new ProductVo();
				product.setProId(rs.getInt("pro_id"));
				product.setItemId(rs.getInt("item_id"));
				product.setProName(rs.getString("pro_name"));
				product.setImageUrl(rs.getString("imageurl"));
				product.setProDesc(rs.getString("pro_desc"));
				product.setProDatetime(rs.getDate("pro_datetime"));
				product.setPurPrice(rs.getFloat("pur_price"));
				product.setOriPrice(rs.getFloat("ori_price"));
				product.setDisPrice(rs.getFloat("dis_price"));
				product.setStock(rs.getInt("stock"));
				product.setSales(rs.getInt("sales"));
				product.setRecommendation(rs.getInt("recommendation"));
				list.add(product);
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

	public int getTotalProductByLike(String name) {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM product WHERE pro_name LIKE ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return total;
	}

}
