package com.shopping.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.shopping.service.ItemService;
import com.shopping.service.impl.ItemServiceImpl;
import com.shopping.vo.ItemVo;

public class TestItemServiceImpl extends TestCase {

	private ItemService service = new ItemServiceImpl();

	@Test
	public void testAddItem() {
		ItemVo item = new ItemVo();

		int catId = 1;
		String itemName = "itemName";
		String itemDesc = "itemDesc";

		item.setCatId(catId);
		item.setItemName(itemName);
		item.setItemDesc(itemDesc);

		assertTrue(service.addItem(item));
	}

	@Test
	public void testFindAllItemIntIntInt() {
		int catId = 1;
		int start = 0;
		int limit = 2;

		List<ItemVo> list = new ArrayList<ItemVo>();

		list = service.findAllItem(catId, start, limit);

		assertEquals(2, list.size());
	}

	@Test
	public void testFindAllItemIntInt() {

		int start = 0;
		int limit = 2;

		List<ItemVo> list = new ArrayList<ItemVo>();

		list = service.findAllItem(start, limit);

		assertEquals(2, list.size());
	}

	@Test
	public void testFindById() {
		int id = 2;

		assertNotNull(service.findById(id));
	}

	@Test
	public void testModifyItem() {
		ItemVo item = new ItemVo();

		int itemId = 2;
		int catId = 1;
		String itemName = "itemName2";
		String itemDesc = "itemDesc2";

		item.setItemId(itemId);
		item.setCatId(catId);
		item.setItemName(itemName);
		item.setItemDesc(itemDesc);

		assertTrue(service.modifyItem(item));
	}

	@Test
	public void testRemoveItemById() {
		int id = 2;

		assertTrue(service.removeItemById(id));
	}

	@Test
	public void testFindItemByCategoryId() {
		List<ItemVo> list = new ArrayList<ItemVo>();
		list = service.findItemByCategoryId(1);

		assertNull(list);
	}

}
