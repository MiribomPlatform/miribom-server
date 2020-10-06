/*
 * @(#)NameSpace.java 2020. 09. 22
 *
 */
package com.miribom.app.server.dao;

import org.apache.commons.lang3.StringUtils;

/**
 * @author changwoo.son
 */
public enum NameSpace {
	USERDB("mybatis.mapper.userdb");

	private String prefix;

	private NameSpace(String prefix) {
		this.prefix = prefix;
	}

	public String prefix() {
		return this.prefix;
	}

	public String statement(String... ids) {
		StringBuilder sb = new StringBuilder(prefix);
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				if (StringUtils.isNotBlank(id)) {
					sb.append(".");
					sb.append(id);
				}
			}
		}

		return sb.toString();
	}
}
