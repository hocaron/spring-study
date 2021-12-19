package Study.SpringStudy.service;

import Study.SpringStudy.domain.Member;
import Study.SpringStudy.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    // 회원가입
    public Long join(Member member){
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // ID로 회원조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
