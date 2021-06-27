package by.ilya.catalog.dto;

import java.util.List;

public class ContestDTO {

    private long id;
    private String name;
    private String description;
    private String submissionFrom;
    private String submissionTo;
    private String votingFrom;
    private String votingTo;
    private String status;
    private double prizeFund;

    private SubGovernanceDTO subGovernance;
    private List<SubmissionDTO> submissions;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrizeFund() {
        return prizeFund;
    }

    public void setPrizeFund(double prizeFund) {
        this.prizeFund = prizeFund;
    }

    public SubGovernanceDTO getSubGovernance() {
        return subGovernance;
    }

    public void setSubGovernance(SubGovernanceDTO subGovernance) {
        this.subGovernance = subGovernance;
    }

    public List<SubmissionDTO> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionDTO> submissions) {
        this.submissions = submissions;
    }
}
