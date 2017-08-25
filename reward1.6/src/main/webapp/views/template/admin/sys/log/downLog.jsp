<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<c:set var="sysPath" value="${pageContext.request.contextPath}" />

<head>
<title></title>
<%
      String savedate=request.getParameter("savedate");
      if(savedate!=null&&!savedate.equals("")){
    	  try{
    		  savedate=savedate.length()>10?savedate.substring(0,10):savedate;
	    	  //连接服务器
	    	  String filename="plsystemlog.log";
	    	  SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	          myFormatter.format(new Date());
	    	  if(myFormatter.format(new Date()).indexOf(savedate)<0){
	    		  filename="plsystemlog.log."+savedate;
	    	  }
	          String url = new File(System.getProperty("catalina.home")) +"/webapps/logs/"+filename;
	         
	          File fl=new File(url);
		  	  if(fl.exists()){
			  		out.clear();   
		  			out = pageContext.pushBody();  
		  			response.reset();
		  			response.setContentType("application/x-msdownload");
		  			response.setHeader("Content-Disposition", "attachment;filename="+filename);
		  			
		  			InputStream instr=new FileInputStream(fl);
		  			BufferedInputStream buff=new BufferedInputStream(instr);
		  			
		  			OutputStream stream=response.getOutputStream();
		  			BufferedOutputStream outstr=new BufferedOutputStream(stream);
		  			
		  			byte[]by=new byte[1024];
		  			int real=0;
		  			while((real=buff.read(by))>0){
		  				outstr.write(by,0,real);
		  			}
		  			
		  		    //注意看以下几句的使用 
		  		    outstr.flush();
		  		    outstr.close();
		  		    outstr=null;
		  			stream.flush();   
		  			stream.close();   
		  			stream=null;   
		  			response.flushBuffer();   
		  			out.clear();   
		  			out = pageContext.pushBody();   
		  			stream.close();
		  			buff.close();
		  			instr.close();
		  		
		  			
		  	  }else{
		  		  %>
		  		      <script type="text/javascript">
		  		           alert("此日期的日志文件不存在，请重新选择.....");
		  		      </script>
		  		  <% 
		  	  }
    	  }catch(Exception e){
    		  %>
    		      <script type="text/javascript">
    		           alert("此日期的日志文件不存在，请重新选择.....");
    		      </script>
    		  <%
    	  }
      }

%>
<script type="text/javascript">
    function onsub(){
         if($("#savedate").val()==""){
              alert("请选择时间...");
              return;
         }
         $("#subform").submit();
    }
</script>
</head>

<body>
	<div>
	    <form action="sys/log/downLog.jsp" id="subform">
	       <div class="pageFormContent" layoutH="528"  style="background-color: #f1f7fa">
	             <p>
	                 <label>选择时间：</label>
			         <input type="text" name="savedate" id="savedate" class="date" readonly="true"/>
				     <a class="inputDateButton" href="javascript:;">选择</a>
				     <input type="button" onclick="onsub()" value="下载"  style="width:60px;"/>
		         </p>
	       </div>
	    </form>
	</div>
</body>
