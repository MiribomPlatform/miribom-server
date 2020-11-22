package com.miribom.app.server.controller.model;

import com.miribom.app.server.model.type.RestaurantType;

/**
 * @author changwoo.son
 */
public class RestaurantCreateRequest {
	private String restaurantName;
	private String address;
	private String mobile;
	private RestaurantType restaurantType;
	private String image;
	private String welcomeMessage;

	public RestaurantCreateRequest() {
	}

	public RestaurantCreateRequest(String restaurantName, String address, String mobile, RestaurantType restaurantType, String image, String welcomeMessage) {
		this.restaurantName = restaurantName;
		this.address = address;
		this.mobile = mobile;
		this.restaurantType = restaurantType;
		this.image = image;
		this.welcomeMessage = welcomeMessage;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public String getMobile() {
		return mobile;
	}

	public RestaurantType getRestaurantType() {
		return restaurantType;
	}

	public String getImage() {
		return image;
	}

	public String getWelcomeMessage() {
		return welcomeMessage;
	}
}
