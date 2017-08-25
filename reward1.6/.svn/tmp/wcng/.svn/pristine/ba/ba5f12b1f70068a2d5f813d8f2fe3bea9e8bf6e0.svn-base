package com.platform.rp.services.organize.external.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.employee.core.dao.BsEmployeeInfoDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.organize.core.dao.BsMerchantsEmployeeDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsInfoDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsStoreDAO;
import com.platform.rp.services.organize.core.dao.BsOrganizeDAO;
import com.platform.rp.services.organize.core.dao.BsPlatformInfoDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.core.dao.entity.BsPlatformInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.RegisterMerchantsEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsEmployeeService;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.organize.external.service.IExBsOrganizeService;
import com.platform.rp.services.store.core.dao.BsStoreAuthDetailDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreAuthDetailEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.services.store.external.service.IExBsStoreInfoService;
import com.platform.rp.services.sys.core.dao.entity.SysAreaInfoEntity;
import com.platform.rp.services.sys.core.dao.entity.SysCodeInfoEntity;
import com.platform.rp.services.sys.external.service.ISysAreaInfoService;
import com.platform.rp.services.sys.external.service.ISysCodeService;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;
import com.platform.rp.services.wechat.common.WxMsgTemplate;
import com.platform.rp.services.wechat.external.common.alipay.sign.MD5;
import com.platform.rp.services.wechat.service.IWxSendMessageService;
import com.platform.rp.util.Constant;
import com.platform.rp.util.KeyConstant;
import com.platform.rp.util.TinyUrlGenerater;
import com.platform.rp.util.cache.CacheConstant;
import com.platform.rp.util.cache.ICache;
import com.platform.rp.util.info.codes.DataCode;

/**
 * 商户
 * @author zhangsheng
 *
 */
@Service
public class ExBsMerchantsInfoServiceImpl implements IExBsMerchantsInfoService {
	

	@Autowired
	BsMerchantsInfoDAO<BsMerchantsInfoEntity> bsMerchantsInfoDAO;
	
	@Autowired
	BsOrganizeDAO<BsOrganizeEntity> bsOrganizeDAO;
	
	@Autowired
	BsEmployeeInfoDAO<BsEmployeeInfoEntity> employeeInfoDAO;		//员工信息

	@Autowired	
	IExBsStoreInfoService storeInfoService;

	@Autowired
	ISysCodeService sysCodeService;

	@Autowired
	BsPlatformInfoDAO<BsPlatformInfoEntity> bsPlatformInfoDAO;		//系统参数
	
	@Autowired
	ISysAreaInfoService areaSerivce;

	@Autowired
	IExBsMerchantsEmployeeService bsMerchantsEmployeeService;
	
	@Autowired
	private IWxSendMessageService sendMessageService;
	
	@Autowired
	BsMerchantsEmployeeDAO<BsMerchantsEmployeeEntity> bsMerchantsEmployeeDAO;
	
	@Autowired
	ICache cache;
	
	@Override
	public BsMerchantsInfoEntity getInfo(
			BsMerchantsInfoEntity bsMerchantsInfoEntity) throws Exception {
		if(!StringUtils.isEmpty(bsMerchantsInfoEntity.getPassword())){
			bsMerchantsInfoEntity.setPassword(MD5.sign(bsMerchantsInfoEntity.getPassword(), KeyConstant.USERKEY, "utf-8"));
		}
		return bsMerchantsInfoDAO.getInfo(bsMerchantsInfoEntity);
	}
	public DataCode checkLoing(String username,String password){
		BsMerchantsInfoEntity bsMerchantsInfoEntity = new BsMerchantsInfoEntity();
		bsMerchantsInfoEntity.setAccount(username);
		BsMerchantsInfoEntity merInfo = bsMerchantsInfoDAO.getInfo(bsMerchantsInfoEntity);
		if(merInfo == null){
			return DataCode.NOEXSIST_USER;
		}else if(MD5.sign(password, KeyConstant.USERKEY, "utf-8").equals(merInfo.getPassword())){
			return DataCode.SYS_SUCCESS;
		}
		return DataCode.NOEXSIST_PASSWORD;
	}
	@Override
	public void update(BsMerchantsInfoEntity bsMerchantsInfoEntity)
			throws Exception {
		update(bsMerchantsInfoEntity,null);	
	}

