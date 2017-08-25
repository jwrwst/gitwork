/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platform.rp.services.sys.inner.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.sys.core.dao.SysAuthInfoDAO;
import com.platform.rp.services.sys.core.dao.SysRoleAuthDAO;
import com.platform.rp.services.sys.core.dao.SysRoleInfoDAO;
import com.platform.rp.services.sys.core.dao.SysUserRoleDetailDAO;
import com.platform.rp.services.sys.core.dao.entity.SysAuthInfoEntity;
import com.platform.rp.services.sys.core.dao.entity.SysRoleAuthEntity;
import com.platform.rp.services.sys.core.dao.entity.SysRoleInfoEntity;
import com.platform.rp.services.sys.core.dao.entity.SysUserRoleDetailEntity;
import com.platform.rp.services.sys.inner.service.ISysRoleInfoService;
import com.platform.rp.services.sys.inner.vo.SysRoleInfoVo;
import com.platform.rp.util.ApplicationUtils;

/**
 * @see 角色处理
 * @author TangJun
 *
 */
@Service
public class SysRoleInfoServiceImpl implements ISysRoleInfoService {

    @Autowired
    SysRoleInfoDAO<SysRoleInfoEntity> sysRoleInfoDAO;

    @Autowired
    SysRoleAuthDAO<SysRoleAuthEntity> roleAuthDAO;
    
    @Autowired
    SysAuthInfoDAO<SysAuthInfoEntity> authInfoDAO;
    
    @Autowired
    SysUserRoleDetailDAO<SysUserRoleDetailEntity> userRoleDetailDAO;
    /**
     * @see 列表分页
     * @param page
     * @param roleName
     * @return
     * @throws Exception
     */
    @Override
	public Page getSysRoleInfoPage(Page page,String roleName)throws Exception{
    	try {
    		int start = page.getPageSize()*(page.getPageNo()-1);
    		int end = page.getPageSize()*page.getPageNo();
    		List<SysRoleInfoVo> list = sysRoleInfoDAO.getSysRoleInfoPage(start,end, roleName);
    		page.setResult(list);
			return page;
		} catch (Exception e) {
			throw e;
		}
    }
    
    /**
     * @see 获取列表
     * @return
     * @throws Exception
     */
	@Override
	public List<SysRoleInfoVo> getSysRoleInfoList()throws Exception {
        try {
			return sysRoleInfoDAO.getSysRoleInfoList();
		} catch (Exception e) {
			throw e;
		}
    }

	
	@Override
	public void save(SysRoleInfoEntity entity,String authList)throws Exception{
		try {
			//保存角色信息
			if(entity.getSysRoleId() > 0)
				sysRoleInfoDAO.update(entity);
			else
				sysRoleInfoDAO.save(entity);
			//保存角色权限信息
			//先删除原有的
			roleAuthDAO.deleteByRoleId(entity.getSysRoleId());
			
			if(authList != null && authList.length() > 0){
				//重新添加
				String[] ids = authList.split(",");
				SysRoleAuthEntity roleAuthEntity=null;
				for (int i = 0; i < ids.length; i++) {
					roleAuthEntity=new SysRoleAuthEntity();
					roleAuthEntity.setAuthId(Integer.parseInt(ids[i]));
					roleAuthEntity.setRoleId(entity.getSysRoleId());
					roleAuthEntity.setCreateTime(ApplicationUtils.formatYMD(new Date()));
					roleAuthDAO.save(roleAuthEntity);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@Override
	public void delete(int id)throws Exception{
		try {
			//先删除权限
			roleAuthDAO.deleteByRoleId(id);
			//删除用户角色
			userRoleDetailDAO.deleteByRoleId(id);
	        //删除角色
			sysRoleInfoDAO.delete(id);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@Override
	public SysRoleInfoEntity get(int id){
		return sysRoleInfoDAO.get(id);
	}
}
