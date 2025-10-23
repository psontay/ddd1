package com.sontaypham.cache.redis.impl;

import com.sontaypham.cache.redis.RedisInfrastructure;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@Slf4j
public class RedisInfrastructureServiceImpl implements RedisInfrastructure {
    @Resource
    private RedisTemplate<String , Object> redisTemplate;
    @Override
    public void setString(String key, String value) {
        if ( !StringUtils.hasLength(key) ) return;
        redisTemplate.opsForValue().set( key, value );
    }

    @Override
    public String getString(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key))
                .map(String::valueOf)
                .orElse(null);
    }

    @Override
    public void setObject(String key, Object value) {
        if ( !StringUtils.hasLength(key) ) return;
        redisTemplate.opsForValue().set( key, value );
    }

    @Override
    public <T> T getObject(String key, Class<T> targetClass) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key)).map(targetClass::cast).orElse(null);
    }
}
