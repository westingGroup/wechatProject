package com.infosys.basic.dao;

import java.util.List;

import com.infosys.basic.dao.base.IBaseDao;
import com.infosys.basic.entity.Apply;

public interface IApplyDao extends IBaseDao<Apply> {

    List<Apply> listBySId(String sId);
}
