package com.infosys.basic.dao;

import java.util.List;

import com.infosys.basic.dao.base.IBaseDao;
import com.infosys.basic.entity.Demander;

public interface IDemanderDao extends IBaseDao<Demander>{

    Demander loadByOpenid(String openid);

    List<Demander> list();
}
