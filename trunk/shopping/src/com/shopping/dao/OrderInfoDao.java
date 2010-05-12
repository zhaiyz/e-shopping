/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.dao;

import java.util.List;

import com.shopping.vo.OrderInfoVo;

/**
 * OrderInfoDao
 */
public interface OrderInfoDao {

	/**
	 * ��Ӷ���������Ϣ
	 * 
	 * @param info
	 * @return boolean �Ƿ���ӳɹ�
	 */
	public boolean addOrderInfo(OrderInfoVo info);

	/**
	 * ��IDɾ����Ӧ����������Ϣ
	 * 
	 * @param id
	 * @return boolean �Ƿ�ɾ���ɹ�
	 */
	public boolean removeOrderInfoById(int id);

	/**
	 * �޸Ķ���������Ϣ
	 * 
	 * @param info
	 * @return boolean �Ƿ��޸ĳɹ�
	 */
	public boolean modifyOrderInfo(OrderInfoVo info);

	/**
	 * ��ѯȫ������������Ϣ
	 * 
	 * @param start
	 *            int ��ʼλ��
	 * @param limit
	 *            int ƫ����
	 * @return list ��������������OrderInfo��¼
	 */
	public List<OrderInfoVo> findAllOrderInfo(int start, int limit);

	/**
	 * ��ID��ѯ��Ӧ��OrderInfo��¼
	 * 
	 * @param id
	 *            orderInfoId
	 * @return һ��OrderInfo��¼
	 */
	public OrderInfoVo findOrderInfoById(int id);

	/**
	 * ��������ID��ѯOrderInfo��¼��û�з�ҳ
	 * 
	 * @param id
	 *            int ������ID orderId
	 * @return
	 */
	public List<OrderInfoVo> findOrderInfoByOrderId(int id);

	/**
	 * �����ж�һ����Ʒ�Ƿ񱻹����
	 * 
	 * @param proId
	 * @return
	 */
	public boolean findOrderInfoByProId(int proId);
}
