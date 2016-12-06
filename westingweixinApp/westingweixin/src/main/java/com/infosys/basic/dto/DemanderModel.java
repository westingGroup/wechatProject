package com.infosys.basic.dto;

public class DemanderModel extends SearchModel<DemanderDto> {

    private static final long serialVersionUID = 1L;

    private String conditions;

    private String startTime;

    private String endTime;

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
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

}
