package com.zhiyao.support.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 封装redis操作
 *
 * @author Delonix
 */
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * redis写入缓存
     *
     * @param key   缓存key
     * @param value 缓存value
     * @return 是否缓存成功
     */
    public boolean set(final String key, String value) {
        boolean setResult = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            setResult = true;
        } catch (Exception e) {
            log.error("redis缓存写入失败！", e);
        }
        return setResult;
    }

    /**
     * redis设置缓存过期时间写入缓存
     *
     * @param key           缓存key
     * @param value         缓存value
     * @param expireSeconds 过期时间
     * @return 是否缓存成功
     */
    public boolean set(final String key, String value, Long expireSeconds) {
        boolean setResult = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
            setResult = true;
        } catch (Exception e) {
            log.error("redis设置缓存过期时间写入缓存失败！", e);
        }
        return setResult;
    }

    /**
     * 获取某个缓存
     *
     * @param key 缓存key
     * @return 缓存对象
     */
    public Object get(final String key) {
        Object result = null;
        try {
            result = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("获取redis缓存失败！key: {}", key, e);
        }
        return result;
    }

    /**
     * redis是否存在某个key
     *
     * @param key 待检查的key
     * @return 是否存在
     */
    public boolean exists(final String key) {
        Boolean isExists = false;
        try {
            isExists = redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("判断key: {} 是否存在失败", key, e);
        }
        if (isExists == null) {
            return false;
        }
        return isExists;
    }

    /**
     * 从redis删除一个key
     *
     * @param key 待删除key
     * @return 是否删除成功
     */
    public boolean remove(final String key) {
        Boolean removeResult = false;
        try {
            if (exists(key)) {
                removeResult = redisTemplate.delete(key);
            } else {
                removeResult = true;
            }
        } catch (Exception e) {
            log.error("删除key: {} 失败", key, e);
        }
        if (removeResult == null) {
            return false;
        }
        return removeResult;
    }

    /**
     * 从redis批量删除key
     *
     * @param keys 待删除key数组
     */
    public void remove(final String... keys) {
        if (keys == null) {
            return;
        }
        for (String key : keys) {
            remove(key);
        }
    }

}
