package com.infosys.course.message.event;

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
public class MenuEvent extends BaseEvent {
	// 事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
