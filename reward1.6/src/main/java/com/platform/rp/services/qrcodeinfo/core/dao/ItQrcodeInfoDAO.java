package com.platform.rp.services.qrcodeinfo.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItQrcodeInfoEntity;

/**
 * 
 * @author TangJun
 *
 */
public interface ItQrcodeInfoDAO<T> extends BaseDAO<T>{

	
	public ItQrcodeInfoEntity getByQrcode(@Param("qrcode")String qrcode,@Param("storeId")long storeId);
	
	public List<ItQrcodeInfoEntity> getListByOrderNum(@Param("orderNum")String orderNUm);
	
}
