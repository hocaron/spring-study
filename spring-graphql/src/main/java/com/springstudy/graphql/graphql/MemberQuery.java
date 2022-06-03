package com.springstudy.graphql.graphql;

import com.springstudy.graphql.domain.Member;
import com.springstudy.graphql.dto.MemberDto;
import com.springstudy.graphql.repository.MemberRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class MemberQuery implements GraphQLQueryResolver {

    private final MemberRepository memberRepository;

    public MemberDto getMember(int id) {
        Member member = memberRepository.findById(id)
            .orElse(null);
        return MemberDto.from(member);
    }
}