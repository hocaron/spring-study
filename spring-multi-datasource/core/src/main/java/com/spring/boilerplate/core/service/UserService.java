package com.spring.boilerplate.core.service;

import org.springframework.stereotype.Service;

import com.spring.boilerplate.core.entity.user.User;
import com.spring.boilerplate.core.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final AccountService accountService;

	public User register(String email) {
		User user = userRepository.save(User.from(email));
		accountService.create(user);
		return user;
	}
}
