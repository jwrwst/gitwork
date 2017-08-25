<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>二维码管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			$("#ele_endDate").hide();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function changeQrType(a){
			if($(a).val()=="PERMANENT"){
				$("#ele_sceneId").show();
				$("#ele_endDate").hide();
			}else{
				$("#ele_endDate").show();
				$("#ele_sceneId").hide();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/weixinQrcode/">二维码列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinQrcode" action="${ctx}/weixin/weixinQrcode/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>二维码类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="请选择二维码类型"/>
					<form:options items="${fns:getDictList('weixin_qrcode_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a id="synchro" class="btn btn-primary"  href="#modal-container-373309" data-toggle="modal" >获取二维码</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>二维码ticket</th>
				<th>场景值ID</th>
				<th>二维码类型</th>
				<th>过期时间</th>
				<th>解析地址</th>
				<th>创建时间</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinQrcode">
			<tr>
				<td>
					${weixinQrcode.ticket}
				</td>
				<td>
					${weixinQrcode.sceneId}
				</td>
				<td>
					${fns:getDictLabel(weixinQrcode.type, 'weixin_qrcode_type', '')}
				</td>
				<td>
					${weixinQrcode.expireSeconds}
				</td>
				<td>
					${weixinQrcode.url}
				</td>
				<td>
					<fmt:formatDate value="${weixinQrcode.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinQrcode.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<div class="modal hide fade" id="modal-container-373309" role="dialog" aria-hidden="true" aria-labelledby="myModalLabel">
		<div class="modal-header">
			 <button class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>
			<h3 id="myModalLabel">
				新建二维码
			</h3>
		</div>
		<div class="modal-body">
			<form:form id="inputForm" modelAttribute="weixinQrcode" action="${ctx}/weixin/weixinQrcode/save" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<sys:message content="${message}"/>		
				<div class="control-group">
					<label class="control-label">二维码类型：</label>
					<div class="controls">
						<form:select path="type" class="input-medium" onchange="changeQrType(this);">
							<form:options items="${fns:getDictList('weixin_qrcode_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div id="ele_sceneId" class="control-group">
					<label class="control-label">场景ID：</label>
					<div class="controls">
						<form:input path="sceneId" htmlEscape="false" maxlength="128" class="input-xlarge "/>
					</div>
				</div>
				<div id="ele_endDate" class="control-group">
					<label class="control-label">过期时间：</label>
					<div class="controls">
						<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注：</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
					</div>
				</div>
			</form:form>
		</div>
		<div class="modal-footer">
			 <button class="btn" aria-hidden="true" data-dismiss="modal">关闭</button> <button class="btn btn-primary">保存设置</button>
		</div>
	</div>
	

</body>
</html>