	public void update(BsMerchantsInfoEntity bsMerchantsInfoEntity,Integer status)
			throws Exception {
		bsMerchantsInfoEntity.setUpdateTime(new Date());
		BsOrganizeEntity org = buildBsOrganizeEntity(bsMerchantsInfoEntity, status);
	
		bsOrganizeDAO.update(org);
		bsMerchantsInfoDAO.update(bsMerchantsInfoEntity);	
		
		///修改下级信息
		try{
			updateLevelInfo(bsMerchantsInfoEntity);
		}catch(Exception e){
			throw e;
		}
	}
	
	@Autowired
	IExBsOrganizeService bsOrganizeService;
	
	@Autowired
	BsMerchantsStoreDAO<BsMerchantsStoreEntity> bsMerchantsStoreDAO; 
	
	@Autowired
	BsStoreAuthDetailDAO<BsStoreAuthDetailEntity> bsStoreAuthDetailDAO;
	
	private void  updateLevelInfo(BsMerchantsInfoEntity entity)throws Exception{
		Date dt =new Date();
		//获取下级节点
		List<String> orgList=bsOrganizeService.findLevel(entity.getOrgCode());
		//获取当前节点下所有关联门店
		List<BsMerchantsStoreEntity> merStoreList=bsMerchantsStoreDAO.getMerchantStoreList(orgList);
		//if(entity.getIseditVal1() == 2){

			//保存下级节点信息
			BsMerchantsInfoEntity tempEntity;
			for (String str : orgList) {
				if(str.equals(entity.getOrgCode()))continue;
				tempEntity = new BsMerchantsInfoEntity();
				tempEntity.setOrgCode(str);
				tempEntity.setAuthType(entity.getAuthType());
				tempEntity.setIsedit(entity.getIsedit());
				tempEntity.setIseditVal1(entity.getIseditVal1());
				tempEntity.setIseditVal2(entity.getIseditVal2());
				tempEntity.setIseditVal3(entity.getIseditVal3());
				tempEntity.setUpdateTime(dt);
				bsMerchantsInfoDAO.update(tempEntity);
			}
			
			//保存店铺信息
			BsStoreAuthDetailEntity authEntity;
			for (BsMerchantsStoreEntity bsMerchantsStoreEntity : merStoreList) {				
				authEntity=new BsStoreAuthDetailEntity();
				authEntity.setStoreId(bsMerchantsStoreEntity.getStoreId());
				authEntity.setAuthCode(entity.getAuthType());
				authEntity.setUpdateTime(dt);
				bsStoreAuthDetailDAO.update(authEntity);				
			}
		//}
	}
	
	private BsOrganizeEntity buildBsOrganizeEntity(BsMerchantsInfoEntity bsMerchantsInfoEntity,Integer status){
		BsOrganizeEntity org=new BsOrganizeEntity();
		org.setOrgCode(bsMerchantsInfoEntity.getOrgCode());
		org.setOrgName(bsMerchantsInfoEntity.getOrgName());
		org.setUpdateTime(bsMerchantsInfoEntity.getUpdateTime());
		org.setLeap(bsMerchantsInfoEntity.getLeap());
		if(StringUtils.isNotEmpty(bsMerchantsInfoEntity.getParentCode() )){
			org.setParentCode(bsMerchantsInfoEntity.getParentCode());
		}else{
			org.setParentCode("0");
			org.setSchema(bsMerchantsInfoEntity.getOrgCode());
		}
		if(status!=null){
			org.setStatus(status);
		}
		return org;
	}
	
	/**
	 * 根据店铺编号获取上级权限
	 */
	@Override
	public BsMerchantsInfoEntity getInfoByStoreID(long storeId)
			throws Exception {
		
		return bsMerchantsInfoDAO.getPreMerchantsInfoByStoreID(storeId);
	}

