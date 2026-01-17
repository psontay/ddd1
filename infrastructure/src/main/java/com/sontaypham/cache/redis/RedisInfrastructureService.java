package com.sontaypham.cache.redis;

public interface RedisInfrastructureService {
    void setString ( String key, String value );
    String getString ( String key );
    void setObject ( String key , Object value );
    <T> T getObject ( String key , Class<T> targetClass );
    void setTTL ( String key , long ttl );
    long getTTL ( String key );
    void delete ( String key );
}
