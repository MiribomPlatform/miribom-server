/*
 * @(#)User.java 2020. 10. 03
 */
package com.miribom.app.server.model;

import java.time.LocalDateTime;

/**
 * @author changwoo.son
 */
public class User {
	private int userNo;
	private String userId;
	private String userName;
	private String mobile;
	private String email;
	private String avatar;
	private LocalDateTime regYmdt;
	private LocalDateTime updateYmdt;
	private LocalDateTime deleteReservedYmdt;

	public User() {
	}

	public User(String userId, String userName, String mobile, String email, LocalDateTime regYmdt,
		LocalDateTime updateYmdt) {
		this.userId = userId;
		this.userName = userName;
		this.mobile = mobile;
		this.email = email;
		this.regYmdt = regYmdt;
		this.updateYmdt = updateYmdt;
	}

	public User(int userNo, String userId, String userName, String mobile, String email, String avatar,
		LocalDateTime regYmdt, LocalDateTime updateYmdt, LocalDateTime deleteReservedYmdt) {
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.mobile = mobile;
		this.email = email;
		this.avatar = avatar;
		this.regYmdt = regYmdt;
		this.updateYmdt = updateYmdt;
		this.deleteReservedYmdt = deleteReservedYmdt;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public LocalDateTime getRegYmdt() {
		return regYmdt;
	}

	public void setRegYmdt(LocalDateTime regYmdt) {
		this.regYmdt = regYmdt;
	}

	public LocalDateTime getUpdateYmdt() {
		return updateYmdt;
	}

	public void setUpdateYmdt(LocalDateTime updateYmdt) {
		this.updateYmdt = updateYmdt;
	}

	public LocalDateTime getDeleteReservedYmdt() {
		return deleteReservedYmdt;
	}

	public void setDeleteReservedYmdt(LocalDateTime deleteReservedYmdt) {
		this.deleteReservedYmdt = deleteReservedYmdt;
	}
}
