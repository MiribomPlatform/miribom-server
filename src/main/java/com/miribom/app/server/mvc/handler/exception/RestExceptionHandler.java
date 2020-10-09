/*
 * @(#)RestExceptionHandler.java 2020. 10. 08
 */
package com.miribom.app.server.mvc.handler.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.miribom.app.common.exception.ErrorCd;
import com.miribom.app.common.exception.ServiceException;
import com.miribom.app.common.util.ServletUtil;

/**
 * @author changwoo.son
 */
@RestControllerAdvice
public class RestExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 에러 발생 시 응답 body
	 * @author changwoo.son
	 */
	public static class Response {
		private String code;
		private String message;

		public Response(String code, String message) {
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	}

	/**
	 * serviceException
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ServiceException.class})
	public ResponseEntity<Response> serviceException(HttpServletRequest request, ServiceException exception) {
		ErrorCd errorCd = ErrorCd.INTERNAL_SERVER_ERROR;
		try {
			errorCd = ErrorCd.valueOf(exception.getErrCode());
		} catch (IllegalArgumentException e) {
			logger.warn("invalid serviceException errCode:{}", exception.getErrCode());
		}

		return handleException(request, exception, errorCd, exception.getErrMessage());
	}
	/**
	 * spring - request exception
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({MissingServletRequestParameterException.class, ServletRequestBindingException.class, MissingServletRequestPartException.class, ConversionNotSupportedException.class, TypeMismatchException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class, BindException.class})
	public ResponseEntity<Response> parameterException(HttpServletRequest request, Exception exception) {
		String errorMessage = ErrorCd.INVALID_PARAMETER.getMessage();

		// 오류가 발생한 첫번째 파라미터를 에러메시지 끝에 추가함.
		String paramName = getParamNameFormSpringRequestException(exception);
		if (StringUtils.isNotEmpty(paramName)) {
			errorMessage += " : " + paramName;
		}

		return handleException(request, exception, ErrorCd.INVALID_PARAMETER, errorMessage);
	}

	/**
	 * spring request exception으로부터 오류가 발생한 첫번째 파라미터명 추출
	 * @param exception
	 * @return
	 */
	private String getParamNameFormSpringRequestException(Exception exception) {
		if (exception instanceof MissingServletRequestParameterException) {
			return ((MissingServletRequestParameterException)exception).getParameterName();
		} else if (exception instanceof MissingServletRequestPartException) {
			return ((MissingServletRequestPartException)exception).getRequestPartName();
		} else if (exception instanceof MethodArgumentNotValidException) {
			BindingResult bindingResult = ((MethodArgumentNotValidException)exception).getBindingResult();
			if (bindingResult.hasFieldErrors()) {
				return bindingResult.getFieldError().getField();
			}
		} else if (exception instanceof MethodArgumentTypeMismatchException) {
			return ((MethodArgumentTypeMismatchException)exception).getName();
		} else if (exception instanceof MethodArgumentConversionNotSupportedException) {
			return ((MethodArgumentConversionNotSupportedException)exception).getName();
		} else if (exception instanceof BindException) {
			BindException bindException = ((BindException)exception);
			// 오류가 발생한 첫번째 파라미터명만 리턴
			if (bindException.hasFieldErrors()) {
				return bindException.getFieldError().getField();
			}
		}

		return null;
	}

	/**
	 * not acceptable media type
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
	public ResponseEntity<Response> mediaTypeNotAcceptableException(HttpServletRequest request, Exception exception) {
		return handleException(request, exception, ErrorCd.NOT_ACCEPTABLE);
	}


	/**
	 * unsupported media type
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({HttpMediaTypeNotSupportedException.class})
	public ResponseEntity<Response> mediaTypeNotSupportedException(HttpServletRequest request, Exception exception) {
		return handleException(request, exception, ErrorCd.UNSUPPORTED_MEDIA_TYPE);
	}
	/**
	 * 모든 Exception 체크 후, 그 외 예외 처리
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Response> generalException(HttpServletRequest request, Exception exception) {
		return handleException(request, exception, ErrorCd.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 에러 Response 메시지 생성
	 * @param request
	 * @param exception
	 * @param errorCd
	 * @param errorMessage
	 * @return
	 */
	private ResponseEntity<Response> handleException(HttpServletRequest request, Exception exception, ErrorCd errorCd, String errorMessage) {
		logger.error("{} {}, parameter:{}", request.getMethod(), request.getRequestURI(), ServletUtil.paramsToStr(request), exception);

		Response response = new Response(errorCd.name(), StringUtils.defaultString(errorMessage, errorCd.getMessage()));
		return new ResponseEntity<>(response, errorCd.getHttpStatus());
	}

	private ResponseEntity<Response> handleException(HttpServletRequest request, Exception exception, ErrorCd errorCd) {
		return handleException(request, exception, errorCd, null);
	}
}
