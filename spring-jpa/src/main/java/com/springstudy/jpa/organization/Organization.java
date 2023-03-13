package com.springstudy.jpa.organization;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
