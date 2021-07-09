package com.ilya.catalog.dto.admin;

import java.util.List;

public class AuthorDTO {

    private long id;
    private String freetonForumNickname;
    //private List<SubmissionDTO> submissions;
    private String[] freeTonAddresses;
    private String chosedFreeTonAddress;

    public String getFreetonForumNickname() {
        return freetonForumNickname;
    }

    public void setFreetonForumNickname(String freetonForumNickname) {
        this.freetonForumNickname = freetonForumNickname;
    }

    /*public List<SubmissionDTO> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionDTO> submissions) {
        this.submissions = submissions;
    }*/

    public String[] getFreeTonAddresses() {
        return freeTonAddresses;
    }

    public void setFreeTonAddresses(String[] freeTonAddresses) {
        this.freeTonAddresses = freeTonAddresses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChosedFreeTonAddress() {
        return chosedFreeTonAddress;
    }

    public void setChosedFreeTonAddress(String chosedFreeTonAddress) {
        this.chosedFreeTonAddress = chosedFreeTonAddress;
    }
}
