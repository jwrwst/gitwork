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

<c:import url="../common/_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webpath}/rs/inner/employeeRest/list" method="post" >
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>姓名：</label>
				<input type="text" name="name" value="${vo.name}" alt="请输入名称"/>
			</li>
			<li>
				<label>身份证号：</label>
				<input type="text" name="idcard" value="${vo.idcard}" alt="请输入身份证号"/>
			</li>
			<li>
				<label>所在部门：</label>
				<input type="text" name="vo.storeName" value="${vo.storeName }" suggestFields="storeName" lookupGroup="vo"/>
				<a class="btnLook" style="float: left"  target="dialog" width="920" height="350"  href="${webpath }/rs/inner/storeInfo/toStoreLookup" lookupGroup="vo">查询部门信息</a>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li style="margin-right:50px;"><div class="buttonActive"><div class="buttonContent"><button type="submit" id="searchBnt">检&nbsp;&nbsp;索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${webpath }/rs/inner/employeeRest/toEdit?id=0" rel="page1" target="dialog" width="920" height="450" title="添加" mask="true" auth="sysRoleAdd"><span>添加</span></a></li>
			<li><a class="edit" href="${webpath }/rs/inner/employeeRest/toEdit?id={sid}" rel="page2" target="dialog" width="920" height="450" warn="请选择一条记录" title="修改" mask="true"  auth="sysRoleEdit"><span>修改</span></a></li>
			<li><a class="edit" href="${webpath }/rs/inner/employeeRest/toHeadPic?id={sid}" rel="page2" target="dialog" width="350" height="340" warn="请选择一条记录" title="上传个人头像" mask="true"  auth="sysRoleEdit"><span>个人头像</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table"  width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="60"><input type="checkbox" group="id" class="checkboxCtrl">编号</th>
				<th width="80">姓名</th>
				<th width="80">工号</th>
				<th width="80">昵称</th>
				<th width="80" >性别</th>
				<th width="80" >身份证编码</th>
				<th width="80" >手机号</th>
				<th width="80" >工作岗位</th>
				<th width="80" >等级</th>
				<th width="80" >是否在职</th>
				<th width="80" >门店名称</th>
				<th width="80" >操作</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${emp.page.result }" varStatus="loop">
	            <tr target="sid" rel="${vo.id}"> 
	                  <td><input name="id" value="${vo.id }" type="checkbox">${loop.index + 1 }</td> 
	                  <td>${vo.name}</td> 
	                  <td>${vo.jobNumber}</td> 
	                  <td>${vo.nickname}</td> 
	                  <td>
	                      <plf:show from="System.Gender" fixed="${vo.gender}"/>
	                  </td> 
	                  <td>${vo.idcard}</td> 
	                  <td>${vo.mobile}</td>
	                  <td><plf:show from="System.Post" fixed="${vo.postion}"/></td>
	                  <td><plf:show from="System.Employee.Level" fixed="${vo.level}"/></td> 
	                  <td>
	                  	 <c:if test="${vo.jobable!=false}">是</c:if>
	                  	 <c:if test="${vo.jobable!=true}">否</c:if>
	                  </td>
					  <td>${vo.storeName}</td>
	                  <td>
	                  	<a title="确定要删除吗?" callback="pageRefush" target="ajaxTodo" href="${webpath }/rs/inner/employeeRest/delete?id=${vo.id }" class="btnDel"  auth="sysRoleDelete">删除</a>
						<a target="dialog" rel="page2" width="920" height="450" href="${webpath }/rs/inner/employeeRest/toEdit?id=${vo.id }" class="btnEdit" auth="sysRoleEdit">编辑</a>
					  </td>
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
	<c:import url="../common/_frag/pager/panelBar.jsp"></c:import>
</div>
</body>
</html>