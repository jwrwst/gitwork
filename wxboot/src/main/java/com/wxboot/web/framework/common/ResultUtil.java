package com.wxboot.web.framework.common;

/**
 * 处理返回值
 * @author WHW
 */
public class ResultUtil {

	/**
	 * 成功
	 * @param object
	 * @return
	 */
	public static ResultData<Object> success(Object object){
		ResultData<Object> resultData = new ResultData<Object>();
		resultData.setCode(CodeEnum.SUCCESS.getCode());
		resultData.setMessage(CodeEnum.SUCCESS.getMessage());
		resultData.setData(object);
		return resultData;
	}
	
	/**
	 * 成功
	 * @param object
	 * @return
	 */
	public static ResultData<Object> success(){
		return success(null);
	}
	
	/**
	 * 失败
	 * @param object
	 * @return
	 */
	public static ResultData<Object> error(String code,String message){
		ResultData<Object> resultData = new ResultData<Object>();
		resultData.setCode(code);
		resultData.setMessage(message);
		return resultData;
	}
	
	/**
	 * 失败
	 * @param object
	 * @return
	 */
	public static ResultData<Object> error(){
		return error(CodeEnum.ERROR.getCode(),CodeEnum.ERROR.getMessage());
	}
	
	/**
	 * 
	 * @param codeEnum
	 * @return
	 */
	public static ResultData<Object> error(CodeEnum codeEnum){
		return error(codeEnum.getCode(),codeEnum.getMessage());
	}
	
	/**
	 * 入参基本校验异常
	 * @param codeEnum
	 * @return
	 */
	public static ResultData<Object> paramsError(String message){
		return error(CodeEnum.PARAMS_EX.getCode(),message);
	}
	
	public static ResultData<Object> validateError(String message){
		return error(CodeEnum.PERMISSION_EX.getCode(),message);
	}
	
	/**
	 * 入参数据校验异常
	 * @param codeEnum
	 * @return
	 */
	public static ResultData<Object> dataError(String message){
		return error(CodeEnum.DATA_EX.getCode(),message);
	}
	
	
}
