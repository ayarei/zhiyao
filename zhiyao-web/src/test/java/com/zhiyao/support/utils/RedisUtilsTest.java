package com.zhiyao.support.utils;

import com.zhiyao.common.constant.ZhiYaoConstant;
import com.zhiyao.support.dto.ConnectInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisUtilsTest {

    @Autowired
    private RedisUtils redisUtils;

    private static ConnectInfoDto dto = null;

    @BeforeEach
    void init() {
        dto = ConnectUtils.generateConnectInfo();
    }

    @Test
    void set() {
        String key = ZhiYaoConstant.CONNECT_ID_KEY_PREFIXX + dto.getConnectId();
        String val = dto.getPassword();
        redisUtils.set(key, val);
    }

    @Test
    void testSetExpire() {
        String key = ZhiYaoConstant.CONNECT_ID_KEY_PREFIXX + dto.getConnectId();
        String val = dto.getPassword();
        redisUtils.set(key, val, 60L);
    }

    @Test
    void get() {
        String key = ZhiYaoConstant.CONNECT_ID_KEY_PREFIXX + dto.getConnectId();
        String val = dto.getPassword();
        redisUtils.set(key, val);
        String getVal = (String) redisUtils.get(key);
        assertEquals(val, getVal);
    }

    @Test
    void exists() {
        String existKey = ZhiYaoConstant.CONNECT_ID_KEY_PREFIXX + dto.getConnectId();
        String val = dto.getPassword();
        redisUtils.set(existKey, val);
        assertTrue(redisUtils.exists(existKey));
        assertFalse(redisUtils.exists("notExistKey"));
    }

    @Test
    void remove() {
        String existKey = ZhiYaoConstant.CONNECT_ID_KEY_PREFIXX + dto.getConnectId();
        assertTrue(redisUtils.remove(existKey));
    }

}
