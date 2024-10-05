package com.example.springredis.repository;

import com.example.springredis.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRedisRepository extends CrudRepository<Person, String> {

    List<Person> findAllByAge(int age);
}
