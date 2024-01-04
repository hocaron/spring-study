package com.spring.boilerplate.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.spring.boilerplate.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account", unique = true)
	private String account;

	@Column(name = "user_id")
	private Long userId;

	public Account(String account, Long userId) {
		this.account = account;
		this.userId = userId;
	}

	public static Account of(String account, User user) {
		return new Account(account, user.getId());
	}

	public void updateAccount(String account) {
		this.account = account;
	}
}
