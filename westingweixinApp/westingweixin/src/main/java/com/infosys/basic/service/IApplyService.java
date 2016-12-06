package com.infosys.basic.service;

import com.infosys.basic.entity.Apply;

public interface IApplyService {
    public void add(Apply apply);
    public void update(Apply apply);
    public void delete(int id);
    public Apply load(int id);
}
