package com.infosys.basic.dto;

public class ServiceOrderModel extends SearchModel<ServiceOrderDto> {

    private static final long serialVersionUID = 1L;

    private String startTime;

    private String endTime;

    private String linkname;

    private String linkphone;

    private String status;

    private String createBy;

    private String applyBy;

    private String dealBy;

    private String serviceOrderId;

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getApplyBy() {
        return applyBy;
    }

    public void setApplyBy(String applyBy) {
        this.applyBy = applyBy;
    }

    public String getDealBy() {
        return dealBy;
    }

    public void setDealBy(String dealBy) {
        this.dealBy = dealBy;
    }

}
