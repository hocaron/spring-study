package com.springstudy.jpa.member;

import com.springstudy.jpa.organization.Organization;
import com.springstudy.jpa.organization.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberTempService {
    private final MemberRepository memberRepository;
    private final TempMemberRepository tempMemberRepository;
    private final OrganizationRepository organizationRepository;

    @Transactional
    public TempMember temp() {
        TempMember temp = TempMember.of("test");
        tempMemberRepository.save(temp);
        return temp;
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

    @Transactional
    public Member updateMemberNickname(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(RuntimeException::new);
        String newNickname = "newNickname";
        member.nickname(newNickname);
        return member;
    }
}
