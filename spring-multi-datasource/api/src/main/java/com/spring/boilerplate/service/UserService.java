package com.spring.boilerplate.service;

import com.spring.boilerplate.entity.user.User;
import com.spring.boilerplate.repository.user.UserRepository;
import com.spring.boilerplate.util.EntityManagerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final AccountService accountService;

	@Transactional("userTransactionManager")
	public User register(String email) {
		User user = userRepository.save(User.from(email));

		accountService.create(user);
		accountService.update(user.getId());
		return user;
	}

	@Transactional("userTransactionManager")
	public String getTrxName() {
		return EntityManagerUtils.getEntityManagerName();
	}
}
