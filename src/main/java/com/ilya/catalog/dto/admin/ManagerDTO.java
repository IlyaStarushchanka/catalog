package com.ilya.catalog.dto.admin;

import com.ilya.catalog.domain.AccessEnum;

public class ManagerDTO {

    private long id;
    private String nickName;
    private String access;

    public boolean isAdmin(){
        if (AccessEnum.ADMIN.name().equals(access)){
            return true;
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
