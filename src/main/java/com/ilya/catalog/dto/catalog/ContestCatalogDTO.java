package com.ilya.catalog.dto.catalog;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ContestCatalogDTO {

    private long id;
    private String name;
    private String smallDescription;
    private String bigDescription;
    private String submissionFrom;
    private String submissionTo;
    private String votingFrom;
    private String votingTo;
    private String prizeFund;

    private SubGovernanceCatalogDTO subGovernance;
    private List<SmallSubmissionCatalogDTO> submissions;

    public ContestCatalogDTO(long id, String name, String smallDescription, String bigDescription, String submissionFrom,
                             String submissionTo, String votingFrom, String votingTo, int prizeFund,
                             long subGovId, String subGovName) {
        this.id = id;
        this.name = name;
        this.smallDescription = smallDescription;
        this.bigDescription = bigDescription;
        this.submissionFrom = submissionFrom;
        this.submissionTo = submissionTo;
        this.votingFrom = votingFrom;
        this.votingTo = votingTo;
        this.prizeFund = NumberFormat.getNumberInstance(Locale.US).format(prizeFund);
        this.subGovernance = new SubGovernanceCatalogDTO(subGovId, subGovName);
    }

    public ContestCatalogDTO() {
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

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getBigDescription() {
        return bigDescription;
    }

    public void setBigDescription(String bigDescription) {
        this.bigDescription = bigDescription;
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
