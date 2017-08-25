<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息模板管理</title>
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
		
		function synchro(){
			$.ajax({
				url:"${ctx}/weixin/synchro/synchroTemplate",
				type:"post",
				dataType:"JSON",
				success:function(d){
					alert(d.msg);
					$("#searchForm").submit();
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/weixinTemplate/">消息模板列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinTemplate" action="${ctx}/weixin/weixinTemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模板ID：</label>
				<form:input path="templateId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>模板标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a id="synchro" class="btn btn-primary" href="javascript:synchro()" >提取消息模板</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>模板ID</th>
				<th>模板标题</th>
				<th>模板所属行业的一级行业</th>
				<th>模板所属行业的二级行业</th>
				<th>模板内容</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinTemplate">
			<tr>
				<td>
					${weixinTemplate.templateId}
				</td>
				<td>
					${weixinTemplate.title}
				</td>
				<td>
					${weixinTemplate.primaryIndustry}
				</td>
				<td>
					${weixinTemplate.deputyIndustry}
				</td>
				<td>
					${weixinTemplate.count}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>