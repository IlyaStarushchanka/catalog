package com.ilya.catalog.dto.admin;

public class SmallSubGovernanceAdminDTO {

    private long id;
    private String name;

    public SmallSubGovernanceAdminDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SmallSubGovernanceAdminDTO() {
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
