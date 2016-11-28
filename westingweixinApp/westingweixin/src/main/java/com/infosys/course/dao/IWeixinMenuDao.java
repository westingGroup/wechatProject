package com.infosys.course.dao;

import java.util.List;

import com.infosys.course.dao.base.IBaseDao;
import com.infosys.course.entity.WeixinMenu;

public interface IWeixinMenuDao extends IBaseDao<WeixinMenu>{
	public WeixinMenu loadByKey(String key);
	public List<WeixinMenu> listAll();
}
