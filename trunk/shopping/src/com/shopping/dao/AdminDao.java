/**
 * @author hanxue_lang@foxmil.com
 */
package com.shopping.dao;

import java.util.List;

import com.shopping.vo.AdminVo;

/**
 * 管理员Dao
 */
public interface AdminDao {
    
	/**
	 * 添加管理员
	 * @param admin
	 * @return boolean 是否添加成功
	 */
	public boolean addAdmin(AdminVo admin);
	
	/**
	 * 按ID删除管理员
	 * @param id
	 * @return boolean 是否删除成功
	 */
	public boolean removeAdminById(int id);
	
	/**
	 * 修改管理员信息
	 * @param admin
	 * @return boolean 是否修改成功
	 */
	public boolean modifyAdmin(AdminVo admin);
	
	/**
	 * 按ID查询出管理员
	 * @param id
	 * @return AdminVo
	 */
	public AdminVo findAdminById(int id);
	
	/**
	 * 查询全部分了页啦
	 * @param start int 开始位置
	 * @param limit int 偏移量
	 * @return list 返回的符合条件的Admin
	 */
	public List<AdminVo> findAllAdmin(int start, int limit);
	
	/**
	 * 判断是否能登录成功
	 * @param admin
	 * @return boolean 是否登录成功
	 */
	public boolean isLogin(AdminVo admin);
}
