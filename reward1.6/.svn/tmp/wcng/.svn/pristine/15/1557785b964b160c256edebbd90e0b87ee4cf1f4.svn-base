package com.platform.rp.services.statistics.external.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.common.utils.PageUtils;
import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsStoreService;
import com.platform.rp.services.statistics.core.dao.ComprehensiveSatisicsDAO;
import com.platform.rp.services.statistics.core.dao.entity.ComprehensiveSatisicsEntity;
import com.platform.rp.services.statistics.external.service.IComprehensiveSatisicsService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

/**
 * 统计
 * @author Administrator
 *
 */

@Service
public class ComprehensiveSatisicsServiceImpl implements IComprehensiveSatisicsService{
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private IExBsMerchantsStoreService merchantsStoreService;

	@Autowired
	private ComprehensiveSatisicsDAO<ComprehensiveSatisicsEntity> comprehensiveSatisicsDAO;
	
	public ComprehensiveSatisicsEntity findRealTimeDynamicAll(Map<String,String> entity){
		return comprehensiveSatisicsDAO.findRealTimeDynamicAll(entity);
	}
	
	public RestfulResult findRealTimeDynamic(Map<String,String> entity){
		Page page =  PageUtils.initPage(entity);
		// 查询总数
		List<ComprehensiveSatisicsEntity> list = comprehensiveSatisicsDAO.findRealTimeDynamicPage(entity);
		// 查询总数
		int count = comprehensiveSatisicsDAO.findRealTimeDynamicCount(entity);

		return PageUtils.returnPage(list, count, page);
	}
	
	public  List<ComprehensiveSatisicsEntity> findCharStatistcs(Map<String,String> entity){
		String storeIds = entity.get("storeIds");
		String startTime = entity.get("startTime");
		String endTime = entity.get("endTime");
		List<ComprehensiveSatisicsEntity> entitys = 
				//comprehensiveSatisicsDAO.findCharStatistcs(storeIds.split(","), startTime, endTime);
				comprehensiveSatisicsDAO.findCharStatistcsAllStore(storeIds.split(","), startTime, endTime);
		return entitys;
	}
	public  ComprehensiveSatisicsEntity findCharStatistcsAll(Map<String,String> entity){
		String storeIds = entity.get("storeIds");
		String startTime = entity.get("startTime");
		String endTime = entity.get("endTime");
		if(storeIds == null){
			String orgCode = entity.get("orgCode");
			List<BsMerchantsStoreEntity>  mses = merchantsStoreService.getAllListByOrgCode(orgCode);
			for (int i = 0; i < mses.size(); i++) {
				storeIds += mses.get(i).getStoreId()+",";
			}
			if(storeIds.length()>0){
				storeIds = storeIds.substring(0, storeIds.length()-1);
			}
		}
		ComprehensiveSatisicsEntity entitys = 
				comprehensiveSatisicsDAO.findAllCharStatistcs(storeIds.split(","), startTime, endTime);
		ComprehensiveSatisicsEntity entitys2 = 
				comprehensiveSatisicsDAO.findAllShopownerDivided(storeIds.split(","), startTime, endTime);
		if(entitys==null){
			return entitys2;
		}
		if(entitys2!=null){
			logger.debug("分成:"+entitys2.getShopownerDivided());
			entitys.setShopownerDivided(entitys2.getShopownerDivided());
			entitys.setMerDivided(entitys2.getMerDivided());
			entitys.setEmpDivided(entitys2.getEmpDivided());
		}
		return entitys;
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Page initPage(Map params){
		// 初始化参数
		Page page = PageUtils.initPage(params);
		String stroeIds = (String) params.get("storeIds");
		params.put("storeIds", stroeIds.split(","));
		return page;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page findStoreRankingPage(Map params) {
		Page page = initPage(params);
		// 查询总数
		List list = comprehensiveSatisicsDAO.findStoreRankingPage(params);
		// 查询总数
		int count = comprehensiveSatisicsDAO.findStoreRankingCount(params);
		page.setResult(list);
		page.setTotalCount(count);
		return page;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page findStaffRankingPage(Map params) {
		Page page = initPage(params);
		// 查询总数
		List list = comprehensiveSatisicsDAO.findStaffRankingPage(params);
		// 查询总数
		int count = comprehensiveSatisicsDAO.findStaffRankingCount(params);
		page.setResult(list);
		page.setTotalCount(count);
		return page;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page findLogsByData(Map params) {
		Page page = initPage(params);
		// 查询总数
		List list = comprehensiveSatisicsDAO.findLogsByDataPage(params);
		// 查询总数
		int count = comprehensiveSatisicsDAO.findLogsByDataCount(params);
		page.setResult(list);
		page.setTotalCount(count);
		return page;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  List<ComprehensiveSatisicsEntity> findLogsByDataDetail(Map params) {

		String stroeIds = (String) params.get("storeIds");
		params.put("storeIds", stroeIds.split(","));
		return  comprehensiveSatisicsDAO.findLogsByDataDetail(params);
	}
	
	public  Page findStoreSummaryPage(Map<String, Object> params){
			Page page = initPage(params);
			// 查询总数
			List<ComprehensiveSatisicsEntity> list = comprehensiveSatisicsDAO.findStoreSummaryPage(params);
			// 查询总数
			int count = comprehensiveSatisicsDAO.findStoreSummaryCount(params);

			page.setResult(list);
			page.setTotalCount(count);
			return page;
	}
	public  List<ComprehensiveSatisicsEntity> findStoreSummaryDetail(Map<String, Object> params){
		return  comprehensiveSatisicsDAO.findStoreSummaryDetail(params);
	}
}
