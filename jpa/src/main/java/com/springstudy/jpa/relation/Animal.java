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
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long animalId;

    @Column(name = "owner_id")
    private Long ownerId;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "person_id", insertable = false, updatable = false)
    private Person owner;

    public static Animal of(Long ownerId) {
        return new Animal(ownerId);
    }

    protected Animal(Long ownerId) {
        this.ownerId = ownerId;
    }
}
