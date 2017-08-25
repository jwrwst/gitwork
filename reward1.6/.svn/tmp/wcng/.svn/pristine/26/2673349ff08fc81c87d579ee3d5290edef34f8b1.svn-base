<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="plf" uri="http://www.platform.com" %>
<script type="text/javascript">
<!--
    function pageRefushChild(t){
         navTabAjaxDone(t);   
         addform.reset();
         window.parent.document.getElementById("searchBnt").click();
    }
    
    $(function(){
         $.post("${webpath }/rs/pl/sysRoleInfo/list",function(t){
                var json=t;
                for(var i=0;i<json.length;i++){
                    $("#sysRoleId").append("<option value='"+json[i].sysRoleId+"'>"+json[i].roleName+"</option>");
                }
         });
    });
//-->
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/pl/sysUserInfo/save" name="addform" class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<div class="pageFormContent" layoutH="58">
			<div class="divider">divider</div>
			aaaaaaa<plf:select name="name" classify="classify" fixed="fixed" />
			<div class="unit">
				<label>用户名称：</label>
				<input type="text" size="25" name="userName" class="lettersonly"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>中文名称：</label>
				<input type="text" size="25" name="realName"/>
			</div>
			<div class="unit">
				<label>所属角色：</label>
				<select name="sysRoleId" id="sysRoleId">
				</select>
			</div>
			<div class="unit">
				<label>密码：</label>
				<input type="password" size="25" name="pwd" class="required alphanumeric" minlength="6" maxlength="20"  value=""/>
				<span class="inputInfo">完整的密码</span>
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