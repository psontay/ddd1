package com.sontaypham.service.impl;

import com.sontaypham.model.entity.TicketDetail;
import com.sontaypham.repository.TicketDetailRepository;
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
public class TicketDetailDomainServiceImpl implements TicketDetailDomainService {
//    TicketDetailRepository ticketDetailRepository;
    @Override
    public TicketDetail getTicketDetailById(Long ticketId) {
        return null;
    }
}
