package com.infosys.basic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the w_service_order database table.
 * 
 */
/**
 * 
 * 项目名称：westingweixin 类名称：ServiceOrder 类描述： 创建人：Anne_Yan 创建时间：2016年11月29日
 * 下午8:54:21 修改人：Anne_Yan 修改时间：2016年11月29日 下午8:54:21 修改备注：
 * 
 * @version
 * 
 */
@Entity
@Table(name = "t_service_order")
@NamedQuery(name = "ServiceOrder.findAll", query = "SELECT c FROM ServiceOrder c")
public class ServiceOrder implements Serializable {

    /**
     * serialVersionUID:
     * 
     * @since Ver 1.1
     */

    private static final long serialVersionUID = -2160938685646042358L;

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "service_order_id")
    private String serviceOrderId;

    @Column(name = "category")
    private String category;// A 电缆 B 灯具 C 电器件 D 其他

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

    @Column(name = "service_type")
    private String serviceType;

    public static enum ServiceType {
        install("安装"), debug("调试"), repair("检修"), maintain("保养"), other("其他");

        private final String info;

        private ServiceType(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    private String linkname;

    private String linkphone;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "create_by")
    private int createBy;

    private String createname;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_apply_date")
    private Date lastApplyDate;

    @Column(name = "last_apply_by")
    private int lastApplyBy;

    @Column(name = "last_apply_name")
    private String lastApplyName;

    @Column(name = "status")
    private int status;

    private String evaluate;// 服务评价

    @Column(name = "evaluate_content")
    private String evaluateContent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deal_date")
    private Date dealDate;

    @Column(name = "deal_by")
    private int dealBy;

    private String dealname;

    private String remark1;

    private String remark2;

    private String remark3;

    @Column(name = "type", columnDefinition = "INT default 0")
    private int type;// 提供商处理 1内部 0 外部 自己撤销 9

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "complete_date")
    private Date completeDate;
    
    public ServiceOrder() {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public Date getLastApplyDate() {
        return lastApplyDate;
    }

    public void setLastApplyDate(Date lastApplyDate) {
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

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

}