package com.sontaypham.service.ticket;

import com.sontaypham.model.entity.TicketDetail;

public interface TicketDetailApplicationService {
    TicketDetail getTicketDetailById(Long id);
    boolean orderTicketByTicketId (Long ticketId);
}
