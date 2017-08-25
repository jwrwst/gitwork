/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.platform.rp.services.sys.inner.rest.impl;

import java.io.PrintWriter;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.framework.mvcface.vo.ResponseResult;
import com.platform.rp.framework.mvcface.vo.RestfulResult;
import com.platform.rp.services.sys.SystemContants;
import com.platform.rp.services.sys.core.dao.entity.SysUserInfoEntity;
import com.platform.rp.services.sys.core.dao.entity.SysUserRoleDetailEntity;
import com.platform.rp.services.sys.inner.rest.ISysUserInfoRest;
import com.platform.rp.services.sys.inner.service.ISysRoleInfoService;
import com.platform.rp.services.sys.inner.service.ISysUserInfoService;
import com.platform.rp.services.sys.inner.service.ISysUserRoleDetailService;
import com.platform.rp.services.sys.inner.vo.SysUserInfoVo;
import com.platform.rp.services.sys.inner.vo.SysUserRoleDetailVo;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.Properties;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.spec.Base64;
import com.platform.rp.util.spec.MD5;

/**
 * 
 * @author
 */
@Controller
public class SysUserInfoRestImpl extends BaseController implements ISysUserInfoRest {
	private Log log = LogFactory.getLog(SysUserInfoRestImpl.class);

	@Autowired
	ISysUserInfoService sysUserInfoService;

	@Autowired
	ISysUserRoleDetailService userRoleDetailService;

	@Autowired
	ISysRoleInfoService roleInfoService;
	
	@Autowired
	Properties properties;

	// 用户对象
	SysUserInfoVo sysUserInfoVo;

