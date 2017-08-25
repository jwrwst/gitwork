<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="plf" uri="http://www.platform.com" %>
<head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

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
	<form rel="pagerForm" onsubmit="return dwzSearch(this, 'dialog');" action="${webpath}/rs/inner/employeeRest/toListPage" method="post" >
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>姓名：</label>
				<input type="text" name="empName" value="${sysEmployeeInfoVo.empName}"/>
			</li>
			<li>
				<label>身份证号：</label>
				<input type="text" name="empIdCard" value="${sysEmployeeInfoVo.empIdCard}"/>
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
	<table class="table"  width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="60">编号</th>
				<th width="80">姓名</th>
				<th width="80" >性别</th>
				<th width="80" >身份证编码</th>
				<th width="80" >手机号</th>
				<th width="80" >工作岗位</th>
				<th width="80" >等级</th>
				<th width="80" >是否在1职</th>
				<th width="80" >操作${emp }</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${emp.page.result }">
	            <tr target="sid" rel="${vo.id}"> 
	                  <td>${vo.id }</td> 
	                  <td>${vo.name}</td> 
	                  <td>
	                      <plf:show from="System.Gender" fixed="${vo.gender}"/>
	                  </td> 
	                  <td>${vo.idcard}</td> 
	                  <td>${vo.mobile}</td>
	                  <td><plf:show from="System.Post" fixed="${vo.postion}"/></td>
	                  <td><plf:show from="System.Employee.Level" fixed="${vo.level}"/></td> 
	                  <td>
	                  	 <c:if test="${vo.empJobable!=false}">是</c:if>
	                  	 <c:if test="${vo.empJobable!=true}">否</c:if>
	                  </td>
	                  <td>
						<a class="btnSelect" href="javascript:$.bringBack({empid:'${vo.id}', realName:'${vo.name}'})" title="查找带回">选择</a>
					  </td>
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
	<c:import url="../../common/_frag/pager/panelBar.jsp"></c:import>
</div>
</body>
</html>