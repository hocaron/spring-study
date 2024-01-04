package com.spring.boilerplate.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boilerplate.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
