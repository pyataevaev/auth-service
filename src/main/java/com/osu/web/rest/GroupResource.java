package com.osu.web.rest;

import com.osu.config.Constants;
import com.osu.domain.Group;
import com.osu.repository.GroupRepository;
import com.osu.security.AuthoritiesConstants;
import com.osu.web.rest.util.HeaderUtil;
import com.osu.web.rest.vm.GroupDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    @DeleteMapping("/groups/{id}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("groupManagement.deleted", id.toString())).build();
    }

    @GetMapping("/groups/{id:}")
    public ResponseEntity<GroupDTO> getUser(@PathVariable Long id) {
        return ResponseUtil.wrapOrNotFound(
                groupRepository.findById(id)
                        .map(GroupDTO::new));
    }
}
