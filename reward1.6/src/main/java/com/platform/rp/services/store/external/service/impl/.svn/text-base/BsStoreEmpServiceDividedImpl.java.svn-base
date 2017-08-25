/**
 * 
 */
package com.platform.rp.services.store.external.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.store.core.dao.BsStoreEmployeeDividedDAO;
import com.platform.rp.services.store.core.dao.BsStoreExtRewardDAO;
import com.platform.rp.services.store.core.dao.BsStoreExtStatisticalDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeDividedEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtRewardEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtStatisticalEntity;
import com.platform.rp.services.store.external.service.IExBsStoreEmpDividedService;
import com.platform.rp.services.store.external.vo.BsStoreEmployeeDividedVo;


@Service
public class BsStoreEmpServiceDividedImpl implements IExBsStoreEmpDividedService {

	@Autowired
	BsStoreEmployeeDividedDAO<BsStoreEmployeeDividedEntity> bsStoreEmployeeDividedDAO;
	
	@Autowired
	BsStoreExtRewardDAO<BsStoreExtRewardEntity> bsStoreExtRewardDAO;
	
	@Autowired
	BsStoreExtStatisticalDAO<BsStoreExtStatisticalEntity> bsStoreExtStatisticalDAO;
	
	public List<BsStoreEmployeeDividedVo> getEmpListByStoreId(long storeId)throws Exception{
		try {
			return bsStoreEmployeeDividedDAO.getEmpListByStoreId(storeId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteManager(long storeId,long empId)throws Exception{
		try {
			//删除店长
			bsStoreEmployeeDividedDAO.deleteByStoreIdAndEmpId(storeId, empId);		
			
			//删除店长分成
			bsStoreExtRewardDAO.deleteByStoreIdAndEmpId(storeId, empId);
			
			//保存店铺统计扩展表
			BsStoreExtStatisticalEntity statistiEntity = new BsStoreExtStatisticalEntity();
			statistiEntity.setDividedCount(-1);
			statistiEntity.setUpdateTime(new Date());
			statistiEntity.setStoreId(storeId);
			bsStoreExtStatisticalDAO.update(statistiEntity);
			
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 通过openId和店铺编号获取是否存在店铺
	 */
	public int getCountByOpenIdAndStoreId(String openId,long storeId)throws Exception{
		try {
			return bsStoreEmployeeDividedDAO.getCountByOpenIdAndStoreId(openId, storeId);
		} catch (Exception e) {
			throw e;
		}
	}
}
