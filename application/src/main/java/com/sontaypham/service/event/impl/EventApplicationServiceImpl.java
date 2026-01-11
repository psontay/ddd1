package com.sontaypham.service.event.impl;

import com.sontaypham.service.event.EventApplicationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventApplicationServiceImpl implements EventApplicationService {
    // Call Domain Service
}
