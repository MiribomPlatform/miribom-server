/*
 * @(#)UserRestaurantInfoDaoTest.java 2020. 11. 22
 */
package com.miribom.app.server.dao;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.spring.SqlSessionTemplate;

import com.miribom.app.server.dao.userdb.UserRestaurantInfoDao;
import com.miribom.app.server.model.type.UserPosition;

/**
 * @author changwoo.son
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRestaurantInfoDaoTest {
	@InjectMocks
	private UserRestaurantInfoDao userRestaurantInfoDao;

	@Mock
	private SqlSessionTemplate userdbSqlSessionTemplate;

	@Captor
	private ArgumentCaptor<Map<String, Object>> mapArgumentCaptor;

	@Test
	public void insert() {
		// given
		int userNo = 1;
		int restaurantNo = 2;
		int ownerNo = 3;
		UserPosition position = UserPosition.OWNER;

		// when
		userRestaurantInfoDao.insert(userNo, restaurantNo, ownerNo, position);

		// then
		then(userdbSqlSessionTemplate).should()
				.insert(eq(NameSpace.USERDB.statement("userRestaurantInfo.insert")), mapArgumentCaptor.capture());
		Map<String, Object> params = mapArgumentCaptor.getValue();
		assertEquals(userNo, params.get("userNo"));
		assertEquals(restaurantNo, params.get("restaurantNo"));
		assertEquals(ownerNo, params.get("ownerNo"));
		assertEquals(position, params.get("position"));
		assertTrue(params.containsKey("regYmdt"));
		assertTrue(params.containsKey("updateYmdt"));
	}

}
