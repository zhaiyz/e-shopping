package com.shopping.dao;

import java.util.List;

import com.shopping.vo.CategoryVo;

/**
 * @author domili
 */
public interface CategoryDao {

	/**
	 * 添加一个商品大类
	 * 
	 * @param category
	 *            商品类CategoryVo的对象
	 * @return boolean类型，提示添加商品类别是否成功
	 */
	public boolean addCategory(CategoryVo category);

	/**
	 * 按照商品类名称查找商品大类
	 * 
	 * @param catName
	 *            商品类名称名称
	 * @return CategoryVo商品大类的对象
	 */
	public CategoryVo findCategoryByName(String catName);

	/**
	 * 按商品类别id查询
	 * 
	 * @param catId
	 *            商品类别ID
	 * @return CategoryVo 商品类别对象
	 */
	public CategoryVo findCategoryById(int catId);

	/**
	 * 分页查询所有商品类别
	 * 
	 * @param start
	 *            开始位置
	 * @param limit
	 *            分页中每页显示的数量
	 * @return List<CategoryVo> 查询到的商品类别集合
	 */
	public List<CategoryVo> findAllCategory(int start, int limit);

	/**
	 * 按商品类别的ID删除
	 * 
	 * @param catId
	 *            商品类别的ID
	 * @return 表示删除操作是否成功
	 */
	public boolean removeCategoryById(int catId);

	/**
	 * 修改商品大类的属性
	 * 
	 * @param category
	 *            商品大类的对象
	 * @return 返回boolean型，表示修改是否成功
	 */
	public boolean modifyCategory(CategoryVo category);
}
