<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="plf" uri="http://www.platform.com" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">
    function pageRefushChild(t){
         navTabAjaxDone(t);   
         addform.reset();
         $.pdialog.closeCurrent();
         window.parent.document.getElementById("searchBnt").click();
    }
    
    function onBlur(obj){
    	var jobNumber = obj.value;
    	if("" == jobNumber){
    		alert("工号不能为空");
    		return false;
    	}
    	var id = document.getElementById("id").value;
    	 $.ajax({  
             type:'get',  
             traditional :true,  
             url:'${webpath }/rs/inner/employeeRest/isCheckByJobNumber',  
             data:{"jobNumber":jobNumber,"id":id},  
             success:function(data){  
                if("0000" == data.statusCode){
                	if(data.body.flag>0){
                	    alert("工号已存在！");	
                	}
                }else{
                	//系统错误  清空 工号
               	   alert("系统错误!");
                }
             },
             error:function(data){
            	 //系统错误  清空 工号
            	 alert("系统错误!");
             }
         });
    }
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/employeeRest/edit" name="addform" class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<input type="hidden" size="25" id="id" name="id" value="${vo.id}"/>
		<div class="pageFormContent" layoutH="58">
			<div class="divider">divider</div>
			<p>
						<label>员工姓名：</label>
						<input type="text" size="25" name="name" value="${vo.name}" class="required"/>
			</p>
			<p>
				<label>员工昵称姓名：</label>
				<input type="text" size="25" name="nickname" value="${vo.nickname}" class="required"/>		
			</p>
			<p>
						<label>性别：</label>
						<plf:radio name="gender" classify="System.Gender" fixed="${vo.gender}"/>
	</p><p>
						<label>出生日期：</label>
						<input type="text" size="25" name ="birthday" value="${vo.birthday}" class="date" pattern="yyyy年MM月dd日"/>
			</p><p>
						<label>身份证编码：</label>
						<input type="text" size="25" name="idcard"  value="${vo.idcard}" class="required"/>
					</p><p>
						<label>手机号：</label>
						<input type="text" size="25" name="mobile" value="${vo.mobile}" />
					</p><p>
						<label>所在部门：</label>
						<input type="hidden" name="vo.storeCode" value="${vo.storeCode}"/>
						<input type="text" name="vo.storeName" value="${vo.storeName }" suggestFields="storeName" lookupGroup="vo"/>
						<a class="btnLook"  target="dialog" width="600" height="400"  href="${webpath }/rs/inner/storeInfo/toStoreLookup" lookupGroup="vo">查询部门信息</a>
				</p><p>
						<label>工号：</label>
						
						<c:choose>
						       <c:when test="${empty vo.jobNumber}">
						             <input type="text" size="25" name="jobNumber" value="${vo.jobNumber}" onBlur="onBlur(this);" class="required"/>
						       </c:when>
						       <c:otherwise>
						              <input type="text" size="25" name="jobNumber" value="${vo.jobNumber}" readonly=true class="required"/>
						       </c:otherwise>
						</c:choose>
						
					</p><p>
						<label>工作岗位：</label>
						<plf:select  name="postion" classify="System.Post" fixed="${vo.postion}" />
					</p><p>
						<label>等级：</label>
						<plf:select name="level" classify="System.Employee.Level" fixed="${vo.level}" />
					</p><%-- <p>
						<label>头像地址：</label>
						<input type="file" name="avatarSite" value="${vo.avatarSite}" />
					</p> --%><p>
						<label>支付宝账号：</label>
						<input type="text" size="25" name="alipayAccount" value="${vo.alipayAccount}" />
					</p><p>
						<label>微信账号：</label>
						<input type="text" size="25" name="wxAccount" value="${vo.wxAccount}" />
				</p><p>
						<label>情感状态：</label>
						<plf:select name="emotion" classify="System.Employee.Emotion" fixed="${vo.emotion}" />
				</p><p>
						<label>兴趣爱好：</label>
						<input type="text" size="25" name="avocation" value="${vo.avocation}" />
				</p>
				<p>
						<label>银行账户：</label>
						<input type="text" size="25" name="bankCard" value="${vo.bankCard}" />
				</p><p>
						<label>开户行：</label>
						<input type="text" size="30" name="bankName" value="${vo.bankName}" />
				</p>
				<p>
						<label>QQ号：</label>
						<input type="text" size="30" name="qqNumber" value="${vo.qqNumber}" />
				</p><p></p>
				<p>
						<label>个性签名：</label>
						<label>
						<textarea rows="3" cols="80" name="signature">${vo.signature }</textarea>
						</label>
				</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保&nbsp;&nbsp;&nbsp;&nbsp;存</button></div></div></li>
			</ul>
		</div>
	</form>
</div>