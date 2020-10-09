/*
 * @(#)UserDao.java 2020. 10. 03
 *
 */
package com.miribom.app.server.dao.userdb;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miribom.app.server.dao.NameSpace;
import com.miribom.app.server.model.User;

/**
 * 사용자 Dao
 * @author changwoo.son
 */
@Repository
public class UserDao {
	@Autowired
	private SqlSessionTemplate userdbSqlSessionTemplate;

	/**
	 * 새로운 사용자 insert
	 * @param user 사용자 정보
	 * @return
	 */
	public void insert(User user) {
		userdbSqlSessionTemplate.insert(NameSpace.USERDB.statement("user.insert"), user);
	}

	/**
	 * 사용자 Id에 대한 User정보 조회
	 * @param userId 사용자 Id
	 * @return
	 */
	public User selectByUserId(String userId) {
		return userdbSqlSessionTemplate.selectOne(NameSpace.USERDB.statement("user.selectByUserId"), userId);
	}
}
