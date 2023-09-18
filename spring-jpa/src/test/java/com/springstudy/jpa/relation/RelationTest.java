package com.springstudy.jpa.relation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class RelationTest {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    PersonRepository personRepository;

    private Long animalId;
    private Long personId;

    @BeforeEach
    void setUp() {
        personId = personRepository.save(Person.of("name1")).personId();
        animalId = animalRepository.save(Animal.of(personId)).animalId();
    }

    @Test
    void getOwnerTest() {
        var animal = animalRepository.findById(animalId).get();

        assertThat(animal.owner().personId()).isEqualTo(personId);
        assertThat(animal.owner().name()).isEqualTo("name1");
    }

    @Test
    void getAnimalTest() {
        var person = personRepository.findById(personId).get();

        assertThat(person.hasAnimal()).isTrue();
        assertThat(person.animal().animalId()).isEqualTo(animalId);
    }
}
