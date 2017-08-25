package com.platform.rp.services.organize.external.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.common.utils.PageUtils;
import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.employee.external.service.IExBsEmployeeInfoService;
import com.platform.rp.services.organize.core.dao.BsMerchantsEmployeeDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsStoreDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsEmployeeService;
import com.platform.rp.services.store.core.dao.BsStoreExtRewardDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtRewardEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.util.Constant;

@Service
public class ExBsMerchantsEmployeeServiceImpl implements IExBsMerchantsEmployeeService {

	@Autowired
	BsMerchantsEmployeeDAO<BsMerchantsEmployeeEntity> merchantsEmployeeDAO;
	
	@Autowired
	IExBsEmployeeInfoService bsEmployeeInfoService;
	
	@Autowired
	BsMerchantsStoreDAO<BsMerchantsStoreEntity> bsMerchantsStoreDAO;
	
	@Autowired
	BsStoreExtRewardDAO<BsStoreExtRewardEntity> bsStoreExtRewardDAO;
	
	@Override
	public Page getPage(Map params) throws Exception {
		Page page = PageUtils.initPage(params);		
		//查询员工
		if((params.get("last")+"").equals("1")){
			params.put("storeId", params.get("orgCode"));
			return bsEmployeeInfoService.getPage(page, params);
		}else{
			//查询组织信息
			List<BsMerchantsEmployeeEntity> orgList = merchantsEmployeeDAO.getPage(params);		
			int count=merchantsEmployeeDAO.count(params);
			
			page.setResult(orgList);
			page.setTotalCount(count);
		}
		return page;
	}	
	
	public void save(BsMerchantsEmployeeEntity entity) throws Exception{
		merchantsEmployeeDAO.save(entity);
	}

	public List<BsMerchantsEmployeeEntity> getList(String orgCode)throws Exception{
		return merchantsEmployeeDAO.getlist(orgCode);
	}
	
	public void deleteMerchantsEmployee(String orgCode,long empId)throws Exception{
		merchantsEmployeeDAO.deleteByOrgCodeAndEmpId(orgCode, empId);
		List<BsStoreInfoEntity> storeList = bsMerchantsStoreDAO.getListByOrgCode(orgCode);
		if(null != storeList){
			for (BsStoreInfoEntity bsStoreInfoEntity : storeList) {
				bsStoreExtRewardDAO.deleteByStoreIdAndEmpId(bsStoreInfoEntity.getStoreId(), empId);
			}
		}
	}

	@Override
	public void save(BsEmployeeInfoEntity emp,
			BsMerchantsInfoEntity merchantsInfoEntity) {
		BsMerchantsEmployeeEntity merEmp = new BsMerchantsEmployeeEntity();
		merEmp.setEmpId(emp.getEmpId());
		merEmp.setOrgCode(merchantsInfoEntity.getOrgCode());
		merEmp.setStatus(Constant.ABLE);
		merEmp.setUpdateTime(new Date());
		//merEmp.setEmpName(emp.getName());
		//merEmp.setJobNumber(jobNumber);
		merchantsEmployeeDAO.save(merEmp);
	}

	@Override
	public int getCountByOpenIdAndOrgCode(String openId, String orgCode) {
		return merchantsEmployeeDAO.getCountByOpenIdAndOrgCode(openId, orgCode);
	}
	
}
