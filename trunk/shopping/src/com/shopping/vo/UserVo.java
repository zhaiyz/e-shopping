/**
 * User值对象
 */
package com.shopping.vo;

import java.util.Date;

public class UserVo {
	/** 用户ID */
	private int userId;
	/** 用户登录名 */
	private String userName;
	/** 用户登录密码 */
	private String userPassword;
	/** 用户性别 */
	private int gender;
	/** 用户等级 */
	private int grade;
	/** 账户余额 */
	private float balance;
	/** 密码提示 */
	private String prompt;
	/** 密码答案 */
	private String answer;
	/** 爱好 */
	private String favor;
	/** 消费额 */
	private float payed;
	/** 用户状态，0：可登录;1：不可登录 */
	private int userState;
	/** 注册时间 */
	private Date regDatetime;
	/** 用户邮箱 */
	private String email;
	/** 电话 */
	private String phone;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getFavor() {
		return favor;
	}

	public void setFavor(String favor) {
		this.favor = favor;
	}

	public float getPayed() {
		return payed;
	}

	public void setPayed(float payed) {
		this.payed = payed;
	}

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

	public Date getRegDatetime() {
		return regDatetime;
	}

	public void setRegDatetime(Date regDatetime) {
		this.regDatetime = regDatetime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
