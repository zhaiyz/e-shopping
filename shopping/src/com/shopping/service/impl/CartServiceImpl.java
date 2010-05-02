package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.CartDao;
import com.shopping.dao.impl.CartDaoImpl;
import com.shopping.service.CartService;
import com.shopping.vo.CartVo;

public class CartServiceImpl implements CartService {

	private CartDao dao = new CartDaoImpl();

	public boolean addCart(CartVo cart) {
		return dao.addCart(cart);
	}

	public List<CartVo> findAllCart(int start, int limit) {
		return dao.findAllCart(start, limit);
	}

	public CartVo findCartById(int id) {
		return dao.findCartById(id);
	}

	public List<CartVo> findCartByUserId(int id) {
		return dao.findCartByUserId(id);
	}

	public boolean modifyCart(CartVo cart) {
		return dao.modifyCart(cart);
	}

	public boolean removeByUserId(int id) {
		return dao.removeByUserId(id);
	}

	public boolean removeCartById(int id) {
		return dao.removeCartById(id);
	}

}
