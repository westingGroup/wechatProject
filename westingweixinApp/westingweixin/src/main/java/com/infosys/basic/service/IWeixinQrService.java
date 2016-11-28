package com.infosys.basic.service;

import java.util.List;

import com.infosys.basic.entity.WeixinQr;

public interface IWeixinQrService {
	public WeixinQr add(WeixinQr wq);
	
	public void delete(String id);
	
	public WeixinQr load(String id);
	
	public void update(WeixinQr wq);
	
	public List<WeixinQr> listBaseQr();
	
	public List<WeixinQr> listTempQr();
	
	public WeixinQr loadBySnum(int snum);
}
