package com.springstudy.jpa.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberWithIdentityRepository extends JpaRepository<MemberWithIdentity, Long> {
    Optional<MemberWithIdentity> findByNickname(String nickname);
}
