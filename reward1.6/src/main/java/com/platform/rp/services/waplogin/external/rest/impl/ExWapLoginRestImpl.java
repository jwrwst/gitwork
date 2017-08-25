package com.platform.rp.services.waplogin.external.rest.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItFileInfoEntity;
import com.platform.rp.services.waplogin.external.rest.IExWapLoginRest;
import com.platform.rp.services.waplogin.external.service.IExWapLoginService;
import com.platform.rp.util.Properties;
import com.platform.rp.util.ZipTool;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.info.codes.DataCode;
import com.platform.rp.util.qrcode.impl.MatrixImageServiceImpl;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.result.RestfulResult;

/**
 * 微信调用
 * 
 * @author tangjun
 * 
 *         2015年6月12日
 * 
 */
@Controller
public class ExWapLoginRestImpl extends BaseController implements IExWapLoginRest {

	private static final Logger logger = Logger.getLogger(ExWapLoginRestImpl.class);
		
	@Resource(name="matrixImageService")
	MatrixImageServiceImpl  matrixImageService;
	  
    @Resource(name="systemProperties")
    private Properties properties;
    
    @Autowired
    IExWapLoginService exWapLoginService;

    
    public RestfulResult uploadQrcode(String orderNum){
    	try {
    		ItFileInfoEntity infoEntity=exWapLoginService.uploadQrcode(orderNum);
    		String[] arr=infoEntity.getQrcodeArr();
    		ByteArrayOutputStream stream1 = null;
    		ByteArrayInputStream  input1 = null;
    		String str=null;
    		InputStream[] inputArr=new InputStream[arr.length];;
    		try{    		
	    		for (int i = 0; i < arr.length; i++) {
	    			stream1 = new ByteArrayOutputStream();
	    			str=properties.host+properties.wxLogin+"?state=3&data="+arr[i];

	    			matrixImageService.write(str,"jpg",stream1,new FileInputStream(new File(this.getClass().getClassLoader().getResource("img/tipslogo.png").getPath())) );
	    			
	    			input1 = new ByteArrayInputStream(stream1.toByteArray());
	    			inputArr[i]= input1;
	    			
	    			stream1.flush();
	    			stream1.close();
				}           
    		}finally{
    			if(null!=stream1){
    				stream1.close();
    			}    			
    		}
    		
    		String filename="众赏-"+(infoEntity.getStoreName()==null?"":infoEntity.getStoreName() )+"-"+orderNum;
    		response.reset();			
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gbk"),"iso-8859-1")+".zip");
			response.setContentType("application/octet-stream");
			ZipTool tool = new ZipTool();
			OutputStream outputStream = response.getOutputStream();
			tool.zipFiles(inputArr, outputStream);
			outputStream.flush();
			outputStream.close();
    		
			exWapLoginService.updateItFileInfo(orderNum);
		} catch (Exception e) {
			logger.error(e);
			return RestfulResultProvider.buildCodeResult(DataCode.DATA_NOEXSIST);
		}
    	return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
    	
    }
	
    public RestfulResult uploadLogoQrcode(String orderNum){
    	try {
    		ItFileInfoEntity infoEntity=exWapLoginService.uploadQrcode(orderNum);
    		String[] arr=infoEntity.getQrcodeArr();
    		ByteArrayOutputStream stream1 = null;
    		ByteArrayInputStream  input1 = null;
    		String str=null;
    		InputStream[] inputArr=new InputStream[arr.length];;
    		try{    		
	    		for (int i = 0; i < arr.length; i++) {
	    			stream1 = new ByteArrayOutputStream();
	    			str=properties.host+properties.wxLogin+"?state=3&data="+arr[i];

	    			matrixImageService.write(str,"jpg",stream1,new FileInputStream(new File(this.getClass().getClassLoader().getResource("img/hjlogo.png").getPath())) );
	    			
	    			input1 = new ByteArrayInputStream(stream1.toByteArray());
	    			inputArr[i]= input1;
	    			
	    			stream1.flush();
	    			stream1.close();
				}           
    		}finally{
    			if(null!=stream1){
    				stream1.close();
    			}    			
    		}
    		
    		String filename="众赏-"+(infoEntity.getStoreName()==null?"":infoEntity.getStoreName() )+"-"+orderNum;
    		response.reset();			
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gbk"),"iso-8859-1")+".zip");
			response.setContentType("application/octet-stream");
			ZipTool tool = new ZipTool();
			OutputStream outputStream = response.getOutputStream();
			tool.zipFiles(inputArr, outputStream);
			outputStream.flush();
			outputStream.close();
    		
			exWapLoginService.updateItFileInfo(orderNum);
		} catch (Exception e) {
			logger.error(e);
			return RestfulResultProvider.buildCodeResult(DataCode.DATA_NOEXSIST);
		}
    	return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
    	
    }
	
	
	
}
