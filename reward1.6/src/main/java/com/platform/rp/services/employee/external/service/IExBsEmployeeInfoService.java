/**
 * 
 */
package com.platform.rp.services.employee.external.service;

import java.util.List;
import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.wechat.external.vo.LoginVo;

/**
 * 
 * @author tangjun
 * 
 * 2016年3月16日
 */
public interface IExBsEmployeeInfoService {

	/**
	 * 保存员工信息
	 * @param entity
	 * @throws Exception
	 */
	public void save(BsEmployeeInfoEntity entity)throws Exception;
	
	/**
	 * 修改员工信息
	 * @param entity
	 * @throws Exception
	 */
	public void update(BsEmployeeInfoEntity entity)throws Exception;
	
	/**
	 * 根据openId查询员工信息
	 * @param openId
	 * @throws Exception
	 */
	public BsEmployeeInfoEntity getInfoByOpenId(String openId)throws Exception;
	
	/**
	 * 根据openId查询员工总数
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public int getCountByOpenId(String openId)throws Exception;
	
	/**
	 * 进入我的收入，判断系统中无当前用户信息，自动保存当前用户基础信息
	 * @param vo
	 * @throws Exception
	 */
	public BsEmployeeInfoEntity saveBaseEmpInfo(LoginVo vo)throws Exception;

	/**
	 * 根据员工ID获取员工信息
	 */
	public BsEmployeeInfoEntity getEmployeeInfoByEmpId(long empID);
	
	@SuppressWarnings({ "rawtypes" })
	//根据店铺ID分页获取该店铺所有店员信息
	public Page getPage(Page page, Map params) throws Exception;
	
	//从店铺中移除指定店员
	public void removeEmployee(long empID) throws Exception;
	
	/**
	 * 获取所有员工
	 * @param orgCode
	 * @throws Exception
	 */
	public Map<String, List> getAllEmployee(String orgCode,String storeId)throws Exception;
}
