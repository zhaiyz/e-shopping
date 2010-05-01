/**
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.vo;

import java.util.Date;

/**
 * 管理员
 */
public class AdminVo {
    
	/** 管理员ID */
	private int adminId;
	
	/** 管理员登录名 */
	private String adminName;
	
	/** 管理员登录密码 */
	private String adminPassword;
	
	/**　管理员生成时间 */
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
