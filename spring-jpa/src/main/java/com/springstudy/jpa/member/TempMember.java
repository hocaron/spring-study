package com.springstudy.jpa.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@Setter
@DynamicUpdate
public class TempMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nickname;

    public static TempMember of(String nickname) {
        TempMember member = new TempMember();
        member.nickname = nickname;
        return member;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
