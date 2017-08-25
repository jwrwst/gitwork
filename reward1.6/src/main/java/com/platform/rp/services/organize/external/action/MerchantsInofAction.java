package com.platform.rp.services.organize.external.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.UploadFileAction;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.RegisterMerchantsEntity;
import com.platform.rp.services.organize.external.rest.IExBsMerchantsInfoRest;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.sms.external.service.ISmsService;
import com.platform.rp.util.Constant;
import com.platform.rp.util.info.codes.DataCode;
import com.platform.rp.util.response.RestfulResultProvider;
@Controller
@Results({
        @Result(name = "success", type = "freemarker", location = "/views/template/${forwardpath}"),
        @Result(name = "error", type = "freemarker", location = "/views/error/error.html") })
@ParentPackage("default")
public class MerchantsInofAction extends UploadFileAction /*implements ModelDriven<RegisterMerchantsEntity>*/{
	

	
	
	private RegisterMerchantsEntity entity;

	@Autowired
	private IExBsMerchantsInfoService bsMerchantsInfoService;
	

	@Autowired
	private IExBsMerchantsInfoRest merchantsInfoRest;

/*    
	@Override
	public RegisterMerchantsEntity getModel() {
		if(entity == null){
			entity = new RegisterMerchantsEntity();
		}
		return entity;
	}

    */
    public RegisterMerchantsEntity getEntity() {
		return entity;
	}
	public void setEntity(RegisterMerchantsEntity entity) {
		this.entity = entity;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//1:账号重复  2手机号重复    3：只是注册     4验证码错误      
	private String type;
	private String code;
	//98703457542432432:管理员注册，不需要验证
	private String rootCode;

	@Autowired
	ISmsService smsService;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setRootCode(String rootCode) {
		this.rootCode = rootCode;
	}
	/**
     * 门店管理
     * 
     * @return
     */
   // @Action("/registerMerForm")
   // @Action(value = "/registerMerForm", results = { @Result(name = SUCCESS, type= "redirect",location = "/views/template/cusAdmin/login.html")
    @Action(value = "/registerMerForm", results = { @Result(name = SUCCESS, type= "redirect",location = "/views/template/cusAdmin/regist2.xhtml")
    , @Result(name = "error", type= "redirect",location = "/views/template/cusAdmin/regist.xhtml?type=${type}")}) 
    public String registerMerForm() {
		try {
			//营业执照
			String filePath = entity.getFilePath();
			if(StringUtils.isNotEmpty(uploadFileName)){
				filePath = copyFile("merchantsfile");
				filePath = properties.host+"/"+filePath;
		    	entity.setFilePath(filePath);
		    	logger.debug(filePath);
			}
			//如果从系统平台登录，不进行验证
			boolean isBypass = //!"98703457542432432".equals(rootCode);
			getSession().getAttribute("userid")==null;
			//验证码
			if(!Constant.IS_DEBUG){
				if(entity.getMerId()==null && isBypass){
					String resutl = smsService.getSmsAuthCode(entity.getPhone());
					if(!code.trim().equals(resutl)){
						type = "4";
						return ERROR;
					}
				}
			}
			String orgCode = bsMerchantsInfoService.updateRegisterMerchants(entity);
			if(entity.getMerId() == null){
				BsMerchantsInfoEntity mer = new BsMerchantsInfoEntity();
				mer.setAccount(entity.getPhone());
				mer.setPassword(entity.getPassword());
				login(mer);
			}
		} catch (Exception e) {
			logger.error("创建商户失败",e);
			type = e.getMessage();
			return ERROR;
		}
    	logger.debug(entity);
        return SUCCESS;   
    }
    
    public void login(BsMerchantsInfoEntity bsMerchantsInfoEntity) throws Exception{	
		BsMerchantsInfoEntity entity = bsMerchantsInfoService.getInfo(bsMerchantsInfoEntity);
		getSession().setAttribute("orgCode", entity.getOrgCode());
		getSession().setAttribute("loginVo", entity);
    }
}
