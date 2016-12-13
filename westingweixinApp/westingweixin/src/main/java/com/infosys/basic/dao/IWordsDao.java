package com.infosys.basic.dao;

import java.util.List;

import com.infosys.basic.dao.base.IBaseDao;
import com.infosys.basic.entity.Words;

public interface IWordsDao  extends IBaseDao<Words> {

    List<Words> listByCondition(String condition);


}
