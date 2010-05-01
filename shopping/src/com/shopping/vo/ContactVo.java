/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.vo;

/**
 * 订单寄送地址信息
 */
public class ContactVo {
    
	/** 寄送地址ID */
	private int conId;
	
	/** 订单ID */
	private int orderId;
	
	/** 收货人姓名 */
	private String name;
	
	/** 邮编 */
	private String postcode;
	
	/** 收货地址 */
	private String address;
	
	/** 收货人电话 */
	private String telphone;
	
	/** 收货时间0:全周,1:周一至周五;2:周末 */
	private int freetime;

	public int getConId() {
		return conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public int getFreetime() {
		return freetime;
	}

	public void setFreetime(int freetime) {
		this.freetime = freetime;
	}
}
