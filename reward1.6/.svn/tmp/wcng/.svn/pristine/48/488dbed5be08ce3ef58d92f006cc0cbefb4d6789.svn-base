<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    //成功页面刷新
    function pageRefushChild(t){
         navTabAjaxDone(t);   
         addform.reset();
         $.pdialog.closeCurrent();
         $('#classifyContainer', window.parent.document).loadUrl('${webpath }/rs/inner/sysCodeInfo/toListPage');
    }
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/sysCodeInfo/modifyClassify" name="addform"  class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<div class="pageFormContent" layoutH="58">
			<!-- 隐藏id -->
			<input type="hidden" size="25" name="id" value="${classifyVo.id}"/>
			<div class="divider">divider</div>
			<div class="unit">
				<label>分类名称：</label>
				<input type="text" size="25" name="name" value="${classifyVo.name}" class="required" minlength="2" maxlength="100"/>
			</div>
			<div class="unit">
				<label>分类值：</label>
				<input type="text" size="25" name="value" value="${classifyVo.value}" class="required alphadot" minlength="5" maxlength="100"/>
			</div>
			<div class="unit">
				<label>是否启用：</label>
				<input type="radio" name="useable" value="1" <c:if test="${classifyVo.useable == true or empty classifyVo.useable }">checked="checked"</c:if>>是
				<input type="radio" name="useable" value="0" <c:if test="${classifyVo.useable == false }">checked="checked"</c:if>>否
			</div>
			<div class="unit">
				<label>描述：</label>
				<textarea rows="5" cols="35" name="description">${classifyVo.description}</textarea>
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