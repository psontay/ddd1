package com.sontaypham.service.event.impl;

import com.sontaypham.service.event.EventApplicationService;
import org.springframework.stereotype.Service;

@Service
public class EventApplicationServiceImpl implements EventApplicationService {
    @Override
    public String sucCac(String who) {
        return who;
    }
}
