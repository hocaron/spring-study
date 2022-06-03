package com.springstudy.graphql.graphql;

import com.springstudy.graphql.domain.Member;
import com.springstudy.graphql.domain.Role;
import com.springstudy.graphql.dto.MemberDto;
import com.springstudy.graphql.repository.MemberRepository;
import com.springstudy.graphql.repository.RoleRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class MemberMutation implements GraphQLMutationResolver {

    private final MemberRepository memberRepository;

    private final RoleRepository roleRepository;

    public MemberDto createMember(MemberDto memberDto) {
        Member member = memberRepository.save(Member.builder()
            .loginId(memberDto.getLoginId())
            .name(memberDto.getName())
            .password(memberDto.getPassword())
            .build());
        return MemberDto.from(member);
    }

    public Boolean deleteMember(int id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        Role role = roleRepository.findByMemberId(id);
        if (optionalMember.isPresent()) {
            roleRepository.delete(role);
            memberRepository.delete(optionalMember.get());
        }
        return true;
    }
}
