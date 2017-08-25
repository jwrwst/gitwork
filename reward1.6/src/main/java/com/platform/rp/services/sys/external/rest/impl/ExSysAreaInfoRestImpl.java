package com.platform.rp.services.sys.external.rest.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.organize.external.rest.impl.ExBsMerchantsInfoRestImpl;
import com.platform.rp.services.sys.core.dao.entity.SysAreaInfoEntity;
import com.platform.rp.services.sys.external.rest.iExSysAreaInfoRest;
import com.platform.rp.services.sys.external.service.ISysAreaInfoService;
import com.platform.rp.services.sys.external.vo.DataVo;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExSysAreaInfoRestImpl implements iExSysAreaInfoRest{


	private Log log = LogFactory.getLog(ExBsMerchantsInfoRestImpl.class);
	@Autowired
	ISysAreaInfoService sysAreaService;

	@Override
	public RestfulResult getAreaInfoList(String parCode){
		try {
			List<SysAreaInfoEntity> list =sysAreaService.getListByParentCode(parCode);
			log.debug(parCode+"  size:"+list.size());
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	@Override
	public RestfulResult getAllList(){
		try {
			List<DataVo> list =sysAreaService.getList();
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
}
