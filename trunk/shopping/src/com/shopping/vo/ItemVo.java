package com.shopping.vo;

/**
 * @author ���Ҷ�� ��ƷС��Vo
 */
public class ItemVo {
	/** ��ƷС��ID */
	private int itemId;

	/** ��Ʒ����ID */
	private int catId;

	/** ��ƷС������ */
	private String itemName;

	/** ��ƷС������ */
	private String itemDesc;

	/** �����Ʒ���� */
	private String itemDatetime;

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getItemDatetime() {
		return itemDatetime;
	}

	public void setItemDatetime(String itemDatetime) {
		this.itemDatetime = itemDatetime;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
