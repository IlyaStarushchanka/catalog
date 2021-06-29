package by.ilya.catalog.dto.catalog;

import by.ilya.catalog.dto.admin.ResponseFile;

import java.util.List;

public class SubmissionCatalogDTO {

    private List<ResponseFile> files;
    private long id;
    private String description;
    private String number;
    private String rate;
    private String place;
    private String prize;
    private String authorFreeTonAddress;
    private SmallContestCatalogDTO contest;
    private AuthorCatalogDTO author;

    public List<ResponseFile> getFiles() {
        return files;
    }

    public void setFiles(List<ResponseFile> files) {
        this.files = files;
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

    public SmallContestCatalogDTO getContest() {
        return contest;
    }

    public void setContest(SmallContestCatalogDTO contest) {
        this.contest = contest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorCatalogDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorCatalogDTO author) {
        this.author = author;
    }
}
