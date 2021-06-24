package by.ilya.catalog.dto;

import java.util.List;

public class AuthorDTO {

    private long id;
    private String telegramNickname;
    private String freetonForumNickname;
    private List<SubmissionDTO> submissions;
    private String[] freeTonAddresses;

    public String getTelegramNickname() {
        return telegramNickname;
    }

    public void setTelegramNickname(String telegramNickname) {
        this.telegramNickname = telegramNickname;
    }

    public String getFreetonForumNickname() {
        return freetonForumNickname;
    }

    public void setFreetonForumNickname(String freetonForumNickname) {
        this.freetonForumNickname = freetonForumNickname;
    }

    public List<SubmissionDTO> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionDTO> submissions) {
        this.submissions = submissions;
    }

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
}
