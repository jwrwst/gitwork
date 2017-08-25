package com.wxboot.web.framework.common;

/**
 * code枚举类
 * @author WHW
 */
public enum CodeEnum {
	
	PERMISSION_EX("-2","权限验证失败"),
	ERROR("-1","系统错误"),
	SUCCESS("200","成功"),
	//登录注册异常 1000-1999
	LOGIN_EX("1000","用户名或密码错误"),
	REG_EX("1001","手机已注册"),
	USE_EX("1002","该用户不存在"),
	USER_OUT_EX("1003","该用户已注销"),
	//参数基本校验异常2000-2999
	PARAMS_EX("2000","参数异常"),
	
	//金钱类的异常3000-3999
	BANK_CARD_EX("3000","添加银行卡重复"),
	PAY_TYPE_EX("3100","请选择正确支付方式"),
	WEIXIN_VALID_EX("3101","微信回调校验失败"),
	WEIXIN_EX("3200","微信调用失败"),
	//数据库数据校验的异常4000-4999
	DATA_EX("4000","数据异常（信息不存在）"),
	//订单类的异常5000-5999

	//伴伴类的异常6000-6999
	USER_JOIN_EX("6001","您已加入不能重复加入"),
	BANBAN_FULL_EX("6002","该结伴人数已满，请选择其他的结伴！"),
	//播播类的异常7000-7999
	
	//微信端的异常8000-8999
	WEIXIN_OPENID_EX("8000","微信openid获取失败！"),
	//短信异常9000-9500
	MSG_CODE_EX("9000","验证码条数超出"),
	MSG_CODE_ERROR("9001","验证码错误"),
	MSG_CODE_INVALID("9002","验证码失效");
	
/*	
	
	ADD_FAIL("2001","新增失败"),
	USER_EXIST("2008","用户存在"),
	USER_NO_EXIST("2009","用户不存在"),
	VALICATION_CODE_ERROR("9001","验证码错误"),
	IS_NOT_MARK("400","未选择评分星级,请选择星级"),
	ORDER_FAIL("401","下单失败"),
	MONEY_EX("4002","金额不能为负"),
	VALIDATE_FAIL("4003","验证失败"),
	PERSON_NOT_ENOUGH("4004","开班人数不足最小开班人数"),
	CARRY_CATOLOG_EX("4005","播播携带物类别异常"),
	DATA_EX("4006","数据异常"),
	SIGIN_EXIST("4007","今天已经签到了"),
	PERSON_IS_FULL("1003","人员已满"),
	REPEAT_COMMENT("1004","请勿重复评价");
	*/
	private String code;
	
	private String message;
	
	CodeEnum(String code,String message){
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
