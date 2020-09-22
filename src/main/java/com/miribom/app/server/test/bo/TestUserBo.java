/*
 * @(#)TestUserBo.java 2020. 09. 22
 *
 */
package com.miribom.app.server.test.bo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miribom.app.server.test.dao.testdb.TestUserDao;
import com.miribom.app.server.test.model.TestUser;

/**
 * TestUser Bo
 * @author changwoo.son
 */
@Service
public class TestUserBo {
	@Autowired
	private TestUserDao testUserDao;

	/**
	 * TestUser 생성
	 * @param userId
	 * @param userName
	 * @param mobile
	 * @param email
	 * @return
	 */
	public TestUser create(String userId, String userName, String mobile, String email) {
		TestUser testUser = new TestUser();
		testUser.setUserId(userId);
		testUser.setUserName(userName);
		testUser.setMobile(mobile);
		testUser.setEmail(email);
		testUser.setRegYmdt(LocalDateTime.now());
		testUser.setUpdateYmdt(LocalDateTime.now());
		int seqNo = testUserDao.insert(testUser);
		testUser.setSeqNo(seqNo);

		return testUser;
	}

}
