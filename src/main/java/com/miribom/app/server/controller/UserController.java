/*
 * @(#)UserController.java 2020. 10. 03
 */
package com.miribom.app.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miribom.app.server.controller.model.UserCreateRequest;

/**
 * @author changwoo.son
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

	@PostMapping("/new")
	public void create(@RequestBody UserCreateRequest req) {

	}

}
