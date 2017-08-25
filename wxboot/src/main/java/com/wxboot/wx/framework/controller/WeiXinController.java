package com.wxboot.wx.framework.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxboot.web.framework.common.CodeEnum;
import com.wxboot.web.framework.exception.SysException;
import com.wxboot.wx.framework.config.WeiXinUtils;
import com.wxboot.wx.framework.constants.CommonConstant;
import com.wxboot.wx.framework.utils.SignUtils;
import com.wxboot.wx.message.service.MessageService;

/**
 * 家家帮
 * @author wang
 * 2017年4月3日 下午12:32:08
 * 类描述：
 */
@RestController
@RequestMapping(value = "weixin", produces = "application/json; charset=UTF-8")
public class WeiXinController extends BaseController{
	@Resource
	private MessageService messageService;
	
	/**
	 * 开发者模式验证
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "index",method = RequestMethod.GET)
	public void indexGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String signature = request.getParameter("signature");//微信加密签名  
		String timestamp = request.getParameter("timestamp");//时间戳  
		String nonce = request.getParameter("nonce");//随机数  
		String echostr = request.getParameter("echostr");//随机字符串  
		PrintWriter out = response.getWriter();
		if (SignUtils.checkSignature(signature, timestamp, nonce)) {
			out.println(echostr);
		}
		out.close();  
        out = null;
	}
	
	/**
	 * 微信消息与事件处理与反馈信息处理
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "index",method = RequestMethod.POST)
	public void indexPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//1、将请求、响应的编码均设置为UTF-8（防止中文乱码）  
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//2、获取输出对象
		PrintWriter out = response.getWriter();
		//3、xml转换为map对象
		Map<String, String> map = WeiXinUtils.xmlToMap(request);
		if (map == null) {
			throw new SysException(CodeEnum.PARAMS_EX);
		}
		//4、获取map属性
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		String msgType = map.get("MsgType");
		String content = map.get("Content");
		//5、初始化信息
		String message = null;
		if (CommonConstant.MSG_TYPE.TEXT.equals(msgType)) {
			if ("1".equals(content)) {
				message = messageService.initText(fromUserName, toUserName, messageService.firstMenu());
				log.info("content1:"+message);
			}else if ("2".equals(content)) {
				message = messageService.initText(fromUserName, toUserName, messageService.secondMenu());
				log.info("content2:"+message);
			}else if("3".equals(content)){
				message = messageService.initText(fromUserName, toUserName, messageService.threeMenu());
			}else if("4".equals(content)){
				message = messageService.initImageMessage(fromUserName, toUserName);
				log.info("content4:"+message);
			}else{
				//初始化菜单
				message = messageService.initText(fromUserName, toUserName, messageService.menuText());
				log.info("default content:"+message);
			}
		}else if (CommonConstant.MSG_TYPE.EVENT.equals(msgType)) {
			String eventType = map.get("Event");//LOCATION
			log.info("eventType:"+eventType);
			System.out.println("eventType:"+eventType);
			if (CommonConstant.EVENT_CHILD.SUBSCRIBE.equals(eventType)) {
				//初始化菜单
				message = messageService.initText(fromUserName, toUserName, messageService.menuText());
			}else if(CommonConstant.EVENT_CHILD.LOCATION.equals(eventType)){
				message = messageService.initText(fromUserName, toUserName, messageService.menuText());
			}else if (CommonConstant.EVENT_CHILD.CLICK.equals(eventType)) {
				message = messageService.initVoiceMessage(fromUserName, toUserName);
			}
		}else if(CommonConstant.MSG_TYPE.VOICE.equals(msgType)){
			message = messageService.initVoiceMessage(fromUserName, toUserName);
		}else if(CommonConstant.MSG_TYPE.IMAGE.equals(msgType)){
			message = messageService.initImageMessage(fromUserName, toUserName);
			log.info("content3:"+message);
		}
		//7、输出并关闭输出流
		System.out.println("msgType:"+msgType+"\ncontent:"+content+"\nmessage:"+message);
		out.print(message);
		out.close();
		out = null;
	}
	
	
	
	
	
}

 