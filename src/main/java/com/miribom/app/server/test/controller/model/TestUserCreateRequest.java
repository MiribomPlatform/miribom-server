/*
 * @(#)TestUserCreateRequest.java 2020. 09. 22
 *
 */
package com.miribom.app.server.test.controller.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author changwoo.son
 */
public class TestUserCreateRequest {
	@NotEmpty
	private String userId;
	@NotEmpty
	private String userName;
	private String mobile;
	@Email
	private String email;

	public TestUserCreateRequest() {
	}

	public TestUserCreateRequest(String userId, String userName, String mobile, String email) {
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
