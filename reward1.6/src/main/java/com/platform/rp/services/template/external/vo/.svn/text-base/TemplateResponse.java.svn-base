package com.platform.rp.services.template.external.vo;

import java.io.Serializable;

import com.platform.rp.util.info.ResultMessage;

public class TemplateResponse implements Serializable{
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8618563263745672001L;

	/**
	 * 响应状态
	 * 
	 */
	private ResultMessage resultMessage;

	/**
	 * 响应数据
	 * 
	 */
	private ResultData resultData;

	public ResultMessage getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(ResultMessage resultMessage) {
		this.resultMessage = resultMessage;
	}

	public ResultData getResultData() {
		return resultData;
	}

	public void setResultData(ResultData resultData) {
		this.resultData = resultData;
	}
	
	public class ResultData {
		

	    /**
	     * 模版名称
	     * 
	     */
	    private String name = "";

	    /**
	     * 模版地址
	     * 
	     */
	    private String templatePath = "";

	    /**
	     * 部分模版地址
	     * 
	     */
	    private String partTemplatePath = "";

	    /**
	     * 唯一标识
	     * 
	     */
	    private String uniqueIdentify = "";

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTemplatePath() {
			return templatePath;
		}

		public void setTemplatePath(String templatePath) {
			this.templatePath = templatePath;
		}

		public String getPartTemplatePath() {
			return partTemplatePath;
		}

		public void setPartTemplatePath(String partTemplatePath) {
			this.partTemplatePath = partTemplatePath;
		}

		public String getUniqueIdentify() {
			return uniqueIdentify;
		}

		public void setUniqueIdentify(String uniqueIdentify) {
			this.uniqueIdentify = uniqueIdentify;
		}
	}
}

