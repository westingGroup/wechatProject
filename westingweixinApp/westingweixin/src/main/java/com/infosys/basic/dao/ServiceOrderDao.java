package com.infosys.basic.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.dto.ServiceOrderDto;
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

        List<Map<String, Object>> userListInDB = this.listBySql(sql, new Object[]{openid});
        List<ServiceOrderDto> orderDtoList = new ArrayList<ServiceOrderDto>();
        for (Map<String, Object> valuesMap : userListInDB) {
            ServiceOrderDto order = new ServiceOrderDto();
            order.setId(Integer.parseInt(valuesMap.get("id").toString()));
            order.setServiceOrderId(valuesMap.get("service_order_id").toString());
            order.setCategory(valuesMap.get("category").toString());
            order.setServiceType(valuesMap.get("service_type").toString());
            order.setCreateDate(valuesMap.get("create_date").toString());
            order.setContent(valuesMap.get("content").toString());
            order.setStatus(Integer.parseInt(valuesMap.get("status").toString()));
            order.setEvaluate(valuesMap.get("evaluate") == null ? "" : valuesMap.get("evaluate").toString());
            orderDtoList.add(order);
        }
        return orderDtoList;
    }
    
    public Pager<ServiceOrder> find(ServiceOrder order,int pageSize,int pageOffset) {
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

    private long getDemanderTotalByConditions() {
        StringBuffer sb = new StringBuffer();
        long num = 0L;
        sb.append("select count(*) as total from t_demander c where STATUS=1");
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        for (Map<String, Object> valuesMap : userListInDB) {
            num = new BigDecimal(valuesMap.get("total").toString()).intValue();
        }
        sb = null;
        return num;
    }
    
    @Override
    public PagerInfo<DemanderDto> listDemanderByKewword(DemanderModel demanderModel) {
        PagerInfo<DemanderDto> demanderDtoPager = demanderModel.getPager();
        demanderDtoPager.setTotalRecords(this.getDemanderTotalByConditions()); // 总数
        StringBuffer sb = new StringBuffer(
                "SELECT id,IFNULL(DATE_FORMAT(birthDate,'%Y-%m-%d'),'') birthDate ,IFNULL(business,'') business,IFNULL(company,'') company,IFNULL(linkname,'') linkname,IFNULL(linkphone,'') linkphone,IFNULL(openid,'') openid,IFNULL(qualification,'') qualification,IFNULL(remark,'') remark,STATUS FROM t_demander WHERE STATUS=1");
       sb.append(" limit ").append((demanderDtoPager.getCurrentPage() - 1)
               * demanderDtoPager.getPageSize()).append(",").append(demanderDtoPager.getPageSize());
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        List<DemanderDto> DemanderDtoList = new ArrayList<DemanderDto>();
        for (Map<String, Object> valuesMap : userListInDB) {
            DemanderDto demanderDto = new DemanderDto();
            demanderDto.setId(Integer.valueOf(valuesMap.get("id").toString()));
            demanderDto.setBirthDate(valuesMap.get("birthDate").toString());
            demanderDto.setBusiness(valuesMap.get("business").toString());
            demanderDto.setCompany(valuesMap.get("company").toString());
            demanderDto.setLinkname(valuesMap.get("linkname").toString());
            demanderDto.setLinkphone(valuesMap.get("linkphone").toString());
            demanderDto.setOpenid(valuesMap.get("openid").toString());
            demanderDto.setQualification(valuesMap.get("qualification").toString());
            demanderDto.setRemark(valuesMap.get("remark").toString());
            demanderDto.setStatus(Integer.valueOf(valuesMap.get("STATUS").toString()));
            DemanderDtoList.add(demanderDto);
            demanderDto = null;
        }
        demanderDtoPager.setRecords(DemanderDtoList);
        sb = null;
        return demanderDtoPager;
    }

}
