package com.springstudy.jpa.member;

import com.springstudy.jpa.organization.Organization;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@Accessors(fluent = true)
@Setter
public class Member {

    @Id
    private Long id;

    private String nickname;

    public static Member of(String nickname) {
        Member member = new Member();
        member.nickname = nickname;
        return member;
    }

    public void update(String nickname) {
        this.nickname = nickname;
    }
}
