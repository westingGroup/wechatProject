package com.infosys.course.service;

import java.util.List;

import com.infosys.course.entity.WeixinMenu;
import com.infosys.weixin.model.WeixinMenuDto;

public interface IWeixinMenuService {
	public void add(WeixinMenu wm);
	public void delete(int id);
	public void update(WeixinMenu wm);
	public WeixinMenu load(int id);
	public List<WeixinMenu> listAll();
	public WeixinMenu loadByKey(String key);
	public List<WeixinMenuDto> generateWeixinMenuDto();
}
