package com.spring.boilerplate.api.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boilerplate.core.entity.user.User;
import com.spring.boilerplate.core.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	public User register() {
		return userService.register("test1@email.com");
	}

}
