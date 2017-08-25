<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">

    function pageRefushChild(t){
         navTabAjaxDone(t);   
         addform.reset();
         window.parent.document.getElementById("searchBnt").click();
    }
    
    $("#code").blur(function(){
    	var code=$("#code").val();
    	 $.ajax({
				url: "${webpath }/rs/inner/storeInfo/checkAreaCode",
				type: 'get',
				async:false,
				data: {
				  "code":code
				},
				cache: false,
				success: function (text) {
					if(text.message==0){
						$("#tipMsg").text(code+"可以使用");
					}else{
						$("#code").val("");
						$("#tipMsg").text(code+"数据重复");					}
					
				}
		});
    	
    	  
    });
    
  //下拉框被选中时
    $("#parentid1").change(function(){
    	var cont =" <option value=''></option>";
    	$("#parentid3").html(cont);
    	var v1= $("#parentid1");
    
    	if("0"==v1){
    		$("#parentid2").html(cont);
    	}else{
    	//查询父节点节点
    	var v = $("#parentid1").val();
    	 $.ajax({
				url: "${webpath }/rs/inner/storeInfo/checkAreaNOdeCode",
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
    }
    	
    	//子节点数据
    
    	return ;
    	
    });
   
  //当子菜单被选中时
    $("#parentid2").change(function(){
    	
    	//查询父节点节点
    	var v = $("#parentid1").val();
    	var v2 = $("#parentid2").val();
    	
    	if("3"==v){
    		//查询所有城市信息
    	 $.ajax({
				url: "${webpath }/rs/inner/storeInfo/checkSubNOdeCode",
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
    	 
    }
    
    	return ;
    	
    });
  
  //查询子节点
    function getSubNode(code,id){
    	 var datas = code.date;
         if(datas.length==0){
      	   $(id).html("");
         }else {
        	 var cont="";
          	//cont+="<select name='areaid' size='1' id='selectId1'>";
          	cont +=" <option value=''>请选择</option>";
          	$.each( datas, function(i,n){
          		cont +=" <option value='"+n.id+"'>"+n.name+"</option>";
          		
          	});
          	
          	$(id).html(cont);
         }
     	// 将查询的结果循环遍历，生成HTML代码，放置在表格当中。
     	
     }
    
</script>
<div class="pageContent">
	<form method="post" action="${webpath }/rs/inner/storeInfo/saveArea" name="addform" class="pageForm required-validate" onsubmit="return validateCallback(this,pageRefushChild);">
		<div class="pageFormContent" layoutH="58">
			<div class="divider">divider</div>
			
			<div class="unit">
				<label>地区编码：</label>
				<input type="text" size="25" name="code" id="code" class="required alphanumeric"  />
				 <span id="tipMsg" style="color:red"></span>
			</div>
			
			<div class="unit">
				<label>上级地区：</label>
			<%-- <select name="parentid" size="1"  id="selectId" >
			<option value="">---</option>
              <c:forEach items='${list}' var="ur" >
                  <option  value='${ur.id}'>${ur.name}</option>
              </c:forEach>
            </select> --%>
            <select name="parentid1" size="1"  id="parentid1" >
			  <option value="0">全球</option>
			  <option value="1">国家</option>
			  <option value="2">省份</option>
			  <option value="3">城市</option>
            </select>
            
            <span id="spanIds1">
            <select name="parentid2" size="1" id="parentid2" >
              <option value=""></option>
            </select>
            
            </span>
            
             <span id="spanIds2">
            <select name="parentid3" size="1" id="parentid3" >
              <option value=""></option>
            </select>
            
            </span>
			</div>
			
			<div class="unit">
				<label>地区名称：</label>
				<input type="text" size="25" name="name" class="required" />
			</div>
			

			<!-- <div class="unit">
				<label>等级：</label>
				<input type="text" size="25" name="level" class="number"/>
			</div> -->
			
			<!-- <div class="unit">
				<label>成立时间：</label>
				<input type="text" size="25" name="createdtime" pattern="yyyy年MM月dd日HH:mm:ss" class="date"/>
			</div>
			<div class="divider">divider</div> -->
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			</ul>
		</div>
	</form>
</div>