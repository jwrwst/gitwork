package com.platform.rp.services.organize.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;

/**
 * 
 * @author TangJun
 *
 */
public interface BsMerchantsEmployeeDAO<T> extends BaseDAO<T>{

	
	public List<BsMerchantsEmployeeEntity> getlist(@Param("orgCode") String orgCode);
	
	public void deleteByOrgCodeAndEmpId(@Param("orgCode") String orgCode,@Param("empId") long empId);
	
	public List<BsMerchantsEmployeeEntity> getPage(Map<String, Object> map);
	
	public List<BsMerchantsEmployeeEntity> getAllEmployee(@Param("orgCode") String orgCode);
	
	public int getCount(@Param("orgCode") String orgCode,@Param("empId") long empId);
	
	/**
	 * 根据员工编号查询所有商户
	 * @param empId
	 * @return
	 */
	public List<BsMerchantsEmployeeEntity> getMerchantsListByEmpId(@Param("empId") long empId);
	
	/**
	 * 根据员工编号查询所有机构
	 * @param empId
	 * @return
	 */
	public List<BsMerchantsEmployeeEntity> getListByEmpId(@Param("empId") long empId,@Param("schema") String schema);

	/**
	 * 根据openId,组织CODE获取数量，验证是否存在
	 */
	public int getCountByOpenIdAndOrgCode(@Param("openId") String openId ,@Param("orgCode") String orgCode);
}
