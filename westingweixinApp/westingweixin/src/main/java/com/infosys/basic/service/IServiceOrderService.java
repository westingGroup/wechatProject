package com.infosys.basic.service;

import java.util.List;

import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.dto.ServiceOrderDto;
import com.infosys.basic.dto.ServiceOrderModel;
import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.util.model.Pager;

public interface IServiceOrderService {
	public void add(ServiceOrder serviceOrder);
	public void update(ServiceOrder serviceOrder);
	public void delete(int id);
	public ServiceOrder load(int id);
	public ServiceOrder loadByServiceOrderId(String serviceOrderId);
	public List<ServiceOrder> listDemander(ServiceOrder order);
	public List<ServiceOrder> listProvider(ServiceOrder order);
    public List<ServiceOrderDto> listProviderServiceOrder(String openid);
    public Pager<ServiceOrder> find(ServiceOrder order,int pageSize,int pageOffset) ;
    public PagerInfo<ServiceOrderDto> listServiceOrderByKeyword(ServiceOrderModel serviceOrderModel);
}
