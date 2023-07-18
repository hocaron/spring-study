package com.springstudy.jpa.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Accessors(fluent = true)
@Setter
@Table(name = "member")
public class MemberWithIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    public static MemberWithIdentity of(String nickname) {
        MemberWithIdentity member = new MemberWithIdentity();
        member.nickname = nickname;
        return member;
    }

    public void update(String nickname) {
        this.nickname = nickname;
    }
}
