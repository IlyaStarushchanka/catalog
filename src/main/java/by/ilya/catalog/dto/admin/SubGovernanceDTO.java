package by.ilya.catalog.dto.admin;

import java.util.ArrayList;
import java.util.List;

public class SubGovernanceDTO {

    private long id;
    private String name;
    private List<ContestDTO> contests = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ContestDTO> getContests() {
        return contests;
    }

    public void setContests(List<ContestDTO> contests) {
        this.contests = contests;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
