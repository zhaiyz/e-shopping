package com.shopping.dao;

import java.util.List;

import com.shopping.vo.CardVo;

public interface CardDao {

	/**
	 * 添加充值卡
	 * 
	 * @param card
	 *            充值卡对象
	 * @return 添加是否成功
	 */
	public boolean addCard(CardVo card);

	/**
	 * 按ID查找充值卡
	 * 
	 * @param cardId
	 *            充值卡ID
	 * @return 查找出来的充值卡对象
	 */
	public CardVo findCadrById(int cardId);

	/**
	 * 查找所有充值卡
	 * 
	 * @return 充值卡集合
	 */
	public List<CardVo> findAllCard();

	/**
	 * 删除充值卡
	 * 
	 * @param cardId
	 *            充值卡ID
	 * @return 删除是否成功
	 */
	public boolean removeCard(int cardId);

	/**
	 * 修改充值卡信息
	 * 
	 * @param card
	 *            要修改的充值卡对象
	 * @return 修改是否成功
	 */
	public boolean modifyCard(CardVo card);
}
