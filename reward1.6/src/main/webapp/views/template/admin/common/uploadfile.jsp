<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%-- <%@page import="com.jspsmart.upload.SmartUpload,java.io.File"%>  --%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- <% 
        String parentPath=request.getParameter("path");
        String filename="";
		try {
			SmartUpload mySmartUpload =new SmartUpload();
			mySmartUpload.initialize(config, request, response); 
			//mySmartUpload.setAllowedFilesList("txt,xls,xlsx,csv");
			// 上载form(表单值和文件)
			mySmartUpload.upload("utf-8");
			
			//子文件包名
			String levelName=mySmartUpload.getRequest().getParameter("levelname");
			if(null==levelName){levelName="";}
			
			//上传文件
			String setting="/home/tomcat";
			File newfile=new File(new File(request.getRealPath("/"))+setting );
			if(!newfile.exists()){
				newfile.mkdirs();
		    }
			String path=newfile.getAbsolutePath();
			mySmartUpload.save(path);
			
			//获取文件信息
			com.jspsmart.upload.File file =mySmartUpload.getFiles().getFile(0);
			//文件全路径
			filename=path.replace('\\', '/')+"/"+file.getFileName();
			response.getWriter().write(setting.replace('\\', '/')+"/"+file.getFileName());
		
		    //重命名文件
		    //SimpleDateFormat sdf   =  new SimpleDateFormat("yyyyMMdd_HHmmss"); 
		    //String datefile=sdf.format(new Date())+"."+file.getFileExt();
		    //file.saveAs( path  +File.separator+ datefile  ) ; 
			//response.getWriter().write(setting.replace('\\', '/')+"/"+datefile);
			
		} catch (Exception ex) {
			response.getWriter().write("文件格式不正确，上传失败！");
		}
	%> --%>