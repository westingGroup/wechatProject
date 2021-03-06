package com.infosys.weixin.model;

import java.util.ArrayList;
import java.util.List;

import com.infosys.basic.entity.User;

public class WUser {
	private int subscribe;
	private String openid;
	private String nickname;
	private int sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	private long subscribe_time;
	private int groupid;
	private String remark;
	private String unionid;
//  {"subscribe":1,"openid":"oE8m_wrXmKxdD2L-j9skZahf5ABI","nickname":"anne","sex":2,"language":"zh_CN","city":"青岛","province":"山东","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/nibxxlib1VaPfGtWicc9JYicEqTKnksV1omA6Yn458fkEJ2BwnAsu5frHw0Xia3kcjVmceFPMJYfuKDLDKOnXxGTTvQ\/0","subscribe_time":1480161923,"remark":"","groupid":0,"tagid_list":[]}

	private List<String> tagid_list = new ArrayList<String>();
	
	public List<String> getTagid_list() {
        return tagid_list;
    }
    public void setTagid_list(List<String> tagid_list) {
        this.tagid_list = tagid_list;
    }
    public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public long getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public User getUser() {
		User u = new User();
		u.setBind(0);
		u.setImgUrl(this.getHeadimgurl());
		u.setNickname(this.getNickname());
		u.setOpenid(this.getOpenid());
		u.setSex(this.getSex());
		u.setStatus(1);
		u.setUsername(this.getOpenid());
		u.setPassword(this.getOpenid());
		return u;
	}
}
