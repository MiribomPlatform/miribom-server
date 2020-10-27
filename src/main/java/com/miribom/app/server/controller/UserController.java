/*
 * @(#)UserController.java 2020. 10. 03
 */
package com.miribom.app.server.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miribom.app.server.bo.UserBo;
import com.miribom.app.server.controller.model.UserCheckIdResponse;
import com.miribom.app.server.controller.model.UserCreateRequest;
import com.miribom.app.server.model.User;
import com.miribom.app.server.mvc.handler.exception.RestExceptionHandler;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 사용자 관련 Controller
 * @author changwoo.son
 */
@RestController
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

	@Autowired
	private UserBo userBo;

	/**
	 * 사용자 생성 API
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "사용자 생성")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "사용자 ID", required = true),
		@ApiImplicitParam(name = "userName", value = "이름", required = true),
		@ApiImplicitParam(name = "mobile", value = "전화번호", required = true),
		@ApiImplicitParam(name = "email", value = "이메일(형식 유지)")
	})
	@ApiResponses({
		@ApiResponse(code = 409, message = "CONFLICT", response = RestExceptionHandler.Response.class)
	})
	@PostMapping("/new")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody UserCreateRequest req) {
		return userBo.create(req.getUserId(), req.getUserName(), req.getMobile(), req.getEmail());
	}

	/**
	 * 사용자 Id 중복 체크 API
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "사용자 Id 중복 체크")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "사용자 ID", type = "path", required = true)
	})
	@GetMapping("/checkId")
	@ResponseStatus(HttpStatus.OK)
	public UserCheckIdResponse checkId(@RequestParam(value = "userId") String userId) {
		User user = userBo.getUserByUserId(userId);
		return new UserCheckIdResponse(userId, Objects.nonNull(user));
	}

	/**
	 * 사용자 정보 조회 API
	 * @param userNo
	 * @return
	 */
	@ApiOperation(value = "사용자 정보 조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userNo", value = "사용자 No", type = "path", required = true)
	})
	@ApiResponses({
		@ApiResponse(code = 404, message = "NOT FOUND", response = RestExceptionHandler.Response.class)
	})
	@GetMapping("/{userNo}")
	@ResponseStatus(HttpStatus.OK)
	public User get(@PathVariable(value = "userNo") int userNo) {
		return userBo.getUser(userNo);
	}
}
