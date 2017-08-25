/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platform.rp.util.info.codes;

import com.platform.rp.util.info.ResponseCode;

/**
 * 对外接口服务返回数据中的通用的返回码
 * 
 * @author tangjun
 */
public enum CommonCode implements ResponseCode {

    /** 成功 **/
    SUCCESS((short) 0, "0000", "成功"),
    /** 正常 **/
    NORMAL((short) 0, "2000", "正常"),
    /** 该用户无权使用本服务 **/
    NO_PERMISSION((short) 1, "2001", "该用户无权使用本服务"),
    /** 签名验证失败 **/
    INVALID_SIGN((short) 1, "2005", "签名验证失败"),
    /** 签名失败，需要重新登录 **/
    INVALID_SIGN_RELOGIN((short) 1, "AUTH001", "签名出错,需要重新进入"),
    /** 参数不合法 **/
    INVALID_PARAM((short) 1, "2006", "参数不合法"),
    /** 密码验证错误，您本次操作失败 **/
    PASSWORD_ERROR((short) 1, "3001", "密码验证错误，您本次操作失败"),
    /** 验证码错误，您本次操作失败 **/
    AUTHCODE_ERROR((short) 1, "3002", "验证码错误，您本次操作失败"),
    /** 数据转换异常 **/
    NUMFORMAT_ERROR((short) 1, "4001", "数据转换异常"),
    /** 数据解析异常 **/
    PARSE_ERROR((short) 1, "4002", "数据解析异常"),
    /** 操作数据表异常 **/
    DATADB_ERROR((short) 1, "4003", "操作数据表异常"),
    /** 数据库连接异常 **/
    DATADB_CONNECT_ERROR((short) 1, "4004", "数据库连接异常"),
    
    
    
    /**************************业务提示**********************************/
    REPEEAT_BIND((short) 1, "5001", "重复绑定"),
    
    

    
    /**************************系统提示**********************************/
    /** 系统磁盘读写错误 **/
    SYS_DISK_IO_ERROR((short) 1, "9000", "系统磁盘读写错误"),
    /** 网络错误 **/
    NETWORK_ERROR((short) 1, "9002", "网络错误,连接超时"),
    /** 业务忙，业务缓存 **/
    BUSSINESS_BUSY((short) 1, "9003", "业务忙，业务缓存"),
    /** 系统繁忙 **/
    SYS_BUSY((short) 1, "9005", "系统繁忙"),
    /** 系统异常，并不可用 **/
    SYS_EXECEPTION((short) 1, "9008", "系统异常，并不可用"),
    /** 业务异常，并不可用 **/
    SERVICE_EXECEPTION((short) 1, "9009", "业务异常，并不可用"),
    /** 该业务没有权限调用该接口消息 **/
    SERVICE_NO_PERMISSION((short) 1, "9010", "该业务没有权限调用该接口消息"),
    /** 非法接入IP **/
    ILLEGAL_IP((short) 1, "9017", "非法接入IP"),
    /** 平台其他错误 **/
    PLATFORM_OTHER_ERROR((short) 1, "9999", "平台其他错误"),
    
    ERROR((short) 1, "9991", "操作失败"),
	
    UPDATE_BIND_EXCEPTION((short) 1, "9992", "请输入正确的手机号和验证码"),
    
    OVER_LENGTH_8((short) 1, "5008", "请输入8字以内的内容"),
    
	OVER_LENGTH_50((short) 1, "5050", "请输入50字以内的内容"),
	
	FORBIDDEN_CHAR((short) 1, "6000", "请删掉除，。！以外的标点符号"),
    /** 验证码错误，请重新输入 **/
    CODE_EXECEPTION((short) 1, "6001", "验证码错误，请重新输入"),
    
    /** 帐号输入错误输入错误，请重新输入 **/
    PHONE_EXCEPTION((short) 1,"6002", "帐号不存在，请重新输入"),

    /** 帐号输入错误输入错误，请重新输入 **/
    USER_EXISTS((short) 1,"6003", "帐号已经存在，请重新输入"),
    
    /** 帐号输入错误输入错误，请重新输入 **/
    ORGNAME_EXISTS((short) 1,"6004", "品牌名称已经存在，请重新输入");
    
    private Short status;
    private String code, desc;

    CommonCode(Short status, String code, String desc) {
        this.status = status;
        this.code = code;
        this.desc = desc;
    }

    public String value() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Short getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

}
