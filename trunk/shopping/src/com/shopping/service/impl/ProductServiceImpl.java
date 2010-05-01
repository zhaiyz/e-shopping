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

	public boolean removeProduct(int proId) {
		return productDao.removeProduct(proId);
	}

}
