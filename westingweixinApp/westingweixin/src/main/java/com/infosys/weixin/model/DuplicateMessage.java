package com.infosys.weixin.model;

public class DuplicateMessage {
	private String fromUserName;//控制对象是否相等
	private String createTime;//控制对象是否相等
	private Long curTime;//控制时间，30秒以上的对象清空
	private String rel;//返回的字符串 微信服务器第一次来的数据由于网络原因没有响应时，会在5秒后在重新请求，在第一次把返回的字符串设置到rel中，第二次重试的时候，会直接找到第一次设置的rel，直接返回就可以了，微信服务器接收到返回就不在重新请求，及就是第二次请求的时回复的消息
	
	public Long getCurTime() {
		return curTime;
	}
	public void setCurTime(Long curTime) {
		this.curTime = curTime;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public DuplicateMessage() {
	}
	
	public DuplicateMessage(String fromUserName, String createTime) {
		super();
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.curTime = System.currentTimeMillis();
		this.rel = null;
	}
	@Override
	public boolean equals(Object obj) {
		DuplicateMessage dm = (DuplicateMessage)obj;
		if(dm.getCreateTime().equals(this.getCreateTime())&&(dm.getFromUserName()).equals(this.getFromUserName())) {
			return true;
		}
		return false;
	}
}
