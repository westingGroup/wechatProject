package com.infosys.basic.service;

import java.util.List;

import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.entity.Provider;

public interface IProviderService {
    public void add(Provider provider);
    public void update(Provider provider);
    public void delete(int id);
    public Provider load(int id);
    public Provider get(int id);
    public Provider loadByOpenid(String openid);
    public List<Provider> list();
    public PagerInfo<DemanderDto> listProviderByKeyword(DemanderModel demanderModel);
    public long getDemanderTotalByConditions(DemanderModel demanderModel);
    public PagerInfo<DemanderDto> listProviderByType(DemanderModel demanderSearchModal);
}
