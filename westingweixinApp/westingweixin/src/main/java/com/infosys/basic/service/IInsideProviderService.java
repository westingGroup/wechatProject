package com.infosys.basic.service;

import java.util.List;

import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.entity.InsideProvider;

public interface IInsideProviderService {
    public void add(InsideProvider insideProvider);
    public void update(InsideProvider insideProvider);
    public void delete(int id);
    public InsideProvider load(int id);
    public InsideProvider get(int id);
    public InsideProvider loadByUsername(String username);
    public InsideProvider login(String username,String password);
    public List<InsideProvider> list();
    public PagerInfo<DemanderDto> listInsideProviderByKeyword(DemanderModel demanderModel);
    public long getDemanderTotalByConditions(DemanderModel demanderModel);
}
