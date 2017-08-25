package com.platform.rp.services.store.external.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.employee.core.dao.BsEmployeeInfoDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.organize.core.dao.BsMerchantsEmployeeDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsStoreDAO;
import com.platform.rp.services.organize.core.dao.BsOrganizeDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.store.core.dao.BsStoreExtStatisticalDAO;
import com.platform.rp.services.store.core.dao.BsStoreInfoDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtStatisticalEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.services.store.external.service.IExStoreStatisticService;

@Service
public class ExStoreStatisticInfoServiceImpl implements IExStoreStatisticService {

	@Autowired
	BsStoreExtStatisticalDAO<BsStoreExtStatisticalEntity> bsStoreExtStatisticalDAO;

	@Autowired
	BsMerchantsEmployeeDAO<BsMerchantsEmployeeEntity> bsMerchantsEmployeeEntity;
	@Autowired
	BsStoreInfoDAO<BsStoreInfoEntity> bsStoreInfoDAO;
	
	@Autowired
	BsOrganizeDAO<BsOrganizeEntity> bsOrganizeDAO;
	
	@Autowired
	BsEmployeeInfoDAO<BsOrganizeEntity> bsEmployeeInfoDAO;
	@Autowired
	BsMerchantsStoreDAO<BsMerchantsStoreEntity> bsMerchantsStoreDAO;
	
	public void update(BsStoreExtStatisticalEntity entity)throws Exception{
		try {
			bsStoreExtStatisticalDAO.update(entity);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public BsStoreExtStatisticalEntity get(long storeID) throws Exception {
		try {
			return bsStoreExtStatisticalDAO.get(storeID);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public long getAreaManagerCount(long storeID) {
		return getAreaManagerByStoreID(storeID).size();
	}

	/**
	 * 查询门店所有上级组织的用户
	 * @param org
	 * @param count
	 * @param schema
	 * @return
	 */
	/*private long getManagerCount(String orgCode,int count,String schema){
		List<BsMerchantsEmployeeEntity> emps = bsMerchantsEmployeeEntity.getlist(orgCode);
		count += emps.size();
		String pcode = orgCode;
		//顶级商户下的员工不是区域经理
		if(pcode!=null && !pcode.equals(schema)){
			BsOrganizeEntity porg = bsOrganizeDAO.getInfoByOrgCode(pcode);
			if(porg !=null){
				getManagerCount(porg.getParentCode(), count, schema);
			}
		}
		return count;
	}*/

	@Override
	public List<BsEmployeeInfoEntity> getAreaManagerByStoreID(long storeId) {
		//BsStoreInfoEntity store = bsStoreInfoDAO.get(storeId);
		/*BsStoreInfoEntity store = bsStoreInfoDAO.get(storeId);
		if(mstore==null){
			return 0;
		}*/
		BsMerchantsStoreEntity mstore = bsMerchantsStoreDAO.geOrgByStoreId(storeId);
		List<BsEmployeeInfoEntity>  emps = null;
		if(mstore==null){
			emps = new ArrayList<BsEmployeeInfoEntity>();
		}else{
			emps = bsEmployeeInfoDAO.getEmpByOreCode(mstore.getOrgCode());
		}
		return emps;
	}
}
