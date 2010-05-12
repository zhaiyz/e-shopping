package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.CartDao;
import com.shopping.dao.impl.CartDaoImpl;
import com.shopping.service.CartService;
import com.shopping.vo.CartVo;
import com.shopping.vo.ProductVo;

public class CartServiceImpl implements CartService {

	private CartDao dao = new CartDaoImpl();

	public boolean addCart(CartVo cart) {
		if (dao.findProIdByUserIdAndProId(cart.getUserId(), cart.getProId())) {
			CartVo c = new CartVo();
			c = dao.findCartByUserIdAndProId(cart.getUserId(), cart.getProId());
			c.setProAmount(c.getProAmount() + cart.getProAmount());
			return dao.modifyCart(c);
		} else {
			return dao.addCart(cart);
		}
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

	public ProductVo findProductByCatId(int id) {
		return dao.findProductByCatId(id);
	}

	public boolean findCartByProId(int id) {
		return dao.findCartByProId(id);
	}

}
