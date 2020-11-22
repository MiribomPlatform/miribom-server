/*
 * @(#)UserRestaurantInfo.java 2020. 11. 17
 *
 */
package com.miribom.app.server.model;

import java.time.LocalDateTime;

import com.miribom.app.server.model.type.UserPosition;

/**
 * @author changwoo.son
 */
public class UserRestaurantInfo {
	private int userRestaurantInfoNo;
	private int userNo;
	private int restaurantNo;
	private int ownerNo;
	private UserPosition position;
	private LocalDateTime regYmdt;
	private LocalDateTime updateYmdt;
	private LocalDateTime deleteReservedYmdt;

	public UserRestaurantInfo() {
	}

	public UserRestaurantInfo(int userNo, int restaurantNo, int ownerNo, UserPosition position) {
		this.userNo = userNo;
		this.restaurantNo = restaurantNo;
		this.ownerNo = ownerNo;
		this.position = position;
		this.regYmdt = LocalDateTime.now();
		this.updateYmdt = LocalDateTime.now();
	}

	public UserRestaurantInfo(int userRestaurantInfoNo, int userNo, int restaurantNo, int ownerNo,
		UserPosition position, LocalDateTime regYmdt, LocalDateTime updateYmdt, LocalDateTime deleteReservedYmdt) {
		this.userRestaurantInfoNo = userRestaurantInfoNo;
		this.userNo = userNo;
		this.restaurantNo = restaurantNo;
		this.ownerNo = ownerNo;
		this.position = position;
		this.regYmdt = regYmdt;
		this.updateYmdt = updateYmdt;
		this.deleteReservedYmdt = deleteReservedYmdt;
	}

	public int getUserRestaurantInfoNo() {
		return userRestaurantInfoNo;
	}

	public void setUserRestaurantInfoNo(int userRestaurantInfoNo) {
		this.userRestaurantInfoNo = userRestaurantInfoNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getRestaurantNo() {
		return restaurantNo;
	}

	public void setRestaurantNo(int restaurantNo) {
		this.restaurantNo = restaurantNo;
	}

	public int getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(int ownerNo) {
		this.ownerNo = ownerNo;
	}

	public UserPosition getPosition() {
		return position;
	}

	public void setPosition(UserPosition position) {
		this.position = position;
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
