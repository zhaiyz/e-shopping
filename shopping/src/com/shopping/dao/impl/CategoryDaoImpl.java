package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.CategoryDao;
import com.shopping.util.DBUtil;
import com.shopping.vo.CategoryVo;

public class CategoryDaoImpl implements CategoryDao {

	public boolean addCategory(CategoryVo category) {
		boolean flag = false;
		String sql = "INSERT INTO category (cat_name, cat_desc, cat_datetime) VALUES (?, ?, NOW())";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, category.getCatName());
			pstmt.setString(2, category.getCatDesc());
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

	public List<CategoryVo> findAllCategory(int start, int limit) {
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		String sql = "SELECT * FROM category WHERE 1 = 1 LIMIT " + start + ","
				+ limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CategoryVo category = new CategoryVo();
				category.setCatId(rs.getInt("cat_id"));
				category.setCatName(rs.getString("cat_name"));
				category.setCatDesc(rs.getString("cat_desc"));
				category.setCatDatetime(rs.getString("cat_datetime").substring(
						0, 19));
				list.add(category);
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

	public CategoryVo findCategoryById(int catId) {
		CategoryVo category = new CategoryVo();
		String sql = "SELECT * FROM category WHERE cat_id = ? and 1 = 1";
		PreparedStatement pst = null;
		ResultSet rs = null;
		DBUtil dbc = new DBUtil();
		try {
			pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();
			while (rs.next()) {
				category.setCatId(rs.getInt("cat_id"));
				category.setCatName(rs.getString("cat_name"));
				category.setCatDesc(rs.getString("cat_desc"));
				category.setCatDatetime(rs.getString("cat_datetime").substring(
						0, 19));
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}

		return category;
	}

	public CategoryVo findCategoryByName(String catName) {
		CategoryVo category = new CategoryVo();
		String sql = "SELECT * FROM category WHERE cat_name = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		DBUtil dbc = new DBUtil();
		try {
			pst = dbc.getConnection().prepareStatement(sql);
			pst.setString(1, catName);
			rs = pst.executeQuery();
			while (rs.next()) {
				category.setCatId(rs.getInt("cat_id"));
				category.setCatName(rs.getString("cat_name"));
				category.setCatDesc(rs.getString("cat_desc"));
				category.setCatDatetime(rs.getString("cat_datetime").substring(
						0, 19));
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return category;
	}

	public boolean modifyCategory(CategoryVo category) {
		boolean flag = false;
		String sql = "UPDATE category SET cat_name = ?, cat_desc = ? WHERE cat_id = ?";
		PreparedStatement pst = null;
		DBUtil dbc = new DBUtil();
		try {
			pst = dbc.getConnection().prepareStatement(sql);
			pst.setString(1, category.getCatName());
			pst.setString(2, category.getCatDesc());
			pst.setInt(3, category.getCatId());
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

	public boolean removeCategoryById(int catId) {
		boolean flag = false;
		String sql = "DELETE FROM category WHERE cat_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, catId);
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

	public List<CategoryVo> findAllCategory() {
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		String sql = "SELECT * FROM category WHERE 1 = 1";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CategoryVo category = new CategoryVo();
				category.setCatId(rs.getInt("cat_id"));
				category.setCatName(rs.getString("cat_name"));
				category.setCatDesc(rs.getString("cat_desc"));
				category.setCatDatetime(rs.getString("cat_datetime").substring(
						0, 19));
				list.add(category);
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

	public List<CategoryVo> findCategoryByLike(String key, int start, int limit) {
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		String sql = "SELECT * FROM category WHERE cat_name LIKE ? LIMIT "
				+ start + "," + limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CategoryVo category = new CategoryVo();
				category.setCatId(rs.getInt("cat_id"));
				category.setCatName(rs.getString("cat_name"));
				category.setCatDesc(rs.getString("cat_desc"));
				category.setCatDatetime(rs.getString("cat_datetime").substring(
						0, 19));
				list.add(category);
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

	public int getTotalNum() {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM category WHERE 1 = 1";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
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

	public int getTotalNumByLike(String key) {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM category WHERE cat_name LIKE ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
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
