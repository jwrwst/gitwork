package com.wxboot.wx.message.service;

import java.io.Writer;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wxboot.wx.framework.constants.CommonConstant;
import com.wxboot.wx.message.resp.Image;
import com.wxboot.wx.message.resp.ImageMessage;
import com.wxboot.wx.message.resp.TextMessage;
import com.wxboot.wx.message.resp.Voice;
import com.wxboot.wx.message.resp.VoiceMessage;

/**
 * 家家帮
 * @author wang 
 * 2017年4月2日 上午11:35:18 
 * 类描述：
 */
@Service("messageService")
public class MessageService {
	public final static Logger log = LoggerFactory.getLogger(MessageService.class);
	/** 
     * 扩展xstream，过滤内容含有特殊字符
     * 使其支持CDATA块
     */  
    private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true; 
                @SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);  
                }
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }
                }
            };
        }
    });
    
	public String initVoiceMessage(String fromUserName, String toUserName){
		Voice voice = new Voice();
		voice.setMediaId("OYpj9rRSJ55rIrzfIAN9acEjCsKaXE4RdbhC1oXzrysGZCi5NrhPZpEbErhiR0Rs");
		VoiceMessage message = new VoiceMessage();
		message.setToUserName(fromUserName);
		message.setFromUserName(toUserName);
		message.setMsgType(CommonConstant.MSG_TYPE.VOICE);
		message.setCreateTime(new Date().getTime());
		message.setVoice(voice);
		return voiceMessageToXml(message);
	}
	
	public String voiceMessageToXml(VoiceMessage voiceMessage){
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}
	
	/** 初始化图片回复消息 */
	public String imageMessageToXml(ImageMessage imgmessage) {
		xstream.alias("xml", imgmessage.getClass());// 根节点替换为xml
		return xstream.toXML(imgmessage);
	}

	/** 初始化图片回复消息 */
	public String initImageMessage(String fromUserName, String toUserName) {
		Image image = new Image();
		//TODO mediaId 测试类中有获取方法
		image.setMediaId("xuaHJ8RS-H3b3D0oBXCbSO2hxlLWKdENCxPtCscn6fO4wnewIlw585ZVuGY62Ieq");
//		image.setMediaId("fnoUdAxNsL_-hi2P7M-MabE0ydA3E7V2VPg7F7TY8rccxIl_Oceo6KovbNcu4t7M");
		// 初始化图片消息
		ImageMessage imgmessage = new ImageMessage();
		imgmessage.setFromUserName(toUserName);
		imgmessage.setToUserName(fromUserName);
		imgmessage.setMsgType(CommonConstant.MEDIA_TYPE.IMAGE);
		imgmessage.setCreateTime(new Date().getTime());
		imgmessage.setImage(image);
		return imageMessageToXml(imgmessage);
	}

	/** 初始化文本消息回复 */
	public String initText(String fromUserName, String toUserName, String content) {
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(CommonConstant.MSG_TYPE.TEXT);
		text.setContent(content);
		text.setCreateTime(new Date().getTime());
		return textMessageToXml(text);
	}

	/** 文本消息对象转换为xml */
	public String textMessageToXml(TextMessage message) {
		xstream.alias("xml", message.getClass());// 根节点替换为xml
		return xstream.toXML(message);
	}
	/**初始化主菜单，回复信息*/
	public String menuText() {
		StringBuffer content = new StringBuffer();
		content.append("1、《从军行》王昌龄\n");
		content.append("2、《忆秦娥·娄山关》毛泽东 ");
		content.append("3、《兰亭集序》王羲之\n");
		return content.toString();
	}
	/**初始化第一个菜单回复内容*/
	public String firstMenu() {
		StringBuffer content = new StringBuffer();
		content.append("《从军行》王昌龄\n");
		content.append("青海长云暗雪山，孤城遥望玉门关。\n");
		content.append("黄沙百战穿金甲，不破楼兰终不还！");
		return content.toString();
	}
	
	/**初始化第二个菜单回复内容*/
	public String secondMenu() {
		StringBuffer content = new StringBuffer();
		content.append("《忆秦娥·娄山关》毛泽东 ");
		content.append("西风烈，长空雁叫霜晨月。\n");
		content.append("霜晨月，马蹄声碎，喇叭声咽；\n");
		content.append("雄关漫道真如铁，而今迈步从头越。\n");
		content.append("从头越，苍山如海，残阳如血。");
		return content.toString();
	}
	/**初始化第三个菜单回复内容*/
	public String threeMenu() {
		StringBuffer content = new StringBuffer();
		content.append("《兰亭集序》王羲之\n");
		content.append("永和九年，岁在癸丑，暮春之初，会于会稽山阴之兰亭，修禊事也。群贤毕至，少长咸集。");
		content.append("此地有崇山峻岭，茂林修竹，又有清流激湍，映带左右。引以为流觞曲水，列坐其次。");
		content.append("虽无丝竹管弦之盛，一觞一咏，亦足以畅叙幽情。");
		content.append("\n 是日也，天朗气清，惠风和畅。仰观宇宙之大，俯察品类之盛，所以游目骋怀，足以极视听之娱，信可乐也。");
		content.append("\n 夫人之相与，俯仰一世。或取诸怀抱，悟言一室之内；或因寄所托，放浪形骸之外。虽趣舍万殊，静躁不同，当其欣于所遇，暂得于己，快然自足，不知老之将至。");
		content.append("及其所之既倦，情随事迁，感慨系之矣。向之所欣，俯仰之间，已为陈迹，犹不能不以之兴怀。况修短随化，终期于尽。古人云：“死生亦大矣！”岂不痛哉！");
		content.append("每览昔人兴感之由，若合一契，未尝不临文嗟悼，不能喻之于怀。固知一死生为虚诞，齐彭殇为妄作。后之视今，亦犹今之视昔，悲夫！");
		content.append("故列叙时人，录其所述。虽世殊事异，所以兴怀，其致一也。后之览者，亦将有感于斯文");
		return content.toString();
	}

}
