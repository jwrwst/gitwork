package com.platform.rp.framework.mvcface.rest;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.platform.rp.util.Constant;
import com.platform.rp.util.Properties;

public class UploadFileAction extends ActionSupport {
	protected Log logger = LogFactory.getLog(getClass());
	
	protected File upload;
	protected String uploadContentType;
	protected String uploadFileName;

    @Resource(name="systemProperties")
    protected Properties properties;
    
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {

		this.uploadFileName = uploadFileName;
	}
	
	private static String ROOT_PATH = "uploadfile";
	public String copyFile(String rootPath) {
		// ServletActionContext.getRequest().getRealPath(path)
		
		//String path = getSession().getServletContext().getRealPath(ROOT_PATH);

		String path = properties.uploadfile+"/"+ROOT_PATH;
		logger.debug(path);
		if(StringUtils.isNotEmpty(rootPath)){
			//rootPath = "/"+rootPath;
		}else{
			rootPath = "";
		}
		String newPath = rootPath+ "/" +System.currentTimeMillis();
		File file = new File(path + "/" + newPath);
		file.mkdirs();
		try {
			//newPath = newPath + "/" + uploadFileName;
			Constant.COUNT += new Random().nextInt(100);
			String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
			newPath = newPath + "/" + Constant.COUNT+fileType;
			file = new File(path + "/"+ newPath);
			file.createNewFile();
			FileUtils.copyFile(upload, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ROOT_PATH+"/"+newPath;
	}
	protected HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	public static void main(String[] args) {
		String name = "111.jpn";
		name = name.substring(name.indexOf("."));
		System.out.println(name);
		System.out.println(new Random().nextInt(10));
	}
}
