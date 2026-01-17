package com.sontaypham.service.ticket.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sontaypham.cache.distributed.redisson.RedisDistributedLocker;
import com.sontaypham.cache.distributed.redisson.RedisDistributedService;
import com.sontaypham.cache.redis.RedisInfrastructureService;
import com.sontaypham.model.entity.TicketDetail;
import com.sontaypham.service.TicketDetailDomainService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TicketDetailCacheService {
    RedisDistributedService redisDistributedService;
    RedisInfrastructureService redisInfrastructureService;
    TicketDetailDomainService ticketDetailDomainService;
    Cache<Long, TicketDetail> ticketDetailLocalCache =
            CacheBuilder.newBuilder().initialCapacity(10).concurrencyLevel(10).expireAfterAccess(10, TimeUnit.MINUTES).build();
    public TicketDetail getTicketDetailRedisCache(Long id, Long version) {
        log.info("Implement getTicketDetailRedisCache->, {}, {} ", id, version);
        TicketDetail ticketDetail = redisInfrastructureService.getObject(getEventItemKey(id), TicketDetail.class );
        if ( ticketDetail != null ) {
            log.info("FROM DISTRIBUTED CACHE-> {}, {}, {}", id , version , ticketDetail);
            return ticketDetail;
        }
        RedisDistributedLocker locker = redisDistributedService.getDistributedLock("PRO_LOCK_KEY_ITEM"+id);
        try{
            // lock key
            boolean isLock = locker.tryLock(1, 10, TimeUnit.SECONDS);
            if ( !isLock ) {
                log.info("LOCK WAIT TIME ITEM-> {}", version);
                Thread.sleep(50);
                return redisInfrastructureService.getObject(getEventItemKey(id), TicketDetail.class );
            }
            // check cache 1
            ticketDetail = redisInfrastructureService.getObject(getEventItemKey(id), TicketDetail.class );
            if ( ticketDetail != null ) {
                log.info("FROM CACHE-> {}, {}, {}", id , version , ticketDetail );
                return ticketDetail;
            }
            // if cache null -> dbs
            ticketDetail = ticketDetailDomainService.getTicketDetailById(id);
            log.info("FROM DBS-> {}, {}, {}", id , version , ticketDetail );
            if ( ticketDetail == null) {
                log.info("TICKET DETAIL IS NULL {}", version);
                return null;
            }
            // set cache
            redisInfrastructureService.setObject(getEventItemKey(id), ticketDetail);
            return ticketDetail;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if (locker.isHeldByCurrentThread()) locker.unlock();
        }
    }

    // local cache
    private TicketDetail getTicketDetailLocalCache(Long id) {
        try{
            return ticketDetailLocalCache.getIfPresent(id);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public TicketDetail getTicketDetailLocalRedisCache(Long id, Long version) {
        // cache 1: local
        TicketDetail ticketDetail = getTicketDetailLocalCache(id);
        if ( ticketDetail != null ) {
            log.info("FROM LOCAL CACHE-> {}, {}, {}", id , version , ticketDetail );
            return ticketDetail;
        }
        // miss local -> cache 2: redis check 1
        ticketDetail = redisInfrastructureService.getObject(getEventItemKey(id), TicketDetail.class );
        if ( ticketDetail != null ) {
            log.info("FROM DISTRIBUTED CACHE-> {}, {}, {}", id , version , ticketDetail );
            ticketDetailLocalCache.put(id, ticketDetail);
            return ticketDetail;
        }
        // miss redis -> create lock
        RedisDistributedLocker locker = redisDistributedService.getDistributedLock("PRO_LOCK_KEY_ITEM"+id);
        try{
            boolean isLock = locker.tryLock(1, 10, TimeUnit.SECONDS);
            // lock failed
            if ( !isLock ) {
                log.info("LOCK WAIT TIME ITEM-> {}", version);
                Thread.sleep(50);
                // cache 2: redis check 2
                return redisInfrastructureService.getObject(getEventItemKey(id), TicketDetail.class );
            }
            // lock success -> recheck redis cache ( maybe another thread already set cache )
            ticketDetail = redisInfrastructureService.getObject(getEventItemKey(id), TicketDetail.class );
            if ( ticketDetail != null ) {
                log.info("FROM DISTRIBUTED CACHE-> {}, {}, {}", id , version , ticketDetail );
                ticketDetailLocalCache.put(id, ticketDetail);
                return ticketDetail;
            }
            // miss redis -> call DBS
            ticketDetail = ticketDetailDomainService.getTicketDetailById(id);
            log.info("FROM DBS-> {}, {}, {}", id , version , ticketDetail );
            if ( ticketDetail != null ) {
                // set cache from DBS
                ticketDetailLocalCache.put(id, ticketDetail);
                redisInfrastructureService.setObject(getEventItemKey(id), ticketDetail);
                return ticketDetail;
            }
            log.info("NULL FROM DBS-> {}", id);
            ticketDetailLocalCache.put(id , null);
            return null;
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    private String getEventItemKey( Long id ) {
        return "PRO_TICKET:ITEM" + id;
    }
    public boolean orderTicketByTicketId ( Long ticketId ) {
        ticketDetailLocalCache.invalidate(ticketId); // remove cache from local cache
        redisInfrastructureService.delete(getEventItemKey(ticketId));
        return true;
    }
}
