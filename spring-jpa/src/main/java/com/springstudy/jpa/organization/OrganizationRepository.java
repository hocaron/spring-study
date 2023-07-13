package com.springstudy.jpa.organization;

import com.springstudy.jpa.member.Member;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
