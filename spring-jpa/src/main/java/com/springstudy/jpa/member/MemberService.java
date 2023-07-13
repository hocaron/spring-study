package com.springstudy.jpa.member;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.weaver.ast.Or;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springstudy.jpa.organization.Organization;
import com.springstudy.jpa.organization.OrganizationRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TempMemberRepository tempMemberRepository;
    private final OrganizationRepository organizationRepository;
    private final MemberTempService memberTempService;

    @Transactional
    public void test_NONE_PK로_조회() {

        for (int i = 0; i < 20; i++) {
                Member m = memberRepository.findByNickname(i + " new nickname").get();
                m.update(i + " nickname");
        }
    }

    @Transactional
    @Async
    public void test_PK로_조회() {
        //given
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Member m = memberRepository.findById((long) (i + 1)).get();
            m.update(i + " new nickname");
            members.add(m);
        }
        memberRepository.saveAll(members);
    }

    public void test1() {
        //given
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            members.add(Member.of(i + " nickname"));
            System.out.println(i);
        }

        memberRepository.saveAll(members);
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
