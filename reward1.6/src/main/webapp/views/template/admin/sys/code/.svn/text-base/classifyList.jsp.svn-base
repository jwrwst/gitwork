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
        	$("#classifyContainer").loadUrl('${webpath }/rs/inner/sysCodeInfo/',{});
        }
				        
        function changeClassify(target){
        	$('#globalClassifyid').val($(target).attr('rel'));
        	$('#codeContainer').loadUrl('${webpath }/rs/inner/sysCodeInfo/toCodeListPage',{
        		classifyid: $(target).attr('rel')
        	});
        }
        
        function loadClassify(){
        	$('#classifyContainer').loadUrl('${webpath }/rs/inner/sysCodeInfo/toListPage', {
        		classifyName: $('#classifyName').val()
        	});
        }
    </script>
</head>
<body>
<input type="hidden" id="globalClassifyid" />
<div class="unitBox" id="classifyContainer" style="height:240px;overflow-y: auto;">
	<div class="panelBar">
		<ul class="toolBar">
			<li><b style="line-height:25px;">分类管理</b></li>
			<li><a class="add" href="${webpath }/rs/inner/sysCodeInfo/toClassify" target="dialog" width="540" height="300" title="添加分类" mask="true" auth="sysRoleAdd"><span>添加</span></a></li>
			<li><a class="edit" href="${webpath }/rs/inner/sysCodeInfo/toClassify?id={sid_classify}" target="dialog" width="540" height="300" warn="请选择一个分类" title="修改分类" mask="true"  auth="sysRoleEdit"><span>修改</span></a></li>
			<li class="line">line</li>
			<li>
				<table>
					<tr>
						<td>分类名称：</td>
						<td><input type="text" name="classifyName" id="classifyName" value="${classifyName }"/></td>
						<td style="padding-left: 5px;">
						<a class="edit" href="javascript:loadClassify();" auth="sysRoleEdit"><span>检索</span></a>
						</td>
					</tr>
				</table>
			</li>
		</ul>
	</div>
	<table class="table" style="width:100%; overflow:scroll;" id="classifyTable">
		<thead>
			<tr>
				<th width="8%"><input type="checkbox" group="classifyid" class="checkboxCtrl">编号</th>
				<th width="30%">分类名称</th>
				<th width="30%">分类值</th>
				<th width="10%">是否启用</th>
				<!--  
				<th width="80" >修改日期</th>
				-->
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach var="vo" items="${classifyRestfulResult.page.result }" varStatus="loop">
	            <tr target="sid_classify" rel="${vo.id }" clickCallback="changeClassify(this)"> 
	                  <td><input name="classifyid" value="${vo.id }" type="checkbox">${loop.index + 1 }</td> 
	                  <td>${vo.name }</td> 
	                  <td>${vo.value }</td> 
	                  <td>${vo.useable ? "启用" : "未启用" }</td>
	                  <!--  
	                  <td>
	                  	<fmt:formatDate value="${vo.updatedtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                  </td> 
	                  -->
	                  <td>
						<a title="确定要删除吗?" callback="loadClassify" rel="classifyContainer" target="ajaxTodo" href="${webpath }/rs/inner/sysCodeInfo/deleteClassify?id=${vo.id }" class="btnDel"  auth="sysRoleDelete">删除</a>
						<a target="dialog" width="540" height="300" href="${webpath }/rs/inner/sysCodeInfo/toClassify?id=${vo.id }" class="btnEdit" title="编辑分类" auth="sysRoleEdit">编辑</a>
					  </td>
	            </tr> 
           </c:forEach>
		</tbody>
	</table>
	
</div>

<div class="unitBox" id="codeContainer">
	
</div>

</body>
</html>