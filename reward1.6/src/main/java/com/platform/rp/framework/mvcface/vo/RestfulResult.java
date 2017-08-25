/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.platform.rp.framework.mvcface.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.platform.rp.util.info.ResponseCode;

/**
 * 
 * @author TangJun
 *
 */
@XmlRootElement
public class RestfulResult {
	private int resultFlag;
    private String resultCode;
    private String resultMessage;
    private int total;
    private List<Object>   data;
    private Page page;	

	public int getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(int resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

   

	public RestfulResult() {
		super();
	}

	public RestfulResult(Page page,ResponseCode res) {
		super();
		this.resultCode = res.getCode();
		this.resultMessage=res.getDesc();
		this.resultFlag=res.getStatus();
		this.page = page;
	}
	
	public RestfulResult(ResponseCode res) {
		super();
		this.resultCode = res.getCode();
		this.resultMessage=res.getDesc();
		this.resultFlag=res.getStatus();
	}

}
