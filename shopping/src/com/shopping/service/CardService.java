package com.shopping.service;

import java.util.List;

import com.shopping.vo.CardVo;

public interface CardService {

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
	public List<CardVo> findAllCard(int start, int limit);

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

	/**
	 * 判断用户输入的卡号密码是否匹配
	 * 
	 * @param card
	 * 
	 * @return 是否匹配
	 */
	public boolean accountManage(CardVo card);

	/**
	 * 按卡号、密码查询充值卡
	 * 
	 * @param cardNo
	 *            卡号
	 * @param cardPassword
	 *            密码
	 * @return 查询出的充值卡
	 */
	public CardVo findCardByCardName(String cardNo, String cardPassword);

	/**
	 * 取得全部卡的数量，用于分页
	 * 
	 * @return
	 */
	public int getTotalNum();

	/**
	 * 按状态获得数量
	 * 
	 * @param state
	 * @return
	 */
	public int getTotalNum(int state);

	/**
	 * 按状态查询出卡
	 * 
	 * @param state
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<CardVo> findAllCard(int state, int start, int limit);
}
