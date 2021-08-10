package com.ilya.catalog.dto.admin;

public class SmallAuthorAdminDTO {

    private long id;
    private String freetonForumNickname;
    private String[] freeTonAddresses;
    private String chosedFreeTonAddress;

    public SmallAuthorAdminDTO(long id, String freetonForumNickname) {
        this.id = id;
        this.freetonForumNickname = freetonForumNickname;
        //this.freeTonAddresses = freeTonAddresses.stream().map(FreeTonAddress::getAddress).toArray(String[]::new);
    }

    public SmallAuthorAdminDTO() {
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

    public String[] getFreeTonAddresses() {
        return freeTonAddresses;
    }

    public void setFreeTonAddresses(String[] freeTonAddresses) {
        this.freeTonAddresses = freeTonAddresses;
    }

    public String getChosedFreeTonAddress() {
        return chosedFreeTonAddress;
    }

    public void setChosedFreeTonAddress(String chosedFreeTonAddress) {
        this.chosedFreeTonAddress = chosedFreeTonAddress;
    }
}
