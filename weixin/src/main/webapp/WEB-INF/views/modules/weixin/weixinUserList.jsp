<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信用户管理</title>
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
				url:"${ctx}/weixin/synchro/synchroUser",
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
		<li class="active"><a href="${ctx}/weixin/weixinUser/">微信用户列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinUser" action="${ctx}/weixin/weixinUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>昵称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a id="synchro" class="btn btn-primary" href="javascript:synchro();" >获取用户</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>昵称</th>
				<th>性别</th>
				<th>地址</th>
				<th>OPENID</th>
				<th>关注时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinUser">
			<tr>
				<td>
					${weixinUser.nickname}
				</td>
				<td>
					${weixinUser.sex}
				</td>
				<td>
					${weixinUser.address}
				</td>
				<td>
					${weixinUser.openId}
				</td>
				<td>
					<fmt:formatDate value="${weixinUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>