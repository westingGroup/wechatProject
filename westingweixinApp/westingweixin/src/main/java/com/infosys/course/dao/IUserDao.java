package com.infosys.course.dao;

import java.util.List;

import com.infosys.course.dao.base.IBaseDao;
import com.infosys.course.entity.User;

public interface IUserDao extends IBaseDao<User>{
	public User loadByUsername(String username);
	public User loadByOpenId(String openid);
	public List<User> list();
}
