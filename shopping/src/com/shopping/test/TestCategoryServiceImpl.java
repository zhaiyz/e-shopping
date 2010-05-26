package com.shopping.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.shopping.service.CategoryService;
import com.shopping.service.impl.CategoryServiceImpl;
import com.shopping.vo.CategoryVo;

public class TestCategoryServiceImpl extends TestCase {

	private CategoryService service = new CategoryServiceImpl();

	@Test
	public void testAddCategory() {
		CategoryVo category = new CategoryVo();
		String catName = "首饰";
		String catDesc = "各种首饰";
		
		category.setCatName(catName);
		category.setCatDesc(catDesc);
		
		assertTrue(service.addCategory(category));
	}

	@Test
	public void testFindAllCategory() {
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		int start = 1;
		int limit = 5;
		list = service.findAllCategory(start, limit);
		assertNotNull(list);
	}

	@Test
	public void testFindCategoryById() {
		CategoryVo category = new CategoryVo();
		int id = 2;
		
		category = service.findCategoryById(id);
		
		assertNotNull(category);
		
	}

	@Test
	public void testFindCategoryByName() {
		CategoryVo category = new CategoryVo();
		String name = "category";
		
		category = service.findCategoryByName(name);
		
		assertNotNull(category);
	}

	@Test
	public void testModifyCategory() {
		CategoryVo category = new CategoryVo();
		int catId = 2;
		String catName = "category2";
		String catDesc = "catDesc2";
		
		category.setCatId(catId);
		category.setCatName(catName);
		category.setCatDesc(catDesc);
		
		assertTrue(service.modifyCategory(category));
	}

	@Test
	public void testRemoveCategoryById() {
		int id = 2;
		assertFalse(service.removeCategoryById(id));
	}

}
