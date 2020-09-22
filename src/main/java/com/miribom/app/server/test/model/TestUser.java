/*
 * @(#)TestUser.java 2020. 09. 22
 *
 */
package com.miribom.app.server.test.model;

import java.time.LocalDateTime;

/**
 * @author changwoo.son
 */
public class TestUser {
	private int seqNo;
	private String userId;
	private String userName;
	private String mobile;
	private String email;
	private LocalDateTime regYmdt;
	private LocalDateTime updateYmdt;

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
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
}
