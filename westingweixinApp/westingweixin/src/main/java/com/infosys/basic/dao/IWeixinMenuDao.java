package com.infosys.basic.dao;

import java.util.List;

import com.infosys.basic.dao.base.IBaseDao;
import com.infosys.basic.entity.WeixinMenu;

public interface IWeixinMenuDao extends IBaseDao<WeixinMenu>{
	public WeixinMenu loadByKey(String key);
	public List<WeixinMenu> listAll();
}
