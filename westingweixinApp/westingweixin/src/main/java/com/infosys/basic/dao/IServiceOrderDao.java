package com.infosys.basic.dao;

import java.util.List;

import com.infosys.basic.dao.base.IBaseDao;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.dto.ServiceOrderDto;
import com.infosys.basic.dto.ServiceOrderModel;
import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.util.model.Pager;

public interface IServiceOrderDao extends IBaseDao<ServiceOrder>{
	public ServiceOrder loadByServiceOrderId(String serviceOrderId);
	public List<ServiceOrder> list(ServiceOrder order);
    public List<ServiceOrder> listProvider(ServiceOrder order);
    public List<ServiceOrderDto> listProviderServiceOrder(String openid);
    public Pager<ServiceOrder> find(ServiceOrder order,int pageSize,int pageOffset);
    public PagerInfo<ServiceOrderDto> listServiceOrderByKeyword(ServiceOrderModel serviceOrderModel);
    public PagerInfo<ServiceOrderDto> listServiceOrderByKeywordForMobileApply(ServiceOrderModel serviceOrderModel);
    public PagerInfo<ServiceOrderDto> listServiceOrderByKeywordForMyMobileApplys(ServiceOrderModel serviceOrderModel);
    public PagerInfo<ServiceOrderDto> listProcessServiceOrders(ServiceOrderModel demanderSearchModal);
    public long getOrdersTotalByConditionsForProcess(ServiceOrderModel demanderSearchModal);
}
