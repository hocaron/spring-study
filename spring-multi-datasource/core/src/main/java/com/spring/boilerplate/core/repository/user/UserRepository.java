package com.spring.boilerplate.core.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boilerplate.core.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
