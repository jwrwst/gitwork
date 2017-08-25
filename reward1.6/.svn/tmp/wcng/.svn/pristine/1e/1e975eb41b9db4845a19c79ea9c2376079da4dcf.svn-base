package com.platform.rp.services.wechat.external.common.wxtemplate;

import net.sf.json.JSONObject;

public class WxTemplateMessage {
    
    /**
     * openid
     */
    private String touser;
    
    /**
     * 模板id
     */
    private String template_id;
    
    /**
     * url
     */
    private String url;
    
    /**
     * 顶部颜色
     */
    private String topcolor;
    
    /**
     * 模板中参数内容<br/>
     * json字符串格式<br/>
     * {
           "first": {
              "value":"你好",
              "color":"#173177"
            },
           "keyword1": {
              "value":"8",
              "color":"#173177"
            },
            "keyword2": {
               "value":"10",
               "color":"#173177"
            },
            "keyword3": {
                "value":"2015-06-04 12:00",
                "color":"#173177"
            },
            "remark":{
                 "value":"感谢使用微信排号，欢迎您的到来！",
                 "color":"#173177"
             }
        }
     **/
    private JSONObject data;

    public WxTemplateMessage() {
        super();
    }

    public WxTemplateMessage(String touser, String template_id, String url,
            String topcolor, JSONObject data) {
        super();
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
        this.topcolor = topcolor;
        this.data = data;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

}
