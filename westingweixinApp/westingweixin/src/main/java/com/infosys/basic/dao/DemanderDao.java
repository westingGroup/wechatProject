package com.infosys.basic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.entity.Demander;

@Repository("demanderDao")
public class DemanderDao extends BaseDao<Demander> implements IDemanderDao {

    @Override
    public Demander loadByOpenid(String openid) {
        return (Demander)super.queryObject("from Demander where openid=?", openid);
    }

    @Override
    public List<Demander> list() {
        return super.list("from Demander");
    }


}
