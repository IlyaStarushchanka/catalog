package com.ilya.catalog.dto.catalog;

public class SmallSubmissionCatalogDTO {

    private int linksCount;
    private int filesCount;
    private long id;
    private String number;
    private String rate;
    private String place;
    private String prize;
    private AuthorCatalogDTO author;
    private String authorFreeTonAddress;
    private String image;

    public SmallSubmissionCatalogDTO(long linksCount, long filesCount, long id, String number, String rate, String place,
                                     String prize, long authorId, String authorFreetonForumNickname, String authorFreeTonAddress) {
        this.linksCount = (int)linksCount;
        this.filesCount = (int)filesCount;
        this.id = id;
        this.number = number;
        this.rate = rate;
        this.place = place;
        this.prize = prize;
        this.author = new AuthorCatalogDTO(authorId, authorFreetonForumNickname);
        this.authorFreeTonAddress = authorFreeTonAddress.substring(0, 6) + "..." +
                authorFreeTonAddress.substring(authorFreeTonAddress.length() - 5, authorFreeTonAddress.length() - 1);
        this.image = "/files/submission/img/" + id;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(int filesCount) {
        this.filesCount = filesCount;
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

    public AuthorCatalogDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorCatalogDTO author) {
        this.author = author;
    }

    public int getLinksCount() {
        return linksCount;
    }

    public void setLinksCount(int linksCount) {
        this.linksCount = linksCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
