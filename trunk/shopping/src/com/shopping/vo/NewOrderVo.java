package com.shopping.vo;

/**
 * һ����MyOrderVo��Ӧ��ֵ����
 * 
 * @author wolf
 * 
 */
public class NewOrderVo {

	/** ����ID */
	private int orderId;

	/** ���������û������� */
	private String userName;

	/** �û���ϵ��ʽID */
	private int conId;

	/** ������ */
	private String orderNum;

	/** ��������ʱ�� */
	private String orderDatetime;

	/** ���ѷ�ʽ0:�ȸ���󷢻���1:�������� */
	private int payment;

	/** �ʵݷ�ʽ0:���;1ƽ�� */
	private int post;

	/** �ܼ۸� �����ʷ� */
	private float totalPrice;

	/** ����״̬ 0:δ������1���ѷ�����2�����ջ� */
	private int orderState;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
