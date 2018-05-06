package com.qming.yunprint.model;

import java.util.Date;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-06-22:46
 */
public class FileEntity {
    private int id;
    private String fileUuid;
    private int ownerId;
    private String name;
    private String type;
    private Date uploadTime;
    private int pages;
    private String documentId;
    private String coverUrl;
    private String md5;

    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", fileUuid='" + fileUuid + '\'' +
                ", ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", uploadTime=" + uploadTime +
                ", pages=" + pages +
                '}';
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
