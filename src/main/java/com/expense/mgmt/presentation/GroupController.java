package com.expense.mgmt.presentation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.application.GroupService;
import com.expense.mgmt.domain.model.dto.Group;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/group")
public class GroupController {

    private final GroupService groupService;

    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public Mono<Group> createGroup(@RequestBody Group group) {
        log.info("Creating group {}", group);
        return groupService.createGroup(group);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public Flux<Group> listGroups() {
        log.info("Listing all the groups");
        return groupService.listGroups();
    }
}
