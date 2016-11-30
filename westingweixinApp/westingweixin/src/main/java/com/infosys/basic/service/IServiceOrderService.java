package com.infosys.basic.service;

import java.util.List;

import com.infosys.basic.entity.ServiceOrder;

public interface IServiceOrderService {
	public void add(ServiceOrder serviceOrder);
	public void update(ServiceOrder serviceOrder);
	public void delete(int id);
	public ServiceOrder load(int id);
	public ServiceOrder loadByServiceOrderId(String serviceOrderId);
	public List<ServiceOrder> listDemander(ServiceOrder order);
	public List<ServiceOrder> listProvider(ServiceOrder order);
}
