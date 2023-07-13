package com.springstudy.jpa.organization;

import javax.persistence.*;

import com.springstudy.jpa.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Organization {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  public static Organization of(String name, Member member) {
    Organization organization = new Organization();
    organization.name = name;
    return organization;
  }

  public void update(String name) {
    this.name = name;
  }
}
