package com.thinkgem.jeesite.modules.weixin.util;

import com.thinkgem.jeesite.common.config.Global;

public class TextUtils {

    public static String addWeiXinUser() {
        StringBuffer text = new StringBuffer();
        text.append("感谢关注牛牛医疗——11载医疗服务经验，12万次客户满意的服务，为您提供方便快捷的挂号、送药等就医服务！\n\r");
        text.append("在线客工作时间为9:00---17:00（节假日除外），您也可添加24小时咨询微信：xxxxxxx进行实时沟通。\n\r");
        text.append("您也可以直接点击下列链接进行自助服务：\n\r");
        text.append("\t 1、<a href = \""+getUil("/gerenzhongxin/index")+"\">个人中心</a> \n\r");
        text.append("\t 2、<a href =\""+getUil("/niurensugua/index")+"\">牛牛挂号</a> \n\r");
        text.append("\t 3、<a href = \""+getUil("/niuniusongyao/index")+"\">牛牛送药</a> \n\r");
        return text.toString();
    }

    public static String sendMedicine() {
        String text = "";
        text = "尊敬的会员您好，为了使您的代开药服务更加精准、便捷，请您添加客服微信号：xxxxxxxxx，为您进行一对一指导，或直接点击进入下单入口自助下单即可。";
        text += "委托须知：";
        text += "本平台只提供药品代购及邮寄服务，不提供用药指导。药品价格以医院缴费票据为准，本平台只收取合理的邮寄费用。"
                + "委托方下单前务必核实好正确详细的产品名称及数量及收货地址，所有产品一经发出，不提供退换货服务。";
        return text;

    }

    /**
     * 免费咨询的文本信息
     * @return
     */
    public static String freeConsultation() {
        return "您好，在线客服工作时间为9:00---17:00（节假日除外），您也可添加24小时咨询微信号：xxxxxxx进行实时沟通。"; 
    }
    
    /**
     * 下单咨询的文本信息
     * @return
     */
    public static String xdzxText(){
    	StringBuffer text = new StringBuffer();
    	text.append("您好，在线客服工作时间为9:00---17:00（节假日除外），您也可添加24小时微信号：xxxxxxx进行实时沟通，或点击<a href=\""+getUil("")+"\" >下单入口</a>自助下单。\n\r\n\r");
    	text.append("委托须知：\n\r");
    	text.append("\t 本平台只提供药品代购及邮寄服务，不提供用药指导。药品价格以医院缴费票据为准，本平台只收取合理的邮寄费用。委托方下单前务必核实好正确详细的产品名称及数量及收货地址，所有产品一经发出，不提供退换货服务。");
    	return text.toString();
    }

    public static String healthFile() {
        return "尊敬的会员您好，此项服务为您制定私人健康档案，并进行列年对比，目前只针对满两年会员开放，祝您身体健康、万事如意！";
    }
    
    private static String getUil(String url){
    	return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ConfigUtils.APPID+"&redirect_uri="+Global.getConfig("serverUrl")+url+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    }
}
