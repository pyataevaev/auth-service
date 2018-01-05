package com.osu.web.rest.vm;

import com.osu.domain.Group;

/**
 * Created by Ekaterina Pyataeva
 */
public class GroupDTO {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupDTO(Group group) {
        this(group.getId(), group.getName());
    }

    public GroupDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
