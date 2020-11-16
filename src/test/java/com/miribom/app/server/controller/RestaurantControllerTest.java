/*
 * @(#)RestaurantControllerTest.java $version 2020/10/27
 *
 */
package com.miribom.app.server.controller;

import com.miribom.app.server.bo.RestaurantBo;
import com.miribom.app.server.model.SimpleRestaurantInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * @author jasonyang
 */

@RunWith(MockitoJUnitRunner.class)
public class RestaurantControllerTest {
    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantBo restaurantBo;

    @Test
    public void listByUserNo() {
        //given
        int userNo = 3;
        List<SimpleRestaurantInfo> list = Arrays.asList();

        given(restaurantBo.getRestList(userNo))
                .willReturn(list);

        //when
        List<SimpleRestaurantInfo> result = restaurantController.listByUserNo(userNo);

        //then
        assertEquals(list, result);
        then(restaurantBo).should().getRestList(userNo);
    }

}
