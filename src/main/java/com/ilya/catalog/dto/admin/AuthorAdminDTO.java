package com.ilya.catalog.dto.admin;

public class AuthorAdminDTO {

    private long id;
    private String freetonForumNickname;

    public AuthorAdminDTO(long id, String freetonForumNickname) {
        this.id = id;
        this.freetonForumNickname = freetonForumNickname;
    }

    public AuthorAdminDTO() {
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
