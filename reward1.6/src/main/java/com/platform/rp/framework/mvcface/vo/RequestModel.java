package com.platform.rp.framework.mvcface.vo;

import java.util.Map;

import com.platform.rp.framework.mvcface.vo.formparser.FormParser;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.RegularExpression;

public class RequestModel implements FormParser {

	private Integer pageNum = 0;
	private Integer numPerPage = 20;
	private String orderField;
	private String orderDirection;
	private String keyName;
	private String key;
	private String startDate;
	private String endDate;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	@Override
	public void parseFormValue(Map<String, String> map, Object obj) {
		RequestModel model = (RequestModel) obj;
		String pageNum = map.get("pageNum");
		String numPerPage = map.get("numPerPage");
		String orderField = map.get("orderField"); // 排序方向,多个排序用','分隔
		String orderDirection = map.get("orderDirection"); // 排序字段.多个排序用','分隔
		String keyName = map.get("keyName"); // 查询字段名
		String key = map.get("key"); // 查询字段取值
		String startDate = map.get("startDate");
		String endDate = map.get("endDate");

		if (!ApplicationUtils.isEmpty(pageNum) && RegularExpression.checkZS(pageNum)) {
			model.setPageNum(Integer.parseInt(pageNum));
		}
		if (!ApplicationUtils.isEmpty(numPerPage) && RegularExpression.checkZS(numPerPage)) {
			model.setNumPerPage(Integer.parseInt(numPerPage));
		}
		if (!ApplicationUtils.isEmpty(orderField)) {
			model.setOrderField(orderField);
		}
		if (!ApplicationUtils.isEmpty(orderDirection)) {
			model.setOrderDirection(orderDirection);
		}
		if (!ApplicationUtils.isEmpty(keyName)) {
			model.setKeyName(keyName);
		}
		if (!ApplicationUtils.isEmpty(key)) {
			model.setKey(key);
		}
		if (!ApplicationUtils.isEmpty(startDate)) {
			model.setStartDate(startDate);
		}
		if (!ApplicationUtils.isEmpty(endDate)) {
			model.setEndDate(endDate);
		}
	}

	public Page getPage() {
		Page page = new Page();
		page.setPageNo(this.getPageNum());
		page.setPageSize(this.getNumPerPage());
		if (null != this.getOrderField()) {
			page.setOrder(this.getOrderField());
			page.setOrderBy(this.getOrderDirection());
		}
		page.setStart(page.getPageSize()*(page.getPageNo()-1));
		page.setEnd(page.getStart() + page.getPageSize());
		return page;
	}

}
