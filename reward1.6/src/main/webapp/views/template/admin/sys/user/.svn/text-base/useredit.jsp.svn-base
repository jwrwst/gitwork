<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
    //密码验证
    function pwdvalidate(e){
           var username=$("input[name=userName]").val();
           var pwd=$("input[name=pwdyuan]").val();
           $.post("${webpath }/rs/pl/sysUserInfo/isExist",{"name":username,"password":pwd},function(t){
                   if (t=="null"||t==null||t==""){
                        e.value="";
                   }
           });
    }
    //成功页面刷新
    function pageRefushChild(t){
         navTabAjaxDone(t);   
         addform.reset();
         window.parent.document.getElementById("searchBnt").click();
    }
    $(function(){
         $.post("${webpath }/rs/inner/sysRoleInfo/list",function(t){
                var json=t;
                for(var i=0;i<json.length;i++){
                    if(json[i].sysRoleId=="${sysUserInfoVo.sysRoleId}"){
                         $("#sysRoleId").append("<option value='"+json[i].sysRoleId+"' selected='selected'>"+json[i].roleName+"</option>");
                         continue;
                    }
                    $("#sysRoleId").append("<option value='"+json[i].sysRoleId+"'>"+json[i].roleName+"</option>");
                }
         });
    });
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/sysUserInfo/save" name="addform"  class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<div class="pageFormContent" layoutH="58">
			<div class="divider">divider</div>
			<div class="unit">
				<label>用户名称：</label>
				<input type="hidden" name="sysUserId" value="${sysUserInfoVo.sysUserId }">
				<input type="text" size="25" name="userName" class="lettersonly" value="${sysUserInfoVo.userName}"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>中文名称：</label>
				<input type="hidden" name="sysEmployeeInfoVo.empid" value="${sysUserInfoVo.empid}"/>
				<input type="text" name="sysEmployeeInfoVo.realName" value="${sysUserInfoVo.realName}"/>
				<a class="btnLook" target="dialog" width="600" height="400"  href="${webpath }/rs/inner/sysUserInfo/toEmployeePage"  lookupGroup="sysEmployeeInfoVo">查询部门信息</a>
				<span class="inputInfo">汉字拼音首字母</span>
			</div>
			<div class="unit">
				<label>所属角色：</label>
				<select name="sysRoleId" id="sysRoleId">
				</select>
			</div>
			<div class="unit">
				<label>原密码：</label>
				<input type="password" size="25" name="pwdyuan" class="required alphanumeric" minlength="6" maxlength="20" onblur="pwdvalidate(this)"/>
				<span class="inputInfo">请输入原密码</span>
			</div>
			<div class="unit">
				<label>新密码：</label>
				<input type="password" size="25" name="newpwd" id="newpwd" class="required alphanumeric" minlength="6" maxlength="20" />
				<span class="inputInfo">请输入新密码</span>
			</div>
			<div class="unit">
				<label>确认密码：</label>
				<input type="password" size="25" name="pwd" class="required alphanumeric" minlength="6" maxlength="20" equalTo="#newpwd"/>
				<span class="inputInfo">请输入确认密码</span>
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