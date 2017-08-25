<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
         $.post("${webpath }/rs/innerc",function(t){
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
    
    
  //下拉框被选中时
    $("#parentid1").change(function(){
    	
    	//查询父节点节点
    	var v = $("#parentid1").val();
    	$("#parentid3").html("");
    	 $.ajax({
				url: "${webpath }/rs/inner/storeInfo/selectChilderCode",
				type: 'get',
				async:false,
				dataType :"json",
				data: {
				  "code":v
				},
				cache: false,
				success: function (result) {
					getSubNode(result,"#parentid2");
					
				}
		});
   
    	
    	//子节点数据
    
    	return ;
    	
    });
  
  //当子菜单被选中时
    $("#parentid2").change(function(){
    	
    	//查询父节点节点
    	var v2 = $("#parentid2").val();
    	
    	 $.ajax({
				url: "${webpath }/rs/inner/storeInfo/selectChilderCode",
				type: 'get',
				async:false,
				dataType :"json",
				data: {
				  "code":v2
				},
				cache: false,
				success: function (result) {
					getSubNode(result,"#parentid3");
					
				}
		});
    	 
    
    	return ;
    	
    });
  
  //查询子节点
    function getSubNode(code,id){
        var datas = code.date;
     
     	// 将查询的结果循环遍历，生成HTML代码，放置在表格当中。
     	var cont="";
     	//cont+="<select name='areaid' size='1' id='selectId1'>";
     	
     	$.each( datas, function(i,n){
     		
     		if(datas.length==0)
     			cont +=" <option value=''>---</option>";
     			else
     		cont +=" <option value='"+n.id+"'>"+n.name+"</option>";
     		
     	});
     	
     	$(id).html(cont);
     }  
  
    
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/storeInfo/updateArea" name="addform"  class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<div class="pageFormContent" layoutH="58">
			<div class="divider">divider</div>
			<div class="unit">
				<label>地区编码：</label>
				<input type="hidden" name="id" value="${areainfovo.id }">
				<input type="hidden" name="level" value="${areainfovo.level }">
				<input type="text" size="25" name="code" id="code"  value="${areainfovo.code}" readonly="readonly"/>
			</div>
			
			<div class="unit">
			<label>上级地区：</label>

             <!-- 当前所属国家 -->
            <select name="parentid1" size="1" id="parentid1" >
              <c:forEach items="${list2}" var="ur" >
               <option value="${ur.id}" <c:if test="${ur.id eq countrys.parentid}">selected</c:if> >${ur.name}
               
               </option> 
           
             </c:forEach>
            </select>
            
             <!-- 当前所有省份 -->
            <select name="parentid2" size="1" id="parentid2" >
              <c:forEach items="${list3}" var="ur" >
               <option value="${ur.id}" <c:if test="${ur.id eq provinces.parentid}">selected</c:if> >${ur.name}
               
               </option> 
           
             </c:forEach>
            </select>
            
            <!-- 当前省所有城市 -->
            <select name="parentid3" size="1" id="parentid3" >
              <c:forEach items="${areaCitys}" var="ur" >
               <option value="${ur.id}" <c:if test="${ur.id eq areainfovo.parentid}">selected</c:if> >${ur.name}
               
               </option> 
           
             </c:forEach>
            </select>
            
		</div>
			
			<div class="unit">
				<label>地区名称：</label>
				<input type="text" size="25" name="name"  class="required" value="${areainfovo.name}"/>
			</div>
			
		
			<%-- <div class="unit">
				<label>地区级别：</label>
				<input type="text" size="25" name="level" value="${areainfovo.level}" class="number"/>
				<span class="inputInfo">数字类型</span>
			</div> --%>
			
			
			<%-- <div class="unit">
				<label>成立时间：</label>
				<input type="text" size="25" name="createdtime" value="${areainfovo.createdtime}" pattern="yyyy年MM月dd日HH:mm:ss" class="date"/>
				<span class="inputInfo">日期类型</span>
			</div> --%>
			
			<div class="divider">divider</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				
			</ul>
		</div>
	</form>
</div>