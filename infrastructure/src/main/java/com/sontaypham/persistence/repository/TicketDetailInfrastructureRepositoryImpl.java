package com.sontaypham.persistence.repository;

import com.sontaypham.model.entity.TicketDetail;
import com.sontaypham.persistence.mapper.TicketDetailJPAMapper;
import com.sontaypham.repository.TicketDetailRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketDetailInfrastructureRepositoryImpl implements TicketDetailRepository {
    TicketDetailJPAMapper ticketDetailJPAMapper;

    @Override
    public Optional<TicketDetail> findById(Long id) {

        return ticketDetailJPAMapper.findById(id);
    }
}
