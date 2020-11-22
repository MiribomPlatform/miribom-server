/*
 * @(#)UserBo.java 2020. 10. 03
 */
package com.miribom.app.server.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miribom.app.common.exception.ErrorCd;
import com.miribom.app.server.dao.userdb.UserDao;
import com.miribom.app.server.model.User;

/**
 * 사용자 관련 Bo
 * @author changwoo.son
 */
@Service
public class UserBo {
	private static final Logger logger = LoggerFactory.getLogger(UserBo.class);

	@Autowired
	private UserDao userDao;

	/**
	 * 사용자 생성
	 * @param userId 사용자 ID
	 * @param userName 사용자 이름
	 * @param mobile 전화번호
	 * @param email 이메일
	 * @return 생성된 사용자에 대한 User 오브젝트
	 */
	public User create(String userId, String userName, String mobile, String email) {
		if (checkUserIdDuplicated(userId)) {
			throw ErrorCd.CONFLICT.serviceException("userId already exists. userId: {}", userId);
		}

		User user = new User(userId, userName, mobile, email);

		// 사용자 추가
		userDao.insert(user);
		logger.debug("user created - user:{}", user);

		return user;
	}

	/**
	 * 사용자 ID 중복 체크
	 * @param userId 사용자 ID
	 * @return 이미 존재하는 ID일 경우 true, 존재하지 않으면 false
	 */
	private boolean checkUserIdDuplicated(String userId) {
		return getUserByUserId(userId) != null;
	}

	/**
	 * 사용자 ID를 통해 User 정보 가져오기
	 * @param userId 사용자 ID
	 * @return User 오브젝트
	 */
	public User getUserByUserId(String userId) {
		return userDao.selectByUserId(userId);
	}


	/**
	 * 사용자 No를 통해 User 정보 가져오기
	 * @param userNo 사용자 No
	 * @return
	 */
	public User getUser(int userNo) {
		// 사용자 조회
		User user = userDao.select(userNo);

		if (user == null) {
			throw ErrorCd.NOT_FOUND.serviceException("not exist user - userNo:{}", userNo);
		}

		return user;
	}
}
