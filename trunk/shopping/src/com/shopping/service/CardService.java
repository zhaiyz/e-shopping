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

	/**
	 * �ж��û�����Ŀ��������Ƿ�ƥ��
	 * 
	 * @param card
	 * 
	 * @return �Ƿ�ƥ��
	 */
	public boolean accountManage(CardVo card);

	/**
	 * �����š������ѯ��ֵ��
	 * 
	 * @param cardNo
	 *            ����
	 * @param cardPassword
	 *            ����
	 * @return ��ѯ���ĳ�ֵ��
	 */
	public CardVo findCardByCardName(String cardNo, String cardPassword);

	/**
	 * ȡ��ȫ���������������ڷ�ҳ
	 * 
	 * @return
	 */
	public int getTotalNum();

	/**
	 * ��״̬�������
	 * 
	 * @param state
	 * @return
	 */
	public int getTotalNum(int state);

	/**
	 * ��״̬��ѯ����
	 * 
	 * @param state
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<CardVo> findAllCard(int state, int start, int limit);
}
