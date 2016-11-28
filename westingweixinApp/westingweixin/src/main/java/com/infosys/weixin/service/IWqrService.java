package com.infosys.weixin.service;
public interface IWqrService {
	public String loadTicketByBaseQr(int snum);
	
	public String loadTicketByTempQr(int snum);
}
