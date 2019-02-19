package com.ali.leb.base.cache;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisCacheHandle {

    private JedisPool jedisPool;

    private static Jedis jedis;

    public JedisCacheHandle() {
    }

    public JedisCacheHandle(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
        jedis = this.jedisPool.getResource();
    }

    public static Jedis getResource(){
        return jedis;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
        jedis = this.jedisPool.getResource();
    }



}
