package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.CategoryDao;
import com.shopping.dao.ItemDao;
import com.shopping.dao.impl.CategoryDaoImpl;
import com.shopping.dao.impl.ItemDaoImpl;
import com.shopping.service.CategoryService;
import com.shopping.vo.CategoryVo;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = new CategoryDaoImpl();
	private ItemDao itemDao = new ItemDaoImpl();

	public boolean addCategory(CategoryVo category) {
		return categoryDao.addCategory(category);
	}

	public List<CategoryVo> findAllCategory(int start, int limit) {
		return categoryDao.findAllCategory(start, limit);
	}

	public CategoryVo findCategoryById(int catId) {
		return categoryDao.findCategoryById(catId);
	}

	public CategoryVo findCategoryByName(String catName) {
		return categoryDao.findCategoryByName(catName);
	}

	public boolean modifyCategory(CategoryVo category) {
		return categoryDao.modifyCategory(category);
	}

	public boolean removeCategoryById(int catId) {
		if (itemDao.findItemByCategoryId(catId).size() == 0) {
			// 如果此大类下面没有小类，那么可以删除
			return categoryDao.removeCategoryById(catId);
		} else {
			// 如果此大类正面有小类，那么就不能删除
			return false;
		}
	}

	public List<CategoryVo> findAllCategory() {
		return categoryDao.findAllCategory();
	}

	public List<CategoryVo> findCategoryByLike(String key, int start, int limit) {
		return categoryDao.findCategoryByLike(key, start, limit);
	}

	public int getTotalNum() {
		return categoryDao.getTotalNum();
	}

	public int getTotalNumByLike(String key) {
		return categoryDao.getTotalNumByLike(key);
	}

}
