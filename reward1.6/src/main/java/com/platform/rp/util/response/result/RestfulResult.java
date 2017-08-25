package com.platform.rp.util.response.result;

import javax.xml.bind.annotation.XmlRootElement;

import com.platform.rp.util.info.ResultMessage;
import com.platform.rp.util.response.data.RawData;

@XmlRootElement
public class RestfulResult implements RawResult{

	private static final long serialVersionUID = 1L;

	/** 响应状态 */
	private ResultMessage resultMessage;

	/** 响应数据 */
	private RawData resultData;
	
	public RestfulResult(ResultMessage resultMessage) {
		super();
		this.resultMessage = resultMessage;
	}
	
	public RestfulResult(ResultMessage resultMessage, RawData resultData) {
		super();
		this.resultMessage = resultMessage;
		this.resultData = resultData;
	}

	public ResultMessage getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(ResultMessage resultMessage) {
		this.resultMessage = resultMessage;
	}

	public RawData getResultData() {
		return resultData;
	}

	public void setResultData(RawData resultData) {
		this.resultData = resultData;
	}

}
