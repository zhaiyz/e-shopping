package com.shopping.util;

import java.util.List;

public class PageModel {
	/** �������� */
	private int total;
	
	/** �������� */
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
