/*
 * @(#)TestController.java 2020. 09. 22
 */
package com.miribom.app.server.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miribom.app.common.exception.ErrorCd;

/**
 * @author changwoo.son
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

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
}
