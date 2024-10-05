package com.springstudy.jpa.member;

import java.util.List;

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
    private final OrganizationRepository organizationRepository;

    public Member save(String nickname) {
        Member member1 = Member.of(nickname);
        Member member2 = Member.of(nickname);
        memberRepository.save(member1);
        memberRepository.save(member2);
        return memberRepository.save(member1);
    }

    @Transactional
    public Member saveAndFlush(String nickname) {
        Member member = Member.of(nickname);
        return memberRepository.saveAndFlush(member);
    }

    @Transactional
    public Member saveAndUpdate(String nickname) {
        Member member = Member.of(nickname);
        member = memberRepository.save(member);
        member.setNickname("testNickname2");
        return member;
    }

    @Transactional
    public Member saveAndFlushAndUpdate(String nickname) {
        Member member = Member.of(nickname);
        member = memberRepository.saveAndFlush(member);
        member.setNickname("testNickname2");
        return member;
    }

    @Transactional(readOnly = true)
    public List<Member> findByOrganization(Long organizationId) {
        Organization organization =
            organizationRepository.findById(organizationId).orElseThrow(RuntimeException::new);
        List<Member> members = memberRepository.findByOrganization(organization);
        return members;
    }

    @Transactional(readOnly = true)
    public List<Member> findByOrganizationId(Long organizationId) {
        List<Member> members = memberRepository.findByOrganizationId(organizationId);
        return members;
    }
}
