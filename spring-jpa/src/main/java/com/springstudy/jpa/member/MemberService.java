package com.springstudy.jpa.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void findByNonePkAndUpdate() {

        for (int i = 1; i < 21; i++) {
            var m = memberRepository.findByNickname(i + " nickname").get();
            m.update(i + "new nickname");
        }
    }

    @Transactional
    public void findByPkAndUpdate() {
        List<Member> members = new ArrayList<>();

        for (int i = 1; i < 21; i++) {
            var m = memberRepository.findById((long) (i)).get();
            m.update(i + " new nickname");
            members.add(m);
        }
        memberRepository.saveAll(members);
    }

    @Transactional
    public void saveAll() {
        //given
        List<Member> members = new ArrayList<>();

        for (long i = 1; i < 21; i++) {
            members.add(Member.of(i, i + " nickname"));
        }

        memberRepository.saveAll(members);
    }

    @Transactional
    public void deleteAll() {

        memberRepository.deleteAll();
    }
}
