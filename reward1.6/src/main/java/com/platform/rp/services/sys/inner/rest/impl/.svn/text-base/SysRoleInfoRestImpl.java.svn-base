/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.platform.rp.services.sys.inner.rest.impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.framework.mvcface.vo.ResponseResult;
import com.platform.rp.framework.mvcface.vo.RestfulResult;
import com.platform.rp.services.sys.core.dao.entity.SysRoleInfoEntity;
import com.platform.rp.services.sys.inner.rest.ISysRoleInfoRest;
import com.platform.rp.services.sys.inner.service.ISysAuthInfoService;
import com.platform.rp.services.sys.inner.service.ISysRoleAuthService;
import com.platform.rp.services.sys.inner.service.ISysRoleInfoService;
import com.platform.rp.services.sys.inner.vo.SysAuthInfoVo;
import com.platform.rp.services.sys.inner.vo.SysRoleAuthVo;
import com.platform.rp.services.sys.inner.vo.SysRoleInfoVo;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.Properties;
import com.platform.rp.util.info.codes.CommonCode;

/**
 *
 * @author
 */
@Controller
public class SysRoleInfoRestImpl extends BaseController implements ISysRoleInfoRest{
	private  Log log=LogFactory.getLog(SysRoleInfoRestImpl.class);
	
    @Autowired
    ISysRoleInfoService sysRoleInfoService;
    
    @Autowired
    ISysAuthInfoService sysAuthInfoService;
    
    @Autowired
    ISysRoleAuthService roleAuthService;
    
	@Autowired
	Properties properties;
    
    //用户对象
    SysRoleInfoVo sysRoleInfoVo;

