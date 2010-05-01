package com.shopping.service;

import java.util.List;

import com.shopping.vo.MyOrderVo;

/**
 * MyOrderService
 */
public interface MyOrderService {
	/**
	 * ����һ������
	 * 
	 * @param order
	 * @return boolean �Ƿ�������ȷ
	 */
	public boolean addMyOrder(MyOrderVo order);

	/**
	 * ������IDɾ��
	 * 
	 * @param id
	 *            ����ID
	 * @return boolean �Ƿ�ɾ���ɹ�
	 */
	public boolean removeMyOrderById(int id);

	/**
	 * �޸Ķ�������Ҫ�Ǹ�������״̬
	 * 
	 * @param order
	 * @return boolean �Ƿ����ӳɹ�
	 */
	public boolean modifyMyOrder(MyOrderVo order);

	/**
	 * ��ѯȫ������
	 * 
	 * @param start
	 *            int ��ʼλ��
	 * @param limit
	 *            int ƫ����
	 * @return ��������������MyOrder��¼
	 */
	public List<MyOrderVo> findAllMyOrder(int start, int limit);

	/**
	 * ��ID��ѯ����
	 * 
	 * @param id
	 * @return ��ӦID��MyOrder��¼
	 */
	public MyOrderVo findMyOrderById(int id);
}