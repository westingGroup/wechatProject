package com.infosys.basic.dto;

import java.io.Serializable;
import java.util.Date;

public class ApplyDto implements Serializable {

    private static final long serialVersionUID = 1198105000108381610L;

    private int applyBy;

    private String applyname;

    private int price;

    private Date completeDate;

    private String linkphone;

    private String business;

    public int getApplyBy() {
        return applyBy;
    }

    public void setApplyBy(int applyBy) {
        this.applyBy = applyBy;
    }

    public String getApplyname() {
        return applyname;
    }

    public void setApplyname(String applyname) {
        this.applyname = applyname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

}