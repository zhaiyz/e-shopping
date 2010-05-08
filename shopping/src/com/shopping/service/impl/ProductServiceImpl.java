package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.ProductDao;
import com.shopping.dao.impl.ProductDaoImp;
import com.shopping.service.ProductService;
import com.shopping.vo.ProductVo;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao = new ProductDaoImp();

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
		return productDao.removeProductById(proId);
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

}
