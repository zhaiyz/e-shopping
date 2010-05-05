package com.shopping.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.shopping.service.ProductService;
import com.shopping.service.impl.ProductServiceImpl;
import com.shopping.vo.ProductVo;

public class TestProductServiceImpl extends TestCase {

	private ProductService service = new ProductServiceImpl();

//	@Test
//	public void testAddProduct() {
//		ProductVo product = new ProductVo();
//		int itemId = 1;
//		String proName = "商品六";
//		String proDesc = "商品五的描述";
//		String imageUrl = "5.jpg";
//
//		float purPrice = 35.0f;
//		float oriPrice = 65.0f;
//		float disPrice = 55.0f;
//
//		int stock = 100;
//		int sales = 20;
//
//		int recommendation = 0;
//
//		product.setItemId(itemId);
//		product.setProName(proName);
//		product.setImageUrl(imageUrl);
//		product.setProDesc(proDesc);
//		product.setPurPrice(purPrice);
//		product.setOriPrice(oriPrice);
//		product.setDisPrice(disPrice);
//
//		product.setStock(stock);
//		product.setSales(sales);
//		product.setRecommendation(recommendation);
//
//		assertTrue(service.addProduct(product));
//
//	}
//
//	@Test
//	public void testFindAllProductIntIntInt() {
//		int itemId = 1;
//		int start = 0;
//		int limit = 1;
//
//		assertNotNull(service.findAllProduct(itemId, start, limit));
//	}
//
//	@Test
//	public void testFindAllProductIntInt() {
//		int start = 0;
//		int limit = 2;
//		assertEquals(2, service.findAllProduct(start, limit).size());
//	}
//
//	@Test
//	public void testFindProductById() {
//		int id = 1;
//
//		assertNotNull(service.findProductById(id));
//	}
//
//	@Test
//	public void testFindProductByName() {
//		String name = "proName";
//
//		assertNotNull(service.findProductByName(name));
//	}
//
//	@Test
//	public void testModifyProduct() {
//		ProductVo product = new ProductVo();
//		int proId = 2;
//		int itemId = 1;
//		String proName = "proName2";
//		String proDesc = "proDesc2";
//		String imageUrl = "imageurl2";
//
//		float purPrice = 1020.1f;
//		float oriPrice = 1020.2f;
//		float disPrice = 1020.3f;
//
//		int stock = 1002;
//		int sales = 202;
//
//		int recommendation = 1;
//
//		product.setProId(proId);
//		product.setItemId(itemId);
//		product.setProName(proName);
//		product.setImageUrl(imageUrl);
//		product.setProDesc(proDesc);
//		product.setPurPrice(purPrice);
//		product.setOriPrice(oriPrice);
//		product.setDisPrice(disPrice);
//
//		product.setStock(stock);
//		product.setSales(sales);
//		product.setRecommendation(recommendation);
//
//		assertTrue(service.modifyProduct(product));
//	}
//
//	@Test
//	public void testRemoveProductById() {
//		int proId = 2;
//		
//		assertFalse(service.removeProductById(proId));
//	}
//	
	@Test
	public void testFindProductByLike() {
		String name = "一";
		
		List<ProductVo> list = new ArrayList<ProductVo>();
		
		list = service.findProductByLike(name, 0, 5);
		
		assertEquals(1,list.size());
		
	}
	
	@Test
	public void testGetTotalProductByLike() {
		String name = "商品";
		
		int total = service.getTotalProductByLike(name);
		
		assertEquals(1,total);
	}

}
