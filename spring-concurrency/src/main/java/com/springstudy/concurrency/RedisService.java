package com.springstudy.concurrency;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {

    RedisTemplate<String, String> redisTemplate;

    public Object addItem() {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi(); // transaction start
                operations.opsForValue().set("user2", "222");
                operations.opsForValue().set("user3", "333");
                return operations.exec(); // transaction end
            }
        });
        return null;
    }
}
