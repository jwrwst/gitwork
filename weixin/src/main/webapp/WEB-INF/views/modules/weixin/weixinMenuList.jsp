<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							type: getDictLabel(${fns:toJson(fns:getDictList('weixin_menu'))}, row.type),
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
		
		function synchro(){
			$.ajax({
				url:"${ctx}/weixin/synchro/synchroMenu",
				type:"POST",
				dataType:"JSON",
				success:function(d){
					alert(d.msg);
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/weixinMenu/">菜单列表</a></li>
		<shiro:hasPermission name="weixin:weixinMenu:edit"><li><a href="${ctx}/weixin/weixinMenu/form">菜单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinMenu" action="${ctx}/weixin/weixinMenu/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="请选择类型"/>
					<form:options items="${fns:getDictList('weixin_menu')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a id="synchro" class="btn btn-primary" href="javascript:synchro()" >同步微信</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>类型</th>
				<th>回调值</th>
				<th>排序</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="weixin:weixinMenu:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/weixin/weixinMenu/form?id={{row.id}}">
				{{row.name}}
			</a></td>
			<td>
				{{dict.type}}
			</td>
			<td>
				{{row.val}}
			</td>
			<td>
				{{row.sort}}
			</td>
			<td>
				{{row.createDate}}
			</td>
			<td>
				{{row.updateDate}}
			</td>
			<td>
				{{row.remarks}}
			</td>
			<shiro:hasPermission name="weixin:weixinMenu:edit"><td>
   				<a href="${ctx}/weixin/weixinMenu/form?id={{row.id}}">修改</a>
				<a href="${ctx}/weixin/weixinMenu/delete?id={{row.id}}" onclick="return confirmx('确认要删除该菜单及所有子菜单吗？', this.href)">删除</a>
				<a href="${ctx}/weixin/weixinMenu/form?parent.id={{row.id}}">添加下级菜单</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>