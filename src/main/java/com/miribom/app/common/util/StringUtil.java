/*
 * @(#)StringUtil.java 2020. 09. 22
 */
package com.miribom.app.common.util;

import org.slf4j.helpers.MessageFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author changwoo.son
 */
public class StringUtil {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 메시지 포맷 (message의 {}를 args로 치환)
	 * @param message
	 * @param args
	 * @return
	 */
	public static String format(String message, Object... args) {
		return MessageFormatter.arrayFormat(message, args).getMessage();
	}

	/**
	 * mapper를 이용해 object를 출력가능한 string으로 변환
	 * @param object
	 * @return
	 */
	public static String reflectionToString(Object object) {
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
