/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.vo;

import java.util.Date;

/**
 * ����Ա
 */
public class AdminVo {
    
	/** ����ԱID */
	private int adminId;
	
	/** ����Ա��¼�� */
	private String adminName;
	
	/** ����Ա��¼���� */
	private String adminPassword;
	
	/**������Ա����ʱ�� */
	private Date adminDatetime;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Date getAdminDatetime() {
		return adminDatetime;
	}

	public void setAdminDatetime(Date adminDatetime) {
		this.adminDatetime = adminDatetime;
	}
	
}
