package com.infosys.basic.dao;

import java.util.List;

import com.infosys.basic.dao.base.IBaseDao;
import com.infosys.basic.entity.ServiceOrder;

public interface IServiceOrderDao extends IBaseDao<ServiceOrder>{
	public ServiceOrder loadByServiceOrderId(String serviceOrderId);
	public List<ServiceOrder> list(ServiceOrder order);
}
