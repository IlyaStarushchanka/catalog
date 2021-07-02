package com.ilya.catalog.dto.catalog;

public class AuthorCatalogDTO {

    private long id;
    private String freetonForumNickname;

    public AuthorCatalogDTO(long id, String freetonForumNickname) {
        this.id = id;
        this.freetonForumNickname = freetonForumNickname;
    }

    public AuthorCatalogDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFreetonForumNickname() {
        return freetonForumNickname;
    }

    public void setFreetonForumNickname(String freetonForumNickname) {
        this.freetonForumNickname = freetonForumNickname;
    }
}
