package com.springstudy.jpa.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springstudy.jpa.organization.Organization;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByNickname(String nickname);

    Member findByOrganization(Organization organization);

    Member findByOrganizationId(Long organizationId);
}
