package com.infosys.weixin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.util.JsonUtil;
import com.infosys.weixin.kit.WeixinBasicKit;
import com.infosys.weixin.model.WGroup;
import com.infosys.weixin.model.WeixinFinalValue;

@Service("wGroupService")
@Transactional
public class WGroupService implements IWGroupService {

	public void add(WGroup group) {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.ADD_GROUP);
		Map<String,WGroup> map = new HashMap<String, WGroup>();
		map.put("group", group);
		String json = JsonUtil.getInstance().obj2json(map);
		String con = WeixinBasicKit.sendJsonPost(url, json);
		System.out.println(con);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<WGroup> queryAll() {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.QUERY_ALL_GROUP);
		String json = WeixinBasicKit.sendGet(url);
		Map<String,List<Map>> maps = (Map<String,List<Map>>)JsonUtil.getInstance().json2obj(json, Map.class);
		List<Map> listMap = maps.get("groups");
		List<WGroup> wgs = new ArrayList<WGroup>();
		for(Map m:listMap) {
			WGroup w = new WGroup();
			w.setId((Integer)m.get("id"));
			w.setName((String)m.get("name"));
			w.setCount((Integer)m.get("count"));
			wgs.add(w);
		}
		return wgs;
	}

	@SuppressWarnings("unchecked")
	public WGroup queryUserGroup(String openid) {
		List<WGroup> wgs = queryAll();
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.QUERY_USER_GROUP);
		Map<String,String> map = new HashMap<String,String>();
		map.put("openid", openid);
		String con = WeixinBasicKit.sendJsonPost(url, JsonUtil.getInstance().obj2json(map));
		Map<String,Integer> gm = (Map<String,Integer>)JsonUtil.getInstance().json2obj(con, Map.class);
		Integer gid  = gm.get("groupid");
		for(WGroup w:wgs) {
			if(w.getId()==gid) {
				return w;
			}
		}
		return null;
	}

	public void updateGroupName(int gid, String name) {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.UPDATE_GROUP_NAME);
		Map<String,WGroup> wmap = new HashMap<String,WGroup>();
		WGroup wg = new WGroup();
		wg.setId(gid);
		wg.setName(name);
		wmap.put("group", wg);
		String json = JsonUtil.getInstance().obj2json(wmap);
		String con = WeixinBasicKit.sendJsonPost(url, json);
		System.out.println(con);
	}

	public void moveUserToGroup(String openid, int gid) {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.MOVE_USER_GROUP);
		Map<String,String> wmap = new HashMap<String,String>();
		wmap.put("openid", openid);
		wmap.put("to_groupid", ""+gid);
		String json = JsonUtil.getInstance().obj2json(wmap);
		String con = WeixinBasicKit.sendJsonPost(url, json);
		System.out.println(con);
	}

	public void moveUsersToGroup(List<String> openids, int gid) {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.MOVE_USERS_GROUP);
		Map<String,Object> wmap = new HashMap<String,Object>();
		wmap.put("openid_list", openids);
		wmap.put("to_groupid", gid);
		String json = JsonUtil.getInstance().obj2json(wmap);
		String con = WeixinBasicKit.sendJsonPost(url, json);
		System.out.println(con);
	}

	public void deleteGroup(int gid) {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.DELETE_GROUP);
		Map<String,WGroup> wmap = new HashMap<String,WGroup>();
		WGroup wg = new WGroup();
		wg.setId(gid);
		wmap.put("group", wg);
		String json = JsonUtil.getInstance().obj2json(wmap);
		String con = WeixinBasicKit.sendJsonPost(url, json);
		System.out.println(con);
	}

}
