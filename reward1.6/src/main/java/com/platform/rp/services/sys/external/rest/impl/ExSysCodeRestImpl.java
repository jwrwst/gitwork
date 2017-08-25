package com.platform.rp.services.sys.external.rest.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.external.rest.impl.ExBsMerchantsInfoRestImpl;
import com.platform.rp.services.sys.core.dao.entity.SysCodeInfoEntity;
import com.platform.rp.services.sys.external.rest.IExSysCodeRest;
import com.platform.rp.services.sys.external.service.ISysCodeService;
import com.platform.rp.services.sys.external.vo.DataVo;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.info.codes.DataCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExSysCodeRestImpl implements IExSysCodeRest {

	private Log log = LogFactory.getLog(ExBsMerchantsInfoRestImpl.class);
	@Autowired
	ISysCodeService sysCodeService;
	
	@Override
	public RestfulResult findByParam(String jsonStr) {
		
		if(StringUtils.isEmpty(jsonStr))
			return RestfulResultProvider.buildCodeResult(CommonCode.PARSE_ERROR, "没有任何参数信息。");
		
		JSONObject json = JSONObject.fromObject(jsonStr);
		String codeClass = json.optString("codeClass");
		if(StringUtils.isEmpty(codeClass))
			return RestfulResultProvider.buildCodeResult(CommonCode.PARSE_ERROR, "参数codeClass没有信息。");
		
		String codeValue = json.optString("codeValue");
		if(StringUtils.isEmpty(codeValue))
			return RestfulResultProvider.buildCodeResult(CommonCode.PARSE_ERROR, "参数codeValue没有信息。");
		
		SysCodeInfoVo sysCodeVo = sysCodeService.findSysCodeByClassAndCodeValue(codeClass, codeValue);
		if(sysCodeVo == null)
			return RestfulResultProvider.buildCodeResult(DataCode.DATA_NOEXSIST);

		return RestfulResultProvider.buildSuccessResult(new ResultData(sysCodeVo));
	}

	public RestfulResult findByCodeClass(String codeClass){

		if(StringUtils.isEmpty(codeClass))
			return RestfulResultProvider.buildCodeResult(CommonCode.PARSE_ERROR, "参数codeClass没有信息。");
		
		List<SysCodeInfoVo> list = sysCodeService.getListByClassify(codeClass);
		if(list == null)
			return RestfulResultProvider.buildCodeResult(DataCode.DATA_NOEXSIST);

		return RestfulResultProvider.buildSuccessResult(new ResultData(list));
	}
	public RestfulResult getCodeInfoList(String parCode){
		try {
			List<SysCodeInfoEntity> list =sysCodeService.getListByParentCode(parCode);
			log.debug(parCode+"  size:"+list.size());
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}

	@Override
	public RestfulResult getAllList(String parCode){
		try {
			List<DataVo> list =sysCodeService.getAllList(parCode);
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}

	@Override
	public RestfulResult getWapAllList(String parCode){
		try {
			List<DataVo> list =sysCodeService.getAllList(parCode);
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}

	@Override
	public RestfulResult cleanCache() {
		try {
			sysCodeService.cleanCache();
			return RestfulResultProvider.buildSuccessResult(new ResultData(null));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
}
