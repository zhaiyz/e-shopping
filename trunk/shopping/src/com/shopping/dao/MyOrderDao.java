/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.dao;

import java.util.List;

import com.shopping.vo.MyOrderVo;

/**
 * MyOrderDao
 */
public interface MyOrderDao {

	/**
	 * ���һ������
	 * 
	 * @param order
	 * @return boolean �Ƿ������ȷ
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
	 * @return boolean �Ƿ���ӳɹ�
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

	/**
	 * ͨ�������Ų�ѯ������
	 * 
	 * @param orderNum
	 * @return
	 */
	public MyOrderVo findOrderByOrderNum(String orderNum);

	/**
	 * ͨ���û�������ѯ����ǰ�û����еĶ���
	 * 
	 * @param id
	 * @return
	 */
	public List<MyOrderVo> findOrderByUserId(int id);
}
