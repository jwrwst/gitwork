/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.platform.rp.services.organize.inner.rest.impl;

import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.framework.mvcface.vo.ResponseResult;
import com.platform.rp.framework.mvcface.vo.RestfulResult;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.core.dao.entity.RegisterMerchantsEntity;
import com.platform.rp.services.organize.inner.rest.IBsOrganizeRest;
import com.platform.rp.services.organize.inner.service.IBsOrganizeService;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.Properties;
import com.platform.rp.util.StringUtils;
import com.platform.rp.util.info.codes.CommonCode;

/**
 * 
 * @author
 */
@Controller
public class BsOrganizeRestImpl extends BaseController implements IBsOrganizeRest {
	private Log log = LogFactory.getLog(BsOrganizeRestImpl.class);


	@Autowired
	IBsOrganizeService bsOrganizeService;
	
	@Autowired
	Properties properties;


	@Override
	public void getOrganizeInfoPage(Reader reader) {
		try {
			Map<String,String> params = ApplicationUtils.populate(reader);

			// 初始化参数
			RequestModel model = new RequestModel();
			model.parseFormValue(params, model);
			
			Page page = new Page();
			page.setPageNo(model.getPageNum());
			page.setPageSize(model.getNumPerPage());
			if (null != model.getOrderField()) {
				page.setOrder(model.getOrderField());
				page.setOrderBy(model.getOrderDirection());
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.putAll(params);
			map.put("orgName", model.getKey());
			map.put("startTime", model.getStartDate());
			map.put("endTime", model.getEndDate());
			
			
			page = bsOrganizeService.getOrganizeInfoPage(page, map);
			request.setAttribute("restfulResult", new RestfulResult(page, CommonCode.SUCCESS));
			request.setAttribute("key", model.getKey());

			forward(properties.webUrl+"/views/template/admin/merchants/audit/auditList.jsp?noload=1");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void toListPage(Reader reader) {
		this.getOrganizeInfoPage(reader);
	}

	
	/**
	 * 修改信息
	 * @param bsOrganizeEntity
	 * @throws Exception
	 */
	public String update(Reader reader){			
		
		try {
			Map<String,String> params = ApplicationUtils.populate(reader);
			
			BsOrganizeEntity entity = new BsOrganizeEntity();
			entity.setOrgCode(params.get("orgCode"));
			if(StringUtils.isNotEmpty(params.get("status"))){
				entity.setStatus(Integer.parseInt(params.get("status")) );
			}			
			entity.setRemark(params.get("remark"));
			entity.setUpdateTime(new Date());
			
			bsOrganizeService.update(entity);
			
			return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
		} catch (Exception e) {
			log.error(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.SYS_BUSY));
		}
	}
	
	/**
	 * 批量修改信息
	 * @param bsOrganizeEntity
	 * @throws Exception
	 */
	public String updateList(String orgCodes,int status,String remark){			
		try {			
			bsOrganizeService.updateList(orgCodes, status, remark);
			return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
		} catch (Exception e) {
			log.error(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.SYS_BUSY));
		}
	}

	/**
	 * 查询详细信息
	 * @param orgCode
	 * @return
	 */
	public void getMerchantsInfo(String orgCode){ 
		try {
			BsMerchantsInfoEntity entity= bsOrganizeService.getMerchantsInfo(orgCode);
			RegisterMerchantsEntity regentity =  bsOrganizeService.getRegMerchantsInfo(orgCode);
			request.setAttribute("bsMerchantsInfo", entity);
			request.setAttribute("registerMerchantsEntity", regentity);
			forward(properties.webUrl+"/views/template/admin/merchants/audit/auditedit.jsp");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	

}
