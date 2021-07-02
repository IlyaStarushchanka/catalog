package com.ilya.catalog.dto.catalog;

import java.text.NumberFormat;
import java.util.Locale;

public class SmallContestCatalogDTO {

    private long id;
    private String name;

    private String submissionFrom;
    private String submissionTo;

    private String prizeFund;
    private SubGovernanceCatalogDTO subGovernance;
    private int winnersCount;

    public SmallContestCatalogDTO(long id, String name, String submissionFrom, String submissionTo, int prizeFund,
                                  long subGovId, String subGovName, long winnersCount) {
        this.id = id;
        this.name = name;
        this.submissionFrom = submissionFrom;
        this.submissionTo = submissionTo;
        this.prizeFund = NumberFormat.getNumberInstance(Locale.US).format(prizeFund);
        this.subGovernance = new SubGovernanceCatalogDTO(subGovId, subGovName);
        this.winnersCount = (int)winnersCount;
    }

    public SmallContestCatalogDTO() {
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

    public SubGovernanceCatalogDTO getSubGovernance() {
        return subGovernance;
    }

    public void setSubGovernance(SubGovernanceCatalogDTO subGovernance) {
        this.subGovernance = subGovernance;
    }

    public int getWinnersCount() {
        return winnersCount;
    }

    public void setWinnersCount(int winnersCount) {
        this.winnersCount = winnersCount;
    }
}
