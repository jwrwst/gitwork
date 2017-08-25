package com.platform.rp.services.employee.external.rest.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.employee.external.rest.IExBsEmployeeInfoRest;
import com.platform.rp.services.employee.external.service.IExBsEmployeeInfoService;
import com.platform.rp.services.wechat.external.vo.LoginVo;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExBsEmployeeInfoRestImpl extends BaseController implements IExBsEmployeeInfoRest {

	private static final  Logger log=Logger.getLogger(ExBsEmployeeInfoRestImpl.class);
	
	@Autowired
	IExBsEmployeeInfoService bsEmployeeInfoService;
	
	@Override
	public RestfulResult save(BsEmployeeInfoEntity entity) {
		try {
			bsEmployeeInfoService.save(entity);
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}
	
	@Override
	public RestfulResult getEmployeeInfoByEmpId(long empID,String openId) {
		
		try {
			BsEmployeeInfoEntity empInfo =null;
			if(empID<1){
				if(openId==null){
					openId = getOpenId();
				}
				empInfo = bsEmployeeInfoService.getInfoByOpenId(openId);
			}else{
			    empInfo = bsEmployeeInfoService.getEmployeeInfoByEmpId(empID);
			}
			return RestfulResultProvider.buildSuccessResult(new ResultData(empInfo));
		} catch (Exception e) {
			log.info(e.getMessage());
			
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
	public RestfulResult getEmployeeInfo(){

		try {
			BsEmployeeInfoEntity empInfo = bsEmployeeInfoService.getInfoByOpenId(getOpenId());
			return RestfulResultProvider.buildSuccessResult(new ResultData(empInfo));
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult edit(BsEmployeeInfoEntity vo) {
		try {
			bsEmployeeInfoService.update(vo);
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		} catch (Exception e) {
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION);
		}
	}

	@Override
	public RestfulResult toListPage(Map<String, String> params) {
		try {
			// 初始化参数
			RequestModel model = new RequestModel();
			ApplicationUtils.parseFormValue(params, model);

			Page page = model.getPage();
			page = bsEmployeeInfoService.getPage(page, params);
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			log.info(e.getMessage());
			
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult removeEmployee(long empID) {
		try {
			bsEmployeeInfoService.removeEmployee(empID);
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}

	
	/**
	 * 获取所有员工
	 * @param orgCode
	 * @throws Exception
	 */
	public RestfulResult getAllEmployee(String orgCode,String storeId){
		try {
		
			Map<String, List>  map = bsEmployeeInfoService.getAllEmployee(orgCode, storeId);
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(map));
		} catch (Exception e) {
			log.info(e.getMessage());
			
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
	
}
