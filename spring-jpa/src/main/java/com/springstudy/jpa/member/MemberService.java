package com.springstudy.jpa.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private static final String DEFAULT_NICKNAME = " nickname";

    private static final String NEW_NICKNAME = " new nickname";

    private static final Integer LOOP_COUNT = 21;

    @Transactional
    public void findByNonePkAndUpdate() {

        for (int i = 1; i < LOOP_COUNT; i++) {
            var member = memberRepository.findByNickname(i + DEFAULT_NICKNAME).get();
            member.update(i + NEW_NICKNAME);
            log.info("{} 번째", i);
        }
    }

    @Transactional
    public void findByPkAndUpdate() {

        for (int i = 1; i < LOOP_COUNT; i++) {
            var member = memberRepository.findById((long) (i)).get();
            member.update(i + NEW_NICKNAME);
            log.info("{} 번째", i);
        }
    }

    @Transactional
    public void findAllByPkInAndUpdate() {

        List<Long> ids = new ArrayList<>();
        for (long i = 1; i < LOOP_COUNT; i++) {
            ids.add(i);
        }

        List<Member> members = memberRepository.findAllByIdIn(ids);
        for (int i = 1; i < LOOP_COUNT; i++) {
            members.get(i - 1).update(i + NEW_NICKNAME);
        }
    }

    @Transactional
    public void findAllByNonePkInAndUpdate() {

        List<String> nicknames = new ArrayList<>();
        for (long i = 1; i < LOOP_COUNT; i++) {
            nicknames.add(i + DEFAULT_NICKNAME);
        }

        List<Member> members = memberRepository.findAllByNicknameIn(nicknames);
        for (int i = 1; i < LOOP_COUNT; i++) {
            members.get(i - 1).update(i + NEW_NICKNAME);
            members.get(i - 1).update(i + NEW_NICKNAME);
        }
    }

    @Transactional
    public void saveAll() {
        //given
        List<Member> members = new ArrayList<>();

        for (long i = 1; i < LOOP_COUNT; i++) {
            members.add(Member.of(i, i + DEFAULT_NICKNAME));
        }

        memberRepository.saveAll(members);
    }

    @Transactional
    public void deleteAll() {

        memberRepository.deleteAll();
    }

    @Transactional
    public void findByPk() {
        memberRepository.findById(1L).orElseThrow();
        memberRepository.findById(1L).orElseThrow();
        memberRepository.findById(1L).orElseThrow();
    }

    @Transactional
    public void findByNonePk() {
        log.info("[findByNonePk]");
        memberRepository.findByNickname(1 + DEFAULT_NICKNAME);
        memberRepository.findByNickname(1 + DEFAULT_NICKNAME);
        memberRepository.findByNickname(1 + DEFAULT_NICKNAME);
    }
}
