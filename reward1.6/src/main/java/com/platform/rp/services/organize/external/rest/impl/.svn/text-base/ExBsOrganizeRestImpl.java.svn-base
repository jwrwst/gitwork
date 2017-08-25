package com.platform.rp.services.organize.external.rest.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.external.rest.IExBsOrganizeRest;
import com.platform.rp.services.organize.external.service.IExBsOrganizeService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExBsOrganizeRestImpl implements IExBsOrganizeRest {
	
	private Log log = LogFactory.getLog(ExBsOrganizeRestImpl.class);
	
	@Autowired
	private IExBsOrganizeService bsOrganizeService;
	
	@Context
	HttpServletRequest request;
	
	public  RestfulResult save(BsOrganizeEntity entity){
		try {
			bsOrganizeService.save(entity);
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		
	}
	
	/**
	 * 删除机构
	 * @param entity
	 * @return
	 */
	public  RestfulResult remove(String orgCode){
		try {
			bsOrganizeService.remove(orgCode);
			return RestfulResultProvider.buildSuccessResult();
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		
	}
	
	/**
	 * 获取机构列表
	 */
	public RestfulResult getList(String orgCode,int status,String storeName){
		try {
			orgCode = (String) request.getSession().getAttribute("orgCode");
			List<BsOrganizeEntity> list=bsOrganizeService.getList(orgCode,status,storeName);
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}


	@Override
	public RestfulResult getListByParentCode(Map<String,String> entity) {
		try {
			String parentCode = entity.get("parentCode");
			String schema = entity.get("schema");
			String orgName = entity.get("orgName");
			List<BsOrganizeEntity> list = bsOrganizeService.getListByParentCode(parentCode,schema,orgName);
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
}
