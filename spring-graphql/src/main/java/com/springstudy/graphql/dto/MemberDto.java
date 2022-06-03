package com.springstudy.graphql.dto;

import com.springstudy.graphql.domain.Member;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Integer id;

    private String loginId;

    private String password;

    private String name;

    private List<RoleDto> roles;

    public static List<MemberDto> from(Collection<Member> entities) {
        return entities.stream().map(MemberDto::from).collect(Collectors.toList());
    }

    public static MemberDto from(Member entity) {
        return MemberDto.builder()
            .id(entity.getId())
            .loginId(entity.getLoginId())
            .password(entity.getPassword())
            .name(entity.getName())
            .roles(RoleDto.from(entity.getRole()))
            .build();
    }
}