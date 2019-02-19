package com.ali.leb.base.cache;

import org.springframework.data.redis.core.RedisTemplate;
import sun.misc.Resource;

import java.util.concurrent.TimeUnit;

public class RedisCacheHandle {

    private RedisTemplate redisTemplate;

    public RedisCacheHandle() {
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void delete(String key){
        this.redisTemplate.delete(key);
    }

    public Object getString(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void putString(String k, Object v){
        redisTemplate.opsForValue().set(k, v);
    }

    public void putString(String k, Object v, Long l){
        redisTemplate.opsForValue().set(k, v, l);
    }

    public void putString(String k, Object v, Long l, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(k, v, l, timeUnit);
    }


}
