package com.shopping.vo;

import java.sql.Date;

public class CartVo {

	/** 主键 */
	private int cartId;

	/** 用户主键 */
	private int userId;

	/** 商品主键 */
	private int proId;

	/** 商品数量 */
	private int proAmount;

	/** 添加时间 */
	private Date addDatetime;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getProAmount() {
		return proAmount;
	}

	public void setProAmount(int proAmount) {
		this.proAmount = proAmount;
	}

	public Date getAddDatetime() {
		return addDatetime;
	}

	public void setAddDatetime(Date addDatetime) {
		this.addDatetime = addDatetime;
	}

}
