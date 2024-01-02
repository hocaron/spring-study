package com.spring.boilerplate.api.controller.account;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boilerplate.core.entity.account.Account;
import com.spring.boilerplate.core.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService accountService;

	@GetMapping
	public List<Account> getAll() {
		return accountService.getAll();
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable Long id) {
		return accountService.getById(id);
	}
}
