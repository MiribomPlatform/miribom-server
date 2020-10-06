/*
 * @(#)UserController.java 2020. 10. 03
 */
package com.miribom.app.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miribom.app.server.bo.UserBo;
import com.miribom.app.server.controller.model.UserCreateRequest;
import com.miribom.app.server.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 사용자 관련 Controller
 * @author changwoo.son
 */
@RestController
@RequestMapping(value = "/users")
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
		@ApiImplicitParam(name = "userId", value = "유저 ID", required = true),
		@ApiImplicitParam(name = "userName", value = "이름", required = true),
		@ApiImplicitParam(name = "mobile", value = "전화번호", required = true),
		@ApiImplicitParam(name = "email", value = "이메일(형식 유지)")
	})
	@PostMapping("/new")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody UserCreateRequest req) {
		return userBo.create(req.getUserId(), req.getUserName(), req.getMobile(), req.getEmail());
	}

}
