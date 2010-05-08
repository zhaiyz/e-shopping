package com.shopping.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.shopping.dao.CardDao;
import com.shopping.util.DBUtil;
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<CardVo> findAllCard() {
		return null;
	}

	public CardVo findCadrById(int cardId) {
		return null;
	}

	public boolean modifyCard(CardVo card) {
		return false;
	}

	public boolean removeCard(int cardId) {
		return false;
	}

}
