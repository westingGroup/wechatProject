package com.infosys.basic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_words")
public class Words implements Serializable {

    private static final long serialVersionUID = 5822188560118825929L;

    @Id
    @GeneratedValue
    private int id;

    private String brief;

    @Column(name = "HTML_CONTENTS")
    @Lob
    private String htmlContents; // 内容

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATE_TIME")
    private Date lastUpdateTime; // 最后更新时间

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getHtmlContents() {
        return htmlContents;
    }

    public void setHtmlContents(String htmlContents) {
        this.htmlContents = htmlContents;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}
