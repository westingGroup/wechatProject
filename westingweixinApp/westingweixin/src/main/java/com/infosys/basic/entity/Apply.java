package com.infosys.basic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_apply")
public class Apply implements Serializable {

    /**
     * serialVersionUID:
     * 
     * @since Ver 1.1
     */

    private static final long serialVersionUID = -2160938685646042358L;

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "s_id")
    private String sId;

    private String linkname;

    private String linkphone;

    private int price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "complete_date")
    private Date completeDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "apply_date")
    private Date applyDate;

    @Column(name = "apply_by")
    private int applyBy;

    private String applyname;

    @Column(name = "status")
    private int status;

    public Apply() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
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

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}