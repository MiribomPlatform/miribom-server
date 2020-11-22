/*
 * @(#)ErrorCd.java 2020. 09. 22
 */
package com.miribom.app.common.exception;

import org.springframework.http.HttpStatus;

import com.miribom.app.common.util.StringUtil;

/**
 * @author changwoo.son
 */
public enum ErrorCd {
	// Standard Http Status
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "bad request"),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "unauthorized"),
	FORBIDDEN(HttpStatus.FORBIDDEN, "forbidden"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "not found"),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "method not allowed"),
	NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "not acceptable"),
	CONFLICT(HttpStatus.CONFLICT, "confict"),
	UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "unsupported media type"),
	TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "too many requests"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error"),
	SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "service unavailable"),

	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "invalid parameter"),
	EXIST_RESTAURANT_NAME(HttpStatus.BAD_REQUEST, "exist restaurant name"), // 이미 존재하는 레스토랑 이름
	MAX_RESTAURANT_COUNT(HttpStatus.BAD_REQUEST, "max restaurant count") // 최대 레스토랑 생성 개수 초과 시
	;

	private HttpStatus httpStatus;
	private String message;

	ErrorCd(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public ServiceException serviceException() {
		return new ServiceException(this.name(), message);
	}

	public ServiceException serviceException(Throwable cause) {
		return new ServiceException(this.name(), message, cause);
	}

	public ServiceException serviceException(String debugMessage, Object... debugMessageArgs) {
		return new ServiceException(this.name(), message, StringUtil.format(debugMessage, debugMessageArgs));
	}

}
