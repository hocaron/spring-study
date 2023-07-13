package com.springstudy.jpa.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Member1Repository extends JpaRepository<Member, Long> {
    List<Member1> findAllByNickname(String nickname);

}
