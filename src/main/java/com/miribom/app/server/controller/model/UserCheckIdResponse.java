/*
 * @(#)UserCheckIdResponse.java 2020. 10. 08
 */
package com.miribom.app.server.controller.model;

/**
 * @author changwoo.son
 */
public class UserCheckIdResponse {
	private String userId;
	private boolean exist;

	public UserCheckIdResponse() {
	}

	public UserCheckIdResponse(String userId, boolean exist) {
		this.userId = userId;
		this.exist = exist;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}
}
