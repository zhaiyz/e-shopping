package com.shopping.vo;

public class NewInfoVo {

	/** ������ϸ��ID */
	private int infoId;

	/** ����ID */
	private int orderId;

	/** ��Ʒ���� */
	private String proName;

	/** ��Ʒ���� */
	private int amount;

	/** ��Ʒ�ܼ� */
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
