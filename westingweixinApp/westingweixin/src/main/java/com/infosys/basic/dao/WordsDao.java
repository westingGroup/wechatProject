package com.infosys.basic.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.entity.Words;

@Repository("wordsDao")
public class WordsDao extends BaseDao<Words> implements IWordsDao {

    @Override
    public List<Words> listByCondition(String condition) {
        if(StringUtils.isEmpty(condition)){
            return this.list("from Words ");
        }else{
            return this.list("from Words where brief like ? ", "'%" + condition+ "%'");
        }
    }

}
