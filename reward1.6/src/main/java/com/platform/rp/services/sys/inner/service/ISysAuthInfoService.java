package com.platform.rp.services.sys.inner.service;

import java.util.List;

import com.platform.rp.services.sys.inner.vo.SysAuthInfoVo;

public interface ISysAuthInfoService {

	/**
	 * @see 获取列表
	 * @return
	 * @throws Exception
	 */
	public abstract List<SysAuthInfoVo> getSysAuthInfoList() throws Exception;

}