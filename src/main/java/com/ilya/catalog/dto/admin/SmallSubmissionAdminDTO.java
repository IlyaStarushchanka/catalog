package com.ilya.catalog.dto.admin;

public class SmallSubmissionAdminDTO {

    private long id;
    private String number;
    private String rate;
    private String place;
    private String prize;
    private AuthorAdminDTO author;
    private String authorFreeTonAddress;
    private SmallContestAdminDTO contest;

    public SmallSubmissionAdminDTO(long id, String number, String rate, String place,
                                   String prize, long authorId, String authorFreetonForumNickname, String authorFreeTonAddress, String contestName) {
        this.id = id;
        this.number = number;
        this.rate = rate;
        this.place = place;
        this.prize = prize;
        this.author = new AuthorAdminDTO(authorId, authorFreetonForumNickname);
        this.authorFreeTonAddress = authorFreeTonAddress;
        this.contest = new SmallContestAdminDTO(contestName);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getAuthorFreeTonAddress() {
        return authorFreeTonAddress;
    }

    public void setAuthorFreeTonAddress(String authorFreeTonAddress) {
        this.authorFreeTonAddress = authorFreeTonAddress;
    }

    public AuthorAdminDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorAdminDTO author) {
        this.author = author;
    }

    public SmallContestAdminDTO getContest() {
        return contest;
    }

    public void setContest(SmallContestAdminDTO contest) {
        this.contest = contest;
    }
}
