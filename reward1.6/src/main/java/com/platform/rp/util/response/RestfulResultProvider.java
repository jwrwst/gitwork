package com.platform.rp.util.response;

import com.platform.rp.util.info.ResponseCode;
import com.platform.rp.util.info.ResultMessage;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.data.RawData;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

/**
 * 提供各项创建RestfulResult对象操作策略
 * 
 */
public abstract class RestfulResultProvider {

	/** 操作成功，无数据返回。 */
	public static RestfulResult buildSuccessResult() {

		return new RestfulResult(new ResultMessage(CommonCode.SUCCESS));
	}
	
	/** 操作成功，并能返回正常数据。 */
	public static RestfulResult buildSuccessResult(RawData resultData) {

		return new RestfulResult(new ResultMessage(CommonCode.SUCCESS), resultData);
	}
	
	/** 操作正常，无数据返回 */
	public static RestfulResult buildNormalResult() {

		return new RestfulResult(new ResultMessage(CommonCode.NORMAL));
	}
	
	/** 操作正常，并返回数据。 */
	public static RestfulResult buildNormalResult(RawData resultData) {

		return new RestfulResult(new ResultMessage(CommonCode.NORMAL), resultData);
	}

	/** 操作失败，并返回数据 */
	public static RestfulResult buildFailureResult() {

		return new RestfulResult(new ResultMessage(CommonCode.ERROR));
	}
	
	/** 操作失败，并返回数据 */
	public static RestfulResult buildFailureResult(RawData resultData) {

		return new RestfulResult(new ResultMessage(CommonCode.ERROR), resultData);
	}

	/** 创建并返回RestfulResult */
	public static RestfulResult buildRestfulResult(ResultMessage message, RawData resultData) {

		return new RestfulResult(message, resultData);
	}
	
	/** 创建并返回RestfulResult */
	public static RestfulResult buildCodeResult(ResponseCode code){
		return new RestfulResult(new ResultMessage(code));
	}

	/** 创建并返回RestfulResult */
	public static RestfulResult buildCodeResult(ResponseCode code, RawData resultData) {

		return new RestfulResult(new ResultMessage(code), resultData);
	}

	/** 创建并返回RestfulResult */
	public static RestfulResult buildCodeResult(ResponseCode code, Object o) {
		ResultData resultData = new ResultData();
		resultData.putItem("result", o);
		return new RestfulResult(new ResultMessage(code), resultData);
	}
}
