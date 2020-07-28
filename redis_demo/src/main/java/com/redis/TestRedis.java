package com.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TestRedis {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("test") //可写可不写
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    ObjectMapper objectMapper;

    public void testRedis() {
        redisTemplate.opsForValue().set("hello", "china");

        System.out.println(redisTemplate.opsForValue().get("hello"));

        stringRedisTemplate.opsForValue().set("hello1", "china1");
        System.out.println(stringRedisTemplate.opsForValue().get("hello1"));
    }

    public void testRedis2() {

        RedisConnection conn = redisTemplate.getConnectionFactory().getConnection();
        conn.set("hello02".getBytes(), "nihao".getBytes());
        System.out.println(new String(conn.get("hello02".getBytes())));


        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
        hash.put("sean", "name", "zhouzhilei");
        hash.put("sean", "age", "22");
    }

    public void testReis03() {

        Person p = new Person();
        p.setName("sean");
        p.setAge(18);

        Jackson2HashMapper mapper = new Jackson2HashMapper(objectMapper, false);

        redisTemplate.opsForHash().putAll("sean01", mapper.toHash(p));

        Map map = redisTemplate.opsForHash().entries("sean01");
        Person per = objectMapper.convertValue(map, Person.class);

        System.out.println(per);

    }

    public void testReis04() {

        Person p = new Person();
        p.setName("sean");
        p.setAge(17);

        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));

        Jackson2HashMapper mapper = new Jackson2HashMapper(objectMapper, false);

        stringRedisTemplate.opsForHash().putAll("sean01", mapper.toHash(p));

        Map map = stringRedisTemplate.opsForHash().entries("sean01");
        Person per = objectMapper.convertValue(map, Person.class);

        System.out.println(per);

    }

    public void testReis05() {

        Person p = new Person();
        p.setName("sean");
        p.setAge(15);


        Jackson2HashMapper mapper = new Jackson2HashMapper(objectMapper, false);

        stringRedisTemplate.opsForHash().putAll("sean02", mapper.toHash(p));

        Map map = stringRedisTemplate.opsForHash().entries("sean02");
        Person per = objectMapper.convertValue(map, Person.class);

        System.out.println(per);

        stringRedisTemplate.convertAndSend("test","hello");

        RedisConnection cc = stringRedisTemplate.getConnectionFactory().getConnection();
        cc.subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] bytes) {
                System.out.println(new String(message.getBody()));
            }
        }, "test".getBytes());
        while (true){

        }

    }


}
