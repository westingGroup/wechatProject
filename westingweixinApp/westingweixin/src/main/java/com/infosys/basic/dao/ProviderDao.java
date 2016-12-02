package com.infosys.basic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.entity.Provider;

@Repository("providerDao")
public class ProviderDao extends BaseDao<Provider> implements IProviderDao {

    @Override
    public Provider loadByOpenid(String openid) {
        return (Provider)super.queryObject("from Provider where openid=?", openid);
    }

    @Override
    public List<Provider> list() {
        return super.list("from Provider");
    }


}
