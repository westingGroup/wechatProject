package com.infosys.basic.dao;

import java.util.List;

import com.infosys.basic.dao.base.IBaseDao;
import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.entity.Provider;

public interface IProviderDao extends IBaseDao<Provider>{

    Provider loadByOpenid(String openid);

    List<Provider> list();

    PagerInfo<DemanderDto> listProviderByKeyword(DemanderModel demanderModel);
}
