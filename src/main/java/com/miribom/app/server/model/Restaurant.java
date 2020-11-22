package com.miribom.app.server.model;

import java.time.LocalDateTime;

import com.miribom.app.server.model.type.RestaurantType;

/**
 * @author changwoo.son
 */
public class Restaurant {
    private int restaurantNo;
    private String restaurantName;
    private String address;
    private String mobile;
    private RestaurantType restaurantType;
    private String image;
    private String welcomeMessage;
    private LocalDateTime regYmdt;
    private LocalDateTime updateYmdt;
    private LocalDateTime deleteReservedYmdt;

    public Restaurant(){
    }

    public Restaurant(String restaurantName, String address, String mobile, RestaurantType restaurantType, String image, String welcomeMessage) {
        this.restaurantName = restaurantName;
        this.address = address;
        this.mobile = mobile;
        this.restaurantType = restaurantType;
        this.image = image;
        this.welcomeMessage = welcomeMessage;
        this.regYmdt = LocalDateTime.now();
        this.updateYmdt = LocalDateTime.now();
    }

    public Restaurant(int restaurantNo, String restaurantName, String address, String mobile, RestaurantType restaurantType, String image, String welcomeMessage, LocalDateTime regYmdt, LocalDateTime updateYmdt, LocalDateTime deleteReservedYmdt) {
        this.restaurantNo = restaurantNo;
        this.restaurantName = restaurantName;
        this.address = address;
        this.mobile = mobile;
        this.restaurantType = restaurantType;
        this.image = image;
        this.welcomeMessage = welcomeMessage;
        this.regYmdt = regYmdt;
        this.updateYmdt = updateYmdt;
        this.deleteReservedYmdt = deleteReservedYmdt;
    }

    public int getRestaurantNo() {
        return restaurantNo;
    }

    public void setRestaurantNo(int restaurantNo) {
        this.restaurantNo = restaurantNo;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
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
