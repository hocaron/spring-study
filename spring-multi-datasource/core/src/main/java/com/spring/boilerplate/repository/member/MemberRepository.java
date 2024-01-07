package com.spring.boilerplate.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boilerplate.entity.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
