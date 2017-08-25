<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="../js/auth.js" type="text/javascript"></script>
    <script type="text/javascript">
        function pageRefush(t){
        	$("#classifyContainer").loadUrl('${webpath }/rs/inner/sysCodeInfo/toListPage',{});
        }
        
        function loadCode(){
        	$('#codeContainer').loadUrl('${webpath }/rs/inner/sysCodeInfo/toCodeListPage',{
        		classifyid: $('#globalClassifyid').val(),
        		codeName: $('#codeName').val()
        	});
        }
    </script>
</head>
<body>

	<div class="panelBar" style="position: relative;">
		<ul class="toolBar">
			<li><b style="line-height:25px;">字典管理</b></li>
			<li><a class="add" href="${webpath }/rs/inner/sysCodeInfo/toCode?classifyid=${classifyid}" target="dialog" width="550" height="350" title="添加字典" mask="true" auth="sysRoleAdd"><span>添加</span></a></li>
			<li><a class="edit" href="${webpath }/rs/inner/sysCodeInfo/toCode?id={sid_code}" target="dialog" width="550" height="350" warn="请选择一个用户" title="修改字典" mask="true"  auth="sysRoleEdit"><span>修改</span></a></li>
			<li class="line">line</li>
			<li>
				<table>
					<tr>
						<td>字典名称：</td>
						<td><input type="text" name="codeName" id="codeName" value="${codeName }"/></td>
						<td style="padding-left: 5px;">
						<a class="edit" href="javascript:loadCode();" auth="sysRoleEdit"><span>检索</span></a>
						</td>
					</tr>
				</table>
			</li>
		</ul>
	</div>
	<table class="table" style="width:100%;">
		<thead>
			<tr>
				<th width="8%"><input type="checkbox" group="codeid" class="checkboxCtrl">排序序号</th>
				<th width="30%">字典名称</th>
				<th width="30%">字典值</th>
				<th width="10%">是否启用</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${codeRestfulResult.page.result }" varStatus="loop">
	            <tr target="sid_code" rel="${vo.id }"> 
	                  <td><input name="codeid" value="${vo.id }" type="checkbox">${vo.sorter }</td> 
	                  <td>${vo.name }</td> 
	                  <td>${vo.value }</td> 
	                  <td>${vo.useable ? "启用" : "未启用" }</td> 
	                  <td>
						<a title="确定要删除吗?" callback="loadCode" rel="codeContainer" target="ajaxTodo" href="${webpath }/rs/inner/sysCodeInfo/deleteCode?id=${vo.id }" class="btnDel"  auth="sysRoleDelete">删除</a>
						<a target="dialog" width="550" height="350" href="${webpath }/rs/inner/sysCodeInfo/toCode?id=${vo.id }" title="编辑字典" class="btnEdit" auth="sysRoleEdit">编辑</a>
					  </td>
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
</body>
</html>