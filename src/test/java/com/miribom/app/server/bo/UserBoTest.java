/*
 * @(#)UserBoTest.java 2020. 10. 03
 */
package com.miribom.app.server.bo;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.miribom.app.server.dao.userdb.UserDao;
import com.miribom.app.server.model.User;

/**
 * @author changwoo.son
 */
@RunWith(MockitoJUnitRunner.class)
public class UserBoTest {
	@InjectMocks
	private UserBo userBo;

	@Mock
	private UserDao userDao;

	@Test
	public void create() {
		// given
		String userId = "userId";
		String userName = "userName";
		String mobile = "01012345678";
		String email = "example@email.com";

		// when
		User user = userBo.create(userId, userName, mobile, email);

		// then
		assertEquals(userId, user.getUserId());
		assertEquals(userName, user.getUserName());
		assertEquals(mobile, user.getMobile());
		assertEquals(email, user.getEmail());
		then(userDao).should()
				.insert(any(User.class));
	}
}
