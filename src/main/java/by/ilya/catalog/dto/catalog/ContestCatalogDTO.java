package by.ilya.catalog.dto.catalog;

import java.util.List;

public class ContestCatalogDTO {

    private long id;
    private String name;
    private String description;
    private String submissionFrom;
    private String submissionTo;
    private String votingFrom;
    private String votingTo;
    private String prizeFund;

    private SubGovernanceCatalogDTO subGovernance;
    private List<SmallSubmissionCatalogDTO> submissions;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubmissionFrom() {
        return submissionFrom;
    }

    public void setSubmissionFrom(String submissionFrom) {
        this.submissionFrom = submissionFrom;
    }

    public String getSubmissionTo() {
        return submissionTo;
    }

    public void setSubmissionTo(String submissionTo) {
        this.submissionTo = submissionTo;
    }

    public String getVotingFrom() {
        return votingFrom;
    }

    public void setVotingFrom(String votingFrom) {
        this.votingFrom = votingFrom;
    }

    public String getVotingTo() {
        return votingTo;
    }

    public void setVotingTo(String votingTo) {
        this.votingTo = votingTo;
    }

    public String getPrizeFund() {
        return prizeFund;
    }

    public void setPrizeFund(String prizeFund) {
        this.prizeFund = prizeFund;
    }

    public SubGovernanceCatalogDTO getSubGovernance() {
        return subGovernance;
    }

    public void setSubGovernance(SubGovernanceCatalogDTO subGovernance) {
        this.subGovernance = subGovernance;
    }

    public List<SmallSubmissionCatalogDTO> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SmallSubmissionCatalogDTO> submissions) {
        this.submissions = submissions;
    }
}
