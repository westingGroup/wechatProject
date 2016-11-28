package com.infosys.course.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.course.dao.IWeixinMenuDao;
import com.infosys.course.entity.WeixinMenu;
import com.infosys.weixin.model.WeixinMenuDto;

@Service("weixinMenuService")
@Transactional
public class WeixinMenuService implements IWeixinMenuService {
	@Inject
	private IWeixinMenuDao weixinMenuDao;

	public void add(WeixinMenu wm) {
		if(wm.getType().equals("click"))
			wm.setMenuKey("KEY_"+System.currentTimeMillis());
		weixinMenuDao.add(wm);
	}

	public void delete(int id) {
		weixinMenuDao.delete(id);
	}

	public void update(WeixinMenu wm) {
		weixinMenuDao.update(wm);
	}

	public WeixinMenu load(int id) {
		return weixinMenuDao.load(id);
	}

	public List<WeixinMenu> listAll() {
		return weixinMenuDao.listAll();
	}

	public WeixinMenu loadByKey(String key) {
		return weixinMenuDao.loadByKey(key);
	}

	public List<WeixinMenuDto> generateWeixinMenuDto() {
		List<WeixinMenu> menus = this.listAll();
		List<WeixinMenuDto> menuDtos = new ArrayList<WeixinMenuDto>();
		WeixinMenuDto wmd = null;
		for(WeixinMenu wm:menus) {
			wmd = new WeixinMenuDto();
			wmd.setKey(wm.getMenuKey());
			wmd.setName(wm.getName());
			wmd.setType(wm.getType());
			wmd.setId(wm.getId());
			wmd.setUrl(wm.getUrl());
			if(wm.getPid()==null||wm.getPid()==0) {
				if(wmd.getSub_button()==null) {
					wmd.setSub_button(new ArrayList<WeixinMenuDto>());
				}
				menuDtos.add(wmd);
			} else {
				WeixinMenuDto twmd = findById(wm.getPid(), menuDtos);
				if(twmd==null) throw new RuntimeException("菜单的父类对象有问题，请检查");
				twmd.getSub_button().add(wmd);
			}
		}
		return menuDtos;
	}
	
	private WeixinMenuDto findById(int id,List<WeixinMenuDto> wmds) {
		for(WeixinMenuDto wmd:wmds) {
			if(wmd.getId()==id) {
				return wmd;
			}
		}
		return null;
	}

}
