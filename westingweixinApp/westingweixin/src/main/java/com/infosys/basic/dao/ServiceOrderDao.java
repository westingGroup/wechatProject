package com.infosys.basic.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.entity.ServiceOrder;

@Repository("serviceOrderDao")
public class ServiceOrderDao extends BaseDao<ServiceOrder> implements IServiceOrderDao {

    @Override
    public ServiceOrder loadByServiceOrderId(String serviceOrderId) {
        return (ServiceOrder)super.queryObject("from ServiceOrder where serviceOrderId=?", serviceOrderId);
    }

    @Override
    public List<ServiceOrder> list(ServiceOrder order) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from ServiceOrder where 1=1 ") ;
        if(order.getCreateBy()>0){
            hql.append(" and createBy = ? ");
            params.add(order.getCreateBy());
        }
        hql.append(" order by createDate desc ");
        return super.list(hql.toString(), params.toArray());
    }




}
