package com.spring.boilerplate.entity.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

	@Column(name = "member_id")
	private Long memberId;

	public Account(String account, Long memberId) {
		this.account = account;
		this.memberId = memberId;
	}

	public static Account of(String account, Long memberId) {
		return new Account(account, memberId);
	}

	public void updateAccount(String account) {
		this.account = account;
	}
}
