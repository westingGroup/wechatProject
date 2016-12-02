package com.infosys.basic.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.dao.IProviderDao;
import com.infosys.basic.entity.Provider;

@Service("providerService")
@Transactional
public class ProviderService implements IProviderService {

    @Inject
    private IProviderDao providerDao;
    @Override
    public void add(Provider provider) {
        provider.setStatus(1);
        providerDao.add(provider);        
    }

    @Override
    public void update(Provider provider) {
        providerDao.update(provider);
    }

    @Override
    public void delete(int id) {
        providerDao.delete(id);
    }

    @Override
    public Provider load(int id) {
        return providerDao.load(id);
    }

    @Override
    public Provider loadByOpenid(String openid) {
        return providerDao.loadByOpenid(openid);
    }

    @Override
    public List<Provider> list() {
        return providerDao.list();
    }


}
