/**
 * 
 */
package com.platform.rp.services.store.external.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.organize.core.dao.BsMerchantsEmployeeDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.store.core.dao.BsStoreEmployeeDAO;
import com.platform.rp.services.store.core.dao.BsStoreEmployeeDividedDAO;
import com.platform.rp.services.store.core.dao.BsStoreExtRewardDAO;
import com.platform.rp.services.store.core.dao.BsStoreExtStatisticalDAO;
import com.platform.rp.services.store.core.dao.BsStoreInfoDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeDividedEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtRewardEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtStatisticalEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.services.store.external.service.IExBsStoreEmpService;
import com.platform.rp.services.store.external.vo.BsStoreEmployeeDividedVo;


@Service
public class ExBsStoreEmpServiceImpl implements IExBsStoreEmpService {

	@Autowired
	BsStoreEmployeeDAO<BsStoreEmployeeEntity> bsStoreEmployeeDAO;
	
	@Autowired
	BsStoreExtRewardDAO<BsStoreExtRewardEntity> bsStoreExtRewardDAO;
	
	@Autowired
	BsStoreExtStatisticalDAO<BsStoreExtStatisticalEntity> bsStoreExtStatisticalDAO;
	
	@Autowired
	BsMerchantsEmployeeDAO<BsMerchantsEmployeeEntity> merchantsEmployeeDAO;
	
	@Autowired
	BsStoreEmployeeDividedDAO<BsStoreEmployeeDividedEntity> bsStoreEmployeeDividedDAO;
	
	@Autowired
	BsStoreInfoDAO<BsStoreInfoEntity> bsStoreInfoDAO;
	/**
	 * 根据店铺编号获取店长列表
	 */
	public List<BsStoreEmployeeEntity> getEmpListByStoreId(long storeId,String filter)throws Exception{
		try {
			List<BsStoreEmployeeEntity> empList = bsStoreEmployeeDAO.getEmpListByStoreId(storeId);
			BsStoreEmployeeEntity entity = null;
			if(null!=filter && filter.equals("all")){
				//查询店铺信息
				BsStoreInfoEntity storeEntity = bsStoreInfoDAO.get(storeId);
				//查所有部门人员
				List<BsMerchantsEmployeeEntity> merEmplist = merchantsEmployeeDAO.getAllEmployee(storeEntity.getStoreCode());
				if(merEmplist!=null){
					for (BsMerchantsEmployeeEntity mer : merEmplist) {
						entity = new BsStoreEmployeeEntity();
						entity.setEmpId(mer.getEmpId());
						entity.setEmpName(mer.getEmpName());
						entity.setJobTitle("管理员");
						empList.add(entity);
					}	
				}
				//查询分成人员
				List<BsStoreEmployeeDividedVo>  divideList = bsStoreEmployeeDividedDAO.getEmpList(Long.valueOf(storeId));
				if(null!=divideList){
					for (BsStoreEmployeeDividedVo divi : divideList) {
						entity = new BsStoreEmployeeEntity();
						entity.setEmpId(divi.getEmpId());
						entity.setEmpName(divi.getEmpName());
						entity.setJobTitle(divi.getJobTitle());
						empList.add(entity);
					}
				}
			}
			return empList;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 删除店长
	 */
	public synchronized void deleteManager(long storeId,long empId)throws Exception{
		try {
			//删除店长
			bsStoreEmployeeDAO.deleteByStoreIdAndEmpId(storeId, empId);		
			
			//查询店铺信息
			BsStoreInfoEntity storeEntity = bsStoreInfoDAO.get(storeId);
			//判断是否属于区域经理,如果区域经理，不删除分成比例
			List<BsMerchantsEmployeeEntity> merEmpList=merchantsEmployeeDAO.getListByEmpId(empId,storeEntity.getStoreCode());
			if(merEmpList==null||merEmpList.size()<=0){
				//删除店长分成
				bsStoreExtRewardDAO.updateByStoreIdAndEmpId(storeId, empId);
			}
			
			//保存店铺统计扩展表
			BsStoreExtStatisticalEntity statistiEntity = new BsStoreExtStatisticalEntity();
			statistiEntity.setShopkeeperCount(-1);
			statistiEntity.setUpdateTime(new Date());
			statistiEntity.setStoreId(storeId);
			bsStoreExtStatisticalDAO.update(statistiEntity);
			
		} catch (Exception e) {
			throw e;
		}
	}
}
