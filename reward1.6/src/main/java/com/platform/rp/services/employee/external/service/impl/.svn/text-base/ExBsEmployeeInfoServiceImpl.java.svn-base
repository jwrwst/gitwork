/**
 * 
 */
package com.platform.rp.services.employee.external.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.BsEmployeeInfoDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.employee.external.service.IExBsEmployeeInfoService;
import com.platform.rp.services.employee.external.vo.EmployeeInfoVo;
import com.platform.rp.services.organize.core.dao.BsMerchantsEmployeeDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItQrcodeInfoEntity;
import com.platform.rp.services.qrcodeinfo.external.service.IExItQrcodeInfoService;
import com.platform.rp.services.store.core.dao.BsStoreEmployeeDAO;
import com.platform.rp.services.store.core.dao.BsStoreEmployeeDividedDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeDividedEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtStatisticalEntity;
import com.platform.rp.services.store.external.service.IExStoreStatisticService;
import com.platform.rp.services.store.external.vo.BsStoreEmployeeDividedVo;
import com.platform.rp.services.wechat.external.vo.LoginVo;
import com.platform.rp.util.Constant;


@Service
public class ExBsEmployeeInfoServiceImpl implements IExBsEmployeeInfoService {

	@Autowired
	BsEmployeeInfoDAO<BsEmployeeInfoEntity> bsEmployeeInfoDAO;
	 
	@Autowired
	IExStoreStatisticService exStoreStatisticService;
	
	@Autowired
	IExItQrcodeInfoService exItQrcodeInfoService;
	
	@Autowired
	BsMerchantsEmployeeDAO<BsMerchantsEmployeeEntity> bsMerchantsEmployeeDAO;
	
	@Autowired
	BsStoreEmployeeDAO<BsStoreEmployeeEntity> bsStoreEmployeeDAO;
	
	@Autowired
	BsStoreEmployeeDividedDAO<BsStoreEmployeeDividedEntity> bsStoreEmployeeDividedDAO;
	
