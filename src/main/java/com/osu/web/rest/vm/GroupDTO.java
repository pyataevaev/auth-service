package com.osu.web.rest.vm;

import com.osu.domain.Faculty;
import com.osu.domain.Group;

/**
 * Created by Ekaterina Pyataeva
 */
public class GroupDTO {

    private long id;

    private String name;

    private Faculty faculty;

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
        this(group.getId(), group.getName(), group.getFaculty());
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public GroupDTO(long id, String name, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
    }
}
