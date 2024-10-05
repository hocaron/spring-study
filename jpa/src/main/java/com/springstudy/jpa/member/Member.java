package com.springstudy.jpa.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Getter
@NoArgsConstructor
@Accessors(fluent = true)
@Setter
@Table(name = "member")
public class Member {

    @Id
    private Long id;

    private String nickname;

    @Version
    private Long version;

    public static Member of(Long id, String nickname) {
        Member member = new Member();
        member.id = id;
        member.nickname = nickname;
        return member;
    }

    public void update(String nickname) {
        this.nickname = nickname;
    }
}
