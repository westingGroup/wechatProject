package com.infosys.basic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.entity.Apply;

@Repository("applyDao")
public class ApplyDao extends BaseDao<Apply> implements IApplyDao {

	@Override
	public List<Apply> listBySId(String sId) {
		return this.list("from Apply where sId = ? ", Integer.parseInt(sId));
	}

}
