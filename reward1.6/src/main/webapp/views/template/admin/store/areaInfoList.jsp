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

<c:import url="../common/_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form  rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webpath }/rs/inner/storeInfo/getAreaAll" method="post" name="listform">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>地区名称：</label>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${webpath }/rs/inner/storeInfo/areaAdd" target="dialog" width="450" height="250" title="添加信息" auth="sysRoleAdd"><span>增加</span></a></li>
			<li><a title="确实要删除这些记录吗?"  callback="pageRefush" target="selectedTodo" rel="id" postType="string" href="${webpath }/rs/inner/storeInfo/deleteArea" class="delete"  auth="sysRoleDelete"><span>删除</span></a></li>
			<li><a class="edit" href="${webpath }/rs/inner/storeInfo/getArea?id={id}" target="dialog"  width="450" height="250"  warn="请选择一个地区信息"  auth="stroeEdit"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table"  width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="id" class="checkboxCtrl">编号</th>
				<th width="120">地区编码</th>
				<th width="80" >地区名称</th>
				<th width="80" >上级地区</th>
				<!-- <th width="70">地区级别</th> -->
				<th width="70">成立时间</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${restfulResult.page.result }" varStatus="status">
	                <tr target="id" rel="${vo.id }"> 
	                  <td><input name="id" value="${vo.id}" type="checkbox">${status.index+1 }</td> 
	                  <td>${vo.code }</td> 
	                  <td>${vo.name }</td> 
	                  <td>${vo.parentname }</td> 
	                 <%--  <td>${vo.level }</td>  --%>
	                  <td>${vo.createdtime }</td>
	                  <td>
					    <a title="确定要删除吗?" callback="pageRefush" target="ajaxTodo" href="${webpath }/rs/inner/storeInfo/deleteArea?getid=${vo.id }" class="btnDel"  auth="sysUserDelete">删除</a>
						<a target="dialog" href="${webpath }/rs/inner/storeInfo/getArea?id=${vo.id }" class="btnEdit" auth="areaEdit">修改</a>
					  </td> 
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
    <c:import url="../common/_frag/pager/panelBar.jsp"></c:import>
</div>
</body>
</html>