/*
 * @(#)SimpleRestaurantInfo.java $version 2020/10/13
 *
 */
package com.miribom.app.server.model;

/**
 * @author jasonyang
 */
public class SimpleRestaurantInfo {
    private int restaurantNo;
    private String restaurantName;
    private String address;

    public int getRestaurantNo() {
        return restaurantNo;
    }

    public SimpleRestaurantInfo(int restaurantNo, String restaurantName, String address) {
        this.restaurantNo = restaurantNo;
        this.restaurantName = restaurantName;
        this.address = address;
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

    public SimpleRestaurantInfo() {
    }
}
