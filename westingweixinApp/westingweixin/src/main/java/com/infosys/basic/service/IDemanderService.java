package com.infosys.basic.service;

import java.util.List;

import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.entity.Demander;

public interface IDemanderService {
    public void add(Demander demander);
    public void update(Demander demander);
    public void delete(int id);
    public Demander load(int id);
    public Demander get(int id);
    public Demander loadByOpenid(String openid);
    public List<Demander> list();
    public PagerInfo<DemanderDto> listDemanderByKeyword(DemanderModel demanderModel);
}
