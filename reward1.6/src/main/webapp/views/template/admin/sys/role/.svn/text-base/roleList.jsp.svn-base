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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webpath }/rs/inner/sysRoleInfo/page" method="get" name="listform">
	<div class="searchBar">
		<table style="width:100%">
			<tr>
				<td width="30%">角色名称：<input type="text" name="key" value="${key }"/></td>
				<td style="text-align: right;">
					<ul>
						<li style="margin-right:50px;"><div class="buttonActive"><div class="buttonContent"><button type="submit" id="searchBnt">检索</button></div></div></li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${webpath }/rs/inner/sysRoleInfo/roleadd" target="dialog" width="600" height="500" title="添加角色" auth="sysRoleAdd"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?"  callback="pageRefush" target="selectedTodo" rel="id" postType="string" href="${webpath }/rs/inner/sysRoleInfo/delete" class="delete"  auth="sysRoleDelete"><span>批量删除</span></a></li>
			<li><a class="edit" href="${webpath }/rs/inner/sysRoleInfo/get?id={sid_user}" target="dialog"  width="600" height="500"  warn="请选择一个用户"  auth="sysRoleEdit"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table"  width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="id" class="checkboxCtrl">编号</th>
				<th width="120">角色名称</th>
				<th width="80" >创建日期</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${restfulResult.page.result }">
	            <tr target="sid_user" rel="${vo.sysRoleId }"> 
	                  <td><input name="id" value="${vo.sysRoleId }" type="checkbox">${vo.sysRoleId }</td> 
	                  <td>${vo.roleName }</td> 
	                  <td>${vo.createTime }</td> 
	                  <td>
	                    <c:if test="${vo.sysRoleId != 1}">   
							<a title="确定要删除吗?" callback="pageRefush" target="ajaxTodo" href="${webpath }/rs/inner/sysRoleInfo/delete?getid=${vo.sysRoleId }" class="btnDel"  auth="sysRoleDelete">删除</a>
						</c:if>
						<a target="dialog"  width="600" height="500"  href="${webpath }/rs/inner/sysRoleInfo/get?id=${vo.sysRoleId }" class="btnEdit" auth="sysRoleEdit">编辑</a>
					  </td>
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
    <c:import url="../../common/_frag/pager/panelBar.jsp"></c:import>
</div>
</body>
</html>