package com.platform.rp.services.employee.external.service;

import java.util.List;
import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.entity.ItRecordEmpLogEntity;

public interface IExItRecordEmpLogService {

	/**
	 * @see 门店信息分类
	 */
	@SuppressWarnings({ "rawtypes" })
	public Page getPage(Page page, Map params) throws Exception;

	public void add(ItRecordEmpLogEntity vo);
	
	public void update(ItRecordEmpLogEntity vo);
	
	//根据店铺ID获取该店铺所有打赏及评价信息
	public List<ItRecordEmpLogEntity> getRecordLogListByStoreId(long storeID);
}
