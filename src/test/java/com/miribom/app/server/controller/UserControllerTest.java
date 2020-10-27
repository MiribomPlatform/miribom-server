/*
 * @(#)UserControllerTest.java 2020. 10. 03
 */
package com.miribom.app.server.controller;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.miribom.app.server.bo.UserBo;
import com.miribom.app.server.controller.model.UserCheckIdResponse;
import com.miribom.app.server.controller.model.UserCreateRequest;
import com.miribom.app.server.model.User;

/**
 * @author changwoo.son
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@InjectMocks
	private UserController userController;

	@Mock
	private UserBo userBo;

	@Test
	public void create() {
		// given
		String userId = "userId";
		String userName = "userName";
		String mobile = "01012345678";
		String email = "example@email.com";
		UserCreateRequest userCreateRequest = new UserCreateRequest(userId, userName, mobile, email);
		User user = new User(userId, userName, mobile, email, LocalDateTime.now(), LocalDateTime.now());

		given(userBo.create(userId, userName, mobile, email))
				.willReturn(user);


		// when
		User result = userController.create(userCreateRequest);

		// then
		assertEquals(userId, result.getUserId());
		assertEquals(userName, result.getUserName());
		assertEquals(mobile, result.getMobile());
		assertEquals(email, result.getEmail());
		then(userBo).should()
				.create(userId, userName, mobile, email);
	}

	@Test
	public void checkId() {
		// given
		String userId = "userId";
		User user = new User();

		given(userBo.getUserByUserId(userId))
				.willReturn(user);

		// when
		UserCheckIdResponse result = userController.checkId(userId);

		// then
		assertEquals(userId, result.getUserId());
		assertTrue(result.isExist());
		then(userBo).should()
				.getUserByUserId(userId);
	}

	@Test
	public void get() {
		// given
		int userNo = 123;
		User user = new User();
		given(userBo.getUser(userNo))
				.willReturn(user);

		// when
		User result = userController.get(userNo);

		// then
		assertEquals(user, result);
		then(userBo).should()
				.getUser(userNo);
	}

}
