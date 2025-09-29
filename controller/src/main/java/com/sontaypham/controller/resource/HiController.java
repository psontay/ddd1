package com.sontaypham.controller.resource;

import com.sontaypham.service.event.EventApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HiController {
    private final EventApplicationService eventApplicationService;
    public HiController(EventApplicationService eventApplicationService) {
        this.eventApplicationService = eventApplicationService;
    }
    @GetMapping()
    public String sayHi() {
        return eventApplicationService.sucCac("Trung Coc");
    }
}
