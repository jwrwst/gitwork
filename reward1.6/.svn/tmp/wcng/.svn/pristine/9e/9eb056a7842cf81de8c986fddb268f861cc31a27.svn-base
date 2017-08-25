package com.platform.rp.services.organize.inner.service;

import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.core.dao.entity.RegisterMerchantsEntity;

public interface IBsOrganizeService {

	/**
	 * @see 列表分页
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page getOrganizeInfoPage(Page page, Map<String, Object> params) throws Exception;

	/**
	 * 修改信息
	 * @param bsOrganizeEntity
	 * @throws Exception
	 */
	public void update(BsOrganizeEntity bsOrganizeEntity)
			throws Exception;

	/**
	 * 查询详细信息
	 * @param orgCode
	 * @return
	 */
	public BsMerchantsInfoEntity getMerchantsInfo(String orgCode);
	
	/**
	 * 查询注册信息
	 * @param orgCode
	 * @return
	 * @throws Exception
	 */
	public RegisterMerchantsEntity getRegMerchantsInfo(String orgCode)throws Exception;
	
	/**
	 * 批量修改信息
	 * @param orgCodes
	 * @param status
	 */
	public void updateList(String orgCodes,int status,String remark);
	
}