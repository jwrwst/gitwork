package com.platform.rp.services.wechat.common;

import java.util.Map;

import com.platform.rp.util.Constant;

import net.sf.json.JSONObject;

public class WxMsgTemplate {
	
	/** 标语   **/
	private static final String SLOGAN = "\n众赏打赏平台，众赏之下，人人勇夫" ; 
	
	public static JSONObject auditTemplate(Map<String,String> data){		
		String first =  "您好，您的申请已审核\n" ;
		String keyword1 = "申请开通众赏的打赏平台";			
		String keyword2 =  data.get("result");
		String remark = data.get("remark")==null?"":data.get("remark");
		String status = data.get("status");
		if((Constant.AUDITING+"").equals(status)){
			first = "您好，您的申请已提交，众赏小伙伴正在马不停蹄的帮您审核中，请耐心等待。\n";	
			keyword2 = "审核中";
		}else if ((Constant.AUDIED+"").equals(status)){
			keyword2 = "审核通过";
		}else if ((Constant.UNAUDIT+"").equals(status)){
			keyword2 = "审核被驳回";
		}
		JSONObject jsonObject = new JSONObject();
        jsonObject.element("first", new JSONObject().element("value", first).element("color", "#173177"));
        jsonObject.element("keyword1", new JSONObject().element("value", keyword1).element("color", "#173177"));
	    jsonObject.element("keyword2", new JSONObject().element("value", keyword2).element("color", "#FA1A2B"));        
        //jsonObject.element("keyword3", new JSONObject().element("value", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss")).element("color", "#173177"));
	    
	    jsonObject.element("remark", new JSONObject().element("value", remark+SLOGAN).element("color", "#173177"));
	    
	    return jsonObject;
	}

}
