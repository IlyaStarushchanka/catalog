package com.ilya.catalog.dto.catalog;

public class SmallestContestCatalogDTO {

    private long id;
    private String name;
    private SubGovernanceCatalogDTO subGovernance;

    public SmallestContestCatalogDTO(long id, String name, long subGovId, String subGovName) {
        this.id = id;
        this.name = name;
        this.subGovernance = new SubGovernanceCatalogDTO(subGovId, subGovName);
    }

    public SmallestContestCatalogDTO() {
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

    public SubGovernanceCatalogDTO getSubGovernance() {
        return subGovernance;
    }

    public void setSubGovernance(SubGovernanceCatalogDTO subGovernance) {
        this.subGovernance = subGovernance;
    }
}
