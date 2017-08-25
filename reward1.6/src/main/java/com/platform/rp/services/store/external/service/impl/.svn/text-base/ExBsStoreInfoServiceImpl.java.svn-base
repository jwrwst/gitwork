/**
 * 
 */
package com.platform.rp.services.store.external.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.employee.core.dao.BsEmployeeExtEvaluateDAO;
import com.platform.rp.services.employee.core.dao.BsEmployeeExtRewardDAO;
import com.platform.rp.services.employee.core.dao.BsEmployeeInfoDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtEvaluateEntity;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtRewardEntity;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.organize.core.dao.BsMerchantsEmployeeDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsInfoDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsStoreDAO;
import com.platform.rp.services.organize.core.dao.BsPlatformInfoDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.core.dao.entity.BsPlatformInfoEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.qrcodeinfo.core.dao.ItQrcodeInfoDAO;
import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItQrcodeInfoEntity;
import com.platform.rp.services.store.core.dao.BsStoreAuthDetailDAO;
import com.platform.rp.services.store.core.dao.BsStoreEmployeeDAO;
import com.platform.rp.services.store.core.dao.BsStoreEmployeeDividedDAO;
import com.platform.rp.services.store.core.dao.BsStoreExtRewardDAO;
import com.platform.rp.services.store.core.dao.BsStoreExtStatisticalDAO;
import com.platform.rp.services.store.core.dao.BsStoreInfoDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreAuthDetailEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeDividedEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtRewardEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtStatisticalEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.services.store.external.service.IExBsStoreInfoService;
import com.platform.rp.services.store.external.vo.StoreDynamicInfoVo;
import com.platform.rp.services.sys.core.dao.entity.SysAreaInfoEntity;
import com.platform.rp.services.sys.core.dao.entity.SysCodeInfoEntity;
import com.platform.rp.services.sys.external.service.ISysAreaInfoService;
import com.platform.rp.services.sys.external.service.ISysCodeService;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;
import com.platform.rp.services.wechat.external.vo.LoginVo;
import com.platform.rp.util.Constant;
import com.platform.rp.util.DateUtil;
import com.platform.rp.util.Properties;
import com.platform.rp.util.StringUtils;
import com.platform.rp.util.TinyUrlGenerater;
import com.platform.rp.util.exception.RepeatBindException;


@Service
public class ExBsStoreInfoServiceImpl implements IExBsStoreInfoService {

	@Autowired
	Properties properties;
	
	@Autowired
	BsStoreInfoDAO<BsStoreInfoEntity> bsStoreInfoDAO;
	
	@Autowired
	BsEmployeeInfoDAO<BsEmployeeInfoEntity> bsEmployeeInfoDAO;
	
	@Autowired
	BsStoreEmployeeDAO<BsStoreEmployeeEntity> bsStoreEmployeeDAO;
	
	@Autowired
	BsStoreEmployeeDividedDAO<BsStoreEmployeeDividedEntity> bsStoreEmployeeDividedDAO;
	/**
	 * 商户用户
	 */
	@Autowired
	BsMerchantsEmployeeDAO<BsMerchantsEmployeeEntity> bsMerchantsEmployeeDAO;
	
	@Autowired
	BsMerchantsInfoDAO<BsMerchantsInfoEntity> bsMerchantsInfoDAO;
	
	@Autowired
	BsStoreExtRewardDAO<BsStoreExtRewardEntity> bsStoreExtRewardDAO;
	
	@Autowired
	BsStoreExtStatisticalDAO<BsStoreExtStatisticalEntity> bsStoreExtStatisticalDAO;
	
	@Autowired
	BsStoreAuthDetailDAO<BsStoreAuthDetailEntity> bsStoreAuthDetailDAO;
	
	@Autowired
	BsEmployeeExtEvaluateDAO<BsEmployeeExtEvaluateEntity> empExtEvaluateDAO;
	
	@Autowired
	BsEmployeeExtRewardDAO<BsEmployeeExtRewardEntity> empExtRewardDAO;
	
