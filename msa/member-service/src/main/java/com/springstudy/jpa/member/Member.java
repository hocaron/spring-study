package com.springstudy.jpa.member;

import com.springstudy.jpa.organization.Organization;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public static Member of(String nickname) {
        Member member = new Member();
        member.nickname = nickname;
        return member;
    }
}
