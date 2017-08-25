package com.platform.rp.services.waplogin.external.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.platform.rp.services.customer.core.dao.entity.BsCustomerInfoEntity;
import com.platform.rp.services.customer.external.service.IExBsCustomerInfoService;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.employee.external.service.IExBsEmployeeInfoService;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsEmployeeService;
import com.platform.rp.services.organize.external.service.IExBsOrganizeService;
import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItQrcodeInfoEntity;
import com.platform.rp.services.qrcodeinfo.external.service.IExItQrcodeInfoService;
import com.platform.rp.services.store.external.service.IExBsStoreEmpDividedService;
import com.platform.rp.services.store.external.service.IExBsStoreInfoService;
import com.platform.rp.services.wechat.external.vo.LoginVo;
import com.platform.rp.util.Constant;
import com.platform.rp.util.Properties;
import com.platform.rp.util.StringUtils;

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
        @Result(name = "error", type = "freemarker", location = "/views/error/error.html") })
@ParentPackage("default")
public class WapLoginAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(WapLoginAction.class);
    // 模板路径
    private String forwardpath;
    // weburl
    private String basepath;
   
    @Autowired
    private Properties properties;
    
    @Autowired
    private IExBsStoreInfoService bsStoreInfoService;
    
    @Autowired
    private IExBsEmployeeInfoService bsEmployeeInfoService;
    
    @Autowired
    private IExItQrcodeInfoService exItQrcodeInfoService;
    
    @Autowired
    private IExBsCustomerInfoService bsCustomerInfoService;

    @Autowired
    IExBsStoreEmpDividedService bsStoreEmpDividedService;
    @Autowired
    IExBsMerchantsEmployeeService bsMerchantsEmployeeService;

    @Autowired
    IExBsOrganizeService bsOrganizeService;
    //private LoginVo loginVo = new LoginVo();
    //private LoginVo loginVo = new LoginVo("o3TzSv4jkZduiWYTOME_mUujr1Ys",1615,16); 
    //o21XSv6ePvik0SL5fiH7oSJaWijM
    private LoginVo loginVo = new LoginVo(Constant.DUF_OPENID,1615,16); 
    

    /**
     * 初始化数据
     */
    private boolean invalid()throws Exception{  
    	HttpServletRequest request =ServletActionContext.getRequest();
        //userAgent示例：Mozilla/5.0 (iPhone; CPU iPhone OS 6_1_4 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Mobile/10B350 QQ/5.4.0.454 NetType/WIFI Mem/202@{"resolution":"1280|720","dpi":"320"}
    	basepath = properties.webUrl;
    	String userAgent=request.getHeader("User-Agent");
        logger.info("User-Agent:"+userAgent);  
      
        try {      		
    		Object obj=request.getSession().getAttribute("loginVo");
    		
        	if(null==obj){
        		String state=request.getParameter("state");
        		String data=request.getParameter("data");
        		if(StringUtils.isEmpty(state)){
        			throw new Exception("1000");
        		}        		
        		ServletActionContext.getResponse().sendRedirect(properties.host+properties.wxLogin+"?state="+state+"&data="+data);
        		return false;
        		
        	}        	
        	
    		loginVo = (LoginVo) obj; 	
    		if(StringUtils.isNotEmpty(request.getParameter("data"))){
    			loginVo.setData(request.getParameter("data"));
    		}
    		
    		if("321tips".equals(request.getParameter("user"))){
    			loginVo.setOpenId("aaaaaaaaa");
    			loginVo.setData(request.getParameter("data"));
    		}    
    		logger.info(JSONObject.fromObject(loginVo).toString());
		} catch (Exception e) {
			throw e;
		}
        return true;
    }

    /**
     * 门店管理
     * 
     * @return
     */
    @Action("/storelogin")
    public String storelogin() {
        try {
        	//解析参数
        	if(!invalid())return null;
        	//判断是店长
        	int count = bsStoreInfoService.getCountByOpenId(loginVo.getOpenId());
        	//存储转向页面地址
        	if((count==0||StringUtils.isEmpty(loginVo.getOpenId()) )){        		
        		//forwardpath="tips/store/reginfo.html"; 
        		//保存员工基础信息
//            	BsEmployeeInfoEntity entity=bsEmployeeInfoService.saveBaseEmpInfo(loginVo);
//            	loginVo.setEmpId(entity.getEmpId());
//            	loginVo.setStoreId(entity.getStoreId());
//            	loginVo.setMobile(entity.getMobile()==null?"":entity.getMobile());
//            	loginVo.setName(entity.getName()==null?"":entity.getName());
//            	ServletActionContext.getRequest().getSession().setAttribute("loginVo",loginVo);        		
//            	if((entity.getStoreId()==0||entity.getStoreId()==-1||StringUtils.isEmpty(entity.getQrCode()))){
            		forwardpath = "tips/store/assistant.html";
//            	}else{
//            		ServletActionContext.getResponse().sendRedirect("/rs/external/merchants/wap/toMerchantsManage");
//            		return null;
//            	}        		
        	}else{	        		
        		BsEmployeeInfoEntity entity=bsEmployeeInfoService.getInfoByOpenId(loginVo.getOpenId());
        		loginVo.setEmpId(entity.getEmpId());
        		
	        	forwardpath="tips/store/main.html";  
        	}     	
        	      	
            return SUCCESS;        	
		} catch (Exception e) {
			logger.error("门店管理",e);
			return ERROR;
		}
        
    }
    
    /**
     * 商家注册
     * 
     * @return
     */
    @Action("/reglogin")
    public String wapLogin() {
        try {
        	//解析参数
        	if(!invalid())return null;
        	
        	//保存员工基础信息
        	BsEmployeeInfoEntity entity=bsEmployeeInfoService.saveBaseEmpInfo(loginVo);
        	loginVo.setEmpId(entity.getEmpId());
        	loginVo.setStoreId(entity.getStoreId());
        	loginVo.setMobile(entity.getMobile()==null?"":entity.getMobile());
        	loginVo.setName(entity.getName()==null?"":entity.getName());
        	ServletActionContext.getRequest().getSession().setAttribute("loginVo",loginVo);
        	
        	//forwardpath="tips/store/reginfo.html"; 
        	//ServletActionContext.getResponse().sendRedirect(properties.host+"/views/template/web/tips_tenant_registerMerchants.xhtml");
        	ServletActionContext.getResponse().sendRedirect("/rs/external/merchants/toRegister");
        	
            return null;        	
		} catch (Exception e) {
			logger.error("错误",e);
			return ERROR;
		}
        
    }
    
    /**
     * 商户管理
     * 
     * @return
     */
    @Action("/merchants")
    public String merchants() {
        try {
        	//解析参数
        	if(!invalid())return null;
        	
        	//保存员工基础信息
        	BsEmployeeInfoEntity entity=bsEmployeeInfoService.saveBaseEmpInfo(loginVo);
        	loginVo.setEmpId(entity.getEmpId());
        	loginVo.setStoreId(entity.getStoreId());
        	loginVo.setMobile(entity.getMobile()==null?"":entity.getMobile());
        	loginVo.setName(entity.getName()==null?"":entity.getName());
        	ServletActionContext.getRequest().getSession().setAttribute("loginVo",loginVo);

        	ServletActionContext.getRequest().getSession().setAttribute("type",0);
        	//forwardpath="tips/store/reginfo.html"; 
        	ServletActionContext.getResponse().sendRedirect("/rs/external/merchants/toMerchantsManage");
        	
            return null;        	
		} catch (Exception e) {
			logger.error("错误",e);
			return ERROR;
		}
        
    }

    /**
     * 8:区域管理
     * @return
     */
    @Action("/orgmanage")
    public String orgmanage() {
		try {
			//解析参数
        	if(!invalid())return null;
        	

    		//forwardpath="tips/merchants/orgmanage.html";
        	//forwardpath="tips/merchants/orgmanage.html";
        	putEmpInfo();
        	ServletActionContext.getRequest().getSession().setAttribute("type",1);
			//return SUCCESS; 
        	ServletActionContext.getResponse().sendRedirect("/views/template/web/tips_merchants_manage_list.xhtml");     
        	
            return null;        	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("区域管理",e);
			return ERROR;    
		}

    }
    /**
     * 我的收入
     * 
     * @return
     */
    @Action("/melogin")
    public String melogin() {
        try {
        	//解析参数
        	if(!invalid())return null;
        	
        	//保存员工基础信息
        	BsEmployeeInfoEntity entity=bsEmployeeInfoService.saveBaseEmpInfo(loginVo);
        	loginVo.setEmpId(entity.getEmpId());
        	loginVo.setStoreId(entity.getStoreId());

	        forwardpath="tips/me/main.html";
            return SUCCESS;        	
		} catch (Exception e) {
			logger.error("我的收入",e);
			return ERROR;
		}
        
    }
    
    /**
     * 用户打赏
     * @return
     */
    @Action("/waplogin")
    public String waplogin() {
		try {
			//解析参数
        	if(!invalid())return null;
			
        	ItQrcodeInfoEntity entity = exItQrcodeInfoService.getByQrcode(loginVo.getData(), 0);
        	if(null==entity){
        		throw new Exception("二维码已失效");
        	}
        	
        	if(StringUtils.isEmpty(loginVo.getOpenId())){
        		throw new Exception("获取用户openId失败"+JSONObject.fromObject(loginVo));
        	}
        	if(entity.getState()==Constant.NOTUSER){
        		loginVo.setStoreName(entity.getStoreName());
        		loginVo.setStoreId(entity.getStoreId());
        		loginVo.setEmpId(entity.getEmpId());
        		forwardpath = "tips/me/reginfo.html";
        	}else{
        		loginVo.setEmpId(entity.getEmpId());
        		//保存顾客信息
        		BsCustomerInfoEntity cusEntity=bsCustomerInfoService.save(loginVo);
        		loginVo.setCustomerId(cusEntity.getId());
        		forwardpath = "tips/reward/main.html";
        	}
			
			return SUCCESS;      
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户打赏",e);
			return ERROR;    
		}

    }

    /**
     * 添加店长
     * @return
     */
    @Action("/addmanager")
    public String addManager() {
		try {
			//解析参数
        	if(!invalid())return null;
        	
        	//获取编号
        	String storeStr=loginVo.getData();        	
        	if(StringUtils.isEmpty(storeStr)||storeStr.equals("null")){
        		throw new Exception("店铺不存在");
        	}
        	long storeId=Long.valueOf(storeStr);
        	String openId=loginVo.getOpenId();
        	int count = bsStoreInfoService.getCountByOpenIdAndStoreId(openId, storeId);
        	loginVo.setStoreId(storeId);
        	if(count==0){        	
        		forwardpath="tips/store/add.html";
        	}else{        		
        		forwardpath="tips/store/main.html";
        	}

        	putEmpInfo();
        	
			return SUCCESS;      
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加店长",e);
			return ERROR;    
		}
    }
    
    /**
     * 添加分成人员
     * @return
     */
    @Action("/adddivided")
    public String addDivided() {
		try {
			//解析参数
        	if(!invalid())return null;
        	
        	//获取编号
        	String storeStr=loginVo.getData();        	
        	if(StringUtils.isEmpty(storeStr)||storeStr.equals("null")){
        		throw new Exception("店铺不存在");
        	}
        	long storeId=Long.valueOf(storeStr);
        	String openId=loginVo.getOpenId();
        	int count = bsStoreEmpDividedService.getCountByOpenIdAndStoreId(openId, storeId);
        	loginVo.setStoreId(storeId);
        	if(count==0){        	
        		forwardpath="tips/store/adddivided.html";
        	}else{        		
        		forwardpath="tips/store/main.html";
        	}

        	putEmpInfo();
			return SUCCESS;      
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e,e);
			return ERROR;    
		}

    }
    /**
     * 62：添加区域经理
     * @return
     */
    @Action("/addorgperson")
    public String addorgperson() {
		try {
			//解析参数
        	if(!invalid())return null;
        	
        	//获取编号
        	String orgCode=loginVo.getData();        	
        	if(StringUtils.isEmpty(orgCode)||orgCode.equals("null")){
        		throw new Exception("商户CODE不存在");
        	}
        	String openId=loginVo.getOpenId();
        	int count = bsMerchantsEmployeeService.getCountByOpenIdAndOrgCode(openId, orgCode);
        	loginVo.setData(orgCode);
        	BsOrganizeEntity org = bsOrganizeService.getByOrgCode(orgCode);	
        	if(org == null){
        		throw new Exception("商户不存在:"+orgCode);
        	}
        	int type = 0;
        	if(orgCode.equals(org.getSchema())){
        		type = 1;
        	}

        	ServletActionContext.getRequest().setAttribute("type", type);

        	ServletActionContext.getRequest().setAttribute("count", count);
        	//if(count==0){        	
        		forwardpath="tips/merchants/addorgperson.html";
        	//}
        	/*else{      
        		//1商户   0区域
        		if(type == 0){
            		forwardpath="tips/merchants/orgmanage.html";
        		}else{
            		forwardpath="tips/merchants/manage.html";
        		}
        	}*/
        	putEmpInfo();
			return SUCCESS;      
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e,e);
			return ERROR;    
		}

    }
    private void putEmpInfo() throws Exception{
    	BsEmployeeInfoEntity entity=bsEmployeeInfoService.getInfoByOpenId(loginVo.getOpenId());
    	if(entity!=null){
    		loginVo.setEmpId(entity.getEmpId());
    		loginVo.setName(entity.getName()==null?"":entity.getName());
    	}
    }
    /**
     * 添加店员
     * @return
     */
    @Action("/addwaiter")
    public String addWaiter() {
		try {
			//解析参数
			if(!invalid())return null;

			return SUCCESS;      
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return ERROR;    
		}

    }
    
   
    public String getForwardpath() {
        return forwardpath;
    }

    public void setForwardpath(String forwardpath) {
        this.forwardpath = forwardpath;
    }

   
    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

	public String getBasepath() {
		return basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	public LoginVo getLoginVo() {
		return loginVo;
	}

	public void setLoginVo(LoginVo loginVo) {
		this.loginVo = loginVo;
	}

    

    

}