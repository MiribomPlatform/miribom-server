/*
 * @(#)RestaurantControllerTest.java $version 2020/10/27
 *
 */
package com.miribom.app.server.controller;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.miribom.app.server.bo.RestaurantBo;
import com.miribom.app.server.controller.model.RestaurantCreateRequest;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;
import com.miribom.app.server.model.type.RestaurantType;

/**
 * @author jasonyang, changwoo.son
 */

@RunWith(MockitoJUnitRunner.class)
public class RestaurantControllerTest {
    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantBo restaurantBo;

    @Test
    public void create() {
        // given
        int userNo = 1;
        RestaurantCreateRequest req = new RestaurantCreateRequest("restaurantName", "address", "mobile", RestaurantType.KOREAN, "image", "welcomeMessage");
        Restaurant restaurant = new Restaurant();
        given(restaurantBo.create(userNo, req.getRestaurantName(), req.getAddress(), req.getMobile(), req.getRestaurantType(), req.getImage(), req.getWelcomeMessage()))
                .willReturn(restaurant);

        // when
        Restaurant result = restaurantController.create(userNo, req);

        // then
        assertEquals(restaurant, result);
        then(restaurantBo).should()
                .create(userNo, req.getRestaurantName(), req.getAddress(), req.getMobile(), req.getRestaurantType(), req.getImage(), req.getWelcomeMessage());
    }

    @Test
    public void listByUserNo() {
        // given
        int userNo = 3;
        List<SimpleRestaurantInfo> list = Arrays.asList();

        given(restaurantBo.getSimpleRestaurantList(userNo))
                .willReturn(list);

        // when
        List<SimpleRestaurantInfo> result = restaurantController.listByUserNo(userNo);

        // then
        assertEquals(list, result);
        then(restaurantBo).should().getSimpleRestaurantList(userNo);
    }

}
