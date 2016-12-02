package com.infosys.basic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.entity.InsideProvider;

@Repository("insideProviderDao")
public class InsideProviderDao extends BaseDao<InsideProvider> implements IInsideProviderDao {

    @Override
    public InsideProvider loadByUsername(String username) {
        return (InsideProvider)super.queryObject("from InsideProvider where username=?", username);
    }

    @Override
    public List<InsideProvider> list() {
        return super.list("from InsideProvider");
    }


}
