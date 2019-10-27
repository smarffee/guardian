package com.lin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Created by Lin on 2019/10/26.
 */
public class RedisTest extends BaseTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest() {
        boolean flag = redisTemplate.hasKey("k1");
        System.out.println(flag);
    }

}
