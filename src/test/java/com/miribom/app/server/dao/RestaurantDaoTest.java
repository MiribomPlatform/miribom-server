/*
 * @(#)RestaurantDaoTest.java $version 2020/10/27
 *
 */
package com.miribom.app.server.dao;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.spring.SqlSessionTemplate;

import com.miribom.app.server.dao.userdb.RestaurantDao;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;

/**
 * @author jasonyang, changwoo.son
 */
@RunWith(MockitoJUnitRunner.class)
public class RestaurantDaoTest {
    @InjectMocks
    private RestaurantDao restaurantDao;

    @Mock
    private SqlSessionTemplate userdbSqlSessionTemplate;

    @Test
    public void insert() {
        // given
        Restaurant restaurant = mock(Restaurant.class);

        // when
        restaurantDao.insert(restaurant);

        // then
        then(userdbSqlSessionTemplate).should()
                .insert(NameSpace.USERDB.statement("restaurant.insert"), restaurant);
    }

    @Test
    public void selectByUserNo() {
        // given
        int userNo = 3;
        List<Object> simpleRestaurantInfos = Arrays.asList(
                new SimpleRestaurantInfo(1, "rest1", "Seoul"),
                new SimpleRestaurantInfo(2, "rest2", "Busan"),
                new SimpleRestaurantInfo(3, "rest3", "Daejeon")
        );

        given(userdbSqlSessionTemplate.selectList(NameSpace.USERDB.statement("restaurant.selectByUserNo"), userNo))
                .willReturn(simpleRestaurantInfos);

        // when
        List<SimpleRestaurantInfo> result = restaurantDao.selectByUserNo(userNo);

        // then
        assertEquals(simpleRestaurantInfos, result);
        then(userdbSqlSessionTemplate).should()
                .selectList(NameSpace.USERDB.statement("restaurant.selectByUserNo"), userNo);
    }

    @Test
    public void selectByOwnerNo() {
        // given
        int ownerNo = 3;
        List<Object> simpleRestaurantInfos = Arrays.asList(
            new SimpleRestaurantInfo(1, "rest1", "Seoul"),
            new SimpleRestaurantInfo(2, "rest2", "Busan"),
            new SimpleRestaurantInfo(3, "rest3", "Daejeon")
        );

        given(userdbSqlSessionTemplate.selectList(NameSpace.USERDB.statement("restaurant.selectByOwnerNo"), ownerNo))
                .willReturn(simpleRestaurantInfos);

        // when
        List<SimpleRestaurantInfo> result = restaurantDao.selectByOwnerId(ownerNo);

        // then
        assertEquals(simpleRestaurantInfos, result);
        then(userdbSqlSessionTemplate).should()
                .selectList(NameSpace.USERDB.statement("restaurant.selectByOwnerNo"), ownerNo);

    }
}
