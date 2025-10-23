package com.sontaypham.cache.redis;

public interface RedisInfrastructure {
    void setString ( String key, String value );
    String getString ( String key );
    void setObject ( String key , Object value );
    <T> T getObject ( String key , Class<T> targetClass );
}
