package com.infosys.basic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.entity.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {


	@Override
	public User loadByUsername(String username) {
		
		return (User)super.queryObject("from User where username=?", username);
	}

	@Override
	public User loadByOpenId(String openid) {
		return (User)super.queryObject("from User where openid=?", openid);
	}

	@Override
	public List<User> list() {
		return super.list("from User");
	}

}