	@Override
	public void save(BsMerchantsInfoEntity bsMerchantsInfoEntity)
			throws Exception {	
		bsMerchantsInfoDAO.save(bsMerchantsInfoEntity);		
		//组织
		BsOrganizeEntity org = buildBsOrganizeEntity(bsMerchantsInfoEntity, 1);
		org.setCreateTime(new Date());
		bsOrganizeDAO.save(org);
	}
	
	

	//根据店铺ID获取商户统计起始和结束时间
	public void getStartAndEndTime(long storeID, String[] startAndEnd)
	{
		BsMerchantsInfoEntity mechantsInfo = bsMerchantsInfoDAO.getInfoByStoreID(storeID);
		if(mechantsInfo != null)
		{
			startAndEnd[0] = mechantsInfo.getBusStartTime();
			startAndEnd[1] = mechantsInfo.getBusEndTime();
		}
		if((null == startAndEnd[0]) || (startAndEnd[0].equals("")))
			startAndEnd[0] = "00:00:00";
		if((null == startAndEnd[1]) || (startAndEnd[1].equals("")))
			startAndEnd[1] = "23:59:59";
	}
	//根据店铺ID获取商户统计起始和结束时间
	public void getStartAndEndTime(String orgCode, String[] startAndEnd)
	{
		//BsMerchantsInfoEntity mechantsInfo = bsMerchantsInfoDAO.getInfoByOrgCode(orgCode);
		BsMerchantsInfoEntity mechantsInfo = null;
		BsOrganizeEntity org = bsOrganizeDAO.getInfoByOrgCode(orgCode);
		if(org.getOrgCode().equals(org.getSchema())){
			mechantsInfo = bsMerchantsInfoDAO.getInfoByOrgCode(org.getSchema());
		}
		if(mechantsInfo != null)
		{
			startAndEnd[0] = mechantsInfo.getBusStartTime();
			startAndEnd[1] = mechantsInfo.getBusEndTime();
		}
		if((null == startAndEnd[0]) || (startAndEnd[0].equals("")))
			startAndEnd[0] = "00:00:00";
		if((null == startAndEnd[1]) || (startAndEnd[1].equals("")))
			startAndEnd[1] = "23:59:59";
	}

	/**
	 * 查询微信账号下的所有商户
	 * @param openId
	 * @return
	 */
	public List<BsOrganizeEntity> findMerchantsInfoEntityByOpenId(String openId,String status){
		return bsOrganizeDAO.findMerchantsInfoEntityByOpenId(openId,status);
	//	return bsMerchantsInfoDAO.findMerchantsInfoEntityByOpenId(openId);
	}

	@Override
	public void saveRegisterMerchants(RegisterMerchantsEntity model, String openId) throws Exception {
		if(openId==null){
			throw new RuntimeException("openId 不能为空");
		}
		BsEmployeeInfoEntity eliv;
		//员工
		//创建一个员工
		if(model.getEmpId()==null){
			eliv = new BsEmployeeInfoEntity();
			buildEmployeeInfoEntity(model, openId,eliv);
			employeeInfoDAO.save(eliv);
		}else{
			eliv = employeeInfoDAO.get(model.getEmpId());
			buildEmployeeInfoEntity(model, openId,eliv);
			employeeInfoDAO.update(eliv);
		}
		//商户
		BsMerchantsInfoEntity merchantsInfoEntity = new BsMerchantsInfoEntity();
		buildMerchantsInfoEntity(model, eliv.getEmpId(),merchantsInfoEntity);
		save(merchantsInfoEntity);
		//店铺
		if(StringUtils.isNotEmpty(model.getStoreName())){
			BsStoreInfoEntity bsi = new BsStoreInfoEntity();
			buildStoreInfoEntity(model, bsi, merchantsInfoEntity.getOrgCode());
			storeInfoService.saveStore(bsi);
		}		
	
	}
	
	/**
	 * 推送微信消息
	 * @param eliv
	 */
	private void send(BsEmployeeInfoEntity eliv){		
		//微信推送消息
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "1");
		
