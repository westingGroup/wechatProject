package com.infosys.basic.dao;

import java.util.List;

import com.infosys.basic.dao.base.IBaseDao;
import com.infosys.basic.entity.User;

public interface IUserDao extends IBaseDao<User>{
	public User loadByUsername(String username);
	public User loadByOpenId(String openid);
	public List<User> list();
}
