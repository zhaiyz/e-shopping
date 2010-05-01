package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.CategoryDao;
import com.shopping.dao.impl.CategoryDaoImpl;
import com.shopping.service.CategoryService;
import com.shopping.vo.CategoryVo;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = new CategoryDaoImpl();

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
		return categoryDao.removeCategoryById(catId);
	}

}