    public void getAll(Reader reader) {
		try {
			//初始化参数
			RequestModel model=new RequestModel();
    		ApplicationUtils.parserFormReader(reader,model);
    		
			Page page=new Page();
			page.setPageNo(model.getPageNum());
			page.setPageSize(model.getNumPerPage());
			if(null!=model.getOrderField()){
				page.setOrder(model.getOrderField());
				page.setOrderBy(model.getOrderDirection());
			}
			
			page=sysRoleInfoService.getSysRoleInfoPage(page, model.getKey());
			request.setAttribute("restfulResult", new RestfulResult(page,CommonCode.SUCCESS));
			request.setAttribute("key", model.getKey());
			
			forward(properties.webUrl+"/views/template/admin/sys/role/roleList.jsp?noload=1");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
    }
    
    /**
     * 列表界面跳转
     */
    public void toListPage(Reader reader) {
    	getAll(reader);
    }
	
	/**
	 * 获取角色列表
	 * @return
	 */
    public String getList() {
		List<SysRoleInfoVo> volist=new ArrayList<SysRoleInfoVo>();
		try {
			volist=sysRoleInfoService.getSysRoleInfoList();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return sendResponseArrayResult(volist);
    }
    
    
    /**
     * 根据编号获取对象
     * @param name
     * @param password
     * @return
     */
    public String get(int id) {
    	try {
    		//角色信息
    		SysRoleInfoEntity entity=sysRoleInfoService.get(id);
    		SysRoleInfoVo tmpac =new SysRoleInfoVo();
    		BeanCopier copier = BeanCopier.create(SysRoleInfoEntity.class, SysRoleInfoVo.class, false);
    		copier.copy(entity, tmpac, null);
    		//功能列表
    		List<SysAuthInfoVo> authInfoList=sysAuthInfoService.getSysAuthInfoList();
    		//获取父编号集合
    		Map<Integer, Integer> mapParent=new HashMap<Integer, Integer>();
    		for (SysAuthInfoVo sysAuthInfoVo : authInfoList) {
				mapParent.put(sysAuthInfoVo.getParentId(), sysAuthInfoVo.getParentId());
			}
    		//权限列表
    		List<SysRoleAuthVo> roleAuthList=roleAuthService.getRoleAuthList(id);
    		//判断当前用户有哪些权限，设置操作功能菜单为选中状态
            for (SysAuthInfoVo sysAuthInfoVo : authInfoList) {
            	for (SysRoleAuthVo roleAuthlVo : roleAuthList) {
    				if(sysAuthInfoVo.getSysAuthId()==roleAuthlVo.getAuthId()&&sysAuthInfoVo.getAuthType().equals("03")){
    					sysAuthInfoVo.setIscheck("true");
    				}
    				if(sysAuthInfoVo.getSysAuthId()==roleAuthlVo.getAuthId()&&!mapParent.containsKey(roleAuthlVo.getAuthId())){
    					sysAuthInfoVo.setIscheck("true");
    				}
    			}
			}
    		
    		request.setAttribute("sysRoleInfoVo", tmpac);
    		request.setAttribute("authInfoList", authInfoList);
    		
    		forward(properties.webUrl+"/views/template/admin/sys/role/roleedit.jsp");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
    	return "";
    }
    
	/**
     * 获取当前用户所拥有的权限
     * @param id
     * @return
     */
    public String getCurrentAuth(int id) {
    	try {
    		//权限列表
    		List<SysRoleAuthVo> roleAuthList = roleAuthService.getRoleAuthList(id);
    		
    		request.getSession().setAttribute("userAuthList", roleAuthList);
    		request.getSession().setAttribute("userAuthJSON", sendResponseArrayResult(roleAuthList));
    		
    		sendRedirect(properties.webUrl+"/views/template/admin/index.jsp");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
    	return "";
    }
    
    /**
     * @see 保存
     * @return
     */
    public String save(Reader reader) {
    	try {
    		sysRoleInfoVo=new SysRoleInfoVo();
    		ApplicationUtils.parserFormReader(reader,sysRoleInfoVo);
    		
    		SysRoleInfoEntity entity =sysRoleInfoService.get(sysRoleInfoVo.getSysRoleId());
    		if(entity!=null){
    		    entity.setRoleName(sysRoleInfoVo.getRoleName());
    			entity.setUpdUserId(request.getSession().getAttribute("userid")+"");
    			entity.setUpdTime(ApplicationUtils.formatYMD(new Date(), "yyyy-MM-dd HH:mm:ss"));
    		}else{
	    		entity=new SysRoleInfoEntity();
	    		entity.setRoleName(sysRoleInfoVo.getRoleName());
    			entity.setUpdUserId(request.getSession().getAttribute("userid")+"");
	    		entity.setUpdTime(ApplicationUtils.formatYMD(new Date(), "yyyy-MM-dd HH:mm:ss"));
	    		entity.setCreateUserId(request.getSession().getAttribute("userid")+"");
	    		entity.setCreateTime(ApplicationUtils.formatYMD(new Date(), "yyyy-MM-dd HH:mm:ss"));
	    		
    		}
    		sysRoleInfoService.save(entity,sysRoleInfoVo.getAuthList());
    		return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
		} catch (Exception e) {
			log.info(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.SYS_BUSY));
		}
    }
    
    /**
     * @see 删除
     * @param id
     * @param getid
     * @return
     */
    public String delete(String id,String getid) {
    	try {
    		//批量删除
    		if(null!=id){
	    		String[] ids=id.split(",");
	    		for (int i = 0; i < ids.length; i++) {
	    			sysRoleInfoService.delete(Integer.parseInt(ids[i]));
				}
    		}
    		//单个删除
    		if(null!=getid){
    			sysRoleInfoService.delete(Integer.parseInt(getid));
    		}
    		return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
		} catch (Exception e) {
			log.info(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.SYS_BUSY));
		}
    }
	
	/**
	 * @see 转向添加页面
	 */
	public String roleadd(){
		try {
			//功能列表
    		List<SysAuthInfoVo> authInfoList=sysAuthInfoService.getSysAuthInfoList();
    		request.setAttribute("authInfoList", authInfoList);
    		forward(properties.webUrl+"/views/template/admin/sys/role/roleadd.jsp");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return "";
	}
    
}
