package com.infosys.basic.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.entity.Words;

@Repository("wordsDao")
public class WordsDao extends BaseDao<Words> implements IWordsDao {

    @Override
    public List<Words> listByCondition(String condition) {
        if (StringUtils.isEmpty(condition)) {
            return this.list("from Words ");
        } else {
            return this.list("from Words where brief like ? ", "'%" + condition + "%'");
        }
    }

    @Override
    public Words konw() {
        StringBuffer sb = new StringBuffer(
                "SELECT id,brief,html_contents,last_update_time,status FROM t_words where status = 1 order by last_update_time desc");
        List<Map<String, Object>> userListInDB = this.listBySql(sb.toString(), null);
        List<Words> wordList = new ArrayList<Words>();
        for (Map<String, Object> valuesMap : userListInDB) {
            Words word = new Words();
            word.setId(Integer.valueOf(valuesMap.get("id").toString()));
            word.setBrief(valuesMap.get("brief").toString());
            word.setHtmlContents(valuesMap.get("html_contents").toString());
            word.setLastUpdateTime((Date) valuesMap.get("last_update_time"));
            word.setStatus(Integer.valueOf(valuesMap.get("status").toString()));
            wordList.add(word);
            word = null;
        }
        if (wordList.size() > 0)
            return wordList.get(0);
        else
            return null;
    }

    @Override
    public void deleteOther(int id) {
        this.updateByHql("update Words set status=0 where id!= ? ", id);
    }
}
