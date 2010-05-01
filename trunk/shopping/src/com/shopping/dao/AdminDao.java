/**
 * @author hanxue_lang@foxmil.com
 */
package com.shopping.dao;

import java.util.List;

import com.shopping.vo.AdminVo;

/**
 * ����ԱDao
 */
public interface AdminDao {
    
	/**
	 * ��ӹ���Ա
	 * @param admin
	 * @return boolean �Ƿ���ӳɹ�
	 */
	public boolean addAdmin(AdminVo admin);
	
	/**
	 * ��IDɾ������Ա
	 * @param id
	 * @return boolean �Ƿ�ɾ���ɹ�
	 */
	public boolean removeAdminById(int id);
	
	/**
	 * �޸Ĺ���Ա��Ϣ
	 * @param admin
	 * @return boolean �Ƿ��޸ĳɹ�
	 */
	public boolean modifyAdmin(AdminVo admin);
	
	/**
	 * ��ID��ѯ������Ա
	 * @param id
	 * @return AdminVo
	 */
	public AdminVo findAdminById(int id);
	
	/**
	 * ��ѯȫ������ҳ��
	 * @param start int ��ʼλ��
	 * @param limit int ƫ����
	 * @return list ���صķ���������Admin
	 */
	public List<AdminVo> findAllAdmin(int start, int limit);
	
	/**
	 * �ж��Ƿ��ܵ�¼�ɹ�
	 * @param admin
	 * @return boolean �Ƿ��¼�ɹ�
	 */
	public boolean isLogin(AdminVo admin);
}
