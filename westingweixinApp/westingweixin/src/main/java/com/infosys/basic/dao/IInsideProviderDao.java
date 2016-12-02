package com.infosys.basic.dao;

import java.util.List;

import com.infosys.basic.dao.base.IBaseDao;
import com.infosys.basic.entity.InsideProvider;

public interface IInsideProviderDao extends IBaseDao<InsideProvider>{
    public InsideProvider loadByUsername(String username);
    public List<InsideProvider> list();
}
