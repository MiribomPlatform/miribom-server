/*
 * @(#)RestaurantDao.java $version 2020/10/09
 *
 */
package com.miribom.app.server.dao.userdb;

import com.miribom.app.server.dao.NameSpace;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jasonyang
 */
@Repository
public class RestaurantDao {
    @Autowired
    private SqlSessionTemplate userdbSqlSessionTemplate;
    /**
     * 사용자 Id로 소유 식당 리스트 조회
     * @param userNo
     * @return
     */
    public List<SimpleRestaurantInfo> selectByUserId(int userNo){
        return userdbSqlSessionTemplate.selectList(NameSpace.USERDB.statement("restaurant.selectByUserId"), userNo);

    }

}
