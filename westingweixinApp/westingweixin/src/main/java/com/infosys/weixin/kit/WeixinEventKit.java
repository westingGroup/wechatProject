package com.infosys.weixin.kit;

import java.io.IOException;
import java.util.Map;

import com.infosys.basic.entity.Demander;
import com.infosys.basic.entity.Provider;
import com.infosys.basic.entity.User;
import com.infosys.basic.entity.WeixinMenu;
import com.infosys.basic.entity.WeixinQr;
import com.infosys.basic.service.IDemanderService;
import com.infosys.basic.service.IProviderService;
import com.infosys.basic.service.IUserService;
import com.infosys.basic.service.IWeixinMenuService;
import com.infosys.basic.service.IWeixinQrService;
import com.infosys.weixin.model.WGroup;
import com.infosys.weixin.model.WUser;
import com.infosys.weixin.service.IWGroupService;
import com.infosys.weixin.service.IWUserService;
import com.infosys.weixin.web.servlet.BeanFactoryContext;
import com.infosys.weixin.web.servlet.WeixinContext;

public class WeixinEventKit {

    private static IProviderService providerService = (IProviderService)BeanFactoryContext.getService("providerService");
    private static IDemanderService demanderService = (IDemanderService)BeanFactoryContext.getService("demanderService");
    private static IWeixinQrService weixinQrService = (IWeixinQrService)BeanFactoryContext.getService("weixinQrService");
    private static IWGroupService wGroupService = (IWGroupService)BeanFactoryContext.getService("wGroupService");
    private static IUserService userService = (IUserService)BeanFactoryContext.getService("userService");
    private static IWUserService wUserService = (IWUserService)BeanFactoryContext.getService("wUserService");
    private static IWeixinMenuService weixinMenuService = (IWeixinMenuService)BeanFactoryContext.getService("weixinMenuService");
    
	public static String handlerEventMsg(Map<String, String> msgMap) throws IOException {
		String event = msgMap.get("Event");
		System.out.println(event);
		if("CLICK".equals(event)) {
			return handlerClickEvent(msgMap);
		} else if("SCAN".equals(event)) {
			return handlerScanEvent(msgMap);
		} else if("subscribe".equals(event)) {
			//用户关注事件
			return handlerSubscribeEvent(msgMap);
		} else if("unsubscribe".equals(event)) {
			return handlerUnsubscribeEvent(msgMap);
		}
		return null;
	}
	
