package com.infosys.basic.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.dao.IInsideProviderDao;
import com.infosys.basic.entity.InsideProvider;

@Service("insideProviderService")
@Transactional
public class InsideProviderService implements IInsideProviderService {
    @Inject
    private IInsideProviderDao insideProviderDao;

    @Override
    public void add(InsideProvider insideProvider) {
        InsideProvider u = this.loadByUsername(insideProvider.getUsername());
        if(u!=null) throw new RuntimeException("用户名已经存在");
        insideProvider.setStatus(1);
        insideProviderDao.add(insideProvider);
    }

    @Override
    public void update(InsideProvider insideProvider) {
        insideProviderDao.update(insideProvider);
    }

    @Override
    public void delete(int id) {
        insideProviderDao.delete(id);
    }

    @Override
    public InsideProvider load(int id) {
        return insideProviderDao.load(id);
    }

    @Override
    public InsideProvider loadByUsername(String username) {
        return insideProviderDao.loadByUsername(username);
    }

    @Override
    public InsideProvider login(String username, String password) {
        InsideProvider u = this.loadByUsername(username);
        if(u==null) throw new RuntimeException("用户名不存在!");
        if(!password.equals(u.getPassword())) throw new RuntimeException("用户密码不正确！");
        if(u.getStatus()==0) throw new RuntimeException("用户已经停用!");
        return u;
    }

    @Override
    public List<InsideProvider> list() {
        return insideProviderDao.list();
    }

   
}
