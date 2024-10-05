package com.springstudy.jpa.relation;

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
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "owner")
    private Animal animal;

    public boolean hasAnimal() {
        return animal != null;
    }

    public static Person of(String name) {
        return new Person(name);
    }

    protected Person(String name) {
        this.name = name;
    }
}
