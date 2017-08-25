package com.platform.rp.services.qrcodeinfo.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItFileInfoEntity;

/**
 * 
 * @author TangJun
 *
 */
public interface ItFileInfoDAO<T> extends BaseDAO<T>{

	public ItFileInfoEntity getByOrderNum(@Param("orderNum")String orderNum);

	
	public List<ItFileInfoEntity> getList(ItFileInfoEntity entity);
	
}
