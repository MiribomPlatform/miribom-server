/*
 * @(#)UserRestaurantInfoDao.java 2020. 11. 17
 *
 */
package com.miribom.app.server.dao.userdb;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miribom.app.server.dao.NameSpace;
import com.miribom.app.server.model.type.UserPosition;

/**
 * @author changwoo.son
 */
@Repository
public class UserRestaurantInfoDao {
	@Autowired
	private SqlSessionTemplate userdbSqlSessionTemplate;

	/**
	 * 점주 레스토랑 관계 등록
	 * @param userNo 사용자 no
	 * @param restaurantNo 레스토랑 no
	 * @param ownerNo 레스토랑 오너 no
	 * @param position 직책
	 */
	public void insert(int userNo, int restaurantNo, int ownerNo, UserPosition position) {
		Map<String, Object> params = new HashMap<>();
		params.put("userNo", userNo);
		params.put("restaurantNo", restaurantNo);
		params.put("ownerNo", ownerNo);
		params.put("position", position);
		params.put("regYmdt", LocalDateTime.now());
		params.put("updateYmdt", LocalDateTime.now());

		userdbSqlSessionTemplate.insert(NameSpace.USERDB.statement("userRestaurantInfo.insert"), params);
	}
}
