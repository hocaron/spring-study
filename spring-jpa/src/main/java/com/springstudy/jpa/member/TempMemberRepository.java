package com.springstudy.jpa.member;

import com.springstudy.jpa.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TempMemberRepository extends JpaRepository<TempMember, Long> {

    @Modifying()
    @Query("update TempMember m set m.nickname = :nickname where m.id = :id")
    void updateNickname(Long id, String nickname);

    Optional<TempMember> findByNickname(String nickname);
}
