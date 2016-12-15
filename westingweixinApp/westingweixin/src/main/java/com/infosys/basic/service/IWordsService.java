package com.infosys.basic.service;

import java.util.List;

import com.infosys.basic.entity.Words;

public interface IWordsService {
    public void add(Words words);
    public void update(Words words);
    public void delete(int id);
    public Words load(int id);
    public Words get(int id);
    public List<Words> listByCondition(String condition);
    public Words konw();
    public void deleteOther(int id);
}
