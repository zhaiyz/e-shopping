package com.shopping.vo;

import java.util.Date;

public class CardVo {

	/** 充值卡ID */
	private int cardId;

	/** 充值卡卡号 */
	private String cardNo;

	/** 充值卡密码 */
	private String cardPassword;

	/** 充值卡面值 */
	private float cardValue;

	/** 卡片加入时间 */
	private Date cardDateTime;

	/** 充值卡状态，0为未使用，1为已使用 */
	private int cardFlag;

	public Date getCardDateTime() {
		return cardDateTime;
	}

	public void setCardDateTime(Date cardDateTime) {
		this.cardDateTime = cardDateTime;
	}

	public int getCardFlag() {
		return cardFlag;
	}

	public void setCardFlag(int cardFlag) {
		this.cardFlag = cardFlag;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardPassword() {
		return cardPassword;
	}

	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}

	public float getCardValue() {
		return cardValue;
	}

	public void setCardValue(float cardValue) {
		this.cardValue = cardValue;
	}
}
