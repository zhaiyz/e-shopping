package com.shopping.service;

import java.util.List;

import com.shopping.vo.ItemVo;

public interface ItemService {

	/**
	 * 添加一个商品小类
	 * 
	 * @param item
	 *            ItemVo值对象
	 * @return 添加是否成功
	 */
	public boolean addItem(ItemVo item);

	/**
	 * 按ID查询商品小类
	 * 
	 * @param itemId
	 *            商品小类id
	 * @return 商品小类的对象
	 */
	public ItemVo findById(int itemId);

	/**
	 * 按商品大类ID查询商品小类，不分页
	 * 
	 * @param catId
	 *            商品大类ID
	 * @return 查询出的商品小类集合
	 */
	public List<ItemVo> findItemByCategoryId(int catId);

	/**
	 * 分页查询商品小类
	 * 
	 * @param categoryId
	 *            商品大类ID
	 * @param start
	 *            起始查询位置
	 * @param limit
	 *            分页每页显示的数量
	 * @return 查询出的小类集合
	 */
	public List<ItemVo> findAllItem(int categoryId, int start, int limit);

	/**
	 * 分页查询所有商品小类
	 * 
	 * @param start
	 *            查询的起始位置
	 * @param limit
	 *            分页显示中每页显示的数目
	 * @return 查询出来的商品小类集合
	 */
	public List<ItemVo> findAllItem(int start, int limit);

	/**
	 * 删除商品小类
	 * 
	 * @param itemId
	 *            商品小类的ID
	 * @return 表示删除是否成功
	 */
	public boolean removeItemById(int itemId);

	/**
	 * 编辑产品小类
	 * 
	 * @param itemId
	 *            商品小类对象
	 * @return 修改是否成功
	 */
	public boolean modifyItem(ItemVo item);

	/**
	 * 查询出小类的总数
	 * 
	 * @return
	 */
	public int getTotalNum();

	/**
	 * 按条件查询出小类的总数
	 * 
	 * @param name
	 * @return
	 */
	public int getTotalNum(String name);

	/**
	 * 按条件查询小类
	 * 
	 * @param name
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<ItemVo> findItemByLike(String name, int start, int limit);

	/**
	 * 取得一个商品大类下面小类的总数
	 * 
	 * @param catId
	 * @return
	 */
	public int getTotalNum(int id);
	
	/**
	 * 查询出所有小类
	 * 
	 * @return
	 */
	public List<ItemVo> findAllItem();
}
