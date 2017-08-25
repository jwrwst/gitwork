package com.platform.rp.services.employee.external.service;

import com.platform.rp.services.employee.external.vo.EmployeeHomepageBaseInfoVo;
import com.platform.rp.services.store.core.dao.entity.ItEvaluateInfoEntity;
import com.platform.rp.services.store.core.dao.entity.ItRewardInfoEntity;

public interface IExEmployeeHomepageService {

	//根据openID获取店员主页和店员所属店铺基础信息
	public EmployeeHomepageBaseInfoVo getEmpHomepageBaseInfoByEmpId(long empID);
	
	//顾客评价店员
	public void addEvaluate(ItEvaluateInfoEntity itEvaluateInfo);
	
	//顾客打赏店员
	public void addReward(ItRewardInfoEntity itRewardInfo);
}
