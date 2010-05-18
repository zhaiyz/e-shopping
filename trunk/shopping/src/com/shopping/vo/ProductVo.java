package com.shopping.vo;

/**
 * @author Administrator 商品VO类
 */
public class ProductVo {

	/** 商品ID */
	private int proId;

	/** 商品所属小类ID */
	private int itemId;

	/** 商品名称 */
	private String proName;

	/** 商品图片地址 */
	private String imageUrl;

	/** 商品描述 */
	private String proDesc;

	/** 商品添加时间 */
	private String proDatetime;

	/** 商品进价 */
	private float purPrice;

	/** 商品原价 */
	private float oriPrice;

	/** 商品折扣价 */
	private float disPrice;

	/** 商品库存 */
	private int stock;

	/** 商品售出数量 */
	private int sales;

	/** 商品是否推荐 */
	private int recommendation;

	/** 是否显示 */
	private int productFlag;

	public int getProductFlag() {
		return productFlag;
	}

	public void setProductFlag(int productFlag) {
		this.productFlag = productFlag;
	}

	public float getDisPrice() {
		return disPrice;
	}

	public void setDisPrice(float disPrice) {
		this.disPrice = disPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public float getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(float oriPrice) {
		this.oriPrice = oriPrice;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public float getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(float purPrice) {
		this.purPrice = purPrice;
	}

	public int getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getProDatetime() {
		return proDatetime;
	}

	public void setProDatetime(String proDatetime) {
		this.proDatetime = proDatetime;
	}
}
