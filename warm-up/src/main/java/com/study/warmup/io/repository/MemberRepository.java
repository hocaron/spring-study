package com.study.warmup.io.repository;

import com.study.warmup.io.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByAddressZipCode(String zipCode);
}
