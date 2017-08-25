/**
 * 
 */
package com.platform.rp.common.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.platform.rp.common.application.ApplicationContextUtil;
import com.platform.rp.services.sys.inner.service.ISysCodeInfoService;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;

/**
 * <pre>
 * 
 * Created Date： 2015年7月6日 下午2:04:20
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
public class RadioTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Writer jspWriter;
	
	public String name;
	public String classify;
	public String fixed;
	
	public String excludes;
	
	public String wraper;
	
	public void setJspWriter(Writer jspWriter) {
		this.jspWriter = jspWriter;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public void setFixed(String fixed) {
		this.fixed = fixed;
	}

	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}

	public void setWraper(String wraper) {
		this.wraper = wraper;
	}

	/**
	 * 初始化参数
	 */
	public void init(){
		jspWriter = pageContext.getOut();
	}

	/**
	 * 处理界面渲染控制
	 * @return
	 */
	public String doRender(){
		StringBuffer buffer = new StringBuffer();
		ISysCodeInfoService sysCodeInfoService = ApplicationContextUtil.getApplicationContext()
				.getBean(ISysCodeInfoService.class);
		List<SysCodeInfoVo> codes = sysCodeInfoService.getByClassvalue(classify);
		
		for(SysCodeInfoVo vo : codes){
			if(StringUtils.isNotEmpty(wraper))
				buffer.append("<"+ wraper +">");
			
			if(excludes != null && excludes.length() > 0){
				if(excludes.indexOf(vo.getValue()) < 0){
					buffer.append("<input type=\"radio\" name=\"").append(name).append("\" ");
					if(vo.getValue().equals(fixed))
						buffer.append(" checked=\"checked\" ");
					
					buffer.append(" value=\"").append(vo.getValue()).append("\">").append(vo.getName());
				}
			}else{
				buffer.append("<input type=\"radio\" name=\"").append(name).append("\" ");
				if(vo.getValue().equals(fixed))
					buffer.append(" checked=\"checked\" ");
				
				buffer.append(" value=\"").append(vo.getValue()).append("\">").append(vo.getName());
			}
			
			if(StringUtils.isNotEmpty(wraper))
				buffer.append("</"+ wraper +">");
		}
		
		return buffer.toString();
	}
	
	@Override
	public int doStartTag() throws JspException {
		//初始化重要属性
		init();
		
		try {
			jspWriter.write(doRender());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}
	
	/*@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}*/
}
