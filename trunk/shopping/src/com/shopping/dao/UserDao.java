/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.dao;

import java.util.List;

import com.shopping.vo.UserVo;

public interface UserDao {

	/**
	 * 添加用户，主要是用户注册
	 * 
	 * @param user
	 * @return boolean 是否注册成功
	 */
	public boolean addUser(UserVo user);

	/**
	 * 按ID删除一个用户
	 * 
	 * @param id
	 * @return boolean 是否删除成功
	 */
	public boolean removeUserById(int id);

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return boolean 是否修改成功
	 */
	public boolean modifyUser(UserVo user);

	/**
	 * 查询所有用户
	 * 
	 * @param start
	 *            开始位置
	 * @param limit
	 *            偏移量
	 * @return list 符合条件的用户
	 */
	public List<UserVo> findAllUser(int start, int limit);

	/**
	 * 按ID查询用户
	 * 
	 * @param id
	 * @return UserVo
	 */
	public UserVo findUserById(int id);

	/**
	 * 判断是否登录成功
	 * 
	 * @param user
	 * @return boolean 是否登录成功
	 */
	public boolean isLogin(UserVo user);

	/**
	 * 按姓名查询用户
	 * 
	 * @param name
	 * @return boolean 是否存在些用户名的用户
	 */
	public boolean findByName(String name);

	/**
	 * 通过用户名查询出用户所有的信息
	 * 
	 * @param name
	 * @return 一个用户对象
	 */
	public UserVo findUserByName(String name);
}
