package com.example.springredis;

import com.example.springredis.entity.Person;
import com.example.springredis.repository.PersonRedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisRepositoryTest {

    @Autowired
    private PersonRedisRepository personRedisRepository;

    @Test
    void test() {
        var person = new Person("ho", 20);

        // 저장
        var personId = personRedisRepository.save(person).getId();

        // `keyspace:id` 값을 가져옴
        personRedisRepository.findById(personId);
        personRedisRepository.findAllByAge(20).forEach(System.out::println);
    }
}
