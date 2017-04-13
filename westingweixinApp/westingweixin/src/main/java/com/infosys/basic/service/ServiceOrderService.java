package com.infosys.basic.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.dao.IApplyDao;
import com.infosys.basic.dao.IServiceOrderDao;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.dto.ServiceOrderDto;
import com.infosys.basic.dto.ServiceOrderModel;
import com.infosys.basic.entity.Apply;
import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.util.model.Pager;

@Service("serviceOrderService")
@Transactional
public class ServiceOrderService implements IServiceOrderService {
    @Inject
    private IServiceOrderDao serviceOrderDao;

    @Inject
    private IApplyDao applyDao;

    @Override
    public void add(ServiceOrder serviceOrder) {
        try {
            serviceOrderDao.add(serviceOrder);
        } catch (Exception e) {
            throw new RuntimeException("用户输入的内容包含特殊字符");
        }

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
    public ServiceOrder get(int id) {
        return serviceOrderDao.get(id);
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
    public List<ServiceOrderDto> listProviderServiceOrder(String openid) {
        return serviceOrderDao.listProviderServiceOrder(openid);
    }

    @Override
    public Pager<ServiceOrder> find(ServiceOrder order, int pageSize, int pageOffset) {
        return serviceOrderDao.find(order, pageSize, pageOffset);
    }

    @Override
    public PagerInfo<ServiceOrderDto> listServiceOrderByKeyword(ServiceOrderModel serviceOrderModel) {
        return serviceOrderDao.listServiceOrderByKeyword(serviceOrderModel);
    }

    @Override
    public PagerInfo<ServiceOrderDto> listServiceOrderByKeywordForMobileApply(ServiceOrderModel serviceOrderModel) {
        PagerInfo<ServiceOrderDto> pageInfo = serviceOrderDao
                .listServiceOrderByKeywordForMobileApply(serviceOrderModel);
        List<ServiceOrderDto> serviceList = pageInfo.getRecords();
        List<ServiceOrderDto> rtnList = new ArrayList<ServiceOrderDto>();
        if (serviceList != null && serviceList.size() > 0) {
            for (int i = 0; i < serviceList.size(); i++) {
                ServiceOrderDto dto = serviceList.get(i);
                List<Apply> apList = applyDao.listBySIdApplyBy(String.valueOf(dto.getId()),
                        serviceOrderModel.getApplyBy());
                if (apList != null && apList.size() > 0) {
                    dto.setApplyflag("1");
                }
                rtnList.add(dto);
            }
            pageInfo.setRecords(rtnList);
        }
        return pageInfo;
    }

    @Override
    public PagerInfo<ServiceOrderDto> listServiceOrderByKeywordForMyMobileApplys(ServiceOrderModel serviceOrderModel) {
        PagerInfo<ServiceOrderDto> pageInfo = serviceOrderDao
                .listServiceOrderByKeywordForMyMobileApplys(serviceOrderModel);
        List<ServiceOrderDto> serviceList = pageInfo.getRecords();
        List<ServiceOrderDto> rtnList = new ArrayList<ServiceOrderDto>();
        if (serviceList != null && serviceList.size() > 0) {
            for (int i = 0; i < serviceList.size(); i++) {
                ServiceOrderDto dto = serviceList.get(i);
                List<Apply> apList = applyDao.listBySIdApplyBy(String.valueOf(dto.getId()),
                        serviceOrderModel.getDealBy());
                if (apList != null && apList.size() > 0) {
                    Apply apply = apList.get(0);
                    dto.setPrice(apply.getPrice());
                    if (apply.getApplyBy() != dto.getDealBy()&&dto.getStatus().equals("待完成")) {
                        dto.setStatus("被抢单");
                    }
                }
                rtnList.add(dto);
            }
            pageInfo.setRecords(rtnList);
        }
        return pageInfo;
    }

    @Override
    public PagerInfo<ServiceOrderDto> listProcessServiceOrders(ServiceOrderModel demanderSearchModal) {
        PagerInfo<ServiceOrderDto> pageInfo = serviceOrderDao
                .listProcessServiceOrders(demanderSearchModal);
        List<ServiceOrderDto> serviceList = pageInfo.getRecords();
        List<ServiceOrderDto> rtnList = new ArrayList<ServiceOrderDto>();
        if (serviceList != null && serviceList.size() > 0) {
            for (int i = 0; i < serviceList.size(); i++) {
                ServiceOrderDto dto = serviceList.get(i);
                List<Apply> apList = applyDao.listBySIdApplyBy(String.valueOf(dto.getId()),
                        String.valueOf(dto.getDealBy()));
                if (apList != null && apList.size() > 0) {
                    Apply apply = apList.get(0);
                    dto.setPrice(apply.getPrice());
                    dto.setApplyId(apply.getId());
                }
                rtnList.add(dto);
            }
            pageInfo.setRecords(rtnList);
        }
        return pageInfo;
    }

    @Override
    public long getOrdersTotalByConditionsForProcess(ServiceOrderModel demanderSearchModal) {
        return serviceOrderDao.getOrdersTotalByConditionsForProcess(demanderSearchModal);
    }

}
