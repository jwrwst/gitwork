<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="../js/auth.js" type="text/javascript"></script>
<script type="text/javascript">
	function pageRefush(t) {
		document.getElementById("searchBnt").click();
	}

	$(function() {
		if ("${param.noload}" == null || "${param.noload}" == "") {
			pageRefush();
		}
	});
</script>
</head>
<body>
	<c:import url="../common/_frag/pager/pagerForm.jsp"></c:import>
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return dwzSearch(this, 'dialog');" action="${webpath }/rs/inner/storeInfo/getStoreLookup" method="post" name="listform">
			<div class="searchBar">
				<ul class="searchContent">
					<li>
						<label>门店名称：</label>
						<input type="text" name="name" value="${vo.name }" alt="清输入门店名称"/>
					</li>
				</ul>
				<div class="subBar">
					<ul>
						<li style="margin-right: 50px;">
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit" id="searchBnt">检索</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" layoutH="118" targetType="dialog" width="100%">
			<thead>
				<tr>
					<th width="50"><input type="checkbox" group="id" class="checkboxCtrl">编号</th>
					<th width="120">门店名称</th>
					<th width="80">所在地区</th>
					<th width="70">详细地址</th>
					<th width="70">成立时间</th>
					<th width="70">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${store.page.result }"
					varStatus="status">
					<tr target="id" rel="${vo.id }">
						<td><input name="id" value="${vo.id }" type="checkbox">${status.index+1 }</td>
						<td>${vo.name }</td>
						<td>${vo.areaid }</td>
						<td>${vo.address }</td>
						<td>${vo.createdtime }</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({storeId:'${vo.id }',storeCode:'${vo.storeCode }', storeName:'${vo.name }'})" title="查找带回">选择</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="../common/_frag/pager/panelBar.jsp"></c:import>
	</div>
</body>
</html>