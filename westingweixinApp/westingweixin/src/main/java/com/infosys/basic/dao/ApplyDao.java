package com.infosys.basic.dao;

import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.entity.Apply;

@Repository("applyDao")
public class ApplyDao extends BaseDao<Apply> implements IApplyDao {


}
