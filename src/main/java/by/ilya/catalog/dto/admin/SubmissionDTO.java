package by.ilya.catalog.dto.admin;

import java.util.ArrayList;
import java.util.List;

public class SubmissionDTO {

    private long id;
    private ContestDTO contest;
    private String number;
    private AuthorDTO author;
    private String image;

    private List<ResponseFile> files = new ArrayList<>();
    private String authorFreeTonAddress;
    private String description;
    private String rate;
    private String place;
    private String prize;
    private String statisticsLink;
    private List<LinkDBDTO> links = new ArrayList<>();


    public ContestDTO getContest() {
        return contest;
    }

    public void setContest(ContestDTO contest) {
        this.contest = contest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorFreeTonAddress() {
        return authorFreeTonAddress;
    }

    public void setAuthorFreeTonAddress(String authorFreeTonAddress) {
        this.authorFreeTonAddress = authorFreeTonAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public List<ResponseFile> getFiles() {
        return files;
    }

    public void setFiles(List<ResponseFile> files) {
        this.files = files;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatisticsLink() {
        return statisticsLink;
    }

    public void setStatisticsLink(String statisticsLink) {
        this.statisticsLink = statisticsLink;
    }

    public List<LinkDBDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDBDTO> links) {
        this.links = links;
    }
}
