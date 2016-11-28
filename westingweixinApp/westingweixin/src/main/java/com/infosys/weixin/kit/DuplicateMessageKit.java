package com.infosys.weixin.kit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.infosys.weixin.model.DuplicateMessage;

public class DuplicateMessageKit {
	public static final List<DuplicateMessage> MSGS = new ArrayList<DuplicateMessage>();
	
	public static boolean checkDuplicate(Map<String,String> msgMap) {
		DuplicateMessage dm = initDuplicateMessage(msgMap);
		if(MSGS.contains(dm)) {
			return false; 
		} else {
			MSGS.add(dm);
			return true;
		}
	}
	
	public static String getRel(Map<String,String> msgMap) {
		DuplicateMessage dm = initDuplicateMessage(msgMap);
		DuplicateMessage tdm = MSGS.get(MSGS.indexOf(dm));
		return tdm.getRel();
	}
	
	private static DuplicateMessage initDuplicateMessage(
			Map<String, String> msgMap) {
		String fromUserName = msgMap.get("FromUserName");
		String createTime = msgMap.get("CreateTime");
		DuplicateMessage dm = new DuplicateMessage(fromUserName, createTime);
		return dm;
	}

	public static void setRel(Map<String,String> msgMap,String rel) {
		DuplicateMessage dm = initDuplicateMessage(msgMap);
		DuplicateMessage tdm = MSGS.get(MSGS.indexOf(dm));
		tdm.setRel(rel);
	}
	
	public static void clear() {
		DuplicateMessage dm = null;
		System.out.println("清空开始，之前："+MSGS.size());
		//不能采用迭代器方式，不安全，一个放一个取的
		for(int i=0;i<MSGS.size();i++) {
			dm = MSGS.get(i);
			if(((System.currentTimeMillis()-dm.getCurTime())/1000)>30) {
				MSGS.remove(dm);
			}
		}
		System.out.println("清空结束，之后："+MSGS.size());
	}
}
