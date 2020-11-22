/*
 * @(#)RestaurantBoTest.java $version 2020/10/27
 *
 */
package com.miribom.app.server.bo;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.miribom.app.common.exception.ErrorCd;
import com.miribom.app.common.exception.ServiceException;
import com.miribom.app.server.dao.userdb.RestaurantDao;
import com.miribom.app.server.dao.userdb.UserRestaurantInfoDao;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;
import com.miribom.app.server.model.type.RestaurantType;
import com.miribom.app.server.model.type.UserPosition;

/**
 * @author jasonyang, changwoo.son
 */
@RunWith(MockitoJUnitRunner.class)
public class RestaurantBoTest {
    @InjectMocks
    private RestaurantBo restaurantBo;

    @Mock
    private RestaurantDao restaurantDao;
    @Mock
    private UserRestaurantInfoDao userRestaurantInfoDao;

    @Test
    public void create() {
        // given
        int ownerNo = 1;
        String restaurantName = "name";
        String address = "address";
        String mobile = "mobile";
        RestaurantType restaurantType = RestaurantType.KOREAN;
        String image = "image";
        String welcomeMessage = "welcomeMessage";

        List<SimpleRestaurantInfo> simpleRestaurantInfos = Arrays.asList(new SimpleRestaurantInfo(1, "name1", "address1"), new SimpleRestaurantInfo(2, "name2", "address2"));
        given(restaurantDao.selectByOwnerId(ownerNo))
                .willReturn(simpleRestaurantInfos);

        Restaurant restaurant = new Restaurant(restaurantName, address, mobile, restaurantType, image, welcomeMessage);

        // when
        Restaurant result = restaurantBo.create(ownerNo, restaurantName, address, mobile, restaurantType, image, welcomeMessage);

        // then
        assertEquals(restaurant.getRestaurantName(), result.getRestaurantName());
        assertEquals(restaurant.getAddress(), result.getAddress());
        assertEquals(restaurant.getMobile(), result.getMobile());
        assertEquals(restaurant.getImage(), result.getImage());
        assertEquals(restaurant.getWelcomeMessage(), result.getWelcomeMessage());
        then(restaurantDao).should()
                .selectByOwnerId(ownerNo);
        then(restaurantDao).should()
                .insert(result);
        then(userRestaurantInfoDao).should()
                .insert(ownerNo, result.getRestaurantNo(), ownerNo, UserPosition.OWNER);
    }

    @Test
    public void create_exception_existRestaurantName() {
        // given
        int ownerNo = 1;
        String restaurantName = "duplicatedName";
        String address = "address";
        String mobile = "mobile";
        RestaurantType restaurantType = RestaurantType.KOREAN;
        String image = "image";
        String welcomeMessage = "welcomeMessage";

        List<SimpleRestaurantInfo> simpleRestaurantInfos = Arrays.asList(new SimpleRestaurantInfo(1, "duplicatedName", "address1"));
        given(restaurantDao.selectByOwnerId(ownerNo))
            .willReturn(simpleRestaurantInfos);

        // when
        String errorCd = "";
        try {
            restaurantBo.create(ownerNo, restaurantName, address, mobile, restaurantType, image, welcomeMessage);
        } catch (ServiceException e) {
            errorCd = e.getErrCode();
        }

        // then
        assertEquals(ErrorCd.EXIST_RESTAURANT_NAME.name(), errorCd);
        then(restaurantDao).should()
            .selectByOwnerId(ownerNo);
        then(restaurantDao).should(never())
            .insert(any(Restaurant.class));
        then(userRestaurantInfoDao).should(never())
            .insert(anyInt(), anyInt(), anyInt(), any(UserPosition.class));
    }

    @Test
    public void create_exception_maxRestaurantCreationCount() {
        // given
        int ownerNo = 1;
        String restaurantName = "duplicatedName";
        String address = "address";
        String mobile = "mobile";
        RestaurantType restaurantType = RestaurantType.KOREAN;
        String image = "image";
        String welcomeMessage = "welcomeMessage";

        List<SimpleRestaurantInfo> simpleRestaurantInfos = IntStream.rangeClosed(1, 3).boxed().map(val -> new SimpleRestaurantInfo(val, "name" + val, "")).collect(Collectors.toList());
        given(restaurantDao.selectByOwnerId(ownerNo))
            .willReturn(simpleRestaurantInfos);

        // when
        String errorCd = "";
        try {
            restaurantBo.create(ownerNo, restaurantName, address, mobile, restaurantType, image, welcomeMessage);
        } catch (ServiceException e) {
            errorCd = e.getErrCode();
        }

        // then
        assertEquals(ErrorCd.MAX_RESTAURANT_COUNT.name(), errorCd);
        then(restaurantDao).should()
            .selectByOwnerId(ownerNo);
        then(restaurantDao).should(never())
            .insert(any(Restaurant.class));
        then(userRestaurantInfoDao).should(never())
            .insert(anyInt(), anyInt(), anyInt(), any(UserPosition.class));
    }


    @Test
    public void getSimpleRestaurantList(){
        //given
        int userNo = 3;
        List<SimpleRestaurantInfo> list = Arrays.asList();
        given(restaurantDao.selectByUserNo(userNo))
                .willReturn(list);

        //when
        List<SimpleRestaurantInfo> result = restaurantBo.getSimpleRestaurantList(userNo);

        //then
        assertEquals(list, result);

        then(restaurantDao).should().selectByUserNo(userNo);
    }
}
