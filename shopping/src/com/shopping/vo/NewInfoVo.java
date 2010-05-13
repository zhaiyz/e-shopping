package com.shopping.vo;

public class NewInfoVo {

	/** 订单详细表ID */
	private int infoId;

	/** 订单ID */
	private int orderId;

	/** 商品名称 */
	private String proName;

	/** 商品数量 */
	private int amount;

	/** 商品总价 */
	private float price;

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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}
}
