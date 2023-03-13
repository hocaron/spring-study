package com.springstudy.jpa.member;

import com.springstudy.jpa.organization.Organization;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@NoArgsConstructor
@Accessors(fluent = true)
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    public static Member of(String nickname) {
        Member member = new Member();
        member.nickname = nickname;
        return member;
    }
}
