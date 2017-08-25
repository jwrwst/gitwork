package com.wxboot.web.userinfo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxboot.web.framework.common.Pager;
import com.wxboot.web.userinfo.dao.UserInfoDAO;
import com.wxboot.web.userinfo.model.UserInfo;

/**
 * <p>Discription:[人员信息服务]</p>
 * Created on 2017年03月20日
 * @author WHW
 */
@Service("userInfoService")
public class UserInfoService {
	@Resource
	private UserInfoDAO userInfoDAO;
	
	public Pager<UserInfo> selectPager(UserInfo userInfo,Pager<UserInfo> Pager){
		List<UserInfo> userInfos = userInfoDAO.selectListByPage(userInfo, Pager);
		Long count = userInfoDAO.selectCount(userInfo);
		if(Pager==null){
			Pager = new Pager<UserInfo>();
		}
		Pager.setCount(count);
		Pager.setList(userInfos);
		return Pager;
	}
	
	public List<UserInfo> selectList(UserInfo userInfo){
		List<UserInfo> userInfos = userInfoDAO.selectListByPage(userInfo, null);
		return userInfos;
	}

	public UserInfo selectOneByCondition(UserInfo userInfo){
		return userInfoDAO.selectOne(userInfo);
	}
	
	public UserInfo selectById(String id) {
		return userInfoDAO.selectById(id);
	}
	
	public UserInfo insert(UserInfo userInfo) {
		userInfoDAO.insert(userInfo);
		return userInfo;
	}
	
	public void update(UserInfo userInfo) {
		userInfoDAO.update(userInfo);
	}
}