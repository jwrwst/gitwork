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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webpath}/rs/inner/customer/getAll" method="post" >
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>客户编码：</label>
				<input type="text" name="customerKey" value="${vo.customerKey}"/>
			</li>
			<li>
				<label>捞粉名：</label>
				<input type="text" name="name" value="${vo.name}"/>
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
			<li><a class="add" href="${webpath }/rs/inner/customer/toEdit?customerKey=0" rel="page1" target="dialog" width="520" height="300" title="添加" mask="true" auth="sysRoleAdd"><span>添加</span></a></li>
			<li><a class="edit" href="${webpath }/rs/inner/customer/toEdit?customerKey={sid}" rel="page2" target="dialog" width="520" height="300" warn="请选择一条记录" title="修改" mask="true"  auth="sysRoleEdit"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table"  width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="15%">客户编码</th>
				<th width="20%">捞粉名</th>
				<th width="8%" >性别</th>
				<th width="10%" >手机号</th>
				<th width="10%" >会员等级</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${customer.page.result }">
	            <tr target="sid" rel="${vo.customerKey}"> 
	                  <td>${vo.customerKey }</td> 
	                  <td>${vo.name}</td> 
	                  <td>
	                      <plf:show from="System.Gender" fixed="${vo.gender}"/>
	                  </td> 
	                  <td>${vo.mobilePhone}</td>
	                  <td>${vo.memberLevel }</td>
	                  <td>
	                  	<a title="确定要删除吗?" callback="pageRefush" target="ajaxTodo" href="${webpath }/rs/inner/customer/delete?id=${vo.customerKey }" class="btnDel">删除</a>
					  </td>
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
	<c:import url="../common/_frag/pager/panelBar.jsp"></c:import>
</div>
</body>
</html>