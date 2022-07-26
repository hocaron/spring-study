package com.springstudy.lock.job;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer salary;

    public Integer increaseSalary(Integer salary){
        return this.salary += salary;
    }
}
