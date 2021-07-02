package com.ilya.catalog.dto.admin;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResponseFile {

    private String name;
    private String url;
    private String type;
    private long size;
    private String id;

    public ResponseFile(String name, String url, String type, long size, String id) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.id = id;
    }

    public ResponseFile(String name, String type, String id) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(id)
                .toUriString();
    }

    public ResponseFile() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}