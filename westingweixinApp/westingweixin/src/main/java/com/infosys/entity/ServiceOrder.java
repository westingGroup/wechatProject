package com.infosys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the w_service_order database table.
 * 
 */
@Entity
@Table(name = "W_SERVICE_ORDER")
@NamedQuery(name = "ServiceOrder.findAll", query = "SELECT c FROM ServiceOrder c")
public class ServiceOrder implements Serializable {

    /**
     * serialVersionUID:
     * 
     * @since Ver 1.1
     */

    private static final long serialVersionUID = -2160938685646042358L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SERVICE_ORDER_ID")
    private long serviceOrderId;

    @Column(name = "CATEGORY")
    private CategoryType category;// A 电缆 B 灯具 C 电器件 D 其他

    public static enum CategoryType {
        cable("A", "电缆"), Lamp("B", "灯具"), device("C", "电器件"), other("D", "其他");

        private final String id;

        private final String info;

        private CategoryType(String id, String info) {
            this.id = id;
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public String getId() {
            return id;
        }
    }

    @Column(name = "SERVICE_TYPE")
    private String serviceType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "remark1")
    private String remark1;

    @Column(name = "remark2")
    private String remark2;

    @Column(name = "remark3")
    private String remark3;

    @Column(name = "STATUS")
    private Status status = Status.requirement;

    public static enum Status {
        requirement("新需求"), dealing("处理中"), done("已完成"), finished("完结");

        private final String info;

        private Status(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    public ServiceOrder() {
    }

    public long getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(long serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}