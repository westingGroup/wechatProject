package com.infosys.basic.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.dao.IApplyDao;
import com.infosys.basic.entity.Apply;

@Service("applyService")
@Transactional
public class ApplyService implements IApplyService {
    @Inject
    private IApplyDao applyDao;

    @Override
    public void add(Apply apply) {
        apply.setStatus(1);
        applyDao.add(apply);
    }

    @Override
    public void update(Apply apply) {
        applyDao.update(apply);
    }

    @Override
    public void delete(int id) {
        applyDao.delete(id);
    }

    @Override
    public Apply load(int id) {
        return applyDao.load(id);
    }
    
    @Override
    public Apply get(int id) {
        return applyDao.get(id);
    }

    @Override
    public List<Apply> listBySId(String sId) {
        return applyDao.listBySId(sId);
    }

    @Override
    public List<Apply> listBySIdApplyBy(String sId, String applyBy) {
        return applyDao.listBySIdApplyBy(sId,applyBy);
    }

    @Override
    public void deleteBySId(String id) {
        applyDao.deleteBySId(id);        
    }

}
