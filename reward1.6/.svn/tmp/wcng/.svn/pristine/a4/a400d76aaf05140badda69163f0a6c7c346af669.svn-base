package com.platform.rp.services.template.external.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.platform.rp.services.template.external.vo.TemplateParam;
import com.platform.rp.services.wechat.external.vo.LoginVo;
import com.platform.rp.util.Constant;
import com.platform.rp.util.Properties;

/**
 * 页面控制
 * 
 * @author tangjun
 *
 * 2015年6月12日
 *
 */
@Controller
@Results({
        @Result(name = "success", type = "freemarker", location = "/views/template/web/${forwardpath}"),
        @Result(name = "model", type = "freemarker", location = "/views/model/${forwardpath}"),
        @Result(name = "error", type = "freemarker", location = "/views/error/error.html") })
@ParentPackage("default")
public class TemplatesAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(TemplatesAction.class);
    // 模板路径
    private String forwardpath;
    // weburl
    private String basepath;
    // 签名信息
    private String sign;
    // 页面文档地址
    private String acticleUrl;
    //页面内容
    private String acticleHtml;
    // 模板参数
    private TemplateParam param;
    
    private LoginVo loginVo = new LoginVo(Constant.DUF_OPENID,1615,16); 
//  private LoginVo loginVo = new LoginVo("o21XSv_Y2twGn96dHEkVra_id86Q",1615,16); 
//  private LoginVo loginVo = new LoginVo("o3TzSv8m3gBiVEw3jKHuJuyH7Bck_8",62,16);

    @Autowired
    private Properties properties;

    /**
     * 初始化数据
     */
    private void init() {
    	if(null==param)param=new TemplateParam();
    	
        basepath = properties.webUrl;
        //userAgent示例：Mozilla/5.0 (iPhone; CPU iPhone OS 6_1_4 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Mobile/10B350 QQ/5.4.0.454 NetType/WIFI Mem/202@{"resolution":"1280|720","dpi":"320"}
        String userAgent=ServletActionContext.getRequest().getHeader("User-Agent");
        logger.info("User-Agent:"+userAgent);
        
    }


    /**
     * 请求地址
     * 
     * @return
     */
    @Action("/views/template/web/*")
    public String template() {
    	//纪录日志
        String sn = ServletActionContext.getRequest().getHeader("sn");
        MDC.put("sn", "唯一标识:" + sn + " ");

        // 初始化路径
        init();
        
        try {
        	HttpServletRequest request = ServletActionContext.getRequest();
        
        	if((request.getSession().getAttribute("loginVo") != null)){
        		loginVo = (LoginVo) request.getSession().getAttribute("loginVo");
        	}else if (request.getParameter("adminuser")==null){
        		throw new Exception();
        	}else{
        		try{
        			String adminuser = request.getParameter("adminuser");
        			JSONObject obj= JSONObject.fromObject(adminuser);
        			loginVo.setEmpId(obj.optLong("empId"));
        			loginVo.setOpenId(obj.optString("openId"));
        		}catch(Exception e){}
        		request.getSession().setAttribute("loginVo",loginVo);
        	}
        	//获取请求后后缀
        	String url=request.getRequestURI();
        	String stuff=url.substring(url.indexOf(".")).replace("x", "");
        	//请求页地址
        	ActionMapping mapping=ServletActionContext.getActionMapping();
        	String html=mapping.getName().replaceAll("_", "/")+stuff;
        	
        	forwardpath=html;
        	
            return SUCCESS;        	
		} catch (Exception e) {
			return ERROR;
		}
        
    }
    
    /**
     * 商户管理地址
     * 
     * @return
     */
    @Action(value="/views/template/cusAdmin/*", results = { 
    		@Result(name = "cusAdmin",type = "freemarker", location = "/views/template/cusAdmin/${forwardpath}"),
    		@Result(name = "cusLogin", type = "freemarker", location = "/views/template/cusAdmin/login.html")
    })    
    public String tips() {
    	//纪录日志
        String sn = ServletActionContext.getRequest().getHeader("sn");
        MDC.put("sn", "唯一标识:" + sn + " ");

        // 初始化路径
        init();
        
        try {
        	HttpServletRequest request = ServletActionContext.getRequest();
        	
        	if(request.getSession().getAttribute("loginVo") == null){
        		return "cusLogin";
        	}
        
        	//获取请求后后缀
        	String url=request.getRequestURI();
        	String stuff=url.substring(url.indexOf(".")).replace("x", "");
        	//请求页地址
        	ActionMapping mapping=ServletActionContext.getActionMapping();
        	String html=mapping.getName().replaceAll("_", "/")+stuff;
        	
        	forwardpath=html;
        	
            return "cusAdmin";        	
		} catch (Exception e) {
			return ERROR;
		}
        
    }
    
    /**
     * 商户管理地址不进入安全验证地址
     * 
     * @return
     */
    @Action(value="/views/template/cusAdmin/wap-*", results = { 
    		@Result(name = "cusAdmin",type = "freemarker", location = "/views/template/cusAdmin/${forwardpath}"),
    		@Result(name = "cusLogin", type = "freemarker", location = "/views/template/cusAdmin/login.html")
    })    
    public String pcWap() {
    	//纪录日志
        String sn = ServletActionContext.getRequest().getHeader("sn");
        MDC.put("sn", "唯一标识:" + sn + " ");

        // 初始化路径
        init();
        
        try {
        	HttpServletRequest request = ServletActionContext.getRequest();      	
        
        
        	//获取请求后后缀
        	String url=request.getRequestURI();
        	String stuff=url.substring(url.indexOf(".")).replace("x", "");
        	//请求页地址
        	ActionMapping mapping=ServletActionContext.getActionMapping();
        	String html=mapping.getName().replaceAll("_", "/").replaceAll("wap-", "")+stuff;
        	
        	forwardpath=html;
        	
            return "cusAdmin";        	
		} catch (Exception e) {
			return ERROR;
		}
        
    }
    
    /**
     * 错误地址
     * 
     * @return
     */
    @Action(value="/views/error/*", results = { 
    		@Result(name = "fail",type = "freemarker", location = "/views/error/${forwardpath}")		
    })    
    public String error() {
    	//纪录日志
        String sn = ServletActionContext.getRequest().getHeader("sn");
        MDC.put("sn", "唯一标识:" + sn + " ");

        // 初始化路径
        init();
        
        try {
        	HttpServletRequest request = ServletActionContext.getRequest();
        	
    
        	//获取请求后后缀
        	String url=request.getRequestURI();
        	String stuff=url.substring(url.indexOf(".")).replace("x", "");
        	//请求页地址
        	ActionMapping mapping=ServletActionContext.getActionMapping();
        	String html=mapping.getName().replaceAll("_", "/")+stuff;
        	
        	forwardpath=html;
        	
            return "fail";        	
		} catch (Exception e) {
			return ERROR;
		}
        
    }
  

    public String getForwardpath() {
        return forwardpath;
    }

    public void setForwardpath(String forwardpath) {
        this.forwardpath = forwardpath;
    }

    public TemplateParam getParam() {
        return param;
    }

    public void setParam(TemplateParam param) {
        this.param = param;
    }

    public String getBasepath() {
        return basepath;
    }

    public void setBasepath(String basepath) {
        this.basepath = basepath;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getActicleUrl() {
        return acticleUrl;
    }

    public void setActicleUrl(String acticleUrl) {
        this.acticleUrl = acticleUrl;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

	public String getActicleHtml() {
		return acticleHtml;
	}

	public void setActicleHtml(String acticleHtml) {
		this.acticleHtml = acticleHtml;
	}


	public LoginVo getLoginVo() {
		return loginVo;
	}


	public void setLoginVo(LoginVo loginVo) {
		this.loginVo = loginVo;
	}

	
	
}