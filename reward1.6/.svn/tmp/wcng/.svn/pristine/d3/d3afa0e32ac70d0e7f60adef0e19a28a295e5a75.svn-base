package com.platform.rp.services.store.external.rest.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.store.core.dao.entity.BsStoreExtStatisticalEntity;
import com.platform.rp.services.store.external.rest.IExStoreEmployeeManagerRest;
import com.platform.rp.services.store.external.service.IExStoreStatisticService;
import com.platform.rp.services.store.external.vo.StoreEmpCountVo;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExStoreEmployeeManagerRestImpl implements IExStoreEmployeeManagerRest {
	
	private Log log = LogFactory.getLog(ExStoreEmployeeManagerRestImpl.class);
	
	@Autowired
	private IExStoreStatisticService exStoreStatisticService;

	@Override
	public RestfulResult getStoreEmpCountByStoreID(long storeID) {
		try {
			StoreEmpCountVo vo = new StoreEmpCountVo();
			
			BsStoreExtStatisticalEntity entity = exStoreStatisticService.get(storeID);
			//店铺管理人员
			long acount = exStoreStatisticService.getAreaManagerCount(storeID);
			if(entity != null)
			{
				vo.setKeeperCount(entity.getShopkeeperCount());
				vo.setAssistantCount(entity.getShopassistantCount());
				vo.setDividedCount(entity.getDividedCount());
				vo.setAreaManagerCount(acount);
			}
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(vo));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult getStoreKeeperByStoreID(long storeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestfulResult getStoreAssistantByStoreID(long storeID) {
		// TODO Auto-generated method stub
		return null;
	}
}
