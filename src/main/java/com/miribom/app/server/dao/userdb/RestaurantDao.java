/*
 * @(#)RestaurantDao.java $version 2020/10/09
 *
 */
package com.miribom.app.server.dao.userdb;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miribom.app.server.dao.NameSpace;
import com.miribom.app.server.model.Restaurant;
import com.miribom.app.server.model.SimpleRestaurantInfo;

/**
 * @author jasonyang, changwoo.son
 */
@Repository
public class RestaurantDao {
	@Autowired
	private SqlSessionTemplate userdbSqlSessionTemplate;

	/**
	 * 레스토랑 등록
	 * @param restaurant 레스토랑
	 */
	public void insert(Restaurant restaurant) {
		userdbSqlSessionTemplate.insert(NameSpace.USERDB.statement("restaurant.insert"), restaurant);
	}

	/**
	 * 사용자 Id로 소유 식당 리스트 조회
	 * @param userNo 사용자 no
	 * @return
	 */
	public List<SimpleRestaurantInfo> selectByUserNo(int userNo) {
		return userdbSqlSessionTemplate.selectList(NameSpace.USERDB.statement("restaurant.selectByUserNo"), userNo);
	}

	/**
	 * 오너 Id로 소유 식당 리스트 조회
	 * @param ownerNo 오너 no
	 * @return
	 */
	public List<SimpleRestaurantInfo> selectByOwnerId(int ownerNo) {
		return userdbSqlSessionTemplate.selectList(NameSpace.USERDB.statement("restaurant.selectByOwnerNo"), ownerNo);
	}
}
