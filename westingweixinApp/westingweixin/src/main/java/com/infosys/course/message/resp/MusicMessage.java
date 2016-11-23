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
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}