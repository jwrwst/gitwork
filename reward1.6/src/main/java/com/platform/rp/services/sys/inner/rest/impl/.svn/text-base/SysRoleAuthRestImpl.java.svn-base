/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.platform.rp.services.sys.inner.rest.impl;

import java.io.Reader;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.framework.mvcface.vo.RestfulResult;
import com.platform.rp.services.sys.core.dao.entity.SysRoleAuthEntity;
import com.platform.rp.services.sys.inner.rest.ISysRoleAuthRest;
import com.platform.rp.services.sys.inner.service.ISysRoleAuthService;
import com.platform.rp.services.sys.inner.service.ISysRoleInfoService;
import com.platform.rp.services.sys.inner.vo.SysRoleInfoVo;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.Properties;
import com.platform.rp.util.info.codes.CommonCode;

/**
 * @see 角色权限表
 * @author
 */
@Controller
public class SysRoleAuthRestImpl extends BaseController implements ISysRoleAuthRest{
	private  Log log=LogFactory.getLog(SysRoleAuthRestImpl.class);
	
    @Autowired
    ISysRoleInfoService sysRoleInfoService;
    
    @Autowired
    ISysRoleAuthService roleAuthService;
    
	@Autowired
	Properties properties;
    
	@SuppressWarnings("unchecked")
    public void getAll(Reader reader) {
		try {
			//初始化参数
			RequestModel model=new RequestModel();
    		ApplicationUtils.parserFormReader(reader,model);
    		
			Page page=new Page();
			page.setPageNo(model.getPageNum());
			page.setPageSize(model.getNumPerPage());
			if(null!=model.getOrderField()){
				page.setOrder(model.getOrderField());
				page.setOrderBy(model.getOrderDirection());
			}
			
			page=sysRoleInfoService.getSysRoleInfoPage(page, model.getKey());
			List<SysRoleInfoVo> list=page.getResult();
			List<SysRoleAuthEntity> entityList;
			for (SysRoleInfoVo sysRoleInfoVo : list) {
//				entityList=roleAuthService.getRoleAuthEntityList(sysRoleInfoVo.getSysRoleId());
//				StringBuffer buff=new StringBuffer();
//				for (SysRoleAuthEntity roleAuthEntity : entityList) {
//					if(buff.length()==0){
//						buff.append(roleAuthEntity.getAuthInfo().getAuthName());
//						continue;
//					}
//					buff.append(","+roleAuthEntity.getAuthInfo().getAuthName());
//				}
//				sysRoleInfoVo.setAuthList(buff.toString());
			}
			request.setAttribute("restfulResult", new RestfulResult(page,CommonCode.SUCCESS));
			request.setAttribute("key", model.getKey());
			
			forward(properties.webUrl+"/web-view/server/page/sys/roleauth/roleauthList.jsp?noload=1");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
    }
	
	
    
}
