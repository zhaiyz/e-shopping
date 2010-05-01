package com.shopping.service;

import java.util.List;

import com.shopping.vo.ContactVo;

/**
 * ContactService
 */
public interface ContactService {
	/**
	 * ���һ����͵�ַ
	 * 
	 * @param contact
	 * @return boolean �Ƿ���ӳɹ�
	 */
	public boolean addContact(ContactVo contact);

	/**
	 * ��IDɾ����͵�ַ
	 * 
	 * @param id
	 * @return boolean �Ƿ�ɾ��ɹ�
	 */
	public boolean removeContactById(int id);

	/**
	 * �޸ļ��͵�ַ
	 * 
	 * @param contact
	 * @return boolean �Ƿ��޸ĳɹ�
	 */
	public boolean modifyContact(ContactVo contact);

	/**
	 * ��ѯ��ȫ��jϵ��ַ
	 * 
	 * @param start
	 *            int ��ʼλ��
	 * @param limit
	 *            int ƫ��
	 * @return list ��������Contact��¼
	 */
	public List<ContactVo> findAllContact(int start, int limit);

	/**
	 * ��ID��ѯContact
	 * 
	 * @param id
	 * @return ContactVo
	 */
	public ContactVo findContactById(int id);

	/**
	 * ��OrderId��ѯ����Ӧ��Contact��¼
	 * 
	 * @param id
	 *            orderId ����ID
	 * @return ContactVo ��Ӧ����ID�Ķ������͵�ַ
	 */
	public ContactVo findContactByOrderId(int id);
}
