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

import com.miribom.app.common.exception.ErrorCd;
import com.miribom.app.common.exception.ServiceException;
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
	public void create_exception_conflict() {
		// given
		String userId = "userId";
		String userName = "userName";
		String mobile = "01012345678";
		String email = "example@email.com";

		given(userDao.selectByUserId(userId))
				.willReturn(new User());

		// when
		String errorCd = null;
		try {
			userBo.create(userId, userName, mobile, email);
		} catch (ServiceException e) {
			errorCd = e.getErrCode();
		}

		// then
		assertEquals(ErrorCd.CONFLICT.name(), errorCd);

		then(userDao).should()
				.selectByUserId(userId);
		then(userDao).should(never())
				.insert(any(User.class));
	}

	@Test
	public void create() {
		// given
		String userId = "userId";
		String userName = "userName";
		String mobile = "01012345678";
		String email = "example@email.com";

		given(userDao.selectByUserId(userId))
				.willReturn(null);
		// when

		User user = userBo.create(userId, userName, mobile, email);

		// then
		assertEquals(userId, user.getUserId());
		assertEquals(userName, user.getUserName());
		assertEquals(mobile, user.getMobile());
		assertEquals(email, user.getEmail());

		then(userDao).should()
				.selectByUserId(userId);
		then(userDao).should()
				.insert(any(User.class));
	}

	@Test
	public void getUserByUserId() {
		// given
		String userId = "userId";
		User user = new User();
		given(userDao.selectByUserId(userId))
				.willReturn(user);

		// when
		User result = userBo.getUserByUserId(userId);

		// then
		assertEquals(user, result);

		then(userDao).should()
				.selectByUserId(userId);
	}

	@Test
	public void getUser_exception_notFound() {
		// given
		int userNo = 123;
		given(userDao.select(userNo))
				.willReturn(null);

		// when
		String errorCd = "";
		try {
			userBo.getUser(userNo);
		} catch (ServiceException e) {
			errorCd = e.getErrCode();
		}

		// then
		assertEquals(ErrorCd.NOT_FOUND.name(), errorCd);
	}

	@Test
	public void getUser() {
		// given
		int userNo = 123;
		User user = new User();
		given(userDao.select(userNo))
				.willReturn(user);

		// when
		User result = userBo.getUser(userNo);

		// then
		assertEquals(user, result);
		then(userDao).should()
				.select(userNo);
	}
}
