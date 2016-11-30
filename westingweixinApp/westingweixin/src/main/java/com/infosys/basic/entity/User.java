package com.infosys.basic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_user")
public class User implements Serializable {
	/**    
     * serialVersionUID:
     *    
     * @since Ver 1.1    
     */    
    
    private static final long serialVersionUID = -8644735720724713765L;
    private int id;
	private String nickname;
	private String password;
	private String username;
	private String openid;
	private String imgUrl;
	private int status;
	private int sex;
	private int bind;
	
	private String linkname;
	private String linkphone;
	private String business;
	private String company;
	private String qualification;
	
	private int demander;//0 没有注册为需求方  1 已经注册为需求方 2 审批通过
	private int provider;//0 没有注册为提供商  1 已经注册为提供商  2 审批通过
	
	
    @Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="img_url")
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getBind() {
		return bind;
	}
	public void setBind(int bind) {
		this.bind = bind;
	}
    public String getLinkname() {
        return linkname;
    }
    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }
    public String getLinkphone() {
        return linkphone;
    }
    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }
    public String getBusiness() {
        return business;
    }
    public void setBusiness(String business) {
        this.business = business;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public int getDemander() {
        return demander;
    }
    public void setDemander(int demander) {
        this.demander = demander;
    }
    public int getProvider() {
        return provider;
    }
    public void setProvider(int provider) {
        this.provider = provider;
    }
}
