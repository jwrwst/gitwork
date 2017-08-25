/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ituber.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 需求资源Entity
 * @author 王宏伟
 * @version 2016-08-30
 */
public class ReqRequirementResource extends DataEntity<ReqRequirementResource> {
	
	private static final long serialVersionUID = 1L;
	private Long reqId;		// 需求序号
	private Long resourceId;		// 资源序号
	private String status;		// 参见《基础信息表》中的资源的需求状态；
	private String stopReason;		// 参见《基础信息表》中的终止原因；
	private Date joinDate;		// 进入项目时间
	private Date estimatedQuitDate;		// 预计退出项目时间
	private Date quitDate;		// 实际退出项目时间
	private String quitReason;		// 1：退出；2：离职；
	private String evaluation;		// 评价
	private Date created;		// 创建时间
	private Date modified;		// 修改时间
	
	public ReqRequirementResource() {
		super();
	}

	public ReqRequirementResource(String id){
		super(id);
	}

	public Long getReqId() {
		return reqId;
	}

	public void setReqId(Long reqId) {
		this.reqId = reqId;
	}
	
	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
	@Length(min=0, max=11, message="参见《基础信息表》中的资源的需求状态；长度必须介于 0 和 11 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=11, message="参见《基础信息表》中的终止原因；长度必须介于 0 和 11 之间")
	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEstimatedQuitDate() {
		return estimatedQuitDate;
	}

	public void setEstimatedQuitDate(Date estimatedQuitDate) {
		this.estimatedQuitDate = estimatedQuitDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}
	
	@Length(min=0, max=11, message="1：退出；2：离职；长度必须介于 0 和 11 之间")
	public String getQuitReason() {
		return quitReason;
	}

	public void setQuitReason(String quitReason) {
		this.quitReason = quitReason;
	}
	
	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
}