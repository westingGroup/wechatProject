package com.infosys.test;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.dto.ServiceOrderDto;
import com.infosys.basic.dto.ServiceOrderModel;
import com.infosys.basic.service.IServiceOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
// 整合
@ContextConfiguration(locations = "classpath:applicationContext.xml")
// 加载配置
public class TestAnne {
    @Inject
    private IServiceOrderService serviceOrderService;

    @Test
    public void testDemander() throws UnsupportedEncodingException {
        ServiceOrderModel demanderSearchModal = new ServiceOrderModel();
        PagerInfo<ServiceOrderDto> demanderPage = new PagerInfo<ServiceOrderDto>();
        demanderPage.setCurrentPage(2L);
        demanderPage.setPageSize(10L);
        demanderSearchModal.setPager(demanderPage);
        demanderSearchModal.setCreateBy("2");
        PagerInfo<ServiceOrderDto> userResult = serviceOrderService.listServiceOrderByKeyword(demanderSearchModal);
        userResult.setTotalPage(userResult.getTotalPages());
    }

    @Test
    public void testMobileApply() throws UnsupportedEncodingException {
        ServiceOrderModel demanderSearchModal = new ServiceOrderModel();
        PagerInfo<ServiceOrderDto> demanderPage = new PagerInfo<ServiceOrderDto>();
        demanderPage.setCurrentPage(2L);
        demanderPage.setPageSize(10L);
        demanderSearchModal.setPager(demanderPage);
        demanderSearchModal.setApplyBy("2");
        PagerInfo<ServiceOrderDto> userResult = serviceOrderService
                .listServiceOrderByKeywordForMobileApply(demanderSearchModal);
        userResult.setTotalPage(userResult.getTotalPages());
    }

    @Test
    public void testMyMobileApplys() throws UnsupportedEncodingException {
        ServiceOrderModel demanderSearchModal = new ServiceOrderModel();
        PagerInfo<ServiceOrderDto> demanderPage = new PagerInfo<ServiceOrderDto>();
        demanderPage.setCurrentPage(2L);
        demanderPage.setPageSize(10L);
        demanderSearchModal.setPager(demanderPage);
        demanderSearchModal.setDealBy("2");
        PagerInfo<ServiceOrderDto> userResult = serviceOrderService
                .listServiceOrderByKeywordForMyMobileApplys(demanderSearchModal);
        userResult.setTotalPage(userResult.getTotalPages());
    }
    
    
    @Test
    public void testMethod() throws UnsupportedEncodingException {
    }

}
