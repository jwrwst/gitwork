<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="../js/auth.js" type="text/javascript"></script>
    <script type="text/javascript">
        function pageRefush(t){
	         document.getElementById("searchBnt").click();
        }
        
         $(function(){
            if("${param.noload}"==null||"${param.noload}"==""){
                pageRefush();
            }
        });
    </script>
</head>
<body>

<c:import url="../../common/_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webpath }/rs/pl/roleAuthInfo/page" method="post" name="listform">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>角色名称：</label>
				<input type="text" name="key" value="${key }"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li style="margin-right:50px;"><div class="buttonActive"><div class="buttonContent"><button type="submit" id="searchBnt">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table"  width="100%" layoutH="118">
		<thead>
			<tr>
				<th width="40">编号</th>
				<th width="120">角色名称</th>
				<th width="80" >权限列表</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${restfulResult.data }">
	            <tr> 
	                  <td width="40">${vo.sysRoleId }</td> 
	                  <td width="120">${vo.roleName }</td> 
	                  <th height="80px" style="padding:0px;margin:0px;">
	                       <div style="width:100%;height:80px;overflow: scroll;">
	                          ${vo.authList} 
	                       </div>
	                  </th> 
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
    <c:import url="../../common/_frag/pager/panelBar.jsp"></c:import>
</div>
</body>
</html>