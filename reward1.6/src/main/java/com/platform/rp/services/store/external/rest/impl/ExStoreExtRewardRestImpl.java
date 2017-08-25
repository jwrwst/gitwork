package com.platform.rp.services.store.external.rest.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtRewardEntity;
import com.platform.rp.services.store.external.rest.IExStoreExtRewardRest;
import com.platform.rp.services.store.external.service.IExStoreExtRewardService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExStoreExtRewardRestImpl extends BaseController implements IExStoreExtRewardRest {
	
	private Log log = LogFactory.getLog(ExStoreExtRewardRestImpl.class);

	@Autowired
	IExStoreExtRewardService exStoreExtRewardService;

	@Override
	public RestfulResult save(BsStoreExtRewardEntity vo) {
		try {
			exStoreExtRewardService.add(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}

	@Override
	public RestfulResult edit(BsStoreExtRewardEntity vo) {
		try {
			exStoreExtRewardService.update(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}
	
	public RestfulResult getStoreExtReward(long storeId){
		try {
			List<BsStoreExtRewardEntity> volist=exStoreExtRewardService.getStoreExtReward(storeId);
			return RestfulResultProvider.buildSuccessResult(new ResultData(volist));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		
	}
}
