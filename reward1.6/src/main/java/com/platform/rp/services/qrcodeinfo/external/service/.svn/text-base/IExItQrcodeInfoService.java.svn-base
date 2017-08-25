/**
 * 
 */
package com.platform.rp.services.qrcodeinfo.external.service;

import java.util.List;

import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItQrcodeInfoEntity;

/**
 * 
 * @author tangjun
 * 
 * 2016年3月16日
 */
public interface IExItQrcodeInfoService {

	/**
	 * 根据标识与店铺编号查询信息
	 * @param qrcode
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	public ItQrcodeInfoEntity getByQrcode(String qrcode,long storeId)throws Exception;
	
	
	/**
	 * 根据订单号查询二维码列表
	 * @param orderNUm
	 * @return
	 * @throws Exception
	 */
	public List<ItQrcodeInfoEntity> getListByOrderNum(String orderNUm)throws Exception;

	/**
	 * 保存二维码
	 * @param entity
	 * @throws Exception
	 */
	public void save(ItQrcodeInfoEntity entity)throws Exception;
	
	/**
	 * 修改二维码信息
	 * @param entity
	 * @throws Exception
	 */
	public void update(ItQrcodeInfoEntity entity)throws Exception;

}
