package com.springstudy.jpa.member;

import com.springstudy.jpa.organization.Organization;
import com.springstudy.jpa.organization.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberTempService {
    private final TempMemberRepository tempMemberRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public void temp() {
        List<String> ids = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> ids.add(RandomStringUtils.randomAlphanumeric(10)));

        for (int i = 0; i < 5; i++) {
            List<TempMember> tempMembers = new ArrayList<>();
            ids.forEach(id -> tempMembers.add(TempMember.of(id)));
            tempMemberRepository.saveAll(tempMembers);
            System.out.println("처리완료");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {

            }
        }
//        List<TempMember> tempMembers = new ArrayList<>();
//        ids.forEach(id -> tempMembers.add(TempMember.of(id)));
//        tempMemberRepository.saveAll(tempMembers);
//        System.out.println("처리완료");
//        try {
//            Thread.sleep(10000);
//        } catch (Exception e) {
//
//        }
        System.out.println("진짜완료");
    }

    @Transactional
    public TempMember find(String nickname) {
        TempMember temp = tempMemberRepository.findById(1L).orElseThrow();
        return temp;
    }

//    @Transactional
//    public Member saveAndFlush(String nickname) {
//        Member member = Member.of(nickname);
//        return memberRepository.saveAndFlush(member);
//    }

//    @Transactional
//    public Member saveAndUpdate(String nickname) {
//        Member member = Member.of(nickname);
//        memberRepository.save(member);
//        return member;
//    }
//
//    @Transactional
//    public Member saveAndFlushAndUpdate(String nickname) {
//        Member member = Member.of(nickname);
//        member = memberRepository.saveAndFlush(member);
//        return member;
//    }

//    @Transactional(readOnly = true)
//    public List<Member> findByOrganization(Long organizationId) {
//        Organization organization =
//            organizationRepository.findById(organizationId).orElseThrow(RuntimeException::new);
//        List<Member> members = memberRepository.findByOrganization(organization);
//        return members;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Member> findByOrganizationId(Long organizationId) {
//        List<Member> members = memberRepository.findByOrganizationId(organizationId);
//        return members;
//    }

//    @Transactional
//    public Member updateMemberNickname(Long memberId) {
//        Member member = memberRepository.findById(memberId).orElseThrow(RuntimeException::new);
//        String newNickname = "newNickname";
//        member.nickname(newNickname);
//        return member;
//    }

    @Transactional
    public void test(List<Member> members) {
        int chunkSize = 300;
        int totalSize = members.size();
        int fromIndex = 0;
        int toIndex = Math.min(chunkSize, totalSize);

        // Chunk 단위로 저장
        while (fromIndex < totalSize) {
            List<Member> chunkMembers = members.subList(fromIndex, toIndex);
            memberRepository.saveAll(chunkMembers);
            System.out.println(fromIndex + " saveAll 완료");

            fromIndex = toIndex;
            toIndex = Math.min(fromIndex + chunkSize, totalSize);
        }
    }
}
