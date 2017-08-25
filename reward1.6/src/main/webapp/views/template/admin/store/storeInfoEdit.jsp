<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<script type="text/javascript">
  function pageRefushChild(t) {
    navTabAjaxDone(t);   
    addform.reset();
    $.pdialog.closeCurrent();
    window.parent.document.getElementById("searchBnt").click();
  }
  
  $("#code").blur(function() {
    var code = $("#code").val().trim();
    if (code != null && code != '') {
      $.ajax({
        url: "${webpath }/rs/inner/storeInfo/checkStoreInfoCode",
        type: 'get',
        async: false,
        data: {
          "code": code
        },
        cache: false,
        success: function(text) {
          if (text.message == 0) {
            $("#tipMsg").text(code + "  可以使用");
          } else {
            $("#code").val("");
            $("#tipMsg").text(code + "  数据重复");
          }
        }
      });
    }
  });
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/storeInfo/edit" name="addform" class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<input type="hidden" size="35" name="id" id="id" value="${store.id }"/>
		<div class="pageFormContent" layouth="58">
			<div class="divider">divider</div>
			<div class="unit">
				<label>门店名称：</label>
				<input type="text" size="35" name="name" id="code" class="required" value="${store.name }"/>
				<span id="tipMsg" style="color: red"></span>
			</div>
			<div class="unit">
				<label>门店编码：</label>
				<input type="text" size="35" name="storeCode" id="storeCode" class="required" value="${store.storeCode }"/>
				<span id="tipMsg" style="color: red"></span>
			</div>
			<div class="unit">
				<label>部门编码：</label>
				<input type="text" size="35" name="deptId" id="deptId" class="required" value="${store.deptId }"/>
				<span id="tipMsg" style="color: red"></span>
			</div>
			<div class="unit">
				<label>所在片区：</label>
				<select name="parentDeptid" size="1" id="parentDeptid">
					<option value=''>--请选择--</option>
					<c:forEach items="${parentDept}" var="d">
						<option value="${d.storeCode}" <c:if test="${d.storeCode == store.parentDeptid}">selected</c:if>>${d.name}</option>
					</c:forEach>
				</select>
				<span id="tipMsg" style="color: red"></span>
			</div>
			<div class="unit">
				<tr>
					<label> 所在地区：${store.provinceId }</label>
					<td>
						<select name="province" size="1" id="province">
							<option value=''>--请选择--</option>
							<c:forEach items="${provinces}" var="p">
								<option value="${p.id}" <c:if test="${p.id == store.provinceId}">selected</c:if>>${p.name}</option>
							</c:forEach>
						</select>
						<span id="spanIds1">
							<select name="areaId" size="1" id="areaId">
								<option value=''>--请选择--</option>
								<c:forEach items="${city}" var="p">
									<option value="${p.id}" <c:if test="${p.id == store.areaId}">selected</c:if>>${p.name}</option>
								</c:forEach>
							</select>
						</span>
					</td>
				</tr>
			</div>
			<div class="unit">
				<label> 详细地址：</label>
				<input type="text" size="60" name="address" value="${store.address }"/>
			</div>
			<div class="divider">divider</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="reset"> 清空重输</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>