		JSONObject jsonObject = WxMsgTemplate.auditTemplate(map);		    
	    sendMessageService.sendAuditTemplate(jsonObject, eliv.getWxAccount());
		
	}
	
	
	@Override
	public void updateRegisterMerchants(RegisterMerchantsEntity model, String openId) throws Exception {

		String res = checkAccount(model.getAccount(),model.getMerId()+"");
		if(res != null){
			throw new RuntimeException(res);
		}
		res = checkOrgName(model.getOrgName(),model.getMerId()+"");

		if(res != null){
			throw new RuntimeException(res);
		}
	/*	if(openId==null){
			throw new RuntimeException("openId 不能为空");
		}*/
		//员工
		BsEmployeeInfoEntity eliv = employeeInfoDAO.get(model.getEmpId());
		buildEmployeeInfoEntity(model, openId,eliv);
		employeeInfoDAO.update(eliv);
		//商户
		BsMerchantsInfoEntity merchantsInfoEntity;
		if(model.getMerId()==null){
			merchantsInfoEntity = new BsMerchantsInfoEntity();
			buildMerchantsInfoEntity(model, eliv.getEmpId(),merchantsInfoEntity);
			save(merchantsInfoEntity);	
			//保存中间表
			bsMerchantsEmployeeService.save(eliv,merchantsInfoEntity);
			
			//发送微信消息
			send(eliv);
		}else{
			merchantsInfoEntity = bsMerchantsInfoDAO.get(model.getMerId());
			buildMerchantsInfoEntity(model, eliv.getEmpId(),merchantsInfoEntity);
			//默认更新为审核中
			if(model.getStatus() == null){
				update(merchantsInfoEntity,1);
			}else{
				update(merchantsInfoEntity,model.getStatus());
			}
		}
		//店铺
		if(StringUtils.isNotEmpty(model.getStoreName())){
			if(model.getStoreId()==null){
				BsStoreInfoEntity bsi = new BsStoreInfoEntity();
				buildStoreInfoEntity(model, bsi, merchantsInfoEntity.getOrgCode());
				storeInfoService.saveStore(bsi);
			}else{
				BsStoreInfoEntity bsi = storeInfoService.get(model.getStoreId());
				buildStoreInfoEntity(model, bsi, merchantsInfoEntity.getOrgCode());
				storeInfoService.update(bsi);
			}
		}
	}
	
	@Override
	public String updateRegisterMerchants(RegisterMerchantsEntity model) throws Exception {
		String res = checkAccount(model.getPhone(),model.getMerId()+"");
		if(res != null){
			throw new RuntimeException(res);
		}
		res = checkOrgName(model.getOrgName(),model.getMerId()+"");

		if(res != null){
			throw new RuntimeException(res);
		}
		BsMerchantsInfoEntity merchantsInfoEntity;
		if(model.getMerId()==null){
			merchantsInfoEntity = new BsMerchantsInfoEntity();
			buildMerchantsInfoEntity(model,null,merchantsInfoEntity);
			save(merchantsInfoEntity);	
			//保存中间表
			//bsMerchantsEmployeeService.save(eliv,merchantsInfoEntity);
			
			//发送微信消息
			//send(eliv);
		}else{
			merchantsInfoEntity = bsMerchantsInfoDAO.get(model.getMerId());
			buildMerchantsInfoEntity(model, null,merchantsInfoEntity);
			//默认更新为审核中
			if(model.getStatus() == null){
				update(merchantsInfoEntity,1);
			}else{
				update(merchantsInfoEntity,model.getStatus());
			}
		}
		//店铺
		if(StringUtils.isNotEmpty(model.getStoreName())){
			if(model.getStoreId()==null){
				BsStoreInfoEntity bsi = new BsStoreInfoEntity();
				buildStoreInfoEntity(model, bsi, merchantsInfoEntity.getOrgCode());
				storeInfoService.saveStore(bsi);
			}else{
				BsStoreInfoEntity bsi = storeInfoService.get(model.getStoreId());
				buildStoreInfoEntity(model, bsi, merchantsInfoEntity.getOrgCode());
				storeInfoService.update(bsi);
			}
		}
		return merchantsInfoEntity.getOrgCode();
	}
	private BsEmployeeInfoEntity buildEmployeeInfoEntity(RegisterMerchantsEntity model, String openId
			,BsEmployeeInfoEntity eliv){
		eliv.setMobile(model.getAccount());
		eliv.setName(model.getLegalName());
		//eliv.setWxAccount(openId);
		eliv.setCreatedtime(new Date());
		return eliv;
	}
	/**
	 * 商户
	 * @param model
	 * @param empId
	 * @return
	 * @throws Exception 
	 */
	private void buildMerchantsInfoEntity(RegisterMerchantsEntity model,Long empId,
			BsMerchantsInfoEntity merchantsInfoEntity) throws Exception{
		//商户
		merchantsInfoEntity.setOrgName(model.getOrgName());
		merchantsInfoEntity.setName(model.getMerName());
		
		merchantsInfoEntity.setStore_type(model.getType());
		merchantsInfoEntity.setCodeType(model.getCodeType());
		merchantsInfoEntity.setAddress(model.getAddress());
		merchantsInfoEntity.setTelphone(model.getPhone());
		merchantsInfoEntity.setEmail(model.getEmail());
		merchantsInfoEntity.setLegalName(model.getLegalName());
		//add set password
		merchantsInfoEntity.setAccount(model.getAccount()==null?model.getPhone():model.getAccount());
		merchantsInfoEntity.setPassword(MD5.sign(model.getPassword(), KeyConstant.USERKEY, "utf-8"));
		//地区
		String areaId = model.getAreaId();
		if(areaId==null){
			merchantsInfoEntity.setAreaId("-1");
		}else{
			merchantsInfoEntity.setAreaId(model.getAreaId());
		}
		merchantsInfoEntity.setCityId(model.getCityId());
		merchantsInfoEntity.setProvinceId(model.getProvinceId());
		merchantsInfoEntity.setCountryId(model.getCountryId());
		//营业执照
		merchantsInfoEntity.setPhoto(model.getFilePath());
		if(merchantsInfoEntity.getOrgCode()==null){
			String orgCode =  TinyUrlGenerater.generatorByUUID();
			merchantsInfoEntity.setOrgCode(orgCode);
			merchantsInfoEntity.setSchema(orgCode);
		}

		//merchantsInfoEntity.setp
		if(empId!=null){
			merchantsInfoEntity.setEmpId(empId);
		}
		//设置默认参数
		BsPlatformInfoEntity platformEntity = bsPlatformInfoDAO.getInfo("1");
		merchantsInfoEntity.setIsUpdateMoney(platformEntity.getIsUpdateMoney());
		merchantsInfoEntity.setRewardMoney(platformEntity.getRewardMoney());
	}
	/**
	 * 店铺
	 * @param model
	 * @param bsi
	 * @param orgCode
	 */
	private void buildStoreInfoEntity(RegisterMerchantsEntity model, BsStoreInfoEntity bsi,String orgCode){
		bsi.setStoreName(model.getStoreName());
		bsi.setAddress(model.getStoreAddress());
		bsi.setTelphone(model.getStoreMobile());
		bsi.setOrgCode(orgCode);
		bsi.setEmpId(model.getEmpId());
		//地区
		String areaId = model.getAreaId();
		if(areaId==null){
			bsi.setAreaId("-1");
		}else{
			bsi.setAreaId(model.getAreaId());
		}
		bsi.setStoreType(model.getType());
		bsi.setCodeType(model.getCodeType());
		bsi.setCityId(model.getCityId());
		bsi.setProvinceId(model.getProvinceId());
		bsi.setCountryId(model.getCountryId());
	}

	/**
	 * 处理区域和数据字典
	 * @param mer
	 */
	private void buildSysCode(RegisterMerchantsEntity mer) {
		if (null != mer.getType()) {
			SysCodeInfoVo codeInfo = sysCodeService.getSysCodeInfo(
					"System.store.type", mer.getType());
			if (codeInfo != null) {
				mer.setTypeName(codeInfo.getName());
			}
			// 二级类型
			SysCodeInfoEntity pcode = sysCodeService.getByParentCode(
					mer.getType(), mer.getCodeType());
			if (pcode != null) {
				mer.setCodeTypeName(pcode.getName());
			}
		}
		String proCode = mer.getProvinceId();
		if (proCode != null) {
			SysAreaInfoEntity pro = areaSerivce.getByParentCode(
					SysAreaInfoEntity.DEF_CODE, proCode);
			if (pro != null) {
				mer.setProvinceName(pro.getName());
			}
			String cityCode = mer.getCityId();
			if (cityCode != null) {
				SysAreaInfoEntity city = areaSerivce.getByParentCode(proCode,
						cityCode);
				if (city != null) {
					mer.setCityName(city.getName());
				}
				String areaCode = mer.getAreaId();
				if (areaCode != null) {
					SysAreaInfoEntity area = areaSerivce.getByParentCode(
							cityCode, areaCode);
					if (area != null) {
						mer.setAreaName(area.getName());
					}

				}
			}
		}
	}
	/**
	 * 查询微信账号下的所有商户
	 * @param openId
	 * @return
	 */
	public List<RegisterMerchantsEntity> findRegisterMerchantsEntity(String openId,String orgCode,String status){
		
		List<RegisterMerchantsEntity> list = bsMerchantsInfoDAO.getRegisterMerchantsEntity(openId, orgCode,status);
		for (RegisterMerchantsEntity mer : list) {
			buildSysCode(mer);
		}
		return list;
	}
	/**
	 * 更新用户名密码
	 * @param username
	 * @param password
	 * @param merId
	 */
	public void updateUserName(String username, String password,
			String orgCode){
		password = MD5.sign(password, KeyConstant.USERKEY, "utf-8");
		if(username!=null && password!=null){
			bsMerchantsInfoDAO.updateUserName(username, password, orgCode);
		}
		/*BsMerchantsInfoEntity model = new BsMerchantsInfoEntity();
		if(username!=null && password!=null){
			model.setAccount(username);
			model.setOrgCode(orgCode);
			model.setPassword(password);
		}
		//商户
		bsMerchantsInfoDAO.update(model);
*/
	}
	

	/**
	 * 更新用户名密码
	 * @param username
	 * @param password
	 * @param merId
	 * @throws Exception 
	 */
	public DataCode updatePassword(String username, String srcPassword, String password) throws Exception{

		BsMerchantsInfoEntity merInfo = new BsMerchantsInfoEntity();
		merInfo.setAccount(username);
		merInfo = bsMerchantsInfoDAO.getInfo(merInfo);
		if(merInfo == null){
			return DataCode.NOEXSIST_USER;
		}else if(MD5.sign(srcPassword, KeyConstant.USERKEY, "utf-8").equals(merInfo.getPassword())){
			bsMerchantsInfoDAO.updatePassword(username,MD5.sign(password, KeyConstant.USERKEY, "utf-8"));			
			return DataCode.SYS_SUCCESS;
		}
		return DataCode.NOEXSIST_PASSWORD;
	}

