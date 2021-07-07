package com.ilya.catalog.dto.catalog;

public class SearchNamesDTO {

    private long id;
    private String name;

    public SearchNamesDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SearchNamesDTO() {
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
