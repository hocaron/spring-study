package com.springstudy.jpa.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void accessor_어노테이션_업데이트_테스트() {
        //given
        Member member = new Member();
        member.id(1L);
        member.nickname("nickname");
        memberRepository.save(member);

        //when
        memberService.updateMemberNickname(1L);

        //then
        Member udpatedMember = memberRepository.getReferenceById(1L);
        assertEquals("newNickname", udpatedMember.nickname());
    }
}