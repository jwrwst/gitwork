package com.platform.rp.services.template.external.common.ex;

import javax.servlet.ServletContext;

import org.apache.struts2.views.freemarker.FreemarkerManager;

import com.platform.rp.services.template.external.common.directivemodel.CoreDateFormatModel;
import com.platform.rp.services.template.external.common.directivemodel.CoreDirectiveModel;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class FreemarkerManagerEx extends FreemarkerManager {

	public static final String LABEL_CATEGORY_LIST = "mc_core";
	
	public static final String DATE_FORMAT="coreDateFormat";
	
	@Override
	protected Configuration createConfiguration(ServletContext servletContext)
			throws TemplateException {
		Configuration configuration=super.createConfiguration(servletContext);
		configuration.setSharedVariable(LABEL_CATEGORY_LIST, new CoreDirectiveModel());
		configuration.setSharedVariable(DATE_FORMAT, new CoreDateFormatModel());
		return configuration;
	}
}
