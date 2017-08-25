package com.platform.rp.util.qrcode.rest.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.util.StringUtils;
import com.platform.rp.util.qrcode.impl.MatrixImageServiceImpl;
import com.platform.rp.util.qrcode.rest.IQrCodeRest;

/**
 * 二维码生成
 * @author tangjun
 * 
 *         2015年6月12日
 * 
 */
@Controller
public class QrCodeRestImpl extends BaseController implements IQrCodeRest {

	private static final Logger logger = Logger.getLogger(QrCodeRestImpl.class);

	@Context
	HttpServletResponse response;

	@Context
	HttpServletRequest request;
	
	@Resource(name="matrixImageService")
	MatrixImageServiceImpl  matrixImageService;
	
	@Override
	public void qrcode(String txt) {
		 try{
			 if(StringUtils.isEmpty(txt)){
				 return;
			 }
			logger.info("生成二维码图片："+txt);
            ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
            String str=new String(txt.getBytes("ISO8859-1"),"utf-8");
            matrixImageService.write(str,"jpg",stream1,new FileInputStream(new File(this.getClass().getClassLoader().getResource("img/tipslogo.png").getPath())));
            response.setContentType("image/png");

            OutputStream stream = response.getOutputStream();
            stream.write(stream1.toByteArray());
            stream.flush();
            stream.close();
        }catch(IOException e){
            e.printStackTrace();
            logger.error(e);
        }
		
	}
	
	public static void main(String[] args) {
		System.out.println(QrCodeRestImpl.class.getClassLoader().getResource("key/apiclient_cert.p12").getPath());
	}
	
}
