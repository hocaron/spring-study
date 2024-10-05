package com.spring.boilerplate.entity.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", unique = true)
	private String email;

	public Member(String email) {
		this.email = email;
	}

	public static Member from(String email) {
		return new Member(email);
	}

	public void updateEmail(String email) {
		this.email = email;
	}
}
