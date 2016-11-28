package com.infosys.course.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.infosys.course.dao.base.BaseDao;
import com.infosys.course.entity.User;

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
