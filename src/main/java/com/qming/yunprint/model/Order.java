package com.qming.yunprint.model;

import java.util.Date;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-03-06-22:50
 */
public class Order {
    private int id;
    private String uuid;
    private int ownerId;
    private String name;
    private String fileUuid;
    private double price;
    private int status;
    private Date createTime;
    private Date overTime;
    private boolean colorful;
    private boolean duplex;
    private String phoneNum;
    private int copies;
    private String address;
    private int page;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", fileUuid='" + fileUuid + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", createTime=" + createTime +
                ", overTime=" + overTime +
                ", colorful=" + colorful +
                ", duplex=" + duplex +
                ", phoneNum='" + phoneNum + '\'' +
                ", copies=" + copies +
                ", address='" + address + '\'' +
                ", page=" + page +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public boolean isColorful() {
        return colorful;
    }

    public void setColorful(boolean colorful) {
        this.colorful = colorful;
    }

    public boolean isDuplex() {
        return duplex;
    }

    public void setDuplex(boolean duplex) {
        this.duplex = duplex;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
