package com.springstudy.jpa.relation;

public class AnimalDto {

    private Long animalId;

    private String ownerName;

    public AnimalDto(Animal animal) {
        this.animalId = animal.animalId();
        this.ownerName = animal.owner().name();
    }
}
