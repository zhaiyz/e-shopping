package com.shopping.vo;


/**
 * @author Domili
 */
public class CategoryVo {

	/** 产品大类ID */
	private int catId;

	/** 产品大类名称 */
	private String catName;

	/** 产品大类描述 */
	private String catDesc;

	/** 产品大类创建时间 */
	private String catDatetime;

	public String getCatDatetime() {
		return catDatetime;
	}

	public void setCatDatetime(String catDatetime) {
		this.catDatetime = catDatetime;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
}