	@Override
	public void getAll(Reader reader) {
		try {
			Map<String,String> params = ApplicationUtils.populate(reader);
			SysUserInfoVo userVo = new SysUserInfoVo();
			// 初始化参数
			RequestModel model = new RequestModel();
			model.parseFormValue(params, model);
			
			BeanUtils.populate(userVo, params);

			Page page = new Page();
			page.setPageNo(model.getPageNum());
			page.setPageSize(model.getNumPerPage());
			if (null != model.getOrderField()) {
				page.setOrder(model.getOrderField());
				page.setOrderBy(model.getOrderDirection());
			}
			
			page = sysUserInfoService.getSysUserInfoPage(page, userVo.getUserName(), userVo.getRoleName());
			request.setAttribute("restfulResult", new RestfulResult(page, CommonCode.SUCCESS));
			request.setAttribute("key", model.getKey());
			request.setAttribute("userVo", userVo);

			forward(properties.webUrl+"/views/template/admin/sys/user/userList.jsp?noload=1");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public void toListPage(Reader reader) {
		this.getAll(reader);
	}

	/**
	 * 用户登陆
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public SysUserInfoVo login(String name, String password) {
		try {
			SysUserInfoVo entity = sysUserInfoService.getSysUserInfo(name, password);
			if (entity != null) {
				request.getSession().setAttribute("userid", entity.getSysUserId());
				request.getSession().setAttribute("username", entity.getUserName());
				servletContext.setAttribute("webpath", properties.webUrl);

				// 查询角色
				SysUserRoleDetailVo userRoleDetailVo = userRoleDetailService.getUserRoleDetailVo(entity.getSysUserId());
				if (null != userRoleDetailVo) {
					entity.setSysRoleId(userRoleDetailVo.getRoleId());
				}

				return entity;
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}
	
	@Override
	public void userLogin(String name, String password) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			SysUserInfoVo currentUser = sysUserInfoService.getSysUserInfo(name, password);
			int status= 0; //  0. 登陆失败    1.登陆成功   2.当前登陆人不是接线员
			if (currentUser != null) {
				String empPostion = currentUser.getEmpPostion();
				if(empPostion!=null&&empPostion.equals("5")){
			        HttpSession session = request.getSession();
					String encode = currentUser.getUserName() +"_"+ currentUser.getPwd();
					String ssoid =Base64.encode(encode.getBytes());
					System.out.println("ssoid-->"+ ssoid);
					currentUser.setSsoid(ssoid);
					//将登录用户信息保存到Session中，方便系统读取
					session.setAttribute(SystemContants.CURRENT_USER, currentUser);
					jsonMap.put(SystemContants.CURRENT_USER, currentUser);
			        status= 1;
				}else{
					status= 2;
				}
			}else{
				status= 0;
			}
			jsonMap.put("status", status);
			outJSON(jsonMap);
		} catch (Exception e) {
			jsonMap.put("status", 0);
			log.info(e.getMessage());
			try {
				outJSON(jsonMap);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 判断是否用户名密码正确
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public SysUserInfoVo getSysUserInfo(String name, String password) {
		try {
			SysUserInfoVo entity = sysUserInfoService.getSysUserInfo(name, password);
			if (entity != null) {
				// 查询角色
				SysUserRoleDetailVo vo = userRoleDetailService.getUserRoleDetailVo(entity.getSysUserId());
				if (null != vo) {
					entity.setSysRoleId(vo.getRoleId());
				}
				return entity;
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}

	/**
	 * 跳转维护界面(包括：新增、修改)
	 * 
	 * @return
	 */
	@Override
	public String toPage(int id) {
		try {
			if (id > 0) {
				SysUserInfoEntity entity = sysUserInfoService.get(id);
				SysUserInfoVo tmpac = new SysUserInfoVo();
				BeanCopier copier = BeanCopier.create(SysUserInfoEntity.class, SysUserInfoVo.class, false);
				copier.copy(entity, tmpac, null);

				// 查询角色
				SysUserRoleDetailVo userRoleVo = userRoleDetailService.getUserRoleDetailVo(entity.getSysUserId());
				if (null != userRoleVo) {
					tmpac.setSysRoleId(userRoleVo.getRoleId());
				}
				request.setAttribute("sysUserInfoVo", tmpac);
			}

			forward(properties.webUrl+"/views/template/admin/sys/user/useredit.jsp");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return "";
	}

	/**
	 * 重置密码
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public String updatePwd(int id) {
		try {
			SysUserInfoEntity entity = sysUserInfoService.get(id);
			entity.setUpdUserId(request.getSession().getAttribute("userid") + "");
			entity.setUpdTime(ApplicationUtils.formatYMD(new Date(), "yyyy-MM-dd HH:mm:ss"));
			entity.setPwd(new MD5().md5("888888"));
			sysUserInfoService.save(entity);
			return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
		} catch (Exception e) {
			log.info(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.SYS_BUSY));
		}
	}

	/**
	 * 保存
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public String save(Reader reader) {
		try {
			sysUserInfoVo = new SysUserInfoVo();
			ApplicationUtils.parserFormReader(reader, sysUserInfoVo);

			MD5 md = new MD5();
			SysUserInfoEntity entity = sysUserInfoService.get(sysUserInfoVo.getSysUserId());
			SysUserRoleDetailEntity userRoleEntity = new SysUserRoleDetailEntity();
			SysUserRoleDetailVo userRoleVo = null;
			if (entity != null) {
				entity.setPwd(md.md5(sysUserInfoVo.getPwd()));
				entity.setRealName(sysUserInfoVo.getRealName());
				entity.setEmpid(sysUserInfoVo.getEmpid());
//				entity.setUserName(sysUserInfoVo.getUserName());
				entity.setUpdUserId(request.getSession().getAttribute("userid") + "");
				entity.setUpdTime(ApplicationUtils.formatYMD(new Date(), "yyyy-MM-dd HH:mm:ss"));

				userRoleVo = userRoleDetailService.getUserRoleDetailVo(sysUserInfoVo.getSysUserId());

				if(userRoleVo != null){
					BeanCopier copier = BeanCopier.create(SysUserRoleDetailVo.class, SysUserRoleDetailEntity.class, false);
					copier.copy(userRoleVo, userRoleEntity, null);
				}

				//if (null == userRoleEntity) {
					userRoleEntity.setUserId(entity.getSysUserId());
				//}
				userRoleEntity.setRoleId(roleInfoService.get(sysUserInfoVo.getSysRoleId()).getSysRoleId());
				userRoleEntity.setCreateTime(ApplicationUtils.formatYMD(new Date(), "yyyy-MM-dd HH:mm:ss"));
			} else {
				entity = new SysUserInfoEntity();
				entity.setUserName(sysUserInfoVo.getUserName());
				entity.setPwd(md.md5(sysUserInfoVo.getPwd()));
				entity.setRealName(sysUserInfoVo.getRealName());
				entity.setStatus(String.valueOf("1"));
				entity.setEmpid(sysUserInfoVo.getEmpid());
				entity.setUpdUserId(request.getSession().getAttribute("userid") + "");
				entity.setUpdTime(ApplicationUtils.formatYMD(new Date(), "yyyy-MM-dd HH:mm:ss"));
				entity.setCreateUserId(request.getSession().getAttribute("userid") + "");
				entity.setCreateTime(ApplicationUtils.formatYMD(new Date(), "yyyy-MM-dd HH:mm:ss"));

				userRoleEntity = new SysUserRoleDetailEntity();
				userRoleEntity.setRoleId(roleInfoService.get(sysUserInfoVo.getSysRoleId()).getSysRoleId());
				userRoleEntity.setUserId(entity.getSysUserId());
				userRoleEntity.setCreateTime(ApplicationUtils.formatYMD(new Date(), "yyyy-MM-dd HH:mm:ss"));
			}
			sysUserInfoService.save(entity, userRoleEntity);
			return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
		} catch (Exception e) {
			log.info(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.SYS_BUSY));
		}
	}

	/**
	 * 删除用户名密码正确
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public String delete(String id, String getid) {
		try {
			// 批量删除
			if (null != id) {
				String[] ids = id.split(",");
				for (int i = 0; i < ids.length; i++) {
					sysUserInfoService.delete(Integer.parseInt(ids[i]));
				}
			}
			// 单个删除
			if (null != getid) {
				sysUserInfoService.delete(Integer.parseInt(getid));
			}
			return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
		} catch (Exception e) {
			log.info(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.SYS_BUSY));
		}
	}

	/**
	 * 注销当前用户
	 * 
	 * @return
	 */
	@Override
	public void logout() {
		try {
			request.getSession().invalidate();
		} catch (Exception e) {
		}
	}
	
	private  void outJSON( Object object ) throws Exception {
		PrintWriter out =  response.getWriter();
		response.setContentType("text/json;charset=UTF-8");
		ObjectMapper jsonMapper = new ObjectMapper();
		out.write(jsonMapper.writeValueAsString(object));
		out.flush();
	}
	
    @Override
    public void toEmployeePagePost(Reader reader) {
		try {
			Map<String,String> params = ApplicationUtils.populate(reader);
			// 初始化参数
			RequestModel model = new RequestModel();
			model.parseFormValue(params, model);

			Page page = model.getPage();
			
			if (null != model.getOrderField()) {
				page.setOrder(model.getOrderField());
				page.setOrderBy(model.getOrderDirection());
			}

			
			request.setAttribute("restfulResult", new RestfulResult(page,CommonCode.SUCCESS));
			forward(properties.webUrl+"/views/template/admin/sys/user/empList.jsp?noload=1");
		
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
    }

    @Override
    public void toEmployeePage(Reader reader){
    	this.toEmployeePagePost(reader);
    }
}
