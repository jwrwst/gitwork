<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

    function pageRefushChild(t){
         navTabAjaxDone(t);   
         addform.reset();
         window.parent.document.getElementById("searchBnt").click();
    }


	function operate(){
	     var list="";
		 $(".checked").each(function(i,a){
		       if(list.length==0){
		         list += $(this).find("input").val();
		       }else{
		         list += ","+$(this).find("input").val();
		       }
		       
		 });
		 $(".indeterminate").each(function(i){
		       if(list.length==0){
		         list += $(this).find("input").val();
		       }else{
		         list += ","+$(this).find("input").val();
		       }
		 });
		 
		$("#authList").val(list);
	}
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/sysRoleInfo/save" name="addform" class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<div class="pageFormContent" layoutH="58">
			<div class="divider">divider</div>
			<div class="unit">
				<label>角色名称：</label>
				<input type="text" size="25" name="roleName"/>
			</div>
			<div class="divider">divider</div>
			<div class="unit"><label>功能列表：</label></div>
			<div class="divider">divider</div>
			
			<input type="hidden" name="authList" id="authList">
			<ul class="tree treeFolder treeCheck expand" oncheck="operate" id="treeul">
			<c:forEach var="volist" items="${authInfoList}">
			     <script type="text/javascript">
			          if("${volist.parentId}"==0){
			          		$("#treeul").append("<li id='${volist.sysAuthId}'><a  tname='name' tvalue='${volist.sysAuthId}'>${volist.authName}</a></li>");
			          }else{
				            $("#treeul").find("li").each(function(t){
				                if("${volist.parentId}"==$(this).attr("id")){
				                    if($(this).children("ul").length==0){
				                       $(this).append("<ul id='ul"+$(this).attr("id")+"'></ul>");
				                    }
				                    $("#ul"+$(this).attr("id")).append("<li id='${volist.sysAuthId}'><a  tname='name' tvalue='${volist.sysAuthId}'>${volist.authName}</a></li>");
				                }
				            });
			          }
			         
				 </script>
			     
			</c:forEach>
			</ul>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			</ul>
		</div>
	</form>
</div>