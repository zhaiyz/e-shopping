package com.shopping.service;

import java.util.List;

import com.shopping.vo.UserVo;

public interface UserService {
	/**
	 * �û�ע���ʱ������û�
	 * 
	 * @param user
	 *            UserVo һ��UserVo����
	 * @return Boolean �����ж��Ƿ���ӳɹ�
	 */
	public Boolean addUser(UserVo user);

	/**
	 * ���û�id��ɾ��һ���û�
	 * 
	 * @param id
	 *            int �û���id
	 * @return Boolean �����ж��Ƿ�ɾ���ɹ�
	 */
	public Boolean removeUserById(int id);

	/**
	 * �޸�һ���û�����Ϣ
	 * 
	 * @param user
	 *            UserVo һ��UserVo����
	 * @return Boolean �����ж��Ƿ��޸ĳɹ�
	 */
	public Boolean modifyUser(UserVo user);

	/**
	 * ��ѯ�����е�User
	 * 
	 * @return List ���е�User
	 */
	public List<UserVo> findAllUser();

	/**
	 * ��id��ѯ��User
	 * 
	 * @param id
	 *            int �û���id
	 * @return UserVo һ��UserVo����
	 */
	public UserVo findUserById(int id);
}
