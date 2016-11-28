package com.infosys.course.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.infosys.course.dao.base.BaseDao;
import com.infosys.course.entity.WeixinMenu;

@Repository("weixinMenuDao")
public class WeixinMenuDao extends BaseDao<WeixinMenu> implements
		IWeixinMenuDao {


	public WeixinMenu loadByKey(String key) {
		return (WeixinMenu)super.queryObject("from WeixinMenu where menuKey=?", key);
	}

	public List<WeixinMenu> listAll() {
		return super.list("from WeixinMenu");
	}

}