	@Autowired
	BsPlatformInfoDAO<BsPlatformInfoEntity> bsPlatformInfoDAO;
	
	@Autowired
	ItQrcodeInfoDAO<ItQrcodeInfoEntity> itQrcodeInfoDAO;
	
	@Autowired
	private IExBsMerchantsInfoService merchantsInfoService;
	
	@Autowired
	BsMerchantsStoreDAO<BsMerchantsStoreEntity> bsMerchantsStoreDAO;
	
	@Autowired
	ISysCodeService sysCodeService;
	
	@Autowired
	ISysAreaInfoService areaSerivce;
	
	
	static Map<String,String> openIdMap=new HashMap<String,String>();
	static Lock lock=new ReentrantLock();
	
	/**
	 * 创建单店店铺
	 * 保存员工信息
	 */
	public void save(BsStoreInfoEntity entity)throws Exception{
		try {			
			try{
				lock.lock();
				if(openIdMap.containsKey(entity.getCreate_openId())){
					return;
				}
				openIdMap.put(entity.getCreate_openId(), "1");
				//判断创建的单店铺是否超过系统设置上限
				Map<String,Integer> mapCount = this.getCountByOpenIdAndStoreCode(entity.getCreate_openId());
				if(mapCount.get("count") >= mapCount.get("createNum")){
					throw new Exception("创建店铺超过系统设置的上限");
				}
			}finally{
				lock.unlock();
			}
			
			Date dt=new Date();
			entity.setCreateTime(dt);
			//设置店铺默认信息
			if(StringUtils.isEmpty(entity.getOrgCode())){
				entity.setOrgCode("000");
			}
			BsPlatformInfoEntity platformEntity=null;
			BsMerchantsInfoEntity merchantsEntity=bsMerchantsInfoDAO.getInfoByOrgCode(entity.getOrgCode());	
			if(null!=merchantsEntity){
				entity.setIsUpdate(merchantsEntity.getIsUpdateMoney());
				entity.setRewardMoney(merchantsEntity.getRewardMoney());
				entity.setWish(merchantsEntity.getWish());
				entity.setStoreCode(merchantsEntity.getSchema());
			}else{
				platformEntity = bsPlatformInfoDAO.getInfo("1");
				entity.setIsUpdate(platformEntity.getIsUpdateMoney());
				entity.setRewardMoney(platformEntity.getRewardMoney());
				entity.setStoreCode(entity.getOrgCode());
			}
			
			//entity.setQrcode(DwzClient.getInstance().generate(properties.shortUrl,properties.host+properties.wxLogin+"?data="+entity.getStoreCode()));
			entity.setQrcode(TinyUrlGenerater.generatorByUUID());
			//保存店铺
			bsStoreInfoDAO.save(entity);
			//保存员工信息
			BsEmployeeInfoEntity empEntity=bsEmployeeInfoDAO.getInfoByOpenId(entity.getCreate_openId());
			if(null==empEntity){
				empEntity= new BsEmployeeInfoEntity();
				empEntity.setName(entity.getCreateUser());
				empEntity.setWxAccount(entity.getCreate_openId());
				empEntity.setNickname(entity.getNickname());
				empEntity.setMobile(entity.getTelphone());
				empEntity.setHomeAddress(entity.getCountry()+"|"+entity.getProvince()+"|"+entity.getCity());
				empEntity.setGender(entity.getSex());
				empEntity.setCreatedtime(dt);
				empEntity.setStoreId(entity.getStoreId());
				empEntity.setHeadPic(entity.getHeadimgurl());
				empEntity.setLevel(Constant.EMPLEVEL1);
				bsEmployeeInfoDAO.save(empEntity);
			}else{
				//更新员工信息表
				if(Constant.EMPLEVEL2 == empEntity.getLevel()){
					empEntity.setLevel(Constant.EMPLEVEL3);
				}
				empEntity.setMobile(entity.getTelphone());
				empEntity.setName(entity.getCreateUser());
				bsEmployeeInfoDAO.update(empEntity);
			}
			//保存店铺与员工关系表信息
			BsStoreEmployeeEntity storeEmpEntity=new BsStoreEmployeeEntity();
			storeEmpEntity.setEmpId(empEntity.getEmpId());
			storeEmpEntity.setStoreId(entity.getStoreId());
			storeEmpEntity.setStatus(Constant.ABLE);
			storeEmpEntity.setUpdateTime(dt);
			bsStoreEmployeeDAO.save(storeEmpEntity);
			//保存店铺统计信息
			BsStoreExtStatisticalEntity extStatisEntity=new BsStoreExtStatisticalEntity();
			extStatisEntity.setStoreId(entity.getStoreId());
			extStatisEntity.setShopassistantCount(0);
			extStatisEntity.setShopkeeperCount(1);
			extStatisEntity.setUpdateTime(dt);
			bsStoreExtStatisticalDAO.save(extStatisEntity);
			//保存店铺扩展信息
			if(merchantsEntity == null){
				/**
				BsStoreExtRewardEntity extRewardEntity=new BsStoreExtRewardEntity();
				extRewardEntity.setStoreId(entity.getStoreId());			
				extRewardEntity.setAllotPlan(platformEntity.getAllotPlan());				
				extRewardEntity.setUpdateTime(dt);
				extRewardEntity.setUpdateUser(empEntity.getEmpId()+"");
				bsStoreExtRewardDAO.save(extRewardEntity);
				**/
			}else{
				
			}
			//保存默认权限
			BsStoreAuthDetailEntity storeAuthEntity=new BsStoreAuthDetailEntity();
			storeAuthEntity.setAuthCode(Constant.DEFAUTHCODE);
			storeAuthEntity.setStoreId(entity.getStoreId());
			storeAuthEntity.setUpdateTime(dt);
			bsStoreAuthDetailDAO.save(storeAuthEntity);
			
			//创建关联
			if(StringUtils.isNotEmpty(entity.getOrgCode())&&merchantsEntity!=null){
				BsMerchantsStoreEntity mechantsStore= new BsMerchantsStoreEntity();
				mechantsStore.setOrgCode(entity.getOrgCode());
				mechantsStore.setStoreId(entity.getStoreId());
				mechantsStore.setSchema(merchantsEntity.getSchema());
				mechantsStore.setStatus(Constant.ABLE);
				mechantsStore.setUpdateTime(dt);
				bsMerchantsStoreDAO.save(mechantsStore);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			openIdMap.remove(entity.getCreate_openId());
		}
	}
	
	/**
	 * 只保存店铺信息(商户店铺)
	 */
	public BsStoreInfoEntity saveStore(BsStoreInfoEntity entity)throws Exception{
		try {
			Date dt=new Date();
			entity.setCreateTime(dt);
			//设置店铺默认信息
			BsPlatformInfoEntity platformEntity=null;
			BsMerchantsInfoEntity merchantsEntity=bsMerchantsInfoDAO.getInfoByOrgCode(entity.getOrgCode());	
			if(null!=merchantsEntity){
				entity.setIsUpdate(merchantsEntity.getIsUpdateMoney());
				entity.setRewardMoney(merchantsEntity.getRewardMoney());
				entity.setStoreCode(merchantsEntity.getSchema());
				entity.setWish(merchantsEntity.getWish());
			}else{
				platformEntity = bsPlatformInfoDAO.getInfo("1");
				entity.setIsUpdate(platformEntity.getIsUpdateMoney());
				entity.setRewardMoney(platformEntity.getRewardMoney());
				entity.setStoreCode("0");
			}
			//entity.setStoreCode(UUID.randomUUID().toString().replace("-", ""));
			entity.setQrcode(TinyUrlGenerater.generatorByUUID());
			//保存店铺
			bsStoreInfoDAO.save(entity);
			
			//保存默认权限
			BsStoreAuthDetailEntity storeAuthEntity=new BsStoreAuthDetailEntity();
			storeAuthEntity.setAuthCode(Constant.DEFAUTHCODE);
			storeAuthEntity.setStoreId(entity.getStoreId());
			storeAuthEntity.setUpdateTime(dt);
			bsStoreAuthDetailDAO.save(storeAuthEntity);
			
			//创建关联
			if(StringUtils.isNotEmpty(entity.getOrgCode())&&merchantsEntity!=null){
				BsMerchantsStoreEntity mechantsStore= new BsMerchantsStoreEntity();
				mechantsStore.setOrgCode(entity.getOrgCode());
				mechantsStore.setStoreId(entity.getStoreId());
				mechantsStore.setSchema(merchantsEntity.getSchema());
				mechantsStore.setStatus(Constant.ABLE);
				mechantsStore.setUpdateTime(dt);
				bsMerchantsStoreDAO.save(mechantsStore);
			}			
		
			//保存店铺与员工关系表信息
			if(entity.getEmpId()>0){
				//保存店铺统计信息
				BsStoreExtStatisticalEntity extStatisEntity=new BsStoreExtStatisticalEntity();
				extStatisEntity.setStoreId(entity.getStoreId());
				extStatisEntity.setShopassistantCount(0);
				extStatisEntity.setShopkeeperCount(1);
				extStatisEntity.setUpdateTime(dt);
				bsStoreExtStatisticalDAO.save(extStatisEntity);
				//保存店铺与员工关联关系
				BsStoreEmployeeEntity storeEmpEntity=new BsStoreEmployeeEntity();
				storeEmpEntity.setEmpId(entity.getEmpId());
				storeEmpEntity.setStoreId(entity.getStoreId());
				storeEmpEntity.setStatus(Constant.UNABLE);
				storeEmpEntity.setUpdateTime(dt);
				bsStoreEmployeeDAO.save(storeEmpEntity);
				//保存员工信息
				BsEmployeeInfoEntity empInfoEntity = bsEmployeeInfoDAO.getEmployeeInfoByEmpId(entity.getEmpId());
				if(empInfoEntity.getLevel()!=Constant.EMPLEVEL1&&empInfoEntity.getLevel()!=Constant.EMPLEVEL3){
					if(empInfoEntity.getLevel()==Constant.EMPLEVEL2){
						empInfoEntity.setLevel(Constant.EMPLEVEL3);
					}else{
						empInfoEntity.setLevel(Constant.EMPLEVEL1);
					}					
					bsEmployeeInfoDAO.update(empInfoEntity);
				}
			}else{
				//保存店铺统计信息
				BsStoreExtStatisticalEntity extStatisEntity=new BsStoreExtStatisticalEntity();
				extStatisEntity.setStoreId(entity.getStoreId());
				extStatisEntity.setShopassistantCount(0);
				extStatisEntity.setShopkeeperCount(0);
				extStatisEntity.setUpdateTime(dt);
				bsStoreExtStatisticalDAO.save(extStatisEntity);
			}
			
			return entity;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 根据openId获取创建单店数量
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Integer> getCountByOpenIdAndStoreCode(String openId)throws Exception{
		try {
			int count=bsStoreInfoDAO.getCountByOpenIdAndStoreCode(openId);
			SysCodeInfoVo vo = sysCodeService.getSysCodeInfo("System.number", "create.store.num");
			int createNum = 2;
			if(null!=vo && StringUtils.isEmpty(vo.getName()) ){
				createNum = Integer.parseInt(vo.getName());
			}
			
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("count", count);
			map.put("createNum", createNum);
			
			return map;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 通过openId获取店铺列表
	 */
	public List<BsStoreInfoEntity> getInfoByOpenId(String openId)throws Exception{
		try {
			return bsStoreInfoDAO.getInfoByOpenId(openId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 通过openId获取店铺列表总数
	 */
	public int getCountByOpenId(String openId)throws Exception{
		try {
			return bsStoreInfoDAO.getCountByOpenId(openId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 添加店长
	 * @param loginVo
	 * @throws Exception
	 */
	public void addStoreManager(LoginVo loginVo)throws Exception{
		try {
			bsStoreInfoDAO.get(Integer.valueOf(loginVo.getData()));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 通过openId和店铺编号获取是否存在店铺
	 */
	public int getCountByOpenIdAndStoreId(String openId,long storeId)throws Exception{
		try {
			return bsStoreInfoDAO.getCountByOpenIdAndStoreId(openId, storeId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 根据员工编号获取所在店铺
	 */
	@Override
	public List<BsStoreInfoEntity> getStoreListByEmpId(long empID,String orgCode) {
		//如果是店铺管理，不查询区域店铺
		if(StringUtils.isNotEmpty(orgCode)&&!"null".equals(orgCode)){			
			//查询区域下门店
			/**
			List<BsMerchantsEmployeeEntity> merEmpList = bsMerchantsEmployeeDAO.getListByEmpId(empID);		
			List<BsStoreInfoEntity> merStorelist =null;
			if(null!=merEmpList&&merEmpList.size()>0){
				for (BsMerchantsEmployeeEntity merEmp : merEmpList) {
					merStorelist = bsMerchantsStoreDAO.getListByOrgCode(merEmp.getOrgCode());
					list.addAll(merStorelist);
				}
			}
			**/
			List<BsStoreInfoEntity> merStorelist =bsMerchantsStoreDAO.getListByOrgCode(orgCode);
			
			//去重复
			List<BsStoreInfoEntity> listTemp= new ArrayList<BsStoreInfoEntity>();  
			Iterator<BsStoreInfoEntity> it=merStorelist.iterator(); 
			Map<Long, Integer> map =new HashMap<Long, Integer>();
			while(it.hasNext()){  
				  BsStoreInfoEntity a=it.next();  
				  if(map.containsKey(a.getStoreId())){  
					  it.remove();  
				  }  
				  else{  
					  map.put(a.getStoreId(), 0);
					  listTemp.add(a);  
				  }  
			 }  		
			
			return listTemp;
		}else{
			List<BsStoreInfoEntity> list = bsStoreInfoDAO.getStoreListByEmpId(empID);
			return list;
		}
	}

	/**
	 * 
	 */
	@Override
	public StoreDynamicInfoVo getStoreEmpExtEvaluateByStoreId(long storeID) {
		Map<String, Object> retMap = getDayAndYesterday(storeID);
		
		StoreDynamicInfoVo dayInfoVo = empExtEvaluateDAO.getStoreDayDynamicByStoreId(retMap);
		StoreDynamicInfoVo yesterdayInfoVo = empExtEvaluateDAO.getStoreYesterdayDynamicByStoreId(retMap);
		
		StoreDynamicInfoVo storeDynInfoVo = getStoreDynamicData(dayInfoVo, yesterdayInfoVo, storeID);
		
		return storeDynInfoVo;
	}
	
	@Override
	public StoreDynamicInfoVo getStoreEmpExtRewardByStoreId(long storeID) {
		
		Map<String, Object> retMap = getDayAndYesterday(storeID);
		
		StoreDynamicInfoVo dayInfoVo = empExtRewardDAO.getStoreDayDynamicByStoreId(retMap);
		StoreDynamicInfoVo yesterdayInfoVo = empExtRewardDAO.getStoreYesterdayDynamicByStoreId(retMap);
		
		StoreDynamicInfoVo storeDynInfoVo = getStoreDynamicData(dayInfoVo, yesterdayInfoVo, storeID);
		
		return storeDynInfoVo;
	}

	/**
	 * 修改店铺
	 */
	public void update(BsStoreInfoEntity entity)throws Exception{
		try {
			if(entity.getStoreId()==0)return;
			
			Date dt=new Date();
			entity.setUpdateTime(dt);
			bsStoreInfoDAO.update(entity);
			//目前只有一种功能权限，以修改的方式存储，
			BsStoreAuthDetailEntity authEntity=new BsStoreAuthDetailEntity();
			authEntity.setStoreId(entity.getStoreId());
			authEntity.setAuthCode(entity.getAuthType());
			authEntity.setUpdateTime(dt);
			bsStoreAuthDetailDAO.update(authEntity);
			
			//创建商户关联
			BsMerchantsInfoEntity merchantsEntity=bsMerchantsInfoDAO.getInfoByOrgCode(entity.getOrgCode());	
			if(StringUtils.isNotEmpty(entity.getOrgCode())&&merchantsEntity!=null){
				//先删除店铺原有绑定商户
				bsMerchantsStoreDAO.deleteByOrgCodeAndStoreId(null, entity.getStoreId());
				//重新添加绑定
				BsMerchantsStoreEntity mechantsStore= new BsMerchantsStoreEntity();
				mechantsStore.setOrgCode(entity.getOrgCode());
				mechantsStore.setStoreId(entity.getStoreId());
				mechantsStore.setSchema(merchantsEntity.getSchema());
				mechantsStore.setStatus(Constant.ABLE);
				mechantsStore.setUpdateTime(dt);
				bsMerchantsStoreDAO.save(mechantsStore);
			}
		} catch (Exception e) {
			throw e;
		}		
	}
	
	
	/**
	 * 获取店铺
	 */
	public BsStoreInfoEntity get(long storeId)throws Exception{
		try {
			List<BsStoreAuthDetailEntity> list= bsStoreAuthDetailDAO.getStoreAuthDetailByStoreId(storeId);
			BsStoreInfoEntity entity=bsStoreInfoDAO.get(storeId);
			if(entity!=null){
				entity.setAuthlist(list);
			}
			if(null != entity.getStoreType()){
				SysCodeInfoVo codeInfo = sysCodeService.getSysCodeInfo("System.store.type", entity.getStoreType());
				if(codeInfo!=null){
					entity.setTypeName(codeInfo.getName());
				}
				//二级类型
				SysCodeInfoEntity pcode = sysCodeService.getByParentCode(entity.getStoreType(), 
						entity.getCodeType());
				if(pcode!=null){
					entity.setCodeTypeName(pcode.getName());
				}
			}
			String proCode = entity.getProvinceId();
			if(proCode!= null){
				SysAreaInfoEntity pro = areaSerivce.getByParentCode(SysAreaInfoEntity.DEF_CODE, proCode);
				if(pro!=null){
					entity.setProvinceName(pro.getName());
				}
				String cityCode = entity.getCityId();
				if(cityCode != null){
					SysAreaInfoEntity city = areaSerivce.getByParentCode(proCode, cityCode);
					if(city!=null){
						entity.setCityName(city.getName());
					}
					String areaCode = entity.getAreaId();
					if(areaCode != null){
						SysAreaInfoEntity area = areaSerivce.getByParentCode(cityCode, areaCode);
						if(area!=null){
							entity.setAreaName(area.getName());
						}
						
					}
				}
			}
		
			return entity;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 获取赏金分配
	 */
	public BsStoreInfoEntity getStoreExtReward(long storeId)throws Exception{
		try {
			List<BsStoreExtRewardEntity> list= bsStoreExtRewardDAO.getStoreExtRewardByStoreId(storeId);
			BsStoreInfoEntity entity=bsStoreInfoDAO.get(storeId);
			entity.setExtRewardlist(list);
			return entity;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 修改赏金分配 
	 */
	public void updateStoreExtReward(BsStoreInfoEntity entity)throws Exception{
		try {
			//修改店铺表
			Date dt = new Date();
			entity.setUpdateTime(dt);
			bsStoreInfoDAO.update(entity);
			//修改分配方案表  先删除,后新增
			bsStoreExtRewardDAO.deleteByStoreId(entity.getStoreId());
			List<BsStoreExtRewardEntity> list = entity.getExtRewardlist();
			BsStoreExtRewardEntity reward;
			if(null==list)return;
			for (int i = 0; i < list.size(); i++) {
				reward = new BsStoreExtRewardEntity();
				reward.setAllotPlan(entity.getAllotPlan());
				reward.setStoreId(entity.getStoreId());
				reward.setPercent(list.get(i).getPercent().divide(new BigDecimal(100)));
				reward.setMoney(list.get(i).getMoney());
				reward.setEmpId(list.get(i).getEmpId());
				reward.setParentId(list.get(i).getParentId());
				reward.setRemark(list.get(i).getRemark());
				reward.setUpdateUser(entity.getUpdateUser());
				reward.setUpdateTime(entity.getUpdateTime());
				bsStoreExtRewardDAO.save(reward);
			}	
						
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 保存店长信息
	 */
	public void saveManagerStore(BsStoreInfoEntity entity)throws Exception{
		saveManagerStore(entity, 0);
	}


	/**
	 * 保存分成人员信息信息
	 */
	public void saveDividedStore(BsStoreInfoEntity entity)throws Exception{
		saveManagerStore(entity, 1);
	}
	
	/**
	 * 判断管理员
	 */
	public void saveMerManage(BsStoreInfoEntity entity)throws Exception{
		int count = bsMerchantsEmployeeDAO.getCount(entity.getOrgCode(),entity.getEmpId());
		if(count==0){
			saveManagerStore(entity, 2);	
		}else{
			throw new RepeatBindException("您已经绑定过该商户!");
		}
	}
	/**
	 * 保存店长信息
	 */
	public void saveManagerStore(BsStoreInfoEntity entity,int type)throws Exception{
		try {
			Date dt =new Date();
			//保存员工信息
			long empId=entity.getEmpId();
			if(empId<1){
				BsEmployeeInfoEntity empEntity=bsEmployeeInfoDAO.getInfoByOpenId(entity.getCreate_openId());
				if(null==empEntity){
					empEntity= new BsEmployeeInfoEntity();
					empEntity.setName(entity.getCreateUser());
					empEntity.setWxAccount(entity.getCreate_openId());
					empEntity.setNickname(entity.getNickname());
					empEntity.setMobile(entity.getCreateMobile());
					empEntity.setHomeAddress(entity.getCountry()+"|"+entity.getProvince()+"|"+entity.getCity());
					empEntity.setGender(entity.getSex());
					empEntity.setCreatedtime(dt);
					//empEntity.setStoreId(entity.getStoreId());
					empEntity.setHeadPic(entity.getHeadimgurl());
					if(type==0){
						empEntity.setLevel(Constant.EMPLEVEL1);	
					}
					bsEmployeeInfoDAO.save(empEntity);
				}
				empId=empEntity.getEmpId();
			}else{
				BsEmployeeInfoEntity empEntity=bsEmployeeInfoDAO.getEmployeeInfoByEmpId(empId);
				empEntity.setName(entity.getCreateUser());	
				empEntity.setNickname(entity.getNickname());
				if(type==0){
					if(Constant.EMPLEVEL2 == empEntity.getLevel()){
						empEntity.setLevel(Constant.EMPLEVEL3);
					}else{
						empEntity.setLevel(Constant.EMPLEVEL1);
					}
				}
				bsEmployeeInfoDAO.update(empEntity);
			}
			//绑定为店长
			if(type==0){
				//保存店铺与员工关系表信息
				BsStoreEmployeeEntity storeEmpEntity=new BsStoreEmployeeEntity();
				storeEmpEntity.setEmpId(empId);
				storeEmpEntity.setStoreId(entity.getStoreId());
				storeEmpEntity.setStatus(Constant.ABLE);
				storeEmpEntity.setUpdateTime(dt);
				bsStoreEmployeeDAO.save(storeEmpEntity);
			//绑定为分成人员
			}else if(type==1){
				//保存店铺与员工关系表信息
				BsStoreEmployeeDividedEntity storeEmpEntity=new BsStoreEmployeeDividedEntity();
				storeEmpEntity.setEmpId(empId);
				storeEmpEntity.setStoreId(entity.getStoreId());
				storeEmpEntity.setStatus(Constant.ABLE);
				storeEmpEntity.setUpdateTime(dt);
				bsStoreEmployeeDividedDAO.save(storeEmpEntity);
				
			}else if(type==2){
				//保存商户与员工关系表信息
				BsMerchantsEmployeeEntity merEmp = new BsMerchantsEmployeeEntity();
				merEmp.setEmpId(empId);
				merEmp.setOrgCode(entity.getOrgCode());
				merEmp.setStatus(Constant.ABLE);
				merEmp.setUpdateTime(dt);
				bsMerchantsEmployeeDAO.save(merEmp);
				
			}
			if(type==1 || type ==0){
				//保存店铺统计信息
				BsStoreExtStatisticalEntity extStatisEntity=new BsStoreExtStatisticalEntity();
				extStatisEntity.setStoreId(entity.getStoreId());
				extStatisEntity.setShopassistantCount(0);
	
				//绑定为店长
				if(type==0){
					extStatisEntity.setShopkeeperCount(1);
				}else if(type==1){
					extStatisEntity.setDividedCount(1);			
				}
				extStatisEntity.setUpdateTime(dt);
				bsStoreExtStatisticalDAO.update(extStatisEntity);
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	private StoreDynamicInfoVo getStoreDynamicData(StoreDynamicInfoVo dayInfoVo, StoreDynamicInfoVo yesterdayInfoVo, long storeID)
	{
		int dayCount = 0;
		int yesterdayCount = 0;
		BigDecimal dayData = new BigDecimal(0);
		BigDecimal yesterdayData = new BigDecimal(0);
		
		if(dayInfoVo != null)
		{
			dayCount += dayInfoVo.getDayCount();
			yesterdayCount += dayInfoVo.getYesterdayCount();
			dayData = dayInfoVo.getDayData();
			yesterdayData = dayInfoVo.getYesterdayData();
		}
		
		if(yesterdayInfoVo != null)
		{
			yesterdayCount += yesterdayInfoVo.getDayCount();
			yesterdayData = yesterdayData.add(yesterdayInfoVo.getDayData());
		}
		
		StoreDynamicInfoVo storeDynInfoVo = new StoreDynamicInfoVo();
		storeDynInfoVo.setDayCount(dayCount);
		storeDynInfoVo.setDayData(dayData);
		storeDynInfoVo.setYesterdayCount(yesterdayCount);
		storeDynInfoVo.setYesterdayData(yesterdayData);
		
		return storeDynInfoVo;
	}
	
	//根据修正时间获取店铺统计的今日和昨日时间周期
	private Map<String, Object> getDayAndYesterday(long storeID)
	{
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("storeId", storeID);
		try {
			String[] startAndEnd = new String[2];
			merchantsInfoService.getStartAndEndTime(storeID, startAndEnd);
			
			Map<String, Object> dateMap = DateUtil.getDayAndYesterday(true, startAndEnd[0], startAndEnd[1]);
			
			retMap.put("startDay", dateMap.get("startDay"));
			retMap.put("endDay", dateMap.get("endDay"));
			retMap.put("startYesterday", dateMap.get("startYesterday"));
			retMap.put("endYesterday", dateMap.get("endYesterday"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return retMap;
	}
	
	/**
	 * 删除店铺
	 */
	public void deleteStore(long storeId,String orgCode){
		//删除二维码表
		ItQrcodeInfoEntity entity=new ItQrcodeInfoEntity();
		entity.setStoreId(storeId);
		entity.setEmpId(-1);
		entity.setState(Constant.NOTUSER);
		entity.setUpdateTime(new Date());
		itQrcodeInfoDAO.update(entity);
		
		//删除店长与店铺关系表
		bsStoreEmployeeDAO.delete(storeId);
		
		//删除店铺信息表
		bsStoreInfoDAO.updateStatus(3,storeId);	
		
		//删除商户关联	
		if(storeId!=0&&StringUtils.isNotEmpty(orgCode)){
			bsMerchantsStoreDAO.deleteByOrgCodeAndStoreId(orgCode, storeId);
		}
		
		//保存店铺统计扩展表
		BsStoreExtStatisticalEntity statistiEntity = new BsStoreExtStatisticalEntity();
		statistiEntity.setShopkeeperCount(-1);
		statistiEntity.setUpdateTime(new Date());
		statistiEntity.setStoreId(storeId);
		bsStoreExtStatisticalDAO.update(statistiEntity);
		
	}

	/**
	 * 修改店铺状态，如删除、停用
	 */
	@Override
	public void updateStatus(int status,String storeIds){
		bsStoreInfoDAO.updateStatuses(status, storeIds.split(","));
	}
}
