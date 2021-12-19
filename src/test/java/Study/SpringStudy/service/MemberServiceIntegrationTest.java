package Study.SpringStudy.service;

import Study.SpringStudy.domain.Member;
import Study.SpringStudy.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired  MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("test1");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void validateDuplicateMember(){
        Member member1 = new Member();
        member1.setName("test1");
        Member member2 = new Member();
        member2.setName("test1");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
        Member member1 = new Member();
        member1.setName("test1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("test2");
        memberService.join(member2);

        Member member3 = new Member();
        member3.setName("test3");
        memberService.join(member3);

        List<Member> result = memberRepository.findAll();
        assertThat(result).isEqualTo(memberService.findMembers());
    }

    @Test
    void findOne() {
        Member member1 = new Member();
        member1.setName("test1");
        Long savedId = memberService.join(member1);
        System.out.println(member1);
        System.out.println(savedId);


        Member result = memberService.findOne(savedId).get();
        assertThat(result.getName()).isEqualTo(member1.getName());
    }
}