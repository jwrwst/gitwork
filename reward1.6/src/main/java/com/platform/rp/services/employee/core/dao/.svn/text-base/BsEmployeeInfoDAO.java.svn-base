package com.platform.rp.services.employee.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.employee.external.vo.EmployeeHomepageBaseInfoVo;
import com.platform.rp.services.employee.external.vo.EmployeeInfoVo;

/**
 * 
 * @author TangJun
 *
 */
public interface BsEmployeeInfoDAO<T> extends BaseDAO<T>{

	//根据员工ID获取员工信息
	public BsEmployeeInfoEntity getEmployeeInfoByEmpId(@Param("empId") long empID);
	
	//根据openID获取店员主页和店员所属店铺基础信息
	public EmployeeHomepageBaseInfoVo getEmpHomepageBaseInfoByEmpId(@Param("empId") long empID);
	
	public BsEmployeeInfoEntity getInfoByOpenId(@Param("openId")String openId);
	
	public int getCountByOpenId(@Param("openId")String openId);
	
	//根据店铺ID分页获取该店铺店员信息
	public List<EmployeeInfoVo>getPage(Map<String, Object> params);
	
	/**
	 * 根据商户ID获取商户下的所有员工(管理员s)
	 */
	public List<BsEmployeeInfoEntity> getEmpByOreCode(@Param("orgCode")String orgCode);
	
}
