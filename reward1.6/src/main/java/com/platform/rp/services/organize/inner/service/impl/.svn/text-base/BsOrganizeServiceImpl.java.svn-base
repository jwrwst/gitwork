/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platform.rp.services.organize.inner.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.organize.core.dao.BsMerchantsInfoDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsStoreDAO;
import com.platform.rp.services.organize.core.dao.BsOrganizeDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.core.dao.entity.RegisterMerchantsEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.organize.inner.service.IBsOrganizeService;
import com.platform.rp.services.store.core.dao.BsStoreEmployeeDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeEntity;
import com.platform.rp.services.wechat.common.WxMsgTemplate;
import com.platform.rp.services.wechat.service.IWxSendMessageService;
import com.platform.rp.util.Constant;

/**
 * 
 * @author TangJun
 * 
 */
@Service
public class BsOrganizeServiceImpl implements IBsOrganizeService {

	@Autowired
	BsMerchantsInfoDAO<BsMerchantsInfoEntity> bsMerchantsInfoDAO;
	
	@Autowired
	BsOrganizeDAO<BsOrganizeEntity> bsOrganizeDAO;
	
	@Autowired
	BsMerchantsStoreDAO<BsMerchantsStoreEntity> bsMerchantsStoreDAO; 
	
	@Autowired
	BsStoreEmployeeDAO<BsStoreEmployeeEntity> bsStoreEmployeeDAO;
	
	@Autowired
	private IWxSendMessageService sendMessageService;
	
	@Autowired
	private IExBsMerchantsInfoService bsMerchantsInfoService;

	/**
	 * @see 列表分页
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page getOrganizeInfoPage(Page page, Map<String, Object> params) throws Exception {
		try {
			int start = page.getPageSize()*(page.getPageNo()-1);
    		//int end = page.getPageSize()*page.getPageNo();
			
			params.put("start", start);
			params.put("pageSize", page.getPageSize());
    		List<BsOrganizeEntity> volist = bsOrganizeDAO.getPage(params);
			
    		int count = bsOrganizeDAO.count(params);
			page.setResult(volist);
            page.setTotalCount(count);            
            
			return page;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 修改信息
	 */
	public void update(BsOrganizeEntity bsOrganizeEntity)
			throws Exception {
		bsOrganizeEntity.setUpdateTime(new Date());
		
		bsOrganizeDAO.update(bsOrganizeEntity);
	
		this.updateStoreInfo(bsOrganizeEntity.getOrgCode());
		
		//发送微信通知审核结果  		
		BsEmployeeInfoEntity empEntity= bsMerchantsInfoDAO.getEmpInfoByOrgCode(bsOrganizeEntity.getOrgCode());
		if(null!=empEntity){
			sendAuditMsg(empEntity.getWxAccount(),bsOrganizeEntity.getStatus(),bsOrganizeEntity.getRemark());
		}
	}
	
	/**
	 * 批量修改信息
	 * @param orgCodes
	 * @param status
	 */
	public void updateList(String orgCodes,int status,String remark){
		String[] orgCode = orgCodes.split(",");
		BsOrganizeEntity bsOrganizeEntity= new BsOrganizeEntity();
		Date dt =new Date();
		for (String string : orgCode) {
			bsOrganizeEntity.setOrgCode(string); 
			bsOrganizeEntity.setUpdateTime(dt);
			bsOrganizeEntity.setStatus(status);
			bsOrganizeEntity.setRemark(remark);
			bsOrganizeDAO.update(bsOrganizeEntity);
			this.updateStoreInfo(string);
			
			//发送微信通知审核结果  			
			BsEmployeeInfoEntity empEntity= bsMerchantsInfoDAO.getEmpInfoByOrgCode(string);	
			if(null!=empEntity){
				sendAuditMsg(empEntity.getWxAccount(),status,remark);
			}
		}
		
	}
	
	/**
	 * 发送微信 通知
	 * @param toUser
	 * @param resultStr
	 */
	private void sendAuditMsg(String toUser,int status,String remark){
		//微信推送消息
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", status+"");
		map.put("remark", remark);
		
		JSONObject jsonObject = WxMsgTemplate.auditTemplate(map);		    	    
	    sendMessageService.sendAuditTemplate(jsonObject, toUser);
	}

	/**
	 * 查询详细信息
	 * @param orgCode
	 * @return
	 */
	public BsMerchantsInfoEntity getMerchantsInfo(String orgCode){
		BsMerchantsInfoEntity entity=new BsMerchantsInfoEntity();
		entity.setOrgCode(orgCode);
        return bsMerchantsInfoDAO.getInfo(entity);        
      
	}
	
	/**
	 * 查询注册详细信息
	 * @param orgCode
	 * @return
	 */
	public RegisterMerchantsEntity getRegMerchantsInfo(String orgCode)throws Exception{   
        //List<RegisterMerchantsEntity> list=bsMerchantsInfoDAO.findRegisterMerchantsEntity("-1", orgCode, null);
        List<RegisterMerchantsEntity> list=bsMerchantsInfoService.findRegisterMerchantsEntity("-1", orgCode, null);
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }
        return null; 
	}

	/**
	 * 修改店铺信息
	 * @param storeCode
	 */
	private void updateStoreInfo(String storeCode){
		//bsStoreInfoDAO.updateStatusByStoreCode(Constant.ABLE, storeCode);
		List<BsMerchantsStoreEntity> list = bsMerchantsStoreDAO.getlist(storeCode, 0,null);
		BsStoreEmployeeEntity storeEmp;
		for (BsMerchantsStoreEntity bsMerchantsStoreEntity : list) {
			storeEmp = new BsStoreEmployeeEntity();
			storeEmp.setStoreId(bsMerchantsStoreEntity.getStoreId());
			storeEmp.setStatus(Constant.ABLE);
			bsStoreEmployeeDAO.update(storeEmp);
		}
		
	}
	
	
}
