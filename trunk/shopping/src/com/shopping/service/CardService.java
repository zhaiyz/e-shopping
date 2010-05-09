package com.shopping.service;

import java.util.List;

import com.shopping.vo.CardVo;

public interface CardService {

	/**
	 * ��ӳ�ֵ��
	 * 
	 * @param card
	 *            ��ֵ������
	 * @return ����Ƿ�ɹ�
	 */
	public boolean addCard(CardVo card);

	/**
	 * ��ID���ҳ�ֵ��
	 * 
	 * @param cardId
	 *            ��ֵ��ID
	 * @return ���ҳ����ĳ�ֵ������
	 */
	public CardVo findCadrById(int cardId);

	/**
	 * �������г�ֵ��
	 * 
	 * @return ��ֵ������
	 */
	public List<CardVo> findAllCard(int start, int limit);

	/**
	 * ɾ����ֵ��
	 * 
	 * @param cardId
	 *            ��ֵ��ID
	 * @return ɾ���Ƿ�ɹ�
	 */
	public boolean removeCard(int cardId);

	/**
	 * �޸ĳ�ֵ����Ϣ
	 * 
	 * @param card
	 *            Ҫ�޸ĵĳ�ֵ������
	 * @return �޸��Ƿ�ɹ�
	 */
	public boolean modifyCard(CardVo card);
}
