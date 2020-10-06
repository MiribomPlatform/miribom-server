/*
 * @(#)UserDaoTest.java 2020. 10. 03
 */
package com.miribom.app.server.dao;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.spring.SqlSessionTemplate;

import com.miribom.app.server.dao.userdb.UserDao;
import com.miribom.app.server.model.User;

/**
 * @author changwoo.son
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
	@InjectMocks
	private UserDao userDao;

	@Mock
	private SqlSessionTemplate userdbSqlSessionTemplate;

	@Captor
	private ArgumentCaptor<User> userArgumentCaptor;

	@Test
	public void insert() {
		// given
		String userId = "userId";
		String userName = "userName";
		String mobile = "01012345678";
		String email = "example@email.com";

		User user = new User(userId, userName, mobile, email, LocalDateTime.now(), LocalDateTime.now());

		// when
		userDao.insert(user);

		// then
		then(userdbSqlSessionTemplate).should()
				.insert(eq(NameSpace.USERDB.statement("user.insert")), userArgumentCaptor.capture());
		assertEquals(userArgumentCaptor.getValue(), user);
	}
}
