package com.platform.rp.services.employee.core.dao;

import java.util.List;
import java.util.Map;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.employee.core.dao.entity.ItRecordEmpLogEntity;
import com.platform.rp.services.employee.external.vo.RecordEmpLogVo;

public interface ItRecordEmpLogDAO<T> extends BaseDAO<T>  {

	//根据店铺ID获取该店铺所有打赏及评价信息
	public List<ItRecordEmpLogEntity> getRecordLogListByStoreId(long storeID);
	
	//根据店铺ID分页获取打赏及评价信息
	public List<RecordEmpLogVo>getPage(Map<String, Object> params);
}
