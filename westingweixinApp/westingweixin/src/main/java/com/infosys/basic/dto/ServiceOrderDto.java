package com.infosys.basic.dto;

import java.io.Serializable;

/**
 * 
 * 
 * 项目名称：westingweixin 类名称：ServiceOrderDto 类描述： 创建人：Anne_Yan 创建时间：2016年12月5日
 * 上午11:48:03 修改人：Anne_Yan 修改时间：2016年12月5日 上午11:48:03 修改备注：
 * 
 * @version
 *
 */
public class ServiceOrderDto implements Serializable {

    /**
     * 
     * @since Ver 1.1
     */

    private static final long serialVersionUID = 2401258452953365051L;

    private int id;

    private String serviceOrderId;

    private String category;// A 电缆 B 灯具 C 电器件 D 其他

    private String serviceType;

    private String linkname;

    private String linkphone;

    private String content;

    private String createDate;

    private int createBy;

    private String createname;

    private String lastApplyDate;

    private int lastApplyBy;

    private String lastApplyName;

    private String status;

    private String evaluate;// 服务评价

    private String dealDate;

    private int dealBy;

    private String dealname;

    private String remark1;

    private String remark2;

    private String remark3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }

    public String getLastApplyDate() {
        return lastApplyDate;
    }

    public void setLastApplyDate(String lastApplyDate) {
        this.lastApplyDate = lastApplyDate;
    }

    public int getLastApplyBy() {
        return lastApplyBy;
    }

    public void setLastApplyBy(int lastApplyBy) {
        this.lastApplyBy = lastApplyBy;
    }

    public String getLastApplyName() {
        return lastApplyName;
    }

    public void setLastApplyName(String lastApplyName) {
        this.lastApplyName = lastApplyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public int getDealBy() {
        return dealBy;
    }

    public void setDealBy(int dealBy) {
        this.dealBy = dealBy;
    }

    public String getDealname() {
        return dealname;
    }

    public void setDealname(String dealname) {
        this.dealname = dealname;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

}