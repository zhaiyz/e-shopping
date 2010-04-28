/**
 * User值对象所对应的DAO接口
 */
package com.shopping.dao;

import java.util.List;

import com.shopping.vo.UserVo;

public interface UserDao {

	/**
	 * 用户注册的时候，添加用户
	 * 
	 * @param user
	 *            UserVo 一个UserVo对象
	 * @return Boolean 用来判断是否添加成功
	 */
	public Boolean addUser(UserVo user);

	/**
	 * 按用户id来删除一个用户
	 * 
	 * @param id
	 *            int 用户的id
	 * @return Boolean 用来判断是否删除成功
	 */
	public Boolean removeUserById(int id);

	/**
	 * 修改一个用户的信息
	 * 
	 * @param user
	 *            UserVo 一个UserVo对象
	 * @return Boolean 用来判断是否修改成功
	 */
	public Boolean modifyUser(UserVo user);

	/**
	 * 分页查询所有的用户
	 * 
	 * @param start
	 *            开始位置
	 * @param limit
	 *            偏移量
	 * @return list 查询到的所有用户
	 */
	public List<UserVo> findAllUser(int start, int limit);

	/**
	 * 按id查询出User
	 * 
	 * @param id
	 *            int 用户的id
	 * @return UserVo 一个UserVo对象
	 */
	public UserVo findUserById(int id);
}
