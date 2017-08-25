<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="plf" uri="http://www.platform.com" %>
<script type="text/javascript">
    function pageRefushChild(t){
         navTabAjaxDone(t);   
         addform.reset();
         $.pdialog.closeCurrent();
         window.parent.document.getElementById("searchBnt").click();
    }
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/employeeRest/edit" name="addform" class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<input type="hidden" size="25" name="id" value="${vo.id}"/>
		<div class="pageFormContent">
			<div class="divider">divider</div>
			<table>
				<tr height="32">
					<td>
						<label>员工姓名：</label>
					</td>
					<td>
						<input type="text" size="25" name="name" value="${vo.name}" class="required"/>
					</td>
					<td>
						<label>性别：</label>
					</td>
					<td>
						<plf:radio name="gender" classify="System.Gender" fixed="${vo.gender}"/>
					</td>
				</tr>
				<tr height="32">
					<td>
						<label>出生日期：</label>
					</td>
					<td>
						<input type="text" size="25" name ="birthday" value="${vo.birthday}" class="date" pattern="yyyy年MM月dd日HH:mm:ss"/>
					</td>
					<td>
						<label>身份证编码：</label>
					</td>
					<td>
						<input type="text" size="25" name="idcard"  value="${vo.idcard}" class="required"/>
					</td>
				</tr>
				<tr height="32">
					<td>
						<label>手机号：</label>
					</td>
					<td>
						<input type="text" size="25" name="mobile" value="${vo.mobile}" />
					</td>
					<td>
						<label>所在部门：</label>
					</td>
					<td>
						<input type="hidden" name="vo.storeCode" value="${vo.storeCode}"/>
						<input type="text" name="vo.storeName" value="${vo.storeName }" suggestFields="storeName" lookupGroup="vo"/>
						<a class="btnLook"  target="dialog" width="600" height="400"  href="${webpath }/rs/inner/storeInfo/toStoreList" lookupGroup="vo">查询部门信息</a>
					</td>
				</tr>
				<tr height="32">
					<td>
						<label>工号：</label>
					</td>
					<td>
						<input type="text" size="25" name="jobNumber" value="${vo.jobNumber}" />
					</td>
					<td>
						<label>工作岗位：</label>
					</td>
					<td>
						<plf:select  name="postion" classify="System.Post" fixed="${vo.postion}" />
					</td>
				</tr>
				<tr height="32">
					<td>
						<label>等级：</label>
					</td>
					<td>
						<plf:select name="level" classify="System.Employee.Level" fixed="${vo.level}" />
					</td>
					<td>
						<label>头像地址：</label>
					</td>
					<td>
						<input type="text" size="25" name="avatarSite" value="${vo.avatarSite}" />
					</td>
				</tr>
				<tr height="32">
					<td>
						<label>支付宝账号：</label>
					</td>
					<td>
						<input type="text" size="25" name="alipayAccount" value="${vo.alipayAccount}" />
					</td>
					<td>
						<label>微信账号：</label>
					</td>
					<td>
						<input type="text" size="25" name="wxAccount" value="${vo.wxAccount}" />
					</td>
				</tr>
				<tr height="32">
					<td>
						<label>个性签名：</label>
					</td>
					<td colspan="3">
						<textarea rows="3" cols="80" name="signature">${vo.signature }</textarea>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保&nbsp;&nbsp;&nbsp;&nbsp;存</button></div></div></li>
			</ul>
		</div>
	</form>
</div>