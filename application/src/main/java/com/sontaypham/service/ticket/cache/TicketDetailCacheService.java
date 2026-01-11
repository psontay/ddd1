package com.sontaypham.service.ticket.cache;

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
    public TicketDetail getTicketDefaultCacheNormal ( Long id, Long version) {
        log.info("Implement getTicketDefaultCacheNormal->, {}, {} ", id, version);
        TicketDetail ticketDetail = redisInfrastructureService.getObject(getEventItemKey(id), TicketDetail.class );
        if ( ticketDetail != null ) {
            log.info("FROM CACHE {}, {}, {}", id , version , ticketDetail );
            return ticketDetail;
        }
        ticketDetail = ticketDetailDomainService.getTicketDetailById(id);
        log.info("FROM CACHE {}, {}, {}", id , version , ticketDetail );
        if ( ticketDetail != null ) {
            redisInfrastructureService.setObject(getEventItemKey(id), ticketDetail);
        }
        return ticketDetail;
    }
    public TicketDetail getTicketDefaultCacheVip ( Long id, Long version) {
        log.info("Implement getTicketDefaultCacheVip->, {}, {} ", id, version);
        TicketDetail ticketDetail = redisInfrastructureService.getObject(getEventItemKey(id), TicketDetail.class );
        if ( ticketDetail != null ) {
            log.info("FROM CACHE {}, {}, {}", id , version , ticketDetail );
            return ticketDetail;
        }
        RedisDistributedLocker locker = redisDistributedService.getDistributedLock("PRO_LOCK_KEY_ITEM"+id);
        try{
            // lock key
            boolean isLock = locker.tryLock(1, 10, TimeUnit.SECONDS);
            if ( !isLock ) {
                log.info("LOCK WAIT TIME ITEM {}", version);
                Thread.sleep(50);
                return redisInfrastructureService.getObject(getEventItemKey(id), TicketDetail.class );
            }
            // check cache 1
            ticketDetail = redisInfrastructureService.getObject(getEventItemKey(id), TicketDetail.class );
            if ( ticketDetail != null ) {
                log.info("FROM CACHE {}, {}, {}", id , version , ticketDetail );
                return ticketDetail;
            }
            // if cache null -> db
            ticketDetail = ticketDetailDomainService.getTicketDetailById(id);
            log.info("FROM DBS -> {}, {}, {}", id , version , ticketDetail );
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
    private String getEventItemKey( Long id ) {
        return "PRO_TICKET:ITEM" + id;
    }
}
