package com.springstudy.jpa.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springstudy.jpa.organization.Organization;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByNickname(String nickname);

    List<Member> findByOrganization(Organization organization);

    List<Member> findByOrganizationId(Long organizationId);
}
