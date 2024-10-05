package com.example.springredis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RedisHash(value = "person")
public class Person {

    @Id
    private String id;

    private String name;

    @Indexed
    private Integer age;

    @TimeToLive
    private long ttl = 60;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
