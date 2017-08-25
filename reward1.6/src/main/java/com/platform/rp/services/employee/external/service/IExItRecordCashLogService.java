package com.platform.rp.services.employee.external.service;

import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.entity.ItRecordCashLogEntity;

public interface IExItRecordCashLogService {
	@SuppressWarnings({ "rawtypes" })
	public Page getPage(Page page, Map params) throws Exception;

	public int add(ItRecordCashLogEntity vo);
	
	public void update(ItRecordCashLogEntity vo);
}
