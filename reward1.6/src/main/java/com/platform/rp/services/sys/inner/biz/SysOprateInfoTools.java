package com.platform.rp.services.sys.inner.biz;

import org.springframework.beans.factory.annotation.Autowired;

import com.platform.rp.common.application.ApplicationContextUtil;
import com.platform.rp.services.sys.core.dao.entity.SysOprateInfoEntity;
import com.platform.rp.services.sys.inner.service.ISysOprateInfoService;

public class SysOprateInfoTools {

	@Autowired
	public static ISysOprateInfoService sysOprateInfoService;
	
	public static void log(SysOprateInfoEntity entity){
		if(sysOprateInfoService == null)
			sysOprateInfoService = ApplicationContextUtil.getBean(ISysOprateInfoService.class);
		
		sysOprateInfoService.add(entity);
	}
	
	public static void log(String module, String operateLog, int type, String operator){
		SysOprateInfoEntity entity = new SysOprateInfoEntity();
		entity.setModule(module);
		entity.setOperateLog(operateLog);
		entity.setType(type);
		entity.setOperator(operator);
		
		log(entity);
	}
	
	public static void log(String module, String operateLog, String operator){
		SysOprateInfoEntity entity = new SysOprateInfoEntity();
		entity.setModule(module);
		entity.setOperateLog(operateLog);
		entity.setType(0);
		entity.setOperator(operator);
		
		log(entity);
	}
	
	public static void log(String module, String operateLog){
		SysOprateInfoEntity entity = new SysOprateInfoEntity();
		entity.setModule(module);
		entity.setOperateLog(operateLog);
		entity.setType(0);
		entity.setOperator("");
		
		log(entity);
	}
}
