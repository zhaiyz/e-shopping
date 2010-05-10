package com.shopping.dao;

import java.util.List;

import com.shopping.vo.ProductVo;

public interface ProductDao {

	/**
	 * 添加商品
	 * 
	 * @param product
	 *            商品对象
	 * @return 添加是否成功
	 */
	public boolean addProduct(ProductVo product);

	/**
	 * 修改商品信息
	 * 
	 * @param product
	 *            商品对象
	 * @return 修改是否成功
	 */
	public boolean modifyProduct(ProductVo product);

	/**
	 * 删除商品
	 * 
	 * @param proId
	 *            商品ID
	 * @return 删除是否成功
	 */
	public boolean removeProductById(int proId);

	/**
	 * 分页查询所有商品
	 * 
	 * @param itemId
	 *            商品所属小类的ID
	 * @param start
	 *            查询起始位置
	 * @param limit
	 *            分页显示的数量
	 * @return 查询出的商品集合
	 */
	public List<ProductVo> findAllProduct(int itemId, int start, int limit);

	/**
	 * 分页查询所有商品
	 * 
	 * @param start
	 *            起始查询位置
	 * @param limit
	 *            分页显示数量
	 * @return 查询出的商品集合
	 */
	public List<ProductVo> findAllProduct(int start, int limit);

	/**
	 * 按商品ID查询商品
	 * 
	 * @param proId
	 *            商品ID
	 * @return 查询出的商品
	 */
	public ProductVo findProductById(int proId);

	/**
	 * 按名称查询商品
	 * 
	 * @param proName
	 *            商品名称
	 * @return 商品对象
	 */
	public ProductVo findProductByName(String proName);

	/**
	 * 按商品小类id获得商品总数
	 * 
	 * @return 该小类下所有商品的数量
	 */
	public int getTotalNumber(int id);

	/**
	 * 按商品名称进行模糊查询并分页
	 * 
	 * @param name
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<ProductVo> findProductByLike(String name, int start, int limit);

	/**
	 * 取得按商品名称进行模糊查询所得到的记录总数，用于分页
	 * 
	 * @param name
	 * @return
	 */
	public int getTotalProductByLike(String name);

	/**
	 * 获得商品总数
	 * 
	 * @return
	 */
	public int getTotalNum();

	/**
	 * 获得推荐商品
	 * 
	 * @return
	 * 
	 */
	public List<ProductVo> getRecommenProduct();

	/**
	 * 获得新上架商品
	 * 
	 * @return
	 */
	public List<ProductVo> getNewProduct();
}
