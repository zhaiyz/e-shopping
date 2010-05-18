package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.CartDao;
import com.shopping.dao.OrderInfoDao;
import com.shopping.dao.ProductDao;
import com.shopping.dao.impl.CartDaoImpl;
import com.shopping.dao.impl.OrderInfoDaoImpl;
import com.shopping.dao.impl.ProductDaoImp;
import com.shopping.service.ProductService;
import com.shopping.vo.ProductVo;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao = new ProductDaoImp();
	private OrderInfoDao infoDao = new OrderInfoDaoImpl();
	private CartDao cartDao = new CartDaoImpl();

	public boolean addProduct(ProductVo product) {
		return productDao.addProduct(product);
	}

	public List<ProductVo> findAllProduct(int itemId, int start, int limit) {
		return productDao.findAllProduct(itemId, start, limit);
	}

	public List<ProductVo> findAllProduct(int start, int limit) {
		return productDao.findAllProduct(start, limit);
	}

	public ProductVo findProductById(int proId) {
		return productDao.findProductById(proId);
	}

	public ProductVo findProductByName(String proName) {
		return productDao.findProductByName(proName);
	}

	public boolean modifyProduct(ProductVo product) {
		return productDao.modifyProduct(product);
	}

	public boolean removeProductById(int proId) {
		// 先判断此商品是否被购买过，如果有，那么就不能删除，这种方式不好，但现在也只能这样了
		if (infoDao.findOrderInfoByProId(proId) || cartDao.findCartByProId(proId)) {
			return false;
		} else {
			return productDao.removeProductById(proId);
		}
	}

	public int getTotalNumber(int id) {
		return productDao.getTotalNumber(id);
	}

	public List<ProductVo> findProductByLike(String name, int start, int limit) {
		return productDao.findProductByLike(name, start, limit);
	}

	public int getTotalProductByLike(String name) {
		return productDao.getTotalProductByLike(name);
	}

	public int getTotalNum() {
		return productDao.getTotalNum();
	}

	public List<ProductVo> getNewProduct() {
		return productDao.getNewProduct();
	}

	public List<ProductVo> getRecommenProduct() {
		return productDao.getRecommenProduct();
	}

	public List<ProductVo> findAllProduct(int itemId, int flag, int start,
			int limit) {
		return productDao.findAllProduct(itemId, flag, start, limit);
	}

	public int getTotalNum(int itemId, int flag) {
		return productDao.getTotalNum(itemId, flag);
	}

	public List<ProductVo> findProductByLikeName(String name, int flag,
			int start, int limit) {
		return productDao.findProductByLikeName(name, flag, start, limit);
	}

	public int getTotalNum(String name, int flag) {
		return productDao.getTotalNum(name, flag);
	}

	public List<ProductVo> findAllProduct(int itemId) {
		return productDao.findAllProduct(itemId);
	}

}