/*	public DataCode updatePassword(String username, String srcPassword, String password) throws Exception{

		BsMerchantsInfoEntity merInfo = new BsMerchantsInfoEntity();
		merInfo.setAccount(username);
		merInfo = bsMerchantsInfoDAO.getInfo(merInfo);
		if(merInfo == null){
			return DataCode.NOEXSIST_USER;
		}else if(MD5.sign(srcPassword, KeyConstant.USERKEY, "utf-8").equals(merInfo.getPassword())){
			merInfo.setPassword(MD5.sign(password, KeyConstant.USERKEY, "utf-8"));
			update(merInfo);			
			return DataCode.SYS_SUCCESS;
		}
		return DataCode.NOEXSIST_PASSWORD;
	}*/
	public BsMerchantsInfoEntity getMerchantsByUsername(String username){
		return bsMerchantsInfoDAO.getMerchantsByUsername(username);
	}

	/**
	 * 手机登录绑定商户
	 * @param openId	
	 * @param username		
	 * @param password		
	 */
	public void loginWap(String openId, String username, String password){
		BsMerchantsInfoEntity model = new BsMerchantsInfoEntity();
		model.setAccount(username);
		model.setPassword(password);
		try {
			model = getInfo(model);
			//更新用户openID
			BsEmployeeInfoEntity emp = new BsEmployeeInfoEntity();
			emp.setWxAccount(openId);
			emp.setEmpId(model.getEmpId());
			employeeInfoDAO.update(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void delete(String orgCode) {
		BsMerchantsInfoEntity mer = new BsMerchantsInfoEntity();
		mer.setStatus(Constant.DELETE);
		mer.setOrgCode(orgCode);
		bsMerchantsInfoDAO.update(mer);
	}
	@Override
	public List<BsOrganizeEntity> findParentOrg(String openId, String status) {
		return bsOrganizeDAO.findParentOrg(openId,status);
	}
	@Override
	public BsMerchantsInfoEntity findByOrgCode(String orgCode) {
		return bsMerchantsInfoDAO.getInfoByOrgCode(orgCode);
	}
	@Override
	public BsMerchantsInfoEntity queryMerchantInfoByPhone(String phone) {
		return bsMerchantsInfoDAO.queryMerchantInfoByPhone(phone);
	}
	@Override
	public void updatePasswordByPhone(String phone, String password) {
		bsMerchantsInfoDAO.updatePasswordByPhone(phone, MD5.sign(password, KeyConstant.USERKEY, "utf-8"));
	}
	
	@Override
	public List<BsMerchantsEmployeeEntity> getMerchantsListByEmpId(long empId) {
		try {
			return bsMerchantsEmployeeDAO.getMerchantsListByEmpId(empId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void saveUidToCache(String uid) {
		if(cache.get(CacheConstant.KEY_QRCODE_ID, uid) == null){
			System.out.print("uid不存在");
		   cache.save(CacheConstant.KEY_QRCODE_ID, uid, uid);
		}
		System.out.print("uid已经存在了");
		
	}
	@Override
	public Object getUidFromCache(String uid) {
		Object obj = cache.get(CacheConstant.KEY_QRCODE_ID, uid);
		if(obj != null)
			return obj;
		return null;
	}
	@Override
	public long saveEmpidToCache(String uid, String openId)throws Exception {
		BsEmployeeInfoEntity entity = employeeInfoDAO.getInfoByOpenId(openId);
		
		if(null==entity){
			throw new Exception("用户不存在，授权失败。");
		}
		
		long empId  =entity.getEmpId();
		if(cache.get(CacheConstant.KEY_QRCODE_EMPID, uid) == null){
		   cache.save(CacheConstant.KEY_QRCODE_EMPID, uid, empId);
		}
		return empId;
	}
	@Override
	public Object getEmpidFromCache(String uid) {
		Object obj = cache.get(CacheConstant.KEY_QRCODE_EMPID, uid);
		if(obj != null)
			return obj;
		return null;
	}
	
	public void updateBindMobile(String phone,String newMobile){
		bsMerchantsInfoDAO.updateBindMobile(newMobile, phone);
	}
	

	/**
	 * 校验账号唯一
	 */
	public String checkAccount(String account,String MerId){
		BsMerchantsInfoEntity searchMer = new BsMerchantsInfoEntity();
		if(StringUtils.isNotEmpty(account)){
			searchMer.setAccount(account);
			BsMerchantsInfoEntity res = bsMerchantsInfoDAO.getInfo(searchMer);
			if(res != null && !(res.getId()+"").equals(MerId) ){
				return USER_EXISTS;
			}
		}
		return null;
	}
	/**
	 * 校验品牌名称唯一
	 */
	public String checkOrgName(String orgName,String MerId){
		BsOrganizeEntity searchorg = new BsOrganizeEntity();
		if(StringUtils.isNotEmpty(orgName)){
			searchorg.setOrgName(orgName);
			BsOrganizeEntity res = bsOrganizeDAO.getInfoByOrgName(orgName);
			if(res == null){
				return null;
			}
			
			BsMerchantsInfoEntity mer = bsMerchantsInfoDAO.getInfoByOrgCode(res.getOrgCode());
			if(res != null &&  !(mer.getId()+"").equals(MerId)){
				return ORGNAME_EXISTS;
			}
		}
		return null;
	}
	@Override
	public List<RegisterMerchantsEntity> findMerchantsInfoByOrgCode(String orgCode) {
		List<RegisterMerchantsEntity> entity = bsMerchantsInfoDAO.getRegisterMerchantsEntity("-1", orgCode, null);
		for (int i = 0; i < entity.size(); i++) {
			buildSysCode(entity.get(i));
		}
		return entity;
	}
}
