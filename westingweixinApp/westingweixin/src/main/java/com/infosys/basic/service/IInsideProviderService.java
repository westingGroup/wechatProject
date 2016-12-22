package com.infosys.basic.service;

import java.util.List;

import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.entity.InsideProvider;
import com.infosys.weixin.web.exception.BusinessException;

public interface IInsideProviderService {
    public void add(InsideProvider insideProvider) throws BusinessException;
    public void update(InsideProvider insideProvider);
    public void delete(int id);
    public InsideProvider load(int id);
    public InsideProvider get(int id);
    public InsideProvider loadByUsername(String username);
    public InsideProvider loadByUsernameExcludeId(String username ,int id);
    public InsideProvider login(String username,String password) throws BusinessException;
    public List<InsideProvider> list();
    public PagerInfo<DemanderDto> listInsideProviderByKeyword(DemanderModel demanderModel);
    public long getDemanderTotalByConditions(DemanderModel demanderModel);
}
