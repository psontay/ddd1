package com.sontaypham.service.ticket.impl;

import com.sontaypham.model.entity.TicketDetail;
import com.sontaypham.service.TicketDetailDomainService;
import com.sontaypham.service.ticket.TicketDetailApplicationService;
import com.sontaypham.service.ticket.cache.TicketDetailCacheService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TicketDetailApplicationServiceImpl implements TicketDetailApplicationService {
    TicketDetailDomainService ticketDetailDomainService;
    TicketDetailCacheService ticketDetailCacheService;
    @Override
    public TicketDetail getTicketDetailById(Long id) {
        return null;
    }
}
