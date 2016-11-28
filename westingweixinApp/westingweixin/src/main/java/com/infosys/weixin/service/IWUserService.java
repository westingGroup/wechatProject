package com.infosys.weixin.service;

import com.infosys.weixin.model.WUser;

public interface IWUserService {
	public WUser queryByOpenid(String openid);
	public String queryOpenidByCode(String code);
}
