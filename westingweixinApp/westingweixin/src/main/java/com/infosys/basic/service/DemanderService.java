package com.infosys.basic.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.dao.IDemanderDao;
import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.entity.Demander;

@Service("demanderService")
@Transactional
public class DemanderService implements IDemanderService {
    @Inject
    private IDemanderDao demanderDao;
    @Override
    public void add(Demander demander) {
        demander.setStatus(1);
        demanderDao.add(demander);        
    }

    @Override
    public void update(Demander demander) {
        demanderDao.update(demander);
    }

    @Override
    public void delete(int id) {
        demanderDao.delete(id);
    }

    @Override
    public Demander load(int id) {
        return demanderDao.load(id);
    }

    @Override
    public Demander loadByOpenid(String openid) {
        return demanderDao.loadByOpenid(openid);
    }

    @Override
    public List<Demander> list() {
        return demanderDao.list();
    }
    
    @Override
    public PagerInfo<DemanderDto> listDemanderByKeyword(DemanderModel demanderModel) {
        return demanderDao.listDemanderByKeyword(demanderModel);
    }

    @Override
    public Demander get(int id) {
        return demanderDao.get(id);
    }

	@Override
	public long getDemanderTotalByConditions(DemanderModel demanderModel) {
		return demanderDao.getDemanderTotalByConditions(demanderModel);
	}

}
