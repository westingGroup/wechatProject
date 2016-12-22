package com.infosys.basic.dao;

import java.util.List;

import com.infosys.basic.dao.base.IBaseDao;
import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.entity.InsideProvider;

public interface IInsideProviderDao extends IBaseDao<InsideProvider>{
    public InsideProvider loadByUsername(String username);
    public InsideProvider loadByUsernameExcludeId(String username ,int id);
    public List<InsideProvider> list();
    public PagerInfo<DemanderDto> listInsideProviderByKeyword(DemanderModel demanderModel);
    public long getDemanderTotalByConditions(DemanderModel demanderModel);
}
