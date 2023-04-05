package com.springstudy.jpa.member;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springstudy.jpa.organization.Organization;
import com.springstudy.jpa.organization.OrganizationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final TempMemberRepository tempMemberRepository;
    private final OrganizationRepository organizationRepository;
    private final MemberTempService memberTempService;

    @Transactional
    public TempMember save(String nickname) {
        tempMemberRepository.deleteAll();
        try {
            TimeUnit.SECONDS.sleep(52);
        } catch (Exception e) {
        }
        System.out.println("통과");
        return new TempMember();
    }

    @Transactional
    public TempMember save2(String nickname) {
        tempMemberRepository.deleteAllById(List.of(1L));
        return new TempMember();
    }

    @Transactional
    public TempMember find(String nickname) {
        TempMember temp = tempMemberRepository.findById(1L).orElseThrow();
        return temp;
    }

    @Transactional
    public Member saveAndFlush(String nickname) {
        Member member = Member.of(nickname);
        return memberRepository.saveAndFlush(member);
    }

    @Transactional
    public Member saveAndUpdate(String nickname) {
        Member member = Member.of(nickname);
        memberRepository.save(member);
        return member;
    }

    @Transactional
    public Member saveAndFlushAndUpdate(String nickname) {
        Member member = Member.of(nickname);
        member = memberRepository.saveAndFlush(member);
        return member;
    }


    @Transactional
    public Member updateMemberNickname(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(RuntimeException::new);
        String newNickname = "newNickname";
        member.nickname(newNickname);
        return member;
    }
}
