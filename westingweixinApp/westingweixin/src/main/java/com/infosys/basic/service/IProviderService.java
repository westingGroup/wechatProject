package com.infosys.basic.service;

import java.util.List;

import com.infosys.basic.entity.Provider;

public interface IProviderService {
    public void add(Provider provider);
    public void update(Provider provider);
    public void delete(int id);
    public Provider load(int id);
    public Provider loadByOpenid(String openid);
    public List<Provider> list();
}
