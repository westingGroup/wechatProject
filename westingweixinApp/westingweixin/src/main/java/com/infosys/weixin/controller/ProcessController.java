package com.infosys.weixin.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.entity.Demander;
import com.infosys.basic.entity.Provider;
import com.infosys.basic.service.IDemanderService;
import com.infosys.basic.service.IInsideProviderService;
import com.infosys.basic.service.IProviderService;
import com.infosys.basic.util.JsonUtil;

@RequestMapping("/process")
@Controller
public class ProcessController {
    @Inject
    private IInsideProviderService insideProviderService;

    @Inject
    private IDemanderService demanderService;

    @Inject
    private IProviderService providerService;

    // 首页
    @RequestMapping(value = "/registers")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("process/registers/approvalRegister");
        return modelAndView;
    }

    // 分页
    // type demander 和 provider 和inside
    // status 1 和11
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    public @ResponseBody String listByPage(String currentPage, String pageSize, String linkname, String linkphone,
            String type, String status, HttpSession session) {
        JsonUtil jsonUtil = JsonUtil.getInstance();
        DemanderModel demanderSearchModal = new DemanderModel();
        PagerInfo<DemanderDto> demanderPage = new PagerInfo<DemanderDto>();
        if (StringUtils.isBlank(currentPage)) {
            demanderPage.setCurrentPage(1L);
        } else {
            demanderPage.setCurrentPage(Long.valueOf(currentPage));
        }

        if (StringUtils.isBlank(pageSize)) {
            demanderPage.setPageSize(10L);
        } else {
            demanderPage.setPageSize(Long.valueOf(pageSize));
        }

        demanderSearchModal.setLinkname(StringUtils.isBlank(linkname) ? "" : linkname.trim());
        demanderSearchModal.setLinkphone(StringUtils.isBlank(linkphone) ? "" : linkphone.trim());
        demanderSearchModal
                .setStatus(StringUtils.isBlank(status) ? com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL_STR
                        : status.trim());
        demanderSearchModal.setPager(demanderPage);
        PagerInfo<DemanderDto> userResult = null;
        if (type.equals("demander")) {
            userResult = demanderService.listDemanderByKeyword(demanderSearchModal);
        } else if (type.equals("provider")) {
            userResult = providerService.listProviderByKeyword(demanderSearchModal);
        } else if (type.equals("inside")) {
            userResult = insideProviderService.listInsideProviderByKeyword(demanderSearchModal);
        }
        userResult.setTotalPage(userResult.getTotalPages());
        if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
            userResult.setIsCanUp(1);
        }

        if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
            userResult.setIsCanDown(1);
        }
        return jsonUtil.obj2json(userResult);
    }

    @RequestMapping(value = "/dealDemander", method = RequestMethod.POST)
    public @ResponseBody String dealDemander(String type, String demanderIds, String remark, int dealType,
            HttpSession session) {
        String rtnStr = "操作失败";
        if (StringUtils.isNotBlank(demanderIds)) {
            String[] deIds = demanderIds.split(",");
            if (deIds.length > 0) {
                Demander demander;
                Provider provider;
                for (int i = 0; i < deIds.length; i++) {
                    String id = deIds[i];
                    if (type.equals("demander")) {
                        demander = demanderService.load(Integer.parseInt(id));
                        demander.setRemark(remark);
                        if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_PASS) {
                            demander.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_PASS);
                            demanderService.update(demander);
                        } else if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_REJECT) {
                            demander.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_REJECT);
                            demanderService.update(demander);
                        }
                    } else if (type.equals("provider")) {
                        provider = providerService.load(Integer.parseInt(id));
                        provider.setRemark(remark);
                        if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_PASS) {
                            provider.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_PASS);
                            providerService.update(provider);
                        } else if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_REJECT) {
                            provider.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_REJECT);
                            providerService.update(provider);
                        }
                    }
                }
                demander = null;
                provider = null;
                rtnStr = "操作成功";
            }
        } else {
            rtnStr = "请选择";
        }
        return rtnStr;
    }

    
    
    // 首页
    @RequestMapping(value = "/demanders")
    public ModelAndView approvalDemand() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("process/demanders/approvalDemander");
        return modelAndView;
    }
}