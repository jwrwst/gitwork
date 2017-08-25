<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="plf" uri="http://www.platform.com" %>

<script type="text/javascript">
    //成功页面刷新
    function pageRefushChild(t){
         navTabAjaxDone(t);   
         addform.reset();
         $.pdialog.closeCurrent();
         window.parent.document.getElementById("searchBnt").click();
    }
</script>
<div class="pageContent">
	<form method="post" action="${webpath}/rs/inner/customer/edit" name="addform"  class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<div class="pageFormContent" layoutH="58">
			<div class="divider">divider</div>
			<div class="unit">
				<label>客户键值：</label>
				<input type="text" size="25" name="customerKey" class="required" value="${vo.customerKey }">
			</div>
			<div class="unit">
				<label>捞粉名：</label>
				<input type="text" size="25" name="name" class="required" value="${vo.name}"/>
			</div>
			<div class="unit">
				<label>性别：</label>
				<plf:radio name="gender" classify="System.Gender" fixed="${vo.gender }" />
			</div>
			<div class="unit">
				<label>出生日期：</label>
				<input type="text" size="25" name="birthday" value="${vo.birthday }" class="date" pattern="yyyy年MM月dd日" />
			</div>
			<div class="unit">
				<label>手机号：</label>
				<input type="text" size="25" name="mobilePhone" id="mobilePhone" value="${vo.mobilePhone }" class="required alphanumeric" />
			</div>
			<div class="unit">
				<label>会员级别：</label>
				<input type="text" size="25" name="memberLevel" value="${vo.memberLevel }" class="required alphanumeric" maxlength="2" />
			</div>
			<div class="divider">divider</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			</ul>
		</div>
	</form>
</div>