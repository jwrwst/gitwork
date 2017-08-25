package com.platform.rp.services.statistics.external.rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.statistics.external.rest.IExportStatisticsRest;
import com.platform.rp.services.statistics.external.service.IComprehensiveSatisicsService;
import com.platform.rp.util.excel.EColumnVo;
import com.platform.rp.util.excel.ExcelExportUtils;

@SuppressWarnings("rawtypes")
@Controller
public class ExportStatisticsRestImpl extends BaseController implements IExportStatisticsRest{

	
	@Autowired
	IComprehensiveSatisicsService comprehensiveSatisicsService;

	
	@SuppressWarnings("unchecked")
	@Override
	public void findStoreRankingPage() {
		try {
			Map params = initExcleParams();
			Page page = comprehensiveSatisicsService.findStoreRankingPage(params);
			List<EColumnVo> columnVos = new ArrayList<>();
			ExcelExportUtils.buildExcleColumn(columnVos, "店铺名称", "storeName");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏金额", "amountCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "店长分成", "shopownerDivided");
			ExcelExportUtils.buildExcleColumn(columnVos, "收到打赏员工数", "empCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏人数", "cusCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏次数", "count");
			XSSFWorkbook xssfwk = ExcelExportUtils.export(columnVos, page.getResult());
			dowloadExcle(null,xssfwk);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void findStaffRankingPage() {
		try {
			Map params = initExcleParams();
			Page page = comprehensiveSatisicsService.findStaffRankingPage(params);
			List<EColumnVo> columnVos = new ArrayList<>();
			ExcelExportUtils.buildExcleColumn(columnVos, "店员名称", "empName");
			ExcelExportUtils.buildExcleColumn(columnVos, "店铺名称", "storeName");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏金额", "amountCount");
			//ExcelExportUtils.buildExcleColumn(columnVos, "店长分成", "shopownerDivided");
			//ExcelExportUtils.buildExcleColumn(columnVos, "收到打赏员工数", "empCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏人数", "cusCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏次数", "count");
			XSSFWorkbook xssfwk = ExcelExportUtils.export(columnVos, page.getResult());
			dowloadExcle(null,xssfwk);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void findLogsByData() {

		try {
			Map params = initExcleParams();
			Page page = comprehensiveSatisicsService.findLogsByData(params);
			List<EColumnVo> columnVos = new ArrayList<>();
			ExcelExportUtils.buildExcleColumn(columnVos, "日期", "dateVal");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏金额", "amountCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "店长分成", "shopownerDivided");
			ExcelExportUtils.buildExcleColumn(columnVos, "收到打赏员工数", "empCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏人数", "cusCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏次数", "count");
			XSSFWorkbook xssfwk = ExcelExportUtils.export(columnVos, page.getResult());
			dowloadExcle(null,xssfwk);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void findStoreSummaryPage() {

		try {
			Map params = initExcleParams();
			Page page = comprehensiveSatisicsService.findStoreSummaryPage(params);
			List<EColumnVo> columnVos = new ArrayList<>();
			ExcelExportUtils.buildExcleColumn(columnVos, "店铺名称", "storeName");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏金额", "amountCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "店长分成", "shopownerDivided");
			ExcelExportUtils.buildExcleColumn(columnVos, "收到打赏员工数", "empCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏人数", "cusCount");
			ExcelExportUtils.buildExcleColumn(columnVos, "打赏次数", "count");
			XSSFWorkbook xssfwk = ExcelExportUtils.export(columnVos, page.getResult());
			dowloadExcle(null,xssfwk);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
