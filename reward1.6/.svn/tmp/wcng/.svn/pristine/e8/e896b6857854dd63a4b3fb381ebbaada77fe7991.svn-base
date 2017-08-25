/**
 * 
 */
package com.platform.rp.services.store.external.service;

import java.util.List;
import java.util.Map;

import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.services.store.external.vo.StoreDynamicInfoVo;

/**
 * 
 * @author tangjun
 * 
 * 2016年3月16日
 */
public interface IExBsStoreInfoService {

	/**
	 * 保存店铺信息
	 * @param entity
	 * @throws Exception
	 */
	public void save(BsStoreInfoEntity entity)throws Exception;
	
	/**
	 * 管理员保存店铺
	 * @param entity
	 * @throws Exception
	 */
	public BsStoreInfoEntity saveStore(BsStoreInfoEntity entity)throws Exception;
	
	/**
	 * 修改店铺信息
	 * @param entity
	 * @throws Exception
	 */
	public void update(BsStoreInfoEntity entity)throws Exception;
	
	/**
	 * 获取店铺信息
	 * @param storeId
	 * @throws Exception
	 */
	public BsStoreInfoEntity get(long storeId)throws Exception;
	
	
	/**
	 * 根据openId查询店铺信息
	 * @param openId
	 * @throws Exception
	 */
	public List<BsStoreInfoEntity> getInfoByOpenId(String openId)throws Exception;
	
	/**
	 * 根据openId查询店铺总数
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public int getCountByOpenId(String openId)throws Exception;
	
	/**
	 * 根据openId和店铺编号判断是否绑定
	 * @param openId
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	public int getCountByOpenIdAndStoreId(String openId,long storeId)throws Exception;
	
	/**
	 * 获取门店资金扩展信息
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	public BsStoreInfoEntity getStoreExtReward(long storeId)throws Exception;
	
	/**
	 * 修改分成分配方案表
	 * @param entity
	 * @throws Exception
	 */
	public void updateStoreExtReward(BsStoreInfoEntity entity)throws Exception;
	
	//根据openid获取该店长管理的所有店铺
	public List<BsStoreInfoEntity> getStoreListByEmpId(long empID,String orgCode);

	//根据店铺ID获取该店铺 今日和昨日评价数据
	public StoreDynamicInfoVo getStoreEmpExtEvaluateByStoreId(long storeID);
	
	//根据店铺ID获取该店铺 今日和昨日打赏数据
	public StoreDynamicInfoVo getStoreEmpExtRewardByStoreId(long storeID);
	
	
	/**
	 * 绑定店长
	 * @param entity
	 * @throws Exception
	 */
	public void saveManagerStore(BsStoreInfoEntity entity)throws Exception;

	/**
	 * 绑定为分成人员
	 * @param entity
	 * @throws Exception
	 */
	public void saveDividedStore(BsStoreInfoEntity entity)throws Exception;

	/**
	 * 绑定为商户管理员
	 * @param entity
	 * @throws Exception
	 */
	public void saveMerManage(BsStoreInfoEntity entity)throws Exception;
	/**
	 * 删除店铺
	 * @param storeId
	 */
	public void deleteStore(long storeId,String orgCode);
	

	/**
	 * 更状态
	 * @param status
	 */
	public void updateStatus(int status,String storeIds);
	
	/**
	 * 根据openId获取创建单店数量
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Integer> getCountByOpenIdAndStoreCode(String openId)throws Exception;

}
