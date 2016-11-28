package com.infosys.course.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.infosys.course.dao.base.BaseDao;
import com.infosys.course.entity.WeixinQr;

@Repository("weixinQrDao")
public class WeixinQrDao extends BaseDao<WeixinQr> implements IWeixinQrDao {


	@Override
	public void delete(String id) {
		this.getSession().delete(this.load(id));
	}

	@Override
	public WeixinQr load(String id) {
		return (WeixinQr)this.getSession().load(WeixinQr.class, id);
	}

	@Override
	public List<WeixinQr> listBaseQr() {
		return super.list("from WeixinQr where snum<=?", WeixinQr.MAX_BASE_SNUM);
	}

	@Override
	public List<WeixinQr> listTempQr() {
		return super.list("from WeixinQr where snum>?", WeixinQr.MAX_BASE_SNUM);
	}

	@Override
	public WeixinQr loadBySnum(int snum) {
		return (WeixinQr)super.queryObject("from WeixinQr where snum=?", snum);
	}

}
