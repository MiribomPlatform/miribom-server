/*
 * @(#)RestaurantType.java 2020. 11. 17
 *
 */
package com.miribom.app.server.model.type;

/**
 * @author changwoo.son
 */
public enum RestaurantType {
	KOREAN(0),
	JAPANESE(1),
	CHINESE(2),
	WESTERN(3),
	UNDEFINED(-1)
	;

	private int code;

	RestaurantType(int code) {
		this.code = code;
	}

	public static RestaurantType codeOf(int code) {
		for (RestaurantType type : values()) {
			if (type.code == code) {
				return type;
			}
		}

		return UNDEFINED;
	}

	public int getCode() {
		return code;
	}
}
