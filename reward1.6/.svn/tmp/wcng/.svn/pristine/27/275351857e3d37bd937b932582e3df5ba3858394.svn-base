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
        
        function sync(){
        	if(confirm("确定同步门店信息吗？")){
        		$.ajax({
        			url: '${webpath }/rs/inner/storeInfo/sync',
        			type: 'get',
        	        cache: false,
        	        success: function(result) {
        	        	if(result.statusCode == '0000')
        	        		alert('同步成功！');
        	        	else
        	        		alert('同步异常。'+ JSON.parse(result));
        	        }
        		});
        	}
        }
    </script>
</head>
<body>

<c:import url="../common/_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form  rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webpath }/rs/inner/storeInfo/page" method="post" name="listform">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>门店名称：</label>
				<input type="text" name="name" value="${name }"/>
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
			<li><a class="add" href="${webpath }/rs/inner/storeInfo/get?id=0" target="dialog" width="600" height="300" title="添加信息" auth="sysRoleAdd"><span>增加</span></a></li>
			<li><a class="edit" href="${webpath }/rs/inner/storeInfo/get?id={id}" target="dialog" width="600" height="300" warn="请选择一个门店信息" auth="stroeEdit"><span>修改</span></a></li>
			<li><a class="edit" href="javascript:sync();"><span>门店同步</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table"  width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="id" class="checkboxCtrl">编号</th>
				<th width="8%">门店编码</th>
				<th width="12%">门店名称</th>
				<th width="10%">所在地区</th>
				<th>详细地址</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${restfulResult.page.result }" varStatus="status">
	                <tr target="id" rel="${vo.id }"> 
	                  <td><input name="id" value="${vo.id }" type="checkbox">${status.index+1 }</td> 
	                  <td>${vo.storeCode }</td> 
	                  <td>${vo.name }</td> 
	                  <td>${vo.areaName }</td> 
	                  <td>${vo.address }</td> 
	                  <td>
					    <a title="确定要删除吗?" callback="pageRefush" target="ajaxTodo" href="${webpath }/rs/inner/storeInfo/delete?id=${vo.id }" class="btnDel"  auth="sysUserDelete">删除</a>
						<a target="dialog" width="600" height="300" href="${webpath }/rs/inner/storeInfo/get?id=${vo.id }" class="btnEdit" auth="storeInfoEdit">修改</a>
					  </td>
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
    <c:import url="../common/_frag/pager/panelBar.jsp"></c:import>
</div>
</body>
</html>