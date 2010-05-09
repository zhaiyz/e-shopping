/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.vo;


public class MyOrderVo {

	/** 订单ID */
	private int orderId;

	/** 订单所属用户的ID */
	private int userId;

	/** 用户联系方式ID */
	private int conId;

	/** 订单号 */
	private String orderNum;

	/** 订单生成时间 */
	private String orderDatetime;

	/** 付费方式0:先付款后发货；1:货到付款 */
	private int payment;

	/** 邮递方式0:快递;1平邮 */
	private int post;

	/** 总价格 包括邮费 */
	private float totalPrice;

	/** 订单状态 0:未发货；1：已发货；2：已收货 */
	private int orderState;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getConId() {
		return conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	
	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
}
