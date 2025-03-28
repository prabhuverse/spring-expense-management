package com.expense.mgmt.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.application.GroupService;
import com.expense.mgmt.domain.model.dto.Group;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/group")
public class GroupController {

    private final GroupService groupService;

    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public List<Group> listGroup() {
        return groupService.listGroups();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public Group getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public Group deleteGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }


}
