package by.ilya.catalog.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(length = 1000)
    private String smallDescription;
    @Column(length = 15000)
    private String bigDescription;
    private String submissionFrom;
    private String submissionTo;
    private String votingFrom;
    private String votingTo;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;

    private String prizeFund;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Submission> submissions = new HashSet<>();

    @ManyToOne(optional = false)
    private SubGovernance subGovernance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    public SubGovernance getSubGovernance() {
        return subGovernance;
    }

    public void setSubGovernance(SubGovernance subGovernance) {
        this.subGovernance = subGovernance;
    }

    public String getPrizeFund() {
        return prizeFund;
    }

    public void setPrizeFund(String prizeFund) {
        this.prizeFund = prizeFund;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contest contest = (Contest) o;
        return id.equals(contest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
