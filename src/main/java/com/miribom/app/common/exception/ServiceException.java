/*
 * @(#)ServiceException.java 2020. 09. 22
 */
package com.miribom.app.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @author changwoo.son
 */
public class ServiceException extends RuntimeException{

	private String errCode;
	private String errMessage;
	private String debugMessage;
	private Object data;

	public ServiceException() {

	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String errorCode, String errorMessage, String debugMessage) {
		super(getExceptionMessage(errorCode, errorMessage, debugMessage));
		this.errCode = errorCode;
		this.errMessage = errorMessage;
		this.debugMessage = debugMessage;
	}

	public ServiceException(String errorCode, String errorMessage, String debugMessage, Throwable cause) {
		super(getExceptionMessage(errorCode, errorMessage, debugMessage), cause);
		this.errCode = errorCode;
		this.errMessage = errorMessage;
		this.debugMessage = debugMessage;
	}

	public ServiceException(String errorCode, String errMessage) {
		super(getExceptionMessage(errorCode, errMessage, null));
		this.errCode = errorCode;
		this.errMessage = errMessage;
	}

	public ServiceException(String errorCode, String errMessage, Throwable cause) {
		super(getExceptionMessage(errorCode, errMessage, null), cause);
		this.errCode = errorCode;
		this.errMessage = errMessage;
	}

	private static String getExceptionMessage(String errorCode, String errMessage, String debugMessage) {
		StringBuilder sb = new StringBuilder();
		sb.append(errorCode);
		sb.append(":");
		sb.append(errMessage);

		if (StringUtils.isNotEmpty(debugMessage)) {
			sb.append(" - ");
			sb.append(debugMessage);
		}

		return sb.toString();
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public Object getData() {
		return data;
	}
}
