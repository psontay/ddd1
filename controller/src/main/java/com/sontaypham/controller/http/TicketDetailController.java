package com.sontaypham.controller.http;

import com.sontaypham.controller.model.enums.ResultUtil;
import com.sontaypham.controller.model.vo.ResultMessage;
import com.sontaypham.controller.utils.ResultUtils;
import com.sontaypham.model.entity.TicketDetail;
import com.sontaypham.service.ticket.TicketDetailApplicationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TicketDetailController {
    TicketDetailApplicationService ticketDetailApplicationService;
    @GetMapping("/{ticketId}/detail")
    public ResultMessage<TicketDetail> getTicketDetail(
        @PathVariable("ticketId") Long ticketId //,
//        @PathVariable("detailId") Long detailId
        ){
//        log.info("TicketDetailController.getTicketDetail: ticketId = {}, detailId = {}", ticketId, detailId);
        return ResultUtil.data(ticketDetailApplicationService.getTicketDetailById(ticketId));
    }
}
