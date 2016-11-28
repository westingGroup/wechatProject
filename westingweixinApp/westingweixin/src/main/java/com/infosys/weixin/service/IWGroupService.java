package com.infosys.weixin.service;

import java.util.List;

import com.infosys.weixin.model.WGroup;

public interface IWGroupService {
	public void add(WGroup group);
	public List<WGroup> queryAll();
	public WGroup queryUserGroup(String openid);
	public void updateGroupName(int gid,String name);
	public void moveUserToGroup(String openid,int gid);
	public void moveUsersToGroup(List<String> openids,int gid);
	public void deleteGroup(int gid);
}
