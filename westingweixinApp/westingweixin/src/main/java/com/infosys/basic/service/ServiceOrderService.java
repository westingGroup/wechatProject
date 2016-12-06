package com.infosys.basic.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.dao.IServiceOrderDao;
import com.infosys.basic.dto.ServiceOrderDto;
import com.infosys.basic.entity.ServiceOrder;

@Service("serviceOrderService")
@Transactional
public class ServiceOrderService implements IServiceOrderService {
    @Inject
    private IServiceOrderDao serviceOrderDao;

    @Override
    public void add(ServiceOrder serviceOrder) {
        serviceOrderDao.add(serviceOrder);
    }

    @Override
    public void update(ServiceOrder serviceOrder) {
        serviceOrderDao.update(serviceOrder);
    }

    @Override
    public void delete(int id) {
        serviceOrderDao.delete(id);
    }

    @Override
    public ServiceOrder load(int id) {
        return serviceOrderDao.load(id);
    }

    @Override
    public ServiceOrder loadByServiceOrderId(String serviceOrderId) {
        return serviceOrderDao.loadByServiceOrderId(serviceOrderId);
    }

    @Override
    public List<ServiceOrder> listDemander(ServiceOrder order) {
        return serviceOrderDao.list(order);
    }

    @Override
    public List<ServiceOrder> listProvider(ServiceOrder order) {
        return serviceOrderDao.listProvider(order);
    }
    
    @Override
    public List<ServiceOrderDto> listProviderServiceOrder(String openid){
        return serviceOrderDao.listProviderServiceOrder(openid);
    }

}
