package com.ilya.catalog.dto.catalog;

import com.ilya.catalog.dto.admin.LinkDBDTO;
import com.ilya.catalog.dto.admin.ResponseFile;

import java.util.List;

public class SubmissionCatalogDTO {

    private List<ResponseFile> files;
    private List<LinkDBDTO> links;
    private long id;
    private String smallDescription;
    private String bigDescription;
    private String number;
    private String rate;
    private String place;
    private String prize;
    private String authorFreeTonAddress;
    private SmallestContestCatalogDTO contest;
    private AuthorCatalogDTO author;
    private String statisticsLink;
    private String statisticsShortLink;
    private String image;

    public SubmissionCatalogDTO(long id, String smallDescription, String bigDescription, String number, String rate,
                                String place, String prize, String authorFreeTonAddress, long contestId,
                                String contestName, long subGovId, String subGovName, long authorId,
                                String authorFreetonForumNickname, String statisticsLink) {
        this.id = id;
        this.smallDescription = smallDescription;
        this.bigDescription = bigDescription;
        this.number = number;
        this.rate = rate;
        this.place = place;
        this.prize = prize;
        this.authorFreeTonAddress = authorFreeTonAddress;
        this.contest = new SmallestContestCatalogDTO(contestId, contestName, subGovId, subGovName);
        this.author = new AuthorCatalogDTO(authorId, authorFreetonForumNickname);
        this.statisticsLink = statisticsLink;
        if (statisticsLink != null && statisticsLink.length() > 40){
            this.statisticsShortLink = statisticsLink.substring(0,40) + "...";
        } else {
            this.statisticsShortLink = statisticsLink;
        }
        this.image = "/files/submission/img/" + id;
    }

    public SubmissionCatalogDTO() {
    }

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

    public SmallestContestCatalogDTO getContest() {
        return contest;
    }

    public void setContest(SmallestContestCatalogDTO contest) {
        this.contest = contest;
    }

    public AuthorCatalogDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorCatalogDTO author) {
        this.author = author;
    }

    public List<LinkDBDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDBDTO> links) {
        this.links = links;
    }

    public String getStatisticsLink() {
        return statisticsLink;
    }

    public void setStatisticsLink(String statisticsLink) {
        this.statisticsLink = statisticsLink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatisticsShortLink() {
        return statisticsShortLink;
    }

    public void setStatisticsShortLink(String statisticsShortLink) {
        this.statisticsShortLink = statisticsShortLink;
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

}
