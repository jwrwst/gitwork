<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    //成功页面刷新
    function pageRefushChild(t){
         navTabAjaxDone(t);   
         addform.reset();
         $.pdialog.closeCurrent();
         $("#codeContainer", window.parent.document).loadUrl('${webpath }/rs/inner/sysCodeInfo/toCodeListPage',{
        	 classifyid: $('#globalClassifyid').val()
         });
    }
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/sysCodeInfo/modifyCode" name="addform" class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<div class="pageFormContent" layoutH="58">
			<!-- 隐藏id -->
			<input type="hidden" size="25" name="id" value="${codeVo.id}"/>
			<input type="hidden" size="25" name="classifyid" value="${codeVo.classifyid > 0 ? codeVo.classifyid : classifyid}"/>
			<div class="divider">divider</div>
			<div class="unit">
				<label>排序序号：</label>
				<input type="text" size="25" name="sorter" value="${codeVo.sorter}" class="digits" minlength="1" maxlength="100"/>
			</div>
			<div class="unit">
				<label>字典名称：</label>
				<input type="text" size="25" name="name" value="${codeVo.name}" class="required" minlength="1" maxlength="100"/>
			</div>
			<div class="unit">
				<label>字典值：</label>
				<input type="text" size="25" name="value" value="${codeVo.value}" class="required" minlength="1" maxlength="20"/>
			</div>
			<div class="unit">
				<label>是否启用：</label>
				<input type="radio" name="useable" value="1" <c:if test="${codeVo.useable == true or empty codeVo.useable}">checked="checked"</c:if>>是
				<input type="radio" name="useable" value="0" <c:if test="${codeVo.useable == false }">checked="checked"</c:if>>否
			</div>
			<div class="unit">
				<label>描述：</label>
				<textarea rows="5" cols="35" name="description" maxlength="2000">${codeVo.description}</textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			</ul>
		</div>
	</form>
</div>