/*
 * @(#)ServletUtil.java 2020. 10. 08
 */
package com.miribom.app.common.util;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author changwoo.son
 */
public class ServletUtil {

	public static String paramsToStr(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();

		if (params == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		params.forEach((key, value) ->
			sb.append(key).append("=").append(Arrays.toString(value)).append(",")
		);
		sb.append("}");

		return sb.toString();
	}
}
