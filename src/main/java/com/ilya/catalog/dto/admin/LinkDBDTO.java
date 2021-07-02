package com.ilya.catalog.dto.admin;

public class LinkDBDTO {

    private Long id;
    private String name;

    private String shortUrl;
    private String url;
    private Long submissionId;

    public LinkDBDTO(String name, String url) {
        this.name = name;
        if (url != null && url.length() > 40){
            this.shortUrl = url.substring(0,40) + "...";
        } else {
            this.shortUrl = url;
        }
        this.url = url;
    }

    public LinkDBDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
