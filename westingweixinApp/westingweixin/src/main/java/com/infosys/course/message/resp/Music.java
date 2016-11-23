package com.infosys.course.message.resp;

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
public class Music {
	// 音乐名称
	private String Title;
	// 音乐描述
	private String Description;
	// 音乐链接
	private String MusicUrl;
	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String HQMusicUrl;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String musicUrl) {
		HQMusicUrl = musicUrl;
	}

}
