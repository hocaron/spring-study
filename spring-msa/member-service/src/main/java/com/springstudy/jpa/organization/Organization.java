package com.springstudy.jpa.organization;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Organization {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
}
