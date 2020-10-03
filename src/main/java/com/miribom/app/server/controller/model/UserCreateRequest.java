/*
 * @(#)UserCreateRequest.java 2020. 10. 03
 */
package com.miribom.app.server.controller.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.Nullable;

/**
 * 사용자 생성 Request model
 * @author changwoo.son
 */
public class UserCreateRequest {
	@NotEmpty
	private String userId;
	@NotEmpty
	private String userName;
	@NotEmpty
	private String mobile;
	@Nullable
	@Email
	private String email;

	public UserCreateRequest() {
	}

	public UserCreateRequest(String userId, String userName, String mobile, String email) {
		this.userId = userId;
		this.userName = userName;
		this.mobile = mobile;
		this.email = email;
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
}
