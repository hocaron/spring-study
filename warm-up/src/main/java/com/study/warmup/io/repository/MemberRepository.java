package com.study.warmup.io.repository;

import com.study.warmup.io.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
