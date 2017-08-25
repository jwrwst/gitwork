/**
 * 
 */
package com.platform.rp.services.store.external.service;

import java.util.List;

import com.platform.rp.services.store.external.vo.BsStoreEmployeeDividedVo;

/**
 * 
 * @author tangjun
 * 
 * 2016年3月16日
 */
public interface IExBsStoreEmpDividedService {

	public List<BsStoreEmployeeDividedVo> getEmpListByStoreId(long storeId) throws Exception;

	/**
	 * 删除店长
	 * @param storeId
	 * @param empId
	 * @throws Exception
	 */
	public void deleteManager(long storeId,long empId)throws Exception;

	/**
	 * 根据openId和店铺编号判断是否绑定
	 * @param openId
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	public int getCountByOpenIdAndStoreId(String openId,long storeId)throws Exception;
}
