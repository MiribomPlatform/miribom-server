/*
 * @(#)RestaurantDaoTest.java $version 2020/10/27
 *
 */
package com.miribom.app.server.dao;

import com.miribom.app.server.dao.userdb.RestaurantDao;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.spring.SqlSessionTemplate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;

/**
 * @author jasonyang
 */
@RunWith(MockitoJUnitRunner.class)
public class RestaurantDaoTest {
    @InjectMocks
    private RestaurantDao restaurantDao;

    @Mock
    private SqlSessionTemplate userdbSqlSessionTemplate;

    @Captor
    private ArgumentCaptor<Restaurant> restaurantArgumentCaptor;

    @Test
    public void selectByUserId(){
        //given
        LocalDateTime currentTime = LocalDateTime.now();
        int userNo = 3;

        List<SimpleRestaurantInfo> list = Arrays.asList(
                new SimpleRestaurantInfo(1, "rest1", "Seoul"),
                new SimpleRestaurantInfo(2, "rest2", "Busan"),
                new SimpleRestaurantInfo(3, "rest3", "Daejeon")
        );

        doReturn(list).when(userdbSqlSessionTemplate).selectList(NameSpace.USERDB.statement("restaurant.selectByUserId"), userNo);

        //when
        List<SimpleRestaurantInfo> result = restaurantDao.selectByUserId(userNo);


        //then
        assertEquals(list, result);
        then(userdbSqlSessionTemplate).should()
                .selectList(NameSpace.USERDB.statement("restaurant.selectByUserId"), userNo);

    }
}
