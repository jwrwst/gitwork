package com.platform.rp.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.Logger;

public class PropertyUtil {
	private static Logger logger = Logger.getLogger(PropertyUtil.class);
	
	private static String propFile = "wx_menu.properties";
	private static String tokenPropFile = "token.properties";
	public static String DIGEST_PC = "";
	public static String DIGEST_SP = "";
	public static String JASPER_DIR = "";
	public static String JASPER_IMAGES_DIR = "";
	public static String DB_PASSWORD_EN = "";
	public static String SP_UPLOAD_FILEPATH = "";
	public static String ZIP_FILEPATH_NIGHT = "";
	
	public static Properties token = null;
	
	static {
		try {
			InputStream tis = Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream(tokenPropFile);
	        if (tis == null) {
	        	tis = PropertyUtil.class.getResourceAsStream("/" + tokenPropFile);
	        }
	        token = new Properties();
	        token.load(tis);
	        
			InputStream is = Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream(propFile);
	        if (is == null) {
	        	is = PropertyUtil.class.getResourceAsStream("/" + propFile);
	        }
	        Properties p = new Properties();
	        p.load(is);
	        DIGEST_PC = p.getProperty("authe.digest.pc");
	        DIGEST_SP = p.getProperty("authe.digest.sp");
	        JASPER_DIR = p.getProperty("jasper.dir") + SystemUtils.FILE_SEPARATOR;
	        JASPER_IMAGES_DIR = p.getProperty("jasper.images.dir") + SystemUtils.FILE_SEPARATOR;
	        DB_PASSWORD_EN = p.getProperty("db.password.enhance");
	        SP_UPLOAD_FILEPATH = p.getProperty("sp.upload.filepath");
	        ZIP_FILEPATH_NIGHT = p.getProperty("zipFilePathNight");
	        
	        is.close();
		} catch (Exception e) {
			logger.error("PropertyUtil error",e);
		}
		
	}
	
}
