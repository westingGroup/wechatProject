package com.infosys.dao;


import org.springframework.stereotype.Repository;

import com.infosys.dao.base.BaseDao;
import com.infosys.entity.ServiceOrder;

@Repository("serviceOrderDao")
public class ServiceOrderDao extends BaseDao<ServiceOrder> implements IServiceOrderDao {


}
