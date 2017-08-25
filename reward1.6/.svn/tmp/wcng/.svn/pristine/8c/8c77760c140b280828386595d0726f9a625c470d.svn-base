package com.platform.rp.services.store.external.rest.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.services.store.core.dao.entity.BsStoreAuthDetailEntity;
import com.platform.rp.services.store.external.rest.IExStoreAuthDetailRest;
import com.platform.rp.services.store.external.service.IExStoreAuthDetailService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExStoreAuthDetailRestImpl extends BaseController implements IExStoreAuthDetailRest {
	
	private Log log = LogFactory.getLog(ExStoreAuthDetailRestImpl.class);

	@Autowired
	IExStoreAuthDetailService storeAuthDetailService;

	@Override
	public RestfulResult save(BsStoreAuthDetailEntity vo) {
		try {
			storeAuthDetailService.add(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}
	
	@Override
	public RestfulResult edit(BsStoreAuthDetailEntity vo) {
		
		try {
			storeAuthDetailService.update(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}
	
	@Override
	public RestfulResult getStoreAuthDetailByStoreId(long storeId) {		
		try {
			List<BsStoreAuthDetailEntity> list=storeAuthDetailService.getStoreAuthDetailByStoreId(storeId);
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}		
		
	}
	
}
