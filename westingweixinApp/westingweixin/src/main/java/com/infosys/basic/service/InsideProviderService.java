package com.infosys.basic.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.dao.IInsideProviderDao;
import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.entity.InsideProvider;
import com.infosys.weixin.kit.SecurityKit;
import com.infosys.weixin.web.exception.BusinessException;

@Service("insideProviderService")
@Transactional
public class InsideProviderService implements IInsideProviderService {
	@Inject
	private IInsideProviderDao insideProviderDao;

	@Override
	public void add(InsideProvider insideProvider) throws BusinessException {
		InsideProvider u = this.loadByUsername(insideProvider.getUsername());
		if (u != null)
			throw new BusinessException("用户名已经存在");
		insideProvider.setStatus(1);
		insideProvider.setPassword(SecurityKit.md5("123"));
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
	public InsideProvider login(String username, String password) throws BusinessException {
		InsideProvider u = this.loadByUsername(username);
		if (u == null)
			throw new BusinessException("用户名不存在!");
		if (!SecurityKit.md5(password).equals(u.getPassword()))
			throw new BusinessException("用户密码不正确！");
		if (u.getStatus() == 0)
			throw new BusinessException("用户已经停用!");
		return u;
	}

	@Override
	public List<InsideProvider> list() {
		return insideProviderDao.list();
	}

	@Override
	public PagerInfo<DemanderDto> listInsideProviderByKeyword(
			DemanderModel demanderModel) {
		return insideProviderDao.listInsideProviderByKeyword(demanderModel);
	}

	@Override
	public InsideProvider get(int id) {
		return insideProviderDao.get(id);
	}

	@Override
	public long getDemanderTotalByConditions(DemanderModel demanderModel) {
		return insideProviderDao.getDemanderTotalByConditions(demanderModel);
	}

}
