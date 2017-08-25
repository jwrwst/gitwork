package com.platform.rp.common.utils;

import java.util.List;
import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

public class PageUtils {
	@SuppressWarnings({"unchecked", "rawtypes" })
	public static Page initPage(Map params){
		Page page = new Page();
		int start =  parsertInt(params,"iDisplayStart");
		params.put("start",start);
		params.put("pageSize", parsertInt(params,"iDisplayLength"));
		return page;
	}
	public static int parsertInt(Map params,String filed){
		try {
			int start =  (Integer) params.get(filed);
			return start;
		} catch (ClassCastException e) {
			return Integer.parseInt(""+params.get(filed));
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static RestfulResult returnPage(List list,int count,Page page){
		
		page.setResult(list);
		page.setTotalCount(count);
		
		return RestfulResultProvider.buildSuccessResult(new ResultData(page));
	}
	/**
	 * wap分页
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Page initWapPage(Map params){
		RequestModel model = new RequestModel();
		ApplicationUtils.parseFormValue(params, model);
		Page page = model.getPage();
		params.put("start", page.getStart());
//		params.put("end", page.getEnd());
		params.put("pageSize", page.getPageSize());
		return page;
	}
}	
