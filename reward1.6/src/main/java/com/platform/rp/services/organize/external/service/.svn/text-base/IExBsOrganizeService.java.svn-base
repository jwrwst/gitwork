package com.platform.rp.services.organize.external.service;

import java.util.List;

import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;


public interface IExBsOrganizeService {

	public List<BsOrganizeEntity> getList(String orgCode,int status,String storeName) throws Exception;
	
	public void save(BsOrganizeEntity entity) throws Exception;
	
	
	public void remove(String orgCode) throws Exception;
	
	/**
	 * 查询所有子节点
	 * @param orgCode
	 * @return
	 * @throws Exception
	 */
	public List<String> findLevel(String orgCode)throws Exception;

	/**
	 * 根据父ID查询
	 * @param parentCode
	 * @param orgName 
	 * @return
	 */
	public List<BsOrganizeEntity> getListByParentCode(String parentCode,String schema, String orgName);

	public BsOrganizeEntity getByOrgCode(String orgCode);
}
