/*
 * @(#)TestController.java 2020. 09. 22
 */
package com.miribom.app.server.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miribom.app.common.exception.ErrorCd;
import com.miribom.app.server.test.bo.TestUserBo;
import com.miribom.app.server.test.controller.model.TestUserCreateRequest;
import com.miribom.app.server.test.model.TestUser;

/**
 * Test Controller
 * @author changwoo.son
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private TestUserBo testUserBo;

	public class HelloResponse {
		private String name;

		public HelloResponse(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	@GetMapping("/hello")
	public ResponseEntity<HelloResponse> hello(@RequestParam String name) {
		return new ResponseEntity<>(new HelloResponse(name), HttpStatus.OK);
	}

	@GetMapping("/exception")
	public ResponseEntity<String> exception() {
		throw ErrorCd.INTERNAL_SERVER_ERROR.serviceException(String.format("exception test:%s", new Exception("hello")));
	}

	@PostMapping("/user/new")
	@ResponseStatus(HttpStatus.CREATED)
	public TestUser create(@RequestBody @Valid TestUserCreateRequest req) {
		return testUserBo.create(req.getUserId(), req.getUserName(), req.getMobile(), req.getEmail());
	}
}
