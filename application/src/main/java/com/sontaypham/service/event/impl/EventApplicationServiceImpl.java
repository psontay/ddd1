package com.sontaypham.service.event.impl;

import com.sontaypham.service.HiDomainService;
import com.sontaypham.service.event.EventApplicationService;
import org.springframework.stereotype.Service;

@Service
public class EventApplicationServiceImpl implements EventApplicationService {
    // Call Domain Service
    private final HiDomainService hiDomainService;
    public EventApplicationServiceImpl(HiDomainService hiDomainService) {
        this.hiDomainService = hiDomainService;
    }
    @Override
    public String sayHi(String who) {
        return hiDomainService.sayHi(who);
    }
}
