/*
 * @(#)UserdbDataSourceConfig.java 2020. 10. 03
 *
 */
package com.miribom.app.server.config;

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
public class UserdbDataSourceConfig {
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public SqlSessionTemplate userdbSqlSessionTemplate(@Qualifier("userdbSqlSessionFactory") SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
	}

	@Bean
	public SqlSessionFactoryBean userdbSqlSessionFactory(@Qualifier("userdbDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setFailFast(true);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config-userdb.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mapper/userdb/**/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.miribom.app.server.model");
		sqlSessionFactoryBean.setTypeHandlersPackage("com.miribom.app.server.model.type.handler");
		return sqlSessionFactoryBean;
	}

	@Bean
	@ConfigurationProperties("datasource.userdb")
	public DataSource userdbDataSource() {
		return DataSourceBuilder.create().build();
	}

}
