package com.springstudy.graphql.dto;

import com.springstudy.graphql.domain.Role;
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
public class RoleDto {
    private Integer id;

    private Integer memberId;

    private String role;

    public static List<RoleDto> from(Collection<Role> entities) {
        if(entities == null) {
            return null;
        }
        return entities.stream().map(RoleDto::from).collect(Collectors.toList());
    }

    public static RoleDto from(Role entity) {
        return RoleDto.builder()
            .id(entity.getId())
            .memberId(entity.getMemberId())
            .role(entity.getRole())
            .build();
    }
}
