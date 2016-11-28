package com.infosys.course.service;

import java.util.List;

import com.infosys.course.entity.User;

public interface IUserService {
	public void add(User user);
	public void update(User user);
	public void delete(int id);
	public User load(int id);
	public User loadByUsername(String username);
	public User loadByOpenid(String openid);
	public User login(String username,String password);
	public List<User> list();
}
