package com.spring.boilerplate.controller.user;

import com.spring.boilerplate.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boilerplate.entity.user.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	public User register(@RequestBody RegisterRequest request) {
		return userService.register(request.getEmail());
	}

}
