package com.shopping.vo;

/**
 * @author 枫红叶落 商品小类Vo
 */
public class ItemVo {
	/** 商品小类ID */
	private int itemId;

	/** 商品大类ID */
	private int catId;

	/** 商品小类名称 */
	private String itemName;

	/** 商品小类描述 */
	private String itemDesc;

	/** 添加商品日期 */
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
