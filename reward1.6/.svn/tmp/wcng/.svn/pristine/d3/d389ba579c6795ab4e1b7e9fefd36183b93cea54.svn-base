package com.platform.rp.services.organize.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;

/**
 * 
 * @author TangJun
 *
 */
public interface BsOrganizeDAO<T> extends BaseDAO<T>{

	/**
	 * 根据单位编号获取单位信息
	 * @param orgCode
	 * @return
	 */
	public BsOrganizeEntity getInfoByOrgCode(@Param("orgCode")String orgCode);

	/**
	 * 根据单位名称获取单位信息
	 * @param orgCode
	 * @return
	 */
	public BsOrganizeEntity getInfoByOrgName(@Param("orgName")String orgName);
	/**
	 * 获取列表
	 * @param orgCode
	 * @param status
	 * @return
	 */
	public List<BsOrganizeEntity> getList(@Param("orgCode")String orgCode,@Param("status") int status);
	
	/**
	 * 查询微信账号下的所有商户
	 * @param openId
	 * @return
	 */
	public List<BsOrganizeEntity> findMerchantsInfoEntityByOpenId(@Param("openId")
			String openId,@Param("status") String status);
	
	/**
	 * 获取分页信息
	 * @param params
	 * @return
	 */
	public List<BsOrganizeEntity>getPage(Map<String, Object> params);
	
	/**
	 * 获得子节点
	 * @param orgCode
	 * @return
	 */
	public List<String> getlevelList(@Param("orgCodes") List<String> orgCodes);

	public List<BsOrganizeEntity> getListByParentCode(@Param("parentCode")String parentCode,@Param("schema")String schema
			,@Param("orgName")String orgName);

	public List<BsOrganizeEntity> findParentOrg(@Param("openId")
	String openId,@Param("status") String status);
	
	
}
