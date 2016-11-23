package com.infosys.course.util;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *     
 * 项目名称：westingweixin    
 * 类名称：Button    
 * 类描述：    
 * 创建人：Anne_Yan    
 * 创建时间：2016年11月23日 上午11:10:42    
 * 修改人：Anne_Yan    
 * 修改时间：2016年11月23日 上午11:10:42    
 * 修改备注：    
 * @version     
 *
 */
public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	
	/**
	 * URL编码（utf-8）
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".amr";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}
	
	public static void main(String[] args) {
		String oath="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		String oathURL = "http://b592906e.ngrok.io/weixintest/oAuthServlet";
		System.out.println(oath.replace("APPID", "wx20ec6258cf135ad3").
				replace("REDIRECT_URI", urlEncodeUTF8(oathURL)).replace("SCOPE", "snsapi_userinfo"));
		//
	}
}