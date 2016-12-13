package com.infosys.basic.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.dao.IWordsDao;
import com.infosys.basic.entity.Words;

@Service("wordsService")
@Transactional
public class WordsService implements IWordsService {
    @Inject
    private IWordsDao wordsDao;

    @Override
    public void add(Words words) {
        wordsDao.add(words);
    }

    @Override
    public void update(Words words) {
        wordsDao.update(words);
    }

    @Override
    public void delete(int id) {
        wordsDao.delete(id);
    }

    @Override
    public Words load(int id) {
        return wordsDao.load(id);
    }

    @Override
    public Words get(int id) {
        return wordsDao.get(id);
    }
    
    @Override
    public List<Words> listByCondition(String condition) {
        return wordsDao.listByCondition(condition);
    }

}
