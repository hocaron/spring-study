package com.springstudy.jpa.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);

    List<Member> findAllByIdIn(List<Long> ids);

    List<Member> findAllByNicknameIn(List<String> names);
}
