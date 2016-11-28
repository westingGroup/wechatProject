package com.infosys.course.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.course.dao.IWeixinQrDao;
import com.infosys.course.entity.WeixinQr;
import com.infosys.weixin.service.IWqrService;

@Service("weixinQrService")
@Transactional
public class WeixinQrService implements IWeixinQrService {
	@Inject
	private IWeixinQrDao weixinQrDao;
	@Inject
	private IWqrService wqrService;
	
	@Override
	public WeixinQr add(WeixinQr wq) {
		if(wq.getSnum()==null) throw new RuntimeException("场景值不能为空");
		if(wq.getSnum()<=WeixinQr.MAX_BASE_SNUM) {
			//永久二维码
			WeixinQr twq = this.loadBySnum(wq.getSnum());
			if(twq!=null) throw new RuntimeException("固定二维码的场景值已经存在!");
			wq.setCreateDate(new Date());
			setQrTicket(wq,0);
			weixinQrDao.add(wq);
		} else {
			//临时二维码
			return addTempQr(wq);
		}
		return wq;
	}

	private WeixinQr addTempQr(WeixinQr wq) {
		WeixinQr twq = this.loadBySnum(wq.getSnum());
		if(twq==null) {
			wq.setCreateDate(new Date());
			setQrTicket(wq,1);
			weixinQrDao.add(wq);
			return wq;
		} else {
			if(checkExpired(twq)) {
				//先删除twq,之后添加wq
				twq.setCreateDate(new Date());
				twq.setMsg(wq.getMsg());
				twq.setName(wq.getName());
				twq.setQrData(wq.getQrData());
				twq.setSnum(wq.getSnum());
				twq.setStatus(wq.getStatus());
				twq.setType(wq.getType());
				setQrTicket(twq,1);
				weixinQrDao.update(twq);
				return twq;
			} else {
				wq.setSnum((WeixinQr.MAX_BASE_SNUM+1)+RandomUtils.nextInt());
				return addTempQr(wq);
			}
			
		}
	}

	private void setQrTicket(WeixinQr wq,int type) {
		String ticket;
		if(type==0) {
			ticket = wqrService.loadTicketByBaseQr(wq.getSnum());
		} else {
			ticket = wqrService.loadTicketByTempQr(wq.getSnum());
		}
		if(ticket==null||"".equals(ticket)) throw new RuntimeException("从微信获取二维码失败!");
		wq.setTicket(ticket);		
	}

	private boolean checkExpired(WeixinQr twq) {
		long t = System.currentTimeMillis()-twq.getCreateDate().getTime();
		if((t/1000)>60) return true;
		return false;
	}

	@Override
	public void delete(String id) {
		weixinQrDao.delete(id);
	}

	@Override
	public WeixinQr load(String id) {
		return weixinQrDao.load(id);
	}

	@Override
	public void update(WeixinQr wq) {
		weixinQrDao.update(wq);
	}

	@Override
	public List<WeixinQr> listBaseQr() {
		return weixinQrDao.listBaseQr();
	}

	@Override
	public List<WeixinQr> listTempQr() {
		return weixinQrDao.listTempQr();
	}

	@Override
	public WeixinQr loadBySnum(int snum) {
		return weixinQrDao.loadBySnum(snum);
	}

}
