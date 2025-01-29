package com.study.warmup.io.service;

import com.study.warmup.io.entity.Member;
import com.study.warmup.io.repository.MemberRepository;
import com.study.warmup.warmup.annotation.SkipInWarmup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @SkipInWarmup
    public Member save() {
        var member = new Member();
        member.setName("test");
        return memberRepository.save(member);
    }
}
