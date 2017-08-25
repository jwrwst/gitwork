package com.platform.rp.services.organize.external.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.core.dao.entity.RegisterMerchantsEntity;
import com.platform.rp.util.info.codes.DataCode;


public interface IExBsMerchantsInfoService {
	
	public static String USER_EXISTS = "1";         //用户已经存在
	public static String ORGNAME_EXISTS = "2";      //品牌名称已经存在

	public void update(BsMerchantsInfoEntity bsMerchantsInfoEntity)throws Exception;

	public BsMerchantsInfoEntity getInfo(BsMerchantsInfoEntity bsMerchantsInfoEntity) throws Exception;

	/**
	 * 校验用户名密码
	 * @param bsMerchantsInfoEntity
	 * @return
	 * @throws Exception
	 */
	public DataCode checkLoing(String username,String password);
	
	public void save(BsMerchantsInfoEntity bsMerchantsInfoEntity)
			throws Exception;
	
	//根据店铺ID获取商户统计起始和结束时间
	public void getStartAndEndTime(long storeID, String[] startAndEnd);
	public void getStartAndEndTime(String orgCode, String[] startAndEnd);
	
	public BsMerchantsInfoEntity getInfoByStoreID(long storeId)
			throws Exception;
	/**
	 * 保存注册信息
	 * @param model
	 * @param openId
	 */
	public void saveRegisterMerchants(RegisterMerchantsEntity model, String openId) throws Exception ;
	public void updateRegisterMerchants(RegisterMerchantsEntity model, String openId) throws Exception;
	
	public String updateRegisterMerchants(RegisterMerchantsEntity model) throws Exception ;
	/**
	 * 查询微信账号下的所有商户
	 * @param openId
	 * @return
	 */
	public List<BsOrganizeEntity> findMerchantsInfoEntityByOpenId(String openId,String status);
	

	/**
	 * 查询微信账号下的所有商户
	 * @param openId
	 * @return
	 */
	public List<RegisterMerchantsEntity> findRegisterMerchantsEntity(String openId,String orgCode,String status);

	/**
	 * 更新用户名
	 * @param username
	 * @param password
	 * @param orgCode
	 * @return 
	 */
	public void updateUserName(String username, String password,
			String orgCode);
	/**
	 * 修改密码
	 * @param username
	 * @param srcPassword	
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public DataCode updatePassword(String username, String srcPassword, String password) throws Exception;
	/**
	 * 校验账号是否存在
	 * @param username
	 * @return
	 */
	public BsMerchantsInfoEntity getMerchantsByUsername(String username);

	/**
	 * 手机登录绑定商户
	 * @param openId	
	 * @param par		
	 * @param par2		
	 */
	public void loginWap(String openId, String username, String password);

	/**
	 * 删除商户
	 * @param id
	 */
	public void delete(String orgCode) ;

	/**
	 * 查询所有父区域
	 * @param openId
	 * @param status
	 * @return
	 */
	public List<BsOrganizeEntity> findParentOrg(String openId,
			String status);
	/**
	 * 查询
	 * @param openId
	 * @param status
	 * @return
	 */
	public BsMerchantsInfoEntity findByOrgCode(String orgCode);
	/**
	 * 描述：根据手机号查询商户信息
	 * Administrator 2016年6月2日 下午7:52:04
	 * @param phone
	 * @return
	 */
	public BsMerchantsInfoEntity queryMerchantInfoByPhone(String phone);
	
	/**
	 * 描述：根据手机号重置密码
	 * Administrator 2016年6月2日 下午9:38:51
	 * @param phone
	 * @param password
	 */
	public void updatePasswordByPhone(String phone,String password);
	/**
	 * 描述：更新绑定手机号
	 * Administrator 2016年6月4日 下午10:17:52
	 * @param phone
	 * @param newMobile
	 * @return
	 */
	public void updateBindMobile(String phone,String newMobile);
	
	public List<BsMerchantsEmployeeEntity> getMerchantsListByEmpId(long empId);
	
	public void saveUidToCache(String uid);
	
	public Object getUidFromCache(String uid);
	
	public long saveEmpidToCache(String uid,  String openId)throws Exception;
	public Object getEmpidFromCache(String uid);
	

	/**
	 * 校验账号唯一
	 */
	public String checkAccount(String account,String merId);
	/**
	 * 校验品牌名称唯一
	 */
	public String checkOrgName(String orgName,String merId);

	/**
	 * 根据orgCode获取商户
	 * @param orgCode
	 * @return
	 */
	public List<RegisterMerchantsEntity> findMerchantsInfoByOrgCode(String orgCode);
}
