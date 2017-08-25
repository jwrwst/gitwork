package com.platform.rp.services.organize.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.RegisterMerchantsEntity;

/**
 * 
 * @author TangJun
 *
 */
public interface BsMerchantsInfoDAO<T> extends BaseDAO<T>{

	
	public BsMerchantsInfoEntity getInfoByOrgCode(@Param("orgCode")String orgCode);
	
	public BsMerchantsInfoEntity getInfo(BsMerchantsInfoEntity entity);
	
	//根据店铺ID获取对应商户信息
	public BsMerchantsInfoEntity getInfoByStoreID(@Param("storeId")long storeID);
	
	/**
	 * 根据店铺ID获取上级机构信息
	 * @param storeID
	 * @return
	 */
	public BsMerchantsInfoEntity getPreMerchantsInfoByStoreID(@Param("storeId")long storeID);
	
	/**
	 * 根据商户编号获取用户信息
	 * @param orgCode
	 * @return
	 */
	public BsEmployeeInfoEntity getEmpInfoByOrgCode(@Param("orgCode")String orgCode);

	/**
	 * 查询微信账号下的所有商户
	 * @param openId
	 * @return
	 */
	@Deprecated
	public List<RegisterMerchantsEntity> findRegisterMerchantsEntity(@Param("openId")
			String openId,@Param("orgCode")	String orgCode,@Param("status")String status);
	/**
	 * 查询微信账号下的所有商户(新接口不查门店)
	 * @param openId
	 * @return
	 */
	public List<RegisterMerchantsEntity> getRegisterMerchantsEntity(@Param("openId")
			String openId,@Param("orgCode")	String orgCode,@Param("status")String status);
	/**
	 * 
	 * @param username
	 * @return
	 */
	public BsMerchantsInfoEntity getMerchantsByUsername(String username);
	
	public void updatePassword(@Param("username")String username,@Param("password") String password) ;
	
	public void updateUserName(@Param("username")String username,@Param("password") String password,@Param("orgCode")String orgCode) ;
	/**
	 * 描述：根据电话号码查询商户信息
	 * Administrator 2016年6月2日 下午7:35:07
	 * @param phone
	 */
	public BsMerchantsInfoEntity queryMerchantInfoByPhone(@Param("phone")String phone);
	/**
	 * 描述：根据手机号重置密码
	 * Administrator 2016年6月2日 下午9:34:18
	 * @param phone
	 * @param password
	 */
	public void updatePasswordByPhone(@Param("phone")String phone,@Param("password")String password);
	/**
	 * 描述：更新绑定手机号
	 * Administrator 2016年6月4日 下午10:08:51
	 * @param phone
	 * @param msgCode
	 */
	public void updateBindMobile(@Param("newMobile")String newMobile,@Param("phone")String phone);
}
