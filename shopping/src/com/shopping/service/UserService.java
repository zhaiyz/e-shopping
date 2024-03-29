package com.shopping.service;

import java.util.List;

import com.shopping.vo.UserVo;

public interface UserService {
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

	/**
	 * 判断用户是否存在
	 * 
	 * @param user
	 *            UserVo对象
	 * @return Boolean 是否存在此用户
	 */
	public Boolean isLogin(UserVo user);

	/**
	 * 按用户名查询用户
	 * 
	 * @param name
	 * @return Boolean 是否存在此用户名的用户
	 */
	public Boolean findByName(String name);

	/**
	 * 通过用户名查询出用户所有的信息
	 * 
	 * @param name
	 * @return 一个用户对象
	 */
	public UserVo findUserByName(String name);

	/**
	 * 取得会员数量
	 * 
	 * @return
	 */
	public int getTotalNum();

	/**
	 * 按条件进行查询
	 * 
	 * @param name
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<UserVo> findByLike(String name, int start, int limit);

	/**
	 * 按条件查询出总数
	 * 
	 * @param name
	 * @return
	 */
	public int getTotalNum(String name);
}
