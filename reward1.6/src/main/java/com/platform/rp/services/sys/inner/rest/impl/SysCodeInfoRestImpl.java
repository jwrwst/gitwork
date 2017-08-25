/**
 * 
 */
package com.platform.rp.services.sys.inner.rest.impl;

import java.io.Reader;

import org.apache.commons.lang3.math.NumberUtils;
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
import com.platform.rp.services.sys.core.dao.entity.SysCodeClassEntity;
import com.platform.rp.services.sys.core.dao.entity.SysCodeInfoEntity;
import com.platform.rp.services.sys.inner.rest.ISysCodeInfoRest;
import com.platform.rp.services.sys.inner.service.ISysCodeClassService;
import com.platform.rp.services.sys.inner.service.ISysCodeInfoService;
import com.platform.rp.services.sys.inner.vo.SysCodeClassVo;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.Properties;
import com.platform.rp.util.info.codes.CommonCode;

/**
 * <pre>
 * 
 * Created Date： 2015年7月2日 上午11:06:01
 * 
 * Updator：
 * 
 * Updated Date：
 * 
 * Decription：
 * 
 * Version： 1.0.1
 * </pre>
 */
@Controller
public class SysCodeInfoRestImpl extends BaseController implements ISysCodeInfoRest {

	Log log = LogFactory.getLog(SysCodeInfoRestImpl.class);

	@Autowired
	ISysCodeClassService sysCodeClassService;

	@Autowired
	ISysCodeInfoService sysCodeInfoService;
	
	@Autowired
	Properties properties;

	@Override
	public void toCodeListPage(Reader reader) {
		try {
			// 初始化参数
			RequestModel model = new RequestModel();
			ApplicationUtils.parserFormReader(reader, model);
			// System.out.println("--->"+request.getParameter("classifyid"));
			Page codePage = new Page();
			// codePage.setPageNo(model.getPageNum());
			// codePage.setPageSize(model.getNumPerPage());
			if (null != model.getOrderField()) {
				codePage.setOrder(model.getOrderField());
				codePage.setOrderBy(model.getOrderDirection());
			}

			int classifyid = NumberUtils.toInt(request.getParameter("classifyid"), 0);
			String codeName = request.getParameter("codeName");
			codePage = sysCodeInfoService.getListPage(codePage, classifyid, codeName);
			request.setAttribute("codeRestfulResult", new RestfulResult(codePage, CommonCode.SUCCESS));
			request.setAttribute("classifyid", classifyid);
			request.setAttribute("codeName", codeName);

			forward(properties.webUrl+"/views/template/admin/sys/code/codeList.jsp?noload=1");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public void toClassifyListPage(Reader reader) {
		try {
			// 初始化参数
			RequestModel model = new RequestModel();
			ApplicationUtils.parserFormReader(reader, model);

			Page classifyPage = new Page();
			// classifyPage.setPageNo(model.getPageNum());
			// classifyPage.setPageSize(model.getNumPerPage());
			if (null != model.getOrderField()) {
				classifyPage.setOrder(model.getOrderField());
				classifyPage.setOrderBy(model.getOrderDirection());
			}

			classifyPage = sysCodeClassService.getListPage(classifyPage, "");
			request.setAttribute("classifyRestfulResult", new RestfulResult(classifyPage, CommonCode.SUCCESS));

			forward(properties.webUrl+"/views/template/admin/sys/code/codeList.jsp?noload=1");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public void toListPage(Reader reader) {
		try {
			// 初始化参数
			RequestModel model = new RequestModel();
			ApplicationUtils.parserFormReader(reader, model);

			Page classifyPage = new Page();
			// classifyPage.setPageNo(model.getPageNum());
			// classifyPage.setPageSize(model.getNumPerPage());
			if (null != model.getOrderField()) {
				classifyPage.setOrder(model.getOrderField());
				classifyPage.setOrderBy(model.getOrderDirection());
			}

			String classifyName = request.getParameter("classifyName");
			classifyPage = sysCodeClassService.getListPage(classifyPage, classifyName);
			request.setAttribute("classifyRestfulResult", new RestfulResult(classifyPage, CommonCode.SUCCESS));
			request.setAttribute("classifyName", classifyName);

			forward(properties.webUrl+"/views/template/admin/sys/code/classifyList.jsp?noload=1");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public void toClassify(int id) {
		try {
			if (id > 0) {
				SysCodeClassVo classifyVo = new SysCodeClassVo();
				SysCodeClassEntity entity = sysCodeClassService.get(id);
				if (entity != null) {
					BeanCopier copier = BeanCopier.create(SysCodeClassEntity.class, SysCodeClassVo.class, false);
					copier.copy(entity, classifyVo, null);
				}

				request.setAttribute("classifyVo", classifyVo);
			}

			forward(properties.webUrl+"/views/template/admin/sys/code/classifyedit.jsp?noload=1");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public String modifyClassify(Reader reader) {
		try {
			SysCodeClassVo classifyVo = new SysCodeClassVo();
			ApplicationUtils.parserFormReader(reader, classifyVo);

			SysCodeClassEntity entity = new SysCodeClassEntity();
			BeanCopier copier = BeanCopier.create(SysCodeClassVo.class, SysCodeClassEntity.class, false);
			copier.copy(classifyVo, entity, null);

			sysCodeClassService.modify(entity);
		} catch (Exception e) {
			log.info(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.BUSSINESS_BUSY));
		}
		return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
	}

	@Override
	public String deleteClassify(int id) {
		try {
			if (id > 0)
				sysCodeClassService.delete(id);
		} catch (Exception e) {
			log.info(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.BUSSINESS_BUSY));
		}
		return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
	}

	@Override
	public void toCode(int id, int classifyid) {
		try {
			if (id > 0) {
				SysCodeInfoVo codeVo = new SysCodeInfoVo();
				SysCodeInfoEntity entity = sysCodeInfoService.get(id);
				if (entity != null) {
					BeanCopier copier = BeanCopier.create(SysCodeInfoEntity.class, SysCodeInfoVo.class, false);
					copier.copy(entity, codeVo, null);
				}

				request.setAttribute("codeVo", codeVo);
			}

			request.setAttribute("classifyid", classifyid);

			forward(properties.webUrl+"/views/template/admin/sys/code/codeedit.jsp?noload=1");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public String modifyCode(Reader reader) {
		try {
			SysCodeInfoVo classifyVo = new SysCodeInfoVo();
			ApplicationUtils.parserFormReader(reader, classifyVo);

			SysCodeInfoEntity entity = new SysCodeInfoEntity();
			BeanCopier copier = BeanCopier.create(SysCodeInfoVo.class, SysCodeInfoEntity.class, false);
			copier.copy(classifyVo, entity, null);

			sysCodeInfoService.modify(entity);
		} catch (Exception e) {
			log.info(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.BUSSINESS_BUSY));
		}
		return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
	}

	@Override
	public String deleteCode(int id) {
		try {
			if (id > 0)
				sysCodeInfoService.delete(id);
		} catch (Exception e) {
			log.info(e.getMessage());
			return sendResponseResult(new ResponseResult(CommonCode.BUSSINESS_BUSY));
		}
		return sendResponseResult(new ResponseResult(CommonCode.SUCCESS));
	}
}
