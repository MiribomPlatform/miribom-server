/*
 * @(#)RestaurantBo.java $version 2020/10/09
 *
 */
package com.miribom.app.server.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miribom.app.common.exception.ErrorCd;
import com.miribom.app.server.dao.userdb.RestaurantDao;
import com.miribom.app.server.dao.userdb.UserRestaurantInfoDao;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;
import com.miribom.app.server.model.type.RestaurantType;
import com.miribom.app.server.model.type.UserPosition;

/**
 * Restaurant Bo
 * @author jasonyang. changwoo.son
 */
@Service
public class RestaurantBo {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantBo.class);
    private static final int MAX_RESTAURANT_CREATION_COUNT = 3;

    @Autowired
    private RestaurantDao restaurantDao;
    @Autowired
    private UserRestaurantInfoDao userRestaurantInfoDao;

    /**
     * 레스토랑 생성
     * @param ownerNo 오너 no (레스토랑 생성 사용자 no)
     * @param restaurantName 레스토랑 이름
     * @param address 주소
     * @param mobile 전화번호
     * @param restaurantType 레스토랑 타입
     * @param image 레스토랑 대표 이미지
     * @param welcomeMessage 환영메시지
     * @return
     */
    public Restaurant create(int ownerNo, String restaurantName, String address, String mobile, RestaurantType restaurantType, String image, String welcomeMessage) {
        // 사용자에 대한 restaurant list 조회
        List<SimpleRestaurantInfo> simpleRestaurantInfos = restaurantDao.selectByOwnerId(ownerNo);

        // 레스토랑 이름 중복 체크
        if (simpleRestaurantInfos.stream().map(SimpleRestaurantInfo::getRestaurantName).anyMatch(name -> name.equals(restaurantName))) {
            throw ErrorCd.EXIST_RESTAURANT_NAME.serviceException("exist restaurant name - ownerNo:{}, restaurantName:{}", ownerNo, restaurantName);
        }

        // userNo가 소유한 레스토랑 개수 검즘
        if (simpleRestaurantInfos.size() >= MAX_RESTAURANT_CREATION_COUNT) {
            throw ErrorCd.MAX_RESTAURANT_COUNT.serviceException("max restaurant creation count - ownerNo:{}, number of restaurant: {}", ownerNo, simpleRestaurantInfos.size());
        }

        Restaurant restaurant = new Restaurant(restaurantName, address, mobile, restaurantType, image, welcomeMessage);

        // 레스토랑 등록
        restaurantDao.insert(restaurant);
        logger.debug("restaurant created - restaurant:{}", restaurant);

        // 점주 레스토랑 관계 등록
        userRestaurantInfoDao.insert(ownerNo, restaurant.getRestaurantNo(), ownerNo, UserPosition.OWNER);

        return restaurant;
    }

    /**
     * 레스토랑에 대한 간단한 정보 list 조회
     * @param userNo 사용자 no
     * @return
     */
    public List<SimpleRestaurantInfo> getSimpleRestaurantList(int userNo) {
        return restaurantDao.selectByUserNo(userNo);
    }
}
