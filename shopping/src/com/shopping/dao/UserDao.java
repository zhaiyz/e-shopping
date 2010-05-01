/**
 * Userֵ�������Ӧ��DAO�ӿ�
 */
package com.shopping.dao;

import java.util.List;

import com.shopping.vo.UserVo;

public interface UserDao {

	/**
	 * �û�ע���ʱ������û�
	 * 
	 * @param user
	 *            UserVo һ��UserVo����
	 * @return Boolean ��4�ж��Ƿ���ӳɹ�
	 */
	public boolean addUser(UserVo user);

	/**
	 * ���û�id4ɾ��һ���û�
	 * 
	 * @param id
	 *            int �û���id
	 * @return Boolean ��4�ж��Ƿ�ɾ��ɹ�
	 */
	public boolean removeUserById(int id);

	/**
	 * �޸�һ���û�����Ϣ
	 * 
	 * @param user
	 *            UserVo һ��UserVo����
	 * @return Boolean ��4�ж��Ƿ��޸ĳɹ�
	 */
	public boolean modifyUser(UserVo user);

	/**
	 * ��ҳ��ѯ���е��û�
	 * 
	 * @param start
	 *            ��ʼλ��
	 * @param limit
	 *            ƫ��
	 * @return list ��ѯ���������û�
	 */
	public List<UserVo> findAllUser(int start, int limit);

	/**
	 * ��id��ѯ��User
	 * 
	 * @param id
	 *            int �û���id
	 * @return UserVo һ��UserVo����
	 */
	public UserVo findUserById(int id);

	/**
	 * �ж��û��Ƿ����
	 * 
	 * @param user
	 *            UserVo����
	 * @return Boolean �Ƿ���ڴ��û�
	 */
	public boolean isLogin(UserVo user);

	/**
	 * ���û����ѯ�û�
	 * 
	 * @param name
	 * @return Boolean �Ƿ���ڴ��û�����û�
	 */
	public boolean findByName(String name);
}
