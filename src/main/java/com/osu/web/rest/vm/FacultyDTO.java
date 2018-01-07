package com.osu.web.rest.vm;

/**
 * Created by Ekaterina Pyataeva
 */
public class FacultyDTO {

    private long id;

    private String name;

    public FacultyDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
