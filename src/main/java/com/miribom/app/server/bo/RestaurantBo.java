/*
 * @(#)RestaurantBo.java $version 2020/10/09
 *
 */
package com.miribom.app.server.bo;

import com.miribom.app.server.dao.userdb.RestaurantDao;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Restaurant Bo
 * @author jasonyang
 */

@Service
public class RestaurantBo {

    private final static Logger logger = LoggerFactory.getLogger(UserBo.class);

    @Autowired
    private RestaurantDao restaurantDao;

    public List<SimpleRestaurantInfo> getRestList(int userNo) {

        return restaurantDao.selectByUserId(userNo);
    }
}
