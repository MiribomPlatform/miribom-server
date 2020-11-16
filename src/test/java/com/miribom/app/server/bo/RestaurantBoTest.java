/*
 * @(#)RestaurantBoTest.java $version 2020/10/27
 *
 */
package com.miribom.app.server.bo;

import com.miribom.app.server.dao.userdb.RestaurantDao;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * @author jasonyang
 */
@RunWith(MockitoJUnitRunner.class)
public class RestaurantBoTest {
    @InjectMocks
    private RestaurantBo restaurantBo;

    @Mock
    private RestaurantDao restaurantDao;

    @Test
    public void getRestList_by_userNo(){
        //given
        int userNo = 3;
        List<SimpleRestaurantInfo> list = Arrays.asList();
        given(restaurantDao.selectByUserId(userNo))
                .willReturn(list);


        //when
        List<SimpleRestaurantInfo> result = restaurantBo.getRestList(userNo);

        //then
        assertEquals(list, result);

        then(restaurantDao).should().selectByUserId(userNo);
    }
}
