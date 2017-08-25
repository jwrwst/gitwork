/**
 * 
 */
package com.platform.rp.common.application;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * <pre>
 * 
 * Created Date： 2015年7月8日 下午2:38:19
 * 
 * Updator：
 * 
 * Updated Date：
 * 
 * Decription：
 * 		初始化系统各种参数
 * Version： 1.0.1
 * </pre>
 */
public class SystemInitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		String contextPath = getContextPath();
		
		//初始化加载路劲
		initSystemConfig(contextPath);
	}
	
	/**
	 * 初始化加载路劲
	 * @param contextPath
	 */
	public void initSystemConfig(String contextPath){
		String systemConfigFile = this.getInitParameter("systemConfigFile");
		SystemConfig.initConfigFilePath(contextPath + File.separator + systemConfigFile);
	}
	
	/**
	 * 获取工程路径
	 * @return
	 */
	public String getContextPath(){
		String contextPath = getServletContext().getRealPath("/");
        if (contextPath.length() > 0 && (contextPath.endsWith("/") || contextPath.endsWith("\\")))
            contextPath = contextPath.substring(0, contextPath.length() - 1);

        return contextPath;
	}
}