	/**
	 * 绑定员工二维码
	 */
	public synchronized void save(BsEmployeeInfoEntity entity)throws Exception{
		try {
			Date dt=new Date();
			
			ItQrcodeInfoEntity qrCodeEntity = exItQrcodeInfoService.getByQrcode(entity.getQrCode(),0);
			if(qrCodeEntity!=null&& Constant.USER==qrCodeEntity.getState()){
				throw new Exception("二维码已被绑定");
			}
			
			//判断是否为自由人
			if(entity.getLevel()!=0){
				entity.setLevel(Constant.EMPLEVEL3);
			}else{
				entity.setLevel(Constant.EMPLEVEL2);
			}			
			
			//保存员工信息表
			if(entity.getEmpId()>0){
				//删除历史绑定信息
				this.removeEmployee(entity.getEmpId());
				//修改数据
				this.update(entity);				
			}else{
				BsEmployeeInfoEntity selentity=bsEmployeeInfoDAO.getInfoByOpenId(entity.getWxAccount());
				if(selentity!=null){
					entity.setEmpId(selentity.getEmpId());
					this.update(entity);
				}else{
					bsEmployeeInfoDAO.save(entity);	
				}
			}
			//保存店铺统计扩展表
			BsStoreExtStatisticalEntity statistiEntity = new BsStoreExtStatisticalEntity();
			statistiEntity.setShopassistantCount(1);
			statistiEntity.setUpdateTime(dt);
			statistiEntity.setStoreId(entity.getStoreId());
			exStoreStatisticService.update(statistiEntity);
			
			//保存二维码信息表
			
			ItQrcodeInfoEntity qrcodeEntity=new ItQrcodeInfoEntity();
			qrcodeEntity.setEmpId(entity.getEmpId());
			qrcodeEntity.setUpdateTime(dt);
			qrcodeEntity.setState(Constant.USER);
			qrcodeEntity.setQrcode(entity.getQrCode());
			exItQrcodeInfoService.update(qrcodeEntity);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void update(BsEmployeeInfoEntity entity)throws Exception{
		try {
			bsEmployeeInfoDAO.update(entity);			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public BsEmployeeInfoEntity getInfoByOpenId(String openId)throws Exception{
		try {
			return bsEmployeeInfoDAO.getInfoByOpenId(openId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int getCountByOpenId(String openId)throws Exception{
		try {
			return bsEmployeeInfoDAO.getCountByOpenId(openId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public BsEmployeeInfoEntity saveBaseEmpInfo(LoginVo vo)throws Exception{
		try {
			BsEmployeeInfoEntity entity=bsEmployeeInfoDAO.getInfoByOpenId(vo.getOpenId());
			//如果已存在个人信息，就无须添加
			if(null==entity){
				entity= new BsEmployeeInfoEntity();
				Date dt=new Date();
				entity.setName(vo.getNickname());
				entity.setWxAccount(vo.getOpenId());
				entity.setNickname(vo.getNickname());
				entity.setHomeAddress(vo.getCountry()+"|"+vo.getProvince()+"|"+vo.getCity());
				entity.setGender(vo.getSex());
				entity.setCreatedtime(dt);
				entity.setHeadPic(vo.getHeadimgurl());
				entity.setWxAccount(vo.getOpenId());
				
				bsEmployeeInfoDAO.save(entity);
			}
			return entity;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public BsEmployeeInfoEntity getEmployeeInfoByEmpId(long empID) {
		return bsEmployeeInfoDAO.getEmployeeInfoByEmpId(empID);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page getPage(Page page, Map params) throws Exception {
		try {
			if(!params.containsKey("pageSize")){
				params.put("pageSize", page.getPageSize());
				params.put("start", page.getStart());
			}
			// 查询总数
			int count = bsEmployeeInfoDAO.count(params);
			List<EmployeeInfoVo> list = bsEmployeeInfoDAO.getPage(params);
			page.setResult(list);
			page.setTotalCount(count);
			return page;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 移除员工
	 */
	@Override
	public void removeEmployee(long empID) throws Exception {
		
		//根据员工ID获取员工信息
		BsEmployeeInfoEntity empInfoEntity = bsEmployeeInfoDAO.getEmployeeInfoByEmpId(empID);
		
		if(empInfoEntity != null)
		{
			//更新qrcode_info表
			ItQrcodeInfoEntity qrCodeInfoEntity = exItQrcodeInfoService.getByQrcode(empInfoEntity.getQrCode(), empInfoEntity.getStoreId());
			if(qrCodeInfoEntity != null)
			{
				qrCodeInfoEntity.setEmpId(-1);
				qrCodeInfoEntity.setState(1);
				exItQrcodeInfoService.update(qrCodeInfoEntity);
				
				//保存店铺统计扩展表
				BsStoreExtStatisticalEntity statistiEntity = new BsStoreExtStatisticalEntity();
				statistiEntity.setShopassistantCount(-1);
				statistiEntity.setUpdateTime(new Date());
				statistiEntity.setStoreId(empInfoEntity.getStoreId());
				exStoreStatisticService.update(statistiEntity);
				
			}
					
			//更新员工信息表
			if(Constant.EMPLEVEL3 == empInfoEntity.getLevel()){
				empInfoEntity.setLevel(Constant.EMPLEVEL1);
			}else{
				empInfoEntity.setLevel(0);
			}
			empInfoEntity.setQrCode("");
			empInfoEntity.setStoreId(-1);
			bsEmployeeInfoDAO.update(empInfoEntity);
		}
	}
	
	/**
	 * 获取所有员工
	 * @param orgCode
	 * @throws Exception
	 */
	public Map<String, List> getAllEmployee(String orgCode,String storeId)throws Exception{
		try {
			Map<String, List> map = new HashMap<String, List>();
			//查所有部门人员
			List<BsMerchantsEmployeeEntity> emplist = bsMerchantsEmployeeDAO.getAllEmployee(orgCode);			
			map.put("merchants", emplist);			
			
			if(storeId.equals("0")){
				return map;
			}
			
			//查询当前店的店长
			List<BsStoreEmployeeEntity> storeEmplist = bsStoreEmployeeDAO.getEmpListByStoreId(Long.valueOf(storeId));
			map.put("manager", storeEmplist);	
			
			//查询店员
			/**
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("start", -1);
			params.put("pageSize", 0);
			params.put("storeId", Long.valueOf(storeId));
			List<EmployeeInfoVo> list = bsEmployeeInfoDAO.getPage(params);
			map.put("empployee", list);	
			**/
			//查询分成人员
			List<BsStoreEmployeeDividedVo>  divideList = bsStoreEmployeeDividedDAO.getEmpList(Long.valueOf(storeId));
			map.put("divided", divideList);	
			
			return map;
		} catch (Exception e) {
			throw e;
		}
	}
}
