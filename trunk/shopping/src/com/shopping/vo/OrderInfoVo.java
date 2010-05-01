/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.vo;

/**
 * 订单详细信息
 */
public class OrderInfoVo {

	/** 订单详细表ID */
	private int infoId;

	/** 订单ID */
	private int orderId;

	/** 商品ID */
	private int proId;

	/** 商品数量 */
	private int amount;

	/** 商品总价 */
	private float privce;

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getPrivce() {
		return privce;
	}

	public void setPrivce(float privce) {
		this.privce = privce;
	}
}
