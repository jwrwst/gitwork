/**
 * 
 */
package com.platform.rp.common.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.platform.rp.common.application.ApplicationContextUtil;
import com.platform.rp.services.sys.inner.service.ISysCodeInfoService;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;

/**
 * <pre>
 * 
 * Created Date： 2015年7月6日 上午11:57:54
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
public class ShowNiceTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Writer jspWriter;
	
	public String choose;
	public String fixed;
	public String token;
	public String from;
	public String scope;

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public String getFixed() {
		return fixed;
	}

	public void setFixed(String fixed) {
		this.fixed = fixed;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
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
		if(StringUtils.isNotEmpty(fixed)){
			if(StringUtils.isNotEmpty(from)){
				List<SysCodeInfoVo> codes = ApplicationContextUtil.getApplicationContext()
						.getBean(ISysCodeInfoService.class).getByClassvalue(from);
				if(CollectionUtils.isNotEmpty(codes)){
					for(SysCodeInfoVo vo : codes){
						if(fixed.equals(vo.getValue())){
							buffer.append(vo.getName());
						}
					}
				}
			}else {
				if(StringUtils.isEmpty(token)){
					token = ",";
				}
				String[] splits = choose.split(token);
				String[] pairs;
				for(String s : splits){
					pairs = s.split(":");
					if(pairs[0].equals(fixed)){
						buffer.append(pairs[1]);
					}
				}
			}
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
	
//	@Override
//	public int doEndTag() throws JspException {
//		return super.doEndTag();
//	}
}
