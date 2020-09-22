/*
 * @(#)TestUserDao.java 2020. 09. 22
 *
 */
package com.miribom.app.server.test.dao.testdb;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miribom.app.server.test.dao.NameSpace;
import com.miribom.app.server.test.model.TestUser;

/**
 * @author changwoo.son
 */
@Repository
public class TestUserDao {
	@Autowired
	private SqlSessionTemplate testdbSqlSessionTemplate;

	public int insert(TestUser testUser) {
		return testdbSqlSessionTemplate.insert(NameSpace.TESTDB.statement("testUser.insert"), testUser);
	}
}
