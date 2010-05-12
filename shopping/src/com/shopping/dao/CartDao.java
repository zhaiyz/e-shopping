package com.shopping.dao;

import java.util.List;

import com.shopping.vo.CartVo;
import com.shopping.vo.ProductVo;

public interface CartDao {

	/**
	 * 添加到购物车
	 * 
	 * @param cart
	 * @return 是否添加成功
	 */
	public boolean addCart(CartVo cart);

	/**
	 * 按id删除一条购物车里面的记录
	 * 
	 * @param id
	 * @return 是否删除成功
	 */
	public boolean removeCartById(int id);

	/**
	 * 修改购物车
	 * 
	 * @param cart
	 * @return 是否修改成功
	 */
	public boolean modifyCart(CartVo cart);

	/**
	 * 查询所有的购物车并分页
	 * 
	 * @param start
	 *            开始位置
	 * @param limit
	 *            偏移量
	 * @return 符合条件的记录
	 */
	public List<CartVo> findAllCart(int start, int limit);

	/**
	 * 按用户主键查询该用户所添加到购物车里面的商品
	 * 
	 * @param id
	 * @return 购物车里面的商品
	 */
	public List<CartVo> findCartByUserId(int id);

	/**
	 * 按id查询购物车记录
	 * 
	 * @param id
	 * @return 一条购物车记录
	 */
	public CartVo findCartById(int id);

	/**
	 * 按用户的主键删除购物车信息
	 * 
	 * @param id
	 * @return 是否删除成功
	 */
	public boolean removeByUserId(int id);

	/**
	 * 通过用户主键和商品主键查询在此用户下的购物车中是否存在此商品
	 * 
	 * @param userId
	 * @param proId
	 * @return
	 */
	public boolean findProIdByUserIdAndProId(int userId, int proId);

	/**
	 * 通过用户主键和商品主键查询在当前用户下的一条购物车记录
	 * 
	 * @param userId
	 * @param proId
	 * @return
	 */
	public CartVo findCartByUserIdAndProId(int userId, int proId);

	/**
	 * 按商品的id查询出此条记录的商品信息
	 * 
	 * @param id
	 * @return
	 */
	public ProductVo findProductByCatId(int id);

	/**
	 * 看一个商品是否被添加到购物车
	 * 
	 * @param id
	 * @return
	 */
	public boolean findCartByProId(int id);
}
