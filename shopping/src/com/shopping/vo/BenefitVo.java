package com.shopping.vo;

public class BenefitVo {

	private int catId;
	private String catName;
	private float sales;
	private float benefit;

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public float getSales() {
		return sales;
	}

	public void setSales(float sales) {
		this.sales = sales;
	}

	public float getBenefit() {
		return benefit;
	}

	public void setBenefit(float benefit) {
		this.benefit = benefit;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}
}
