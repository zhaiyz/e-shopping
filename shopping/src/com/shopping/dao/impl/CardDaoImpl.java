package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.CardDao;
import com.shopping.util.DBUtil;
import com.shopping.vo.CardAnalysisVo;
import com.shopping.vo.CardVo;

public class CardDaoImpl implements CardDao {

	public boolean addCard(CardVo card) {
		boolean flag = false;
		String sql = "INSERT INTO card ( card_id, card_no, card_password, card_value, card_datetime, card_flag ) VALUES (?, ?, ?, ?, NOW(), ?)";
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, card.getCardId());
			pst.setString(2, card.getCardNo());
			pst.setString(3, card.getCardPassword());
			pst.setFloat(4, card.getCardValue());
			pst.setInt(5, card.getCardFlag());
			if (pst.executeUpdate() == 1) {
				flag = true;
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return flag;
	}

	public List<CardVo> findAllCard(int start, int limit) {
		List<CardVo> list = new ArrayList<CardVo>();
		String sql = "SELECT * FROM card WHERE 1 = 1 LIMIT " + start + ","
				+ limit;
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				CardVo card = new CardVo();
				card.setCardNo(rs.getString("card_no"));
				card.setCardPassword(rs.getString("card_password"));
				card.setCardValue(rs.getFloat("card_value"));
				card.setCardDateTime(rs.getString("card_datetime").substring(0,
						19));
				card.setCardFlag(rs.getInt("card_flag"));
				list.add(card);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return list;
	}

	public CardVo findCadrById(int cardId) {
		CardVo card = new CardVo();
		String sql = "SELECT * FROM card WHERE card_id = ?";
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, cardId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				card.setCardNo(rs.getString("card_no"));
				card.setCardPassword(rs.getString("card_password"));
				card.setCardValue(rs.getFloat("card_value"));
				card.setCardDateTime(rs.getString("card_datetime").substring(0,
						19));
				card.setCardFlag(rs.getInt("card_flag"));
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return card;
	}

	public boolean modifyCard(CardVo card) {
		boolean flag = false;
		String sql = "UPDATE card SET card_no = ?, card_password = ?, card_value = ?, card_flag = ? WHERE card_id = ? ";
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setString(1, card.getCardNo());
			pst.setString(2, card.getCardPassword());
			pst.setFloat(3, card.getCardValue());
			pst.setInt(4, card.getCardFlag());
			pst.setInt(5, card.getCardId());
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

	public boolean removeCard(int cardId) {
		boolean flag = false;
		String sql = "DELETE FROM CARD WHERE card_id = ?";
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, cardId);
			if (pst.executeUpdate() == 1) {
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

	public boolean accountManage(CardVo card) {
		boolean flag = false;
		String sql = "SELECT * FROM card WHERE card_no = ? AND card_password = ? AND card_flag = 0";
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setString(1, card.getCardNo());
			pst.setString(2, card.getCardPassword());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				flag = true;
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return flag;
	}

	public CardVo findCardByCardName(String cardNo, String cardPassword) {
		CardVo card = new CardVo();
		String sql = "SELECT * FROM card WHERE card_no = ? AND card_password = ?";
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setString(1, cardNo);
			pst.setString(2, cardPassword);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				card.setCardId(rs.getInt("card_id"));
				card.setCardNo(rs.getString("card_no"));
				card.setCardPassword(rs.getString("card_password"));
				card.setCardValue(rs.getFloat("card_value"));
				card.setCardDateTime(rs.getString("card_datetime").substring(0,
						19));
				card.setCardFlag(rs.getInt("card_flag"));
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return card;
	}

	public int getTotalNum() {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM card WHERE 1 = 1";
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
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

	public int getTotalNum(int state) {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM card WHERE card_flag = ?";
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, state);
			ResultSet rs = pst.executeQuery();
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

	public List<CardVo> findAllCard(int state, int start, int limit) {
		List<CardVo> list = new ArrayList<CardVo>();
		String sql = "SELECT * FROM card WHERE card_flag = ? LIMIT " + start
				+ "," + limit;
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, state);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				CardVo card = new CardVo();
				card.setCardNo(rs.getString("card_no"));
				card.setCardPassword(rs.getString("card_password"));
				card.setCardValue(rs.getFloat("card_value"));
				card.setCardDateTime(rs.getString("card_datetime").substring(0,
						19));
				card.setCardFlag(rs.getInt("card_flag"));
				list.add(card);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return list;
	}

	public CardAnalysisVo CardAnalysis(int state) {
		CardAnalysisVo ca = new CardAnalysisVo();
		String sql = "SELECT COUNT(*), SUM(card_value) FROM card WHERE card_flag = ?";
		DBUtil dbc = new DBUtil();
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, state);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ca.setState(state);
				ca.setNumber(rs.getInt(1));
				ca.setValue(rs.getFloat(2));
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbc.close();
		}
		return ca;
	}

}
