/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.vo;

/**
 * ������ϸ��Ϣ
 */
public class OrderInfoVo {

	/** ������ϸ��ID */
	private int infoId;

	/** ����ID */
	private int orderId;

	/** ��ƷID */
	private int proId;

	/** ��Ʒ���� */
	private int amount;

	/** ��Ʒ�ܼ� */
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
