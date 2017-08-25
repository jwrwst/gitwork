package com.platform.rp.services.store.external.rest.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeEntity;
import com.platform.rp.services.store.external.rest.IExBsStoreEmpRest;
import com.platform.rp.services.store.external.service.IExBsStoreEmpService;
import com.platform.rp.services.store.external.service.IExStoreStatisticService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExBsStoreEmpRestImpl implements IExBsStoreEmpRest {

	private static final  Logger log=Logger.getLogger(ExBsStoreEmpRestImpl.class);
	
	@Autowired
	IExBsStoreEmpService bsStoreEmpService;

	@Autowired
	private IExStoreStatisticService exStoreStatisticService;

	@Override
	public RestfulResult getEmpListByStoreId(long storeId,String filter) {
		try {
			List<BsStoreEmployeeEntity> entity=bsStoreEmpService.getEmpListByStoreId(storeId,filter);	
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error("根据店铺编号获取用户列表",e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}		
	}
	
	@Override
	public RestfulResult deleteManager(long storeId,long empId) {
		try {
			bsStoreEmpService.deleteManager(storeId, empId);
			return RestfulResultProvider.buildSuccessResult();
		} catch (Exception e) {
			log.error("删除店长",e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}		
	}

	@Override
	public RestfulResult getAreaManagerByStoreID(long storeId) {
		try {
			List<BsEmployeeInfoEntity> entitys = exStoreStatisticService.getAreaManagerByStoreID(storeId);	
			List<BsStoreEmployeeEntity> semps = new ArrayList<BsStoreEmployeeEntity>();
			for (int i = 0; i < entitys.size(); i++) {
				BsStoreEmployeeEntity semp = new BsStoreEmployeeEntity();
				BsEmployeeInfoEntity en = entitys.get(i);
				semp.setEmpId(en.getEmpId());
				semp.setEmpName(en.getName());
				semp.setEmpNickName(en.getNickname());
				semp.setHeadPic(en.getHeadPic());
				semp.setStoreId(storeId);
				semps.add(semp);
			}
			return RestfulResultProvider.buildSuccessResult(new ResultData(semps));
		} catch (Exception e) {
			log.error("获取区域经理",e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}		
	}

}
