package com.infosys.basic.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.dto.ServiceOrderDto;
import com.infosys.basic.dto.ServiceOrderModel;
import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.util.model.Pager;

@Repository("serviceOrderDao")
public class ServiceOrderDao extends BaseDao<ServiceOrder> implements IServiceOrderDao {

    @Override
    public ServiceOrder loadByServiceOrderId(String serviceOrderId) {
        return (ServiceOrder) super.queryObject("from ServiceOrder where serviceOrderId=?", serviceOrderId);
    }

    @Override
    public List<ServiceOrder> list(ServiceOrder order) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from ServiceOrder where 1=1 ");
        if (order.getCreateBy() > 0) {
            hql.append(" and createBy = ? ");
            params.add(order.getCreateBy());
        }
        hql.append(" order by createDate desc ");
        return super.list(hql.toString(), params.toArray());
    }

    @Override
    public List<ServiceOrder> listProvider(ServiceOrder order) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from ServiceOrder where status = 0 ");
        hql.append(" order by createDate desc ");
        return super.list(hql.toString(), params.toArray());
    }

    @Override
    public List<ServiceOrderDto> listProviderServiceOrder(String openid) {
        String sql = "SELECT s.id,s.service_order_id,s.category,s.service_type,DATE_FORMAT(s.create_date,'%Y-%m-%d %T') create_date,s.content,s.status,s.evaluate "
                + "FROM t_service_order s WHERE s.status =0 OR (s.status=1 AND s.id NOT IN (SELECT s_id FROM t_apply a WHERE a.apply_by =?))";

        List<Map<String, Object>> userListInDB = this.listBySql(sql, new Object[] { openid });
        List<ServiceOrderDto> orderDtoList = new ArrayList<ServiceOrderDto>();
        for (Map<String, Object> valuesMap : userListInDB) {
            ServiceOrderDto order = new ServiceOrderDto();
            order.setId(Integer.parseInt(valuesMap.get("id").toString()));
            order.setServiceOrderId(valuesMap.get("service_order_id").toString());
            order.setCategory(valuesMap.get("category").toString());
            order.setServiceType(valuesMap.get("service_type").toString());
            order.setCreateDate(valuesMap.get("create_date").toString());
            order.setContent(valuesMap.get("content").toString());
            order.setStatus(valuesMap.get("status").toString());
            order.setEvaluate(valuesMap.get("evaluate") == null ? "" : valuesMap.get("evaluate").toString());
            orderDtoList.add(order);
        }
        return orderDtoList;
    }

    public Pager<ServiceOrder> find(ServiceOrder order, int pageSize, int pageOffset) {
        getSystemRequest().setPageSize(pageSize);
        getSystemRequest().setPageOffset(pageOffset);
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from ServiceOrder where 1=1 ");
        if (order.getCreateBy() > 0) {
            hql.append(" and createBy = ? ");
            params.add(order.getCreateBy());
        }
        hql.append(" order by createDate desc ");
        return super.find(hql.toString(), params.toArray());
    }

    private long getOrdersTotalByConditions(ServiceOrderModel serviceOrderModel) {
        StringBuffer sb = new StringBuffer();
        long num = 0L;
        sb.append("select count(*) as total from t_service_order c where 1=1");
        if (StringUtils.isNotBlank(serviceOrderModel.getCreateBy())) {
            sb.append(" and create_by =").append(serviceOrderModel.getCreateBy());
        }
        if (StringUtils.isNotBlank(serviceOrderModel.getLinkname())) {
            sb.append(" and linkname like '%").append(serviceOrderModel.getLinkname()).append("%'");
        }
        if (StringUtils.isNotBlank(serviceOrderModel.getLinkphone())) {
            sb.append(" and linkphone like '%").append(serviceOrderModel.getLinkphone()).append("%'");
        }
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        for (Map<String, Object> valuesMap : userListInDB) {
            num = new BigDecimal(valuesMap.get("total").toString()).intValue();
        }
        sb = null;
        return num;
    }

    @Override
    public PagerInfo<ServiceOrderDto> listServiceOrderByKeyword(ServiceOrderModel serviceOrderModel) {
        PagerInfo<ServiceOrderDto> dtoPager = serviceOrderModel.getPager();
        dtoPager.setTotalRecords(this.getOrdersTotalByConditions(serviceOrderModel)); // 总数
        StringBuffer sb = new StringBuffer(
                "SELECT id,service_order_id,category,service_type,DATE_FORMAT(create_date,'%Y-%m-%d %T') create_date, "
                        + "content,IFNULL(evaluate,'') evaluate,CASE STATUS WHEN 0 THEN '新需求' WHEN 1 THEN '待分配' "
                        + "WHEN 2 THEN '处理中' WHEN 9 THEN '已完成' WHEN 10 THEN '废单'  WHEN 11 THEN '已评价' ELSE '其他' END AS status FROM t_service_order where 1=1 ");
        if (StringUtils.isNotBlank(serviceOrderModel.getCreateBy())) {
            sb.append(" and create_by =").append(serviceOrderModel.getCreateBy());
        }
        if (StringUtils.isNotBlank(serviceOrderModel.getLinkname())) {
            sb.append(" and linkname like '%").append(serviceOrderModel.getLinkname()).append("%'");
        }
        if (StringUtils.isNotBlank(serviceOrderModel.getLinkphone())) {
            sb.append(" and linkphone like '%").append(serviceOrderModel.getLinkphone()).append("%'");
        }
        sb.append(" order by create_date desc limit ").append((dtoPager.getCurrentPage() - 1) * dtoPager.getPageSize())
                .append(",").append(dtoPager.getPageSize());
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        List<ServiceOrderDto> dtoList = new ArrayList<ServiceOrderDto>();
        for (Map<String, Object> valuesMap : userListInDB) {
            ServiceOrderDto dto = new ServiceOrderDto();
            dto.setId(Integer.valueOf(valuesMap.get("id").toString()));
            dto.setServiceOrderId(valuesMap.get("service_order_id").toString());
            dto.setCategory(valuesMap.get("category").toString());
            dto.setServiceType(valuesMap.get("service_type").toString());
            dto.setCreateDate(valuesMap.get("create_date").toString());
            dto.setContent(valuesMap.get("content").toString());
            dto.setEvaluate(valuesMap.get("evaluate").toString());
            dto.setStatus(valuesMap.get("status").toString());
            dtoList.add(dto);
            dto = null;
        }
        dtoPager.setRecords(dtoList);
        sb = null;
        return dtoPager;
    }

    private long getOrdersTotalByConditionsForMobileApply(ServiceOrderModel serviceOrderModel) {
        StringBuffer sb = new StringBuffer();
        long num = 0L;
        sb.append("select count(*) as total from t_service_order s where 1=1");
        if (StringUtils.isNotBlank(serviceOrderModel.getApplyBy())) {
            sb.append(" and s.status =0 OR (s.status=1 AND s.id NOT IN (SELECT s_id FROM t_apply a WHERE a.apply_by =")
                    .append(serviceOrderModel.getApplyBy()).append("))");
        }
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        for (Map<String, Object> valuesMap : userListInDB) {
            num = new BigDecimal(valuesMap.get("total").toString()).intValue();
        }
        sb = null;
        return num;
    }

    @Override
    public PagerInfo<ServiceOrderDto> listServiceOrderByKeywordForMobileApply(ServiceOrderModel serviceOrderModel) {
        PagerInfo<ServiceOrderDto> dtoPager = serviceOrderModel.getPager();
        dtoPager.setTotalRecords(this.getOrdersTotalByConditionsForMobileApply(serviceOrderModel)); // 总数
        StringBuffer sb = new StringBuffer(
                "SELECT s.id id,s.service_order_id service_order_id,s.category category,s.service_type service_type,"
                        + "DATE_FORMAT(s.create_date,'%Y-%m-%d %T') create_date,s.content content,"
                        + "CASE s.status WHEN 0 THEN '新需求' WHEN 1 THEN '待分配' WHEN 2 THEN '处理中' WHEN 9 THEN '已完成' WHEN 10 THEN '废单' WHEN 11 THEN '已评价' ELSE '其他' END AS status,"
                        + "IFNULL(s.evaluate,'') evaluate FROM t_service_order s " + "WHERE 1=1 ");

        if (StringUtils.isNotBlank(serviceOrderModel.getApplyBy())) {
            sb.append(" and s.status =0 OR (s.status=1 AND s.id NOT IN (SELECT s_id FROM t_apply a WHERE a.apply_by =")
                    .append(serviceOrderModel.getApplyBy()).append("))");
        }
        sb.append(" order by create_date desc limit ").append((dtoPager.getCurrentPage() - 1) * dtoPager.getPageSize())
                .append(",").append(dtoPager.getPageSize());
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        List<ServiceOrderDto> dtoList = new ArrayList<ServiceOrderDto>();
        for (Map<String, Object> valuesMap : userListInDB) {
            ServiceOrderDto dto = new ServiceOrderDto();
            dto.setId(Integer.valueOf(valuesMap.get("id").toString()));
            dto.setServiceOrderId(valuesMap.get("service_order_id").toString());
            dto.setCategory(valuesMap.get("category").toString());
            dto.setServiceType(valuesMap.get("service_type").toString());
            dto.setCreateDate(valuesMap.get("create_date").toString());
            dto.setContent(valuesMap.get("content").toString());
            dto.setEvaluate(valuesMap.get("evaluate").toString());
            dto.setStatus(valuesMap.get("status").toString());
            dtoList.add(dto);
            dto = null;
        }
        dtoPager.setRecords(dtoList);
        sb = null;
        return dtoPager;
    }

    private long getOrdersTotalByConditionsForMyMobileApplys(ServiceOrderModel serviceOrderModel) {
        StringBuffer sb = new StringBuffer();
        long num = 0L;
        sb.append("select count(*) as total from t_service_order s where 1=1");
        if (StringUtils.isNotBlank(serviceOrderModel.getDealBy())) {
            sb.append(" and (s.deal_by=")
                    .append(serviceOrderModel.getDealBy())
                    .append(" AND(s.STATUS=2 OR s.STATUS=9)) OR (s.status=1 AND s.id IN (SELECT a.s_id FROM t_apply a WHERE a.apply_by =")
                    .append(serviceOrderModel.getDealBy()).append("))");
        }
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        for (Map<String, Object> valuesMap : userListInDB) {
            num = new BigDecimal(valuesMap.get("total").toString()).intValue();
        }
        sb = null;
        return num;
    }

    @Override
    public PagerInfo<ServiceOrderDto> listServiceOrderByKeywordForMyMobileApplys(ServiceOrderModel serviceOrderModel) {
        PagerInfo<ServiceOrderDto> dtoPager = serviceOrderModel.getPager();
        dtoPager.setTotalRecords(this.getOrdersTotalByConditionsForMyMobileApplys(serviceOrderModel)); // 总数
        StringBuffer sb = new StringBuffer(
                "SELECT s.id id,s.service_order_id service_order_id,s.category category,s.service_type service_type,"
                        + "DATE_FORMAT(s.create_date,'%Y-%m-%d %T') create_date,IFNULL(DATE_FORMAT(s.deal_date,'%Y-%m-%d %T'),'') deal_date,s.content content,"
                        + "CASE s.status WHEN 0 THEN '新需求' WHEN 1 THEN '待分配' WHEN 2 THEN '处理中' WHEN 9 THEN '已完成' WHEN 10 THEN '废单' WHEN 11 THEN '已评价' ELSE '其他' END AS status,"
                        + "IFNULL(s.evaluate,'') evaluate FROM t_service_order s " + "WHERE 1=1 ");

        if (StringUtils.isNotBlank(serviceOrderModel.getDealBy())) {
            sb.append(" and (s.deal_by=")
                    .append(serviceOrderModel.getDealBy())
                    .append(" AND(s.STATUS=2 OR s.STATUS=9)) OR (s.status=1 AND s.id IN (SELECT a.s_id FROM t_apply a WHERE a.apply_by =")
                    .append(serviceOrderModel.getDealBy()).append("))");
        }
        sb.append(" order by create_date desc limit ").append((dtoPager.getCurrentPage() - 1) * dtoPager.getPageSize())
                .append(",").append(dtoPager.getPageSize());
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        List<ServiceOrderDto> dtoList = new ArrayList<ServiceOrderDto>();
        for (Map<String, Object> valuesMap : userListInDB) {
            ServiceOrderDto dto = new ServiceOrderDto();
            dto.setId(Integer.valueOf(valuesMap.get("id").toString()));
            dto.setServiceOrderId(valuesMap.get("service_order_id").toString());
            dto.setCategory(valuesMap.get("category").toString());
            dto.setServiceType(valuesMap.get("service_type").toString());
            dto.setCreateDate(valuesMap.get("create_date").toString());
            dto.setDealDate(valuesMap.get("deal_date").toString());
            dto.setContent(valuesMap.get("content").toString());
            dto.setEvaluate(valuesMap.get("evaluate").toString());
            dto.setStatus(valuesMap.get("status").toString());
            dtoList.add(dto);
            dto = null;
        }
        dtoPager.setRecords(dtoList);
        sb = null;
        return dtoPager;
    }

    public long getOrdersTotalByConditionsForProcess(ServiceOrderModel demanderSearchModal) {
        StringBuffer sb = new StringBuffer();
        long num = 0L;
        sb.append("select count(*) as total from t_service_order s where 1=1");
        if (StringUtils.isNotBlank(demanderSearchModal.getStatus())) {
            if (Integer.parseInt(demanderSearchModal.getStatus()) == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_APPLY) {
                sb.append(" and (s.status=").append(demanderSearchModal.getStatus()).append(" or").append(" s.status=")
                        .append(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_NEW).append(")");
            } else if (Integer.parseInt(demanderSearchModal.getStatus()) == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_DONE) {
                sb.append(" and (s.status=").append(demanderSearchModal.getStatus()).append(" or").append(" s.status=")
                        .append(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_EVALUATE).append(")");
            } else {
                sb.append(" and s.status=").append(demanderSearchModal.getStatus());
            }
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getServiceOrderId())) {
            sb.append(" and service_order_id like '%").append(demanderSearchModal.getServiceOrderId()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getLinkname())) {
            sb.append(" and linkname like '%").append(demanderSearchModal.getLinkname()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getLinkphone())) {
            sb.append(" and linkphone like '%").append(demanderSearchModal.getLinkphone()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getCategory())) {
            sb.append(" and category like '%").append(demanderSearchModal.getCategory()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getServiceType())) {
            sb.append(" and service_type like '%").append(demanderSearchModal.getServiceType()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getEvaluate())) {
            sb.append(" and evaluate like '%").append(demanderSearchModal.getEvaluate()).append("%'");
        }
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        for (Map<String, Object> valuesMap : userListInDB) {
            num = new BigDecimal(valuesMap.get("total").toString()).intValue();
        }
        sb = null;
        return num;
    }

    @Override
    public PagerInfo<ServiceOrderDto> listProcessServiceOrders(ServiceOrderModel demanderSearchModal) {
        PagerInfo<ServiceOrderDto> dtoPager = demanderSearchModal.getPager();
        dtoPager.setTotalRecords(this.getOrdersTotalByConditionsForProcess(demanderSearchModal)); // 总数
        StringBuffer sb = new StringBuffer(
                "SELECT s.id id,s.service_order_id service_order_id,s.category category,s.service_type service_type,"
                        + "DATE_FORMAT(s.create_date,'%Y-%m-%d %T') create_date,IFNULL(DATE_FORMAT(s.deal_date,'%Y-%m-%d %T'),'') deal_date,s.content content,"
                        + "CASE s.status WHEN 0 THEN '新需求' WHEN 1 THEN '待分配' WHEN 2 THEN '处理中' WHEN 9 THEN '已完成' WHEN 10 THEN '废单' WHEN 11 THEN '已评价' ELSE '其他' END AS status,"
                        + "IFNULL(s.evaluate,'') evaluate,IFNULL(s.linkname,'') linkname,IFNULL(s.linkphone,'') linkphone,"
                        + "IFNULL(s.dealname,'') dealname FROM t_service_order s " + "WHERE 1=1 ");

        if (StringUtils.isNotBlank(demanderSearchModal.getStatus())) {
            if (Integer.parseInt(demanderSearchModal.getStatus()) == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_APPLY) {
                sb.append(" and (s.status=").append(demanderSearchModal.getStatus()).append(" or").append(" s.status=")
                        .append(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_NEW).append(")");
            } else if (Integer.parseInt(demanderSearchModal.getStatus()) == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_DONE) {
                sb.append(" and (s.status=").append(demanderSearchModal.getStatus()).append(" or").append(" s.status=")
                        .append(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_EVALUATE).append(")");
            } else {
                sb.append(" and s.status=").append(demanderSearchModal.getStatus());
            }
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getServiceOrderId())) {
            sb.append(" and service_order_id like '%").append(demanderSearchModal.getServiceOrderId()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getLinkname())) {
            sb.append(" and linkname like '%").append(demanderSearchModal.getLinkname()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getLinkphone())) {
            sb.append(" and linkphone like '%").append(demanderSearchModal.getLinkphone()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getCategory())) {
            sb.append(" and category like '%").append(demanderSearchModal.getCategory()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getServiceType())) {
            sb.append(" and service_type like '%").append(demanderSearchModal.getServiceType()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderSearchModal.getEvaluate())) {
            sb.append(" and evaluate like '%").append(demanderSearchModal.getEvaluate()).append("%'");
        }

        sb.append(" order by id asc limit ").append((dtoPager.getCurrentPage() - 1) * dtoPager.getPageSize())
                .append(",").append(dtoPager.getPageSize());
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        List<ServiceOrderDto> dtoList = new ArrayList<ServiceOrderDto>();
        for (Map<String, Object> valuesMap : userListInDB) {
            ServiceOrderDto dto = new ServiceOrderDto();
            dto.setId(Integer.valueOf(valuesMap.get("id").toString()));
            dto.setServiceOrderId(valuesMap.get("service_order_id").toString());
            dto.setCategory(valuesMap.get("category").toString());
            dto.setServiceType(valuesMap.get("service_type").toString());
            dto.setCreateDate(valuesMap.get("create_date").toString());
            dto.setDealDate(valuesMap.get("deal_date").toString());
            dto.setContent(valuesMap.get("content").toString());
            dto.setEvaluate(valuesMap.get("evaluate").toString());
            dto.setStatus(valuesMap.get("status").toString());
            dto.setLinkname(valuesMap.get("linkname").toString());
            dto.setLinkphone(valuesMap.get("linkphone").toString());
            dto.setDealname(valuesMap.get("dealname").toString());
            dtoList.add(dto);
            dto = null;
        }
        dtoPager.setRecords(dtoList);
        sb = null;
        return dtoPager;
    }

}