	private static String handlerScanEvent(Map<String, String> msgMap) throws IOException {
		handlerUserInfo(msgMap);
		String snum = getSence(msgMap, false);
		String openid = msgMap.get("FromUserName");
		WeixinQr wq = weixinQrService.loadBySnum(Integer.parseInt(snum));
		if(wq.getType()==WeixinQr.REPASSWORD_TYPE) {
			//处理修改密码操作
			return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "<a href=\""+wq.getQrData()+"\">"+wq.getMsg()+"</a>"));
		} else if(wq.getType()==WeixinQr.SET_GROUP_TYPE) {
			//处理设置用户组的操作
			wGroupService.moveUserToGroup(openid, Integer.parseInt(wq.getQrData()));
			WGroup wg = wGroupService.queryUserGroup(openid);
			return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "你的用户组已经修改,用户组修改为:"+wg.getName()));
		} else if(wq.getType()==WeixinQr.TEMP_BIND) {
			//处理绑定用户操作
		} else if(wq.getType()==WeixinQr.TEMP_LOGIN) {
			//处理用户扫码登录操作
			long t = System.currentTimeMillis()-wq.getCreateDate().getTime();
			if((t/1000)>60) {
				return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "你所扫描的二维码已经过期，请在网页中重新刷新并且扫描"));
			} else {
				wq.setStatus(1);
				wq.setQrData(openid);
				weixinQrService.update(wq);
			}
		}
		return null;
	}

	private static User getUser(Map<String, String> msgMap) {
		String openid = msgMap.get("FromUserName");
		User u = userService.loadByOpenid(openid);
		return u;
	}
	
	private static Provider getProvider(Map<String, String> msgMap) {
        String openid = msgMap.get("FromUserName");
        Provider u = providerService.loadByOpenid(openid);
        return u;
    }
	
	private static Demander getDemander(Map<String, String> msgMap) {
        String openid = msgMap.get("FromUserName");
        Demander u = demanderService.loadByOpenid(openid);
        return u;
    }

	private static String handlerUnsubscribeEvent(Map<String, String> msgMap) {
		User u = getUser(msgMap);
		Provider per = getProvider(msgMap);
		Demander der = getDemander(msgMap);
        if (u != null) {
            if (per == null && der == null) {
            } else {
                u.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_DELETE);
                userService.update(u);
                if (per != null) {
                    per.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_DELETE);
                    providerService.update(per);
                }
                if (der != null) {
                    der.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_DELETE);
                    demanderService.update(der);
                }
            }
        }
		return null;
	}

	private static String getSence(Map<String,String> msgMap,boolean subscribe) {
		String key = msgMap.get("EventKey");
		if(key==null||"".equals(key)) return null;
		if(subscribe)
			return key.split("_")[1];
		else 
			return key;
	}
	
	private static User handlerUserInfo(Map<String, String> msgMap) {
		String openid = msgMap.get("FromUserName");
		User u = getUser(msgMap);
		if(u==null) {
			WUser wu = wUserService.queryByOpenid(openid);
			u = wu.getUser();
			userService.add(u);
		} else {
	        Provider per = getProvider(msgMap);
	        Demander der = getDemander(msgMap);
			if(u.getStatus()==com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
				u.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
				userService.update(u);
            }
            if (per != null && per.getStatus()==com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
                per.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
                providerService.update(per);
            }
            if (der != null&& der.getStatus()==com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
                der.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
                demanderService.update(der);
            }
		}
		return u;
	}

	private static String handlerSubscribeEvent(Map<String, String> msgMap) throws IOException {
		User u = handlerUserInfo(msgMap);
		String snum = getSence(msgMap, true);
		String openid = msgMap.get("FromUserName");
		if(snum!=null) {
			WeixinQr wq = weixinQrService.loadBySnum(Integer.parseInt(snum));
			if(wq.getType()==WeixinQr.SET_GROUP_TYPE) {
			    //处理未关注扫描 设置分组 二维码
				wGroupService.moveUserToGroup(openid, Integer.parseInt(wq.getQrData()));
			}else if(wq.getType()==WeixinQr.TEMP_LOGIN){
			  //处理用户扫码登录操作-未关注
	            long t = System.currentTimeMillis()-wq.getCreateDate().getTime();
	            if((t/1000)>60) {
	                return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "你所扫描的二维码已经过期，请在网页中重新刷新并且扫描"));
	            } else {
	                wq.setStatus(1);
	                wq.setQrData(openid);
	                weixinQrService.update(wq);
	            }
			}
		}
		if(u.getBind()==0) {
			String bindUrl = WeixinContext.getInstance().getBaseUrl();
			bindUrl += bindUrl+"/user/bindExistUser";
			return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "<a href=\""+bindUrl+"\">请点击绑定用户获得更好的体验</a>"));
		} else {
			String bindUrl = WeixinContext.getInstance().getBaseUrl();
			return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, "<a href=\""+bindUrl+"\">欢迎你再次使用我们的微信平台，点击打开我们的页面</a>"));
		}
	}

	private static String handlerClickEvent(Map<String, String> msgMap) throws IOException {
		String keyCode = msgMap.get("EventKey");
		WeixinMenu wm = weixinMenuService.loadByKey(keyCode);
		if(wm!=null&&wm.getRespType()==1) {
			Map<String,Object> map = MessageCreateKit.createTextMsg(msgMap, wm.getContent());
			return WeixinMessageKit.map2xml(map);
		}
		return null;
	}

}
