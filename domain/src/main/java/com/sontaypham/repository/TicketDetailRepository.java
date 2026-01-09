package com.sontaypham.repository;

import com.sontaypham.model.entity.TicketDetail;

import java.util.Optional;

public interface TicketDetailRepository {
    Optional<TicketDetail> findById(Long id);
}
