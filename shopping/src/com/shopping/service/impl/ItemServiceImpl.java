package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.ItemDao;
import com.shopping.dao.impl.ItemDaoImpl;
import com.shopping.service.ItemService;
import com.shopping.vo.ItemVo;

public class ItemServiceImpl implements ItemService {

	private ItemDao itemDao = new ItemDaoImpl();

	public boolean addItem(ItemVo item) {
		return itemDao.addItem(item);
	}

	public List<ItemVo> findAllItem(int categoryId, int start, int limit) {
		return itemDao.findAllItem(categoryId, start, limit);
	}

	public List<ItemVo> findAllItem(int start, int limit) {
		return itemDao.findAllItem(start, limit);
	}

	public ItemVo findById(int itemId) {
		return itemDao.findById(itemId);
	}

	public boolean modifyItem(ItemVo item) {
		return itemDao.modifyItem(item);
	}

	public boolean removeItem(int itemId) {
		return itemDao.removeItem(itemId);
	}

}
