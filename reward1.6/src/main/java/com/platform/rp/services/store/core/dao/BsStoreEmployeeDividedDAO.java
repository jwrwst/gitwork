package com.platform.rp.services.store.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.store.external.vo.BsStoreEmployeeDividedVo;

/**
 * 
 * @author TangJun
 *
 */
public interface BsStoreEmployeeDividedDAO<T> extends BaseDAO<T>{

	/**
	 * 根据门店ID查询
	 * @param storeId
	 * @return
	 */
	public List<BsStoreEmployeeDividedVo> getEmpListByStoreId(@Param("storeId") long storeId);
	
	/**
	 * 根据门店ID查询(不联合查赏金分配表)
	 * @param storeId
	 * @return
	 */
	public List<BsStoreEmployeeDividedVo> getEmpList(@Param("storeId") long storeId);
	
	/**
	 * 删除
	 * @param storeId
	 * @param empId
	 */
	public void deleteByStoreIdAndEmpId(@Param("storeId") long storeId,@Param("empId") long empId);


	public int getCountByOpenIdAndStoreId(@Param("openId")String openId,@Param("storeId")long storeId);
}
