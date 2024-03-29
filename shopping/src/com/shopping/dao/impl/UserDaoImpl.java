/**
 * UserDao
 */
package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.UserDao;
import com.shopping.util.DBUtil;
import com.shopping.vo.UserVo;

public class UserDaoImpl implements UserDao {

	public boolean addUser(UserVo user) {
		boolean flag = false;
		String sql = "INSERT INTO user (user_name, user_password, gender, grade,"
				+ " balance, prompt, answer, favor, payed, user_state, reg_datetime,"
				+ " email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?)";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setInt(3, user.getGender());
			pstmt.setInt(4, user.getGrade());
			pstmt.setFloat(5, user.getBalance());
			pstmt.setString(6, user.getPrompt());
			pstmt.setString(7, user.getAnswer());
			pstmt.setString(8, user.getFavor());
			pstmt.setFloat(9, user.getPayed());
			pstmt.setInt(10, user.getUserState());
			pstmt.setString(11, user.getEmail());
			pstmt.setString(12, user.getPhone());
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

	public List<UserVo> findAllUser(int start, int limit) {
		List<UserVo> list = new ArrayList<UserVo>();
		String sql = "SELECT * FROM user WHERE 1 = 1 LIMIT " + start + ", "
				+ limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserVo user = new UserVo();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setGender(rs.getInt("gender"));
				user.setGrade(rs.getInt("grade"));
				user.setBalance(rs.getFloat("balance"));
				user.setPrompt(rs.getString("prompt"));
				user.setAnswer(rs.getString("answer"));
				user.setFavor(rs.getString("favor"));
				user.setPayed(rs.getFloat("payed"));
				user.setUserState(rs.getInt("user_state"));
				user.setRegDatetime(rs.getString("reg_datetime").substring(0,
						19));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				list.add(user);
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

	public UserVo findUserById(int id) {
		UserVo user = new UserVo();
		String sql = "SELECT * FROM user WHERE user_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setGender(rs.getInt("gender"));
				user.setGrade(rs.getInt("grade"));
				user.setBalance(rs.getFloat("balance"));
				user.setPrompt(rs.getString("prompt"));
				user.setAnswer(rs.getString("answer"));
				user.setFavor(rs.getString("favor"));
				user.setPayed(rs.getFloat("payed"));
				user.setUserState(rs.getInt("user_state"));
				user.setRegDatetime(rs.getString("reg_datetime").substring(0,
						19));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return user;
	}

	public boolean modifyUser(UserVo user) {
		boolean flag = false;
		String sql = "UPDATE user SET user_password = ?, gender = ?, grade = ?, balance = ?,"
				+ "prompt = ?, answer = ?, favor = ?, payed = ?, user_state = ?, email = ?, phone = ?"
				+ " WHERE user_id = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getUserPassword());
			pstmt.setInt(2, user.getGender());
			pstmt.setInt(3, user.getGrade());
			pstmt.setFloat(4, user.getBalance());
			pstmt.setString(5, user.getPrompt());
			pstmt.setString(6, user.getAnswer());
			pstmt.setString(7, user.getFavor());
			pstmt.setFloat(8, user.getPayed());
			pstmt.setInt(9, user.getUserState());
			pstmt.setString(10, user.getEmail());
			pstmt.setString(11, user.getPhone());
			pstmt.setInt(12, user.getUserId());
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

	public boolean removeUserById(int id) {
		boolean flag = false;
		String sql = "DELETE FROM user WHERE user_id = ?";
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

	public boolean isLogin(UserVo user) {
		boolean flag = false;
		String sql = "SELECT * FROM user WHERE user_name = ? AND user_password = ? AND user_state = 0";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserPassword());
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

	public boolean findByName(String name) {
		boolean flag = false;
		String sql = "SELECT * FROM user WHERE user_name = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, name);
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

	public UserVo findUserByName(String name) {
		UserVo user = new UserVo();
		String sql = "SELECT * FROM user WHERE user_name = ?";
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setGender(rs.getInt("gender"));
				user.setGrade(rs.getInt("grade"));
				user.setBalance(rs.getFloat("balance"));
				user.setPrompt(rs.getString("prompt"));
				user.setAnswer(rs.getString("answer"));
				user.setFavor(rs.getString("favor"));
				user.setPayed(rs.getFloat("payed"));
				user.setUserState(rs.getInt("user_state"));
				user.setRegDatetime(rs.getString("reg_datetime").substring(0,
						19));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return user;
	}

	public int getTotalNum() {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM user WHERE 1 = 1";
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

	public List<UserVo> findByLike(String name, int start, int limit) {
		List<UserVo> list = new ArrayList<UserVo>();
		String sql = "SELECT * FROM user WHERE user_name LIKE ? LIMIT " + start
				+ ", " + limit;
		PreparedStatement pstmt = null;
		DBUtil dbc = new DBUtil();
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserVo user = new UserVo();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setGender(rs.getInt("gender"));
				user.setGrade(rs.getInt("grade"));
				user.setBalance(rs.getFloat("balance"));
				user.setPrompt(rs.getString("prompt"));
				user.setAnswer(rs.getString("answer"));
				user.setFavor(rs.getString("favor"));
				user.setPayed(rs.getFloat("payed"));
				user.setUserState(rs.getInt("user_state"));
				user.setRegDatetime(rs.getString("reg_datetime").substring(0,
						19));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				list.add(user);
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

	public int getTotalNum(String name) {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM user WHERE user_name LIKE ?";
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
