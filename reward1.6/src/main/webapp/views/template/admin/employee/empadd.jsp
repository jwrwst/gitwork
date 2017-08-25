<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="plf" uri="http://www.platform.com" %>
<script type="text/javascript">

	$(function(){
		
		 $("#ifJobable1").hide();
		 $("#ifempPostion").hide();
		 $("#ifpriority").show();
	});

    function pageRefushChild(t){
         navTabAjaxDone(t);   
         addform.reset();
         window.parent.document.getElementById("searchBnt").click();
    }
    
    
    function changeJob(empJobable){

    	if(empJobable=="true"){
    		$("#empJobable").val(true);
   		 	$("#ifJobable1").hide();
	   	}else{
	   		$("#empJobable").val(false);
	   		$("#ifJobable1").show();
	   	}
    }
    
    $(function(){   
    	$(".dialogContent.layoutBox.unitBox").height(365);
 	    $(".dialog").height(406);
 	    $(".shadow_c_c").height(400);
 	   $(".shadow").height(400);
    });
    
    //工作岗位改变
    $("*[name='empPostion']").change(function () {
    	var empPostion= $("*[name='empPostion']").val();
    	if(empPostion == "5"){
    		 $("#ifempPostion").show();
    		 $("#ifpriority").hide();
    		 $("#priority").val("");
    		 //softAccount
    		 $("#softAccount").attr("class", "required textInput");
    		 $("#softPwd").attr("class", "required textInput");
    		 
    		 $(".dialogContent.layoutBox.unitBox").height(365+30);
    	 	    $(".dialog").height(406+30);
    	 	    $(".shadow_c_c").height(400+30);
    	 	   $(".shadow").height(400+30);
    	}else{
    		 $("#softAccount").val("");
    		 $("#softPwd").val("");
    		 $("#ifempPostion").hide();
    		 $("#ifpriority").show();
    		 $("#softAccount").attr("class", "textInput");
    		 $("#softPwd").attr("class", "textInput");
    		 $(".dialogContent.layoutBox.unitBox").height(365);
    	 	 $(".dialog").height(406);
    	 	 $(".shadow_c_c").height(400);
    	 	 $(".shadow").height(400);
    	}
    	
    });
    
  //required textInput
    
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/employeeRest/saveEmp" name="addform" class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<div class="pageFormContent">
			<div class="divider">divider</div>
			<div class="unit">
				<label>员工姓名：</label>
				<input type="text" size="25" name="empName" class="required"/>
				
			</div>
			<div class="unit">
				<label>员工昵称姓名：</label>
				<input type="text" size="25" name="nickname" class="required"/>		
			</div>

			<div class="unit">
				<label>性别：</label>	
				<plf:radio name="empSex" classify="System.Gender" fixed="${sysEmployeeInfoVo.empSex}" />
			</div>
			<div class="unit">
				<label>出生日期：</label>
				<input type="text" name ="empBirthday" class="date" pattern="yyyy年MM月dd日HH:mm:ss"/>
			</div>
			<div class="unit">
				<label>身份证编码：</label>
				<input type="text" size="25" name="empIdCard" class="required"/>
			</div>
			
			<div class="unit">
				<label>手机号：</label>
				<input type="text" size="25" name="empMobile"/>
			</div>
			<div class="unit">
				<label>入职时间：</label>
				<input type="text" name ="empEntrydate" class="date" pattern="yyyy年MM月dd日HH:mm:ss" />
			</div>
			
			<div class="unit" id="ifJobable1" >
				<div class="unit">
					<label>离职原因：</label>
					<input type="text" name="extQuitreason" value="${sysEmployeeInfoVo.extQuitreason}"/>
				</div>
				<div class="unit">
					<label>离职时间：</label>
					<input type="text" name ="extQuitdate" class="date" value="${sysEmployeeInfoVo.extQuitdate}" pattern="yyyy年MM月dd日HH:mm:ss"/>
				</div>
			</div>
			
			<div class="unit">
				<label>住址：</label>
				<input type="text" size="25" name="empHomeaddress"/>
			</div>
			
			<div class="unit">
				<label>所在部门：</label>
				
				<input type="hidden" name="sysEmployeeInfoVo.empStoreId" />
				<input type="text" name="sysEmployeeInfoVo.storeName" />

				<a class="btnLook"  target="dialog" width="600" height="400"  href="${webpath }/rs/inner/employeeRest/toStoreList"  lookupGroup="sysEmployeeInfoVo">查询部门信息</a>
			</div>
			<div class="unit">
				<label>工作岗位：</label>
				<plf:select name="empPostion" classify="System.Post" fixed="1" />
			</div>
			
			
			 <div class="unit" id="ifpriority" >
				<div class="unit">
					<label>门店联系人优先级：</label>
					<plf:select name="priority" classify="System.priority" fixed="1" />
				</div>
		   </div>
		  
			<div class="unit" id="ifempPostion" >
				<div class="unit">
					<label>软电话条账号：</label>
					<input type="text" id="softAccount" name="softAccount" />
				</div>
				<div class="unit">
					<label>软电话条密码：</label>
					<input type="text" id="softPwd" name ="softPwd"   />
				</div>
			 </div>
			 
			<div class="unit">
				<label>等级：</label>
				<plf:select name="empLevel" classify="System.Employee.Level" fixed="1" />
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
