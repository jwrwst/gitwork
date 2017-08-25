<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webpath }/rs/inner/sysUserInfo/getSysUserInfoList" method="post" name="listform">
	<div class="searchBar">
		<!--  
		<ul class="searchContent">
			<li>
				<label>用户名：</label>
				<input type="text" name="key" value="${key }"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li style="margin-right:50px;"><div class="buttonActive"><div class="buttonContent"><button type="submit" id="searchBnt">检索</button></div></div></li>
			</ul>
		</div>
		-->
		<table style="width:100%">
			<tr>
				<td width="30%">用户名：<input type="text" name="userName" value="${userVo.userName }"/></td>
				<td width="30%">所属角色：<input type="text" name="roleName" value="${userVo.roleName }"/></td>
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
			<li><a class="add" rel="add" href="${webpath }/rs/inner/sysUserInfo/toPage" target="dialog"  title="添加用户" auth="sysUserAdd"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?"  callback="pageRefush" target="selectedTodo" rel="id" postType="string" href="${webpath }/rs/inner/sysUserInfo/delete" class="delete" auth="sysUserDelete"><span>批量删除</span></a></li>
			<li><a class="edit" rel="edit" href="${webpath }/rs/inner/sysUserInfo/toPage?id={sid_user}" target="dialog" warn="请选择一个用户" auth="sysUserEdit"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="${webpath }/rs/pl/sysUserInfo/resetPwd?id={sid_user}"  target="ajaxTodo" auth="sysUserEdit" callback="pageRefush" title="确定要重置密码?" warn="请选择一个用户"><span>重置密码</span></a></li>
		</ul>
	</div>
	<table class="table"  width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="id" class="checkboxCtrl"></th>
				<th width="120" orderField="accountNo" class="asc">用户名</th>
				<th >中文名称</th>
				<th >所属角色</th>
				<th width="80" orderField="accountType">创建日期</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${restfulResult.page.result }">
	            <tr target="sid_user" rel="${vo.sysUserId }"> 
	                  <td><input name="id" value="${vo.sysUserId }" type="checkbox">${vo.sysUserId }</td> 
	                  <td>${vo.userName }</td> 
	                  <td>${vo.realName }</td> 
	                  <td>${vo.roleName }</td> 
	                  <td>${vo.createTime }</td> 
	                  <td>
	                    <c:if test="${vo.userName != 'superadmin'}">
						    <a title="确定要删除吗?" callback="pageRefush" target="ajaxTodo" href="${webpath }/rs/inner/sysUserInfo/delete?getid=${vo.sysUserId }" class="btnDel"  auth="sysUserDelete">删除</a>
						</c:if>
						<a target="dialog" rel="s" href="${webpath }/rs/inner/sysUserInfo/toPage?id=${vo.sysUserId }" class="btnEdit" auth="sysUserEdit">编辑</a>
					  </td>
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
    <c:import url="../../common/_frag/pager/panelBar.jsp"></c:import>
</div>
</body>
</html>