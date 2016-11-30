package com.infosys.basic.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.dao.IServiceOrderDao;
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
    public List<ServiceOrder> list(ServiceOrder order) {
        List<ServiceOrder> orders = serviceOrderDao.list(order);
        /*if(orders!=null &&orders.size()>0){
            ServiceOrder sOrder;
            for (int i = 0; i < orders.size(); i++) {
                sOrder = orders.get(i);
                sOrder.setCategory(ServiceOrder.CategoryType.valueOf(sOrder.getCategory()).getInfo());
                sOrder.setServiceType(ServiceOrder.ServiceType.valueOf(sOrder.getServiceType()).getInfo());
            } 
        }*/
        return orders;
    }

}
