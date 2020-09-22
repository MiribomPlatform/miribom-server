/*
 * @(#)TestdbDataSourceConfig.java 2020. 09. 22
 *
 */
package com.miribom.app.server.test.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changwoo.son
 */
@Configuration
public class TestdbDataSourceConfig {
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public SqlSessionTemplate testdbSqlSessionTemplate(@Qualifier("testdbSqlSessionFactory") SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
	}

	@Bean
	public SqlSessionFactoryBean testdbSqlSessionFactory(@Qualifier("testdbDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setFailFast(true);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config-testdb.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mapper/testdb/**/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.miribom.app.server.test.model");
		sqlSessionFactoryBean.setTypeHandlersPackage("com.miribom.app.server.test.model.type.handler");
		return sqlSessionFactoryBean;
	}

	@Bean
	@ConfigurationProperties("datasource.testdb")
	public DataSource testdbDataSource() {
		return DataSourceBuilder.create().build();
	}

}
