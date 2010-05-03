package com.shopping.util;

import java.util.List;

public class PageModel {
	/** 数据总数 */
	private int total;
	
	/** 数据内容 */
	@SuppressWarnings("unchecked")
	private List datas;

	@SuppressWarnings("unchecked")
	public List getDatas() {
		return datas;
	}

	@SuppressWarnings("unchecked")
	public void setDatas(List datas) {
		this.datas = datas;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
