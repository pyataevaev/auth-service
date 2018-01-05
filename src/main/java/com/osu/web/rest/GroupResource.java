package com.osu.web.rest;

import com.osu.repository.GroupRepository;
import com.osu.web.rest.vm.GroupDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ekaterina Pyataeva
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupResource {

    private final GroupRepository groupRepository;

    public GroupResource(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> groups = groupRepository.findAll().stream().map(GroupDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
}
