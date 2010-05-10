package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.ItemDao;
import com.shopping.dao.ProductDao;
import com.shopping.dao.impl.ItemDaoImpl;
import com.shopping.dao.impl.ProductDaoImp;
import com.shopping.service.ItemService;
import com.shopping.vo.ItemVo;

public class ItemServiceImpl implements ItemService {

	private ItemDao itemDao = new ItemDaoImpl();
	private ProductDao proDao = new ProductDaoImp();

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

	public boolean removeItemById(int itemId) {
		if (proDao.findAllProduct(itemId, 0, 10).size() == 0) {
			// 如果此小类里面没有商品，那么就可以删除
			return itemDao.removeItemById(itemId);
		} else {
			// 如果此小类里面有商品，那么就不能删除，直接返回一个false
			return false;
		}
	}

	public List<ItemVo> findItemByCategoryId(int catId) {
		return itemDao.findItemByCategoryId(catId);
	}

	public int getTotalNum() {
		return itemDao.getTotalNum();
	}

	public int getTotalNum(String name) {
		return itemDao.getTotalNum(name);
	}

	public List<ItemVo> findItemByLike(String name, int start, int limit) {
		return itemDao.findItemByLike(name, start, limit);
	}

	public int getTotalNum(int id) {
		return itemDao.getTotalNum(id);
	}

	public List<ItemVo> findAllItem() {
		return itemDao.findAllItem();
	}

}
