package com.sontaypham.service.ticket.cache;

import com.sontaypham.cache.distributed.redisson.RedisDistributedService;
import com.sontaypham.cache.redis.RedisInfrastructureService;
import com.sontaypham.model.entity.TicketDetail;
import com.sontaypham.service.TicketDetailDomainService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TicketDetailCacheService {
    RedisDistributedService redisDistributedService;
    RedisInfrastructureService redisInfrastructureService;
    TicketDetailDomainService ticketDetailDomainService;
    public TicketDetail getTicketDefaultCacheNormal ( Long id, Long version) {
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
    private String getEventItemKey( Long id ) {
        return "PRO_TICKET:ITEM" + id;
    }
}
