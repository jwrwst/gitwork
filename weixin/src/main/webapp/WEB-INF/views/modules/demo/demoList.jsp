<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>样本管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/demo/demo/">样本列表</a></li>
		<shiro:hasPermission name="demo:demo:edit"><li><a href="${ctx}/demo/demo/form">样本添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="demo" action="${ctx}/demo/demo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名字：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('demo_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名字</th>
				<th>类型</th>
				<th>电话</th>
				<th>描述</th>
				<shiro:hasPermission name="demo:demo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="demo">
			<tr>
				<td><a href="${ctx}/demo/demo/form?id=${demo.id}">
					${demo.name}
				</a></td>
				<td>
					${fns:getDictLabel(demo.type, 'demo_type', '')}
				</td>
				<td>
					${demo.phone}
				</td>
				<td>
					${demo.info}
				</td>
				<shiro:hasPermission name="demo:demo:edit"><td>
    				<a href="${ctx}/demo/demo/form?id=${demo.id}">修改</a>
					<a href="${ctx}/demo/demo/delete?id=${demo.id}" onclick="return confirmx('确认要删除该样本吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>