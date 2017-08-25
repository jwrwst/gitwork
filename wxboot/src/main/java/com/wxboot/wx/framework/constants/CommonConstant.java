package com.wxboot.wx.framework.constants;
/**
 * 家家帮
 * @author wang
 * 2017年4月2日 上午11:26:52
 * 类描述：
 */
public class CommonConstant {
	/**媒体文件类型*/
	public interface MEDIA_TYPE{
		/**图片*/
		public static final String IMAGE = "image";
		/**语音*/
		public static final String VOICE  = "voice";
		/**视屏*/
		public static final String VIDEO  = "video";
		/**缩略图*/
		public static final String THUMB  = "thumb";
	}
	/**消息类型*/
	public interface MSG_TYPE{
		/**文本*/
		public static final String TEXT = "text";
		/**图片*/
		public static final String IMAGE = "image";
		/**语音*/
		public static final String VOICE  = "voice";
		/**视屏*/
		public static final String VIDEO  = "video";
		/**小视屏*/
		public static final String SHORTVIDEO  = "shortvideo";
		/**地理位置*/
		public static final String LOCATION  = "location";
		/**链接信息*/
		public static final String LINK  = "link";
		/**事件推送*/
		public static final String EVENT = "event";//以下是event的子类
	}
	
	/**事件推送event子类*/
	public interface EVENT_CHILD{
		/**关注事件*/
		public static final String SUBSCRIBE = "SUBSCRIBE";
		/**取消关注*/
		public static final String UNSUBSCRIBE = "UNSUBSCRIBE";
		/**点击推事件*/
		public static final String CLICK = "CLICK";
		/**跳转URL*/
		public static final String VIEW = "VIEW";
		
		public static final String LOCATION = "LOCATION";
	}
	
	

}

 