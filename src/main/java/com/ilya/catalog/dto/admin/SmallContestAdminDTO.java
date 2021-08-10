package com.ilya.catalog.dto.admin;

import com.ilya.catalog.domain.StatusEnum;
import com.ilya.catalog.dto.catalog.SubGovernanceCatalogDTO;

import java.text.NumberFormat;
import java.util.Locale;

public class SmallContestAdminDTO {

    private long id;
    private String name;

    private String submissionFrom;
    private String submissionTo;
    private String votingFrom;
    private String votingTo;
    private String status;

    private String prizeFund;
    private SmallSubGovernanceAdminDTO subGovernance;

    public SmallContestAdminDTO(long id, String name, String submissionFrom, String submissionTo, String votingFrom,
                                String votingTo, StatusEnum status, int prizeFund, long subGovId, String subGovName) {
        this.id = id;
        this.name = name;
        this.submissionFrom = submissionFrom;
        this.submissionTo = submissionTo;
        this.votingFrom = votingFrom;
        this.votingTo = votingTo;
        this.status = status.name();
        this.prizeFund = NumberFormat.getNumberInstance(Locale.US).format(prizeFund);
        this.subGovernance = new SmallSubGovernanceAdminDTO(subGovId, subGovName);
    }

    public SmallContestAdminDTO(String name) {
        this.name = name;
    }

    public SmallContestAdminDTO() {
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

    public String getPrizeFund() {
        return prizeFund;
    }

    public void setPrizeFund(String prizeFund) {
        this.prizeFund = prizeFund;
    }

    public SmallSubGovernanceAdminDTO getSubGovernance() {
        return subGovernance;
    }

    public void setSubGovernance(SmallSubGovernanceAdminDTO subGovernance) {
        this.subGovernance = subGovernance;
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
}
