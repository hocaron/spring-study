package com.springstudy.jpa.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Accessors(fluent = true)
@Table(name = "member1")
public class Member1 {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nickname;

    private Integer age;

    public Member1(String nickname, Integer age) {
        this.nickname = nickname;
        this.age = age;
    }

    public static Member1 of(String nickname, Integer age) {
        Member1 member = new Member1();
        member.nickname = nickname;
        member.age = age;
        return member;
    }

    public void update(String nickname) {
        this.nickname = nickname;
    }
}
