package Study.SpringStudy.service;

import Study.SpringStudy.domain.Member;
import Study.SpringStudy.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }
    @AfterEach
    public void afterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring1");

        // when
        Long savedId = memberService.join(member);

        // then
        Member savedMember = memberService.findOne(savedId).get();
        assertThat(member).isEqualTo(savedMember);
    }

    @Test
    public void validateDuplicateMember(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberService.join(member2);

        Member member3 = new Member();
        member3.setName("spring3");
        memberService.join(member3);

        List<Member> result = memoryMemberRepository.findAll();
        assertThat(result).isEqualTo(memberService.findMembers());
    }

    @Test
    void findOne() {
        Member member1 = new Member();
        member1.setName("spring1");
        Long savedId = memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberService.join(member2);

        Member result = memberService.findOne(savedId).get();
        assertThat(result).isEqualTo(member1);
    }
}