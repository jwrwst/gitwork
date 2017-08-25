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
 * Created Date： 2015年7月6日 上午10:19:05
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
public class SelectTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Writer jspWriter;
	
	public String name;
	public String classify;
	public String fixed;
	
	public String style;
	public String clazz;
	
	public String excludes;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getExcludes() {
		return excludes;
	}

	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public void setFixed(String fixed) {
		this.fixed = fixed;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
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
		
		ISysCodeInfoService sysCodeInfoService = ApplicationContextUtil.getApplicationContext()
				.getBean(ISysCodeInfoService.class);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<select id=\"").append(name).append("\" name=\"").append(name).append("\"");
		if(StringUtils.isNotEmpty(style)){
			buffer.append(" style=\"").append(style).append("\"");
		}
		if(StringUtils.isNotEmpty(clazz)){
			buffer.append(" class=\"").append(clazz).append("\"");
		}
		buffer.append(">")
		.append("<option value=\"\">-----请选择-----</option>");
		
		List<SysCodeInfoVo> codes = sysCodeInfoService.getByClassvalue(classify);
		for(SysCodeInfoVo vo : codes){
			if(excludes != null && excludes.length() > 0){
				if(excludes.indexOf(vo.getValue()) < 0){
					buffer.append("<option ");
					if(vo.getValue().equals(fixed))
						buffer.append(" selected=\"true\" ");
					
					buffer.append(" value=\"").append(vo.getValue()).append("\">").append(vo.getName()).append("</option>");
				}
			}else{
				buffer.append("<option ");
				if(vo.getValue().equals(fixed)){
					buffer.append(" selected=\"true\" ");
				}
				buffer.append(" value=\"").append(vo.getValue()).append("\">").append(vo.getName()).append("</option>");
			}
		}
		
		buffer.append("</select>");
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
	
//	@Override
//	public int doEndTag() throws JspException {
//		return super.doEndTag();
//	}
}
