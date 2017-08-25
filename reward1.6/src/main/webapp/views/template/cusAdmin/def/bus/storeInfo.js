var orgCode = window.localStorage.getItem("orgCode");
var gloabObj={
		last:0,//0商户，1门店
		leap:0,//节点层级
		selCode:orgCode,
		selName:"",
		parentCode : 0 ,
		isParent : 0,
		empList:[],
		rewardList:[]
};

var StoreInfo = function () {
    var zTree;
    
    //初始化部门
    var initOrganize = function(){
    	  var setting = {              
    	        data: {
    	            simpleData: {
    	                enable:true,
    	                idKey: "id",
    	                pIdKey: "pId",
    	                rootPId: ""
    	            },
    	            key:{
    	            	name :"name",
    	            	title :"title"
    	            }
    	        },
    	        view: {
    	        	showTitle: true    	    		
    	    	},
    	        callback: {
    	            beforeClick: function(treeId, treeNode) {
    	                var zTree = $.fn.zTree.getZTreeObj("tree");
    	                if (treeNode.isParent && treeNode.open == false) {
    	                    zTree.expandNode(treeNode);    	                        
    	                } 
    	                gloabObj.last = treeNode.last;
    	                gloabObj.selCode = treeNode.id;
    	                gloabObj.selName = treeNode.name;
    	                gloabObj.parentCode = treeNode.pId;
    	                gloabObj.leap = treeNode.leap;
    	                
    	                StoreInfo.empTable.fnDraw();
    	                //loading 进度条
    	                App.loading();
    	                //初始化店长
    	                selStoreManager();
    	                if($(".nav-tabs").find(".active").hasClass("managerdis")){
    	                	$("#tabBaseInfo").click();
    	                }
    	            }
    	        }
    	 };
    	  
    	  var choicSetting = {  
    			check:{
    				enable: true,
    				chkStyle: "radio",
    				radioType: "all"
    			},
      	        data: {
      	            simpleData: {
      	                enable:true,
      	                idKey: "id",
      	                pIdKey: "pId",
      	                rootPId: ""
      	            },
      	            key:{
      	            	title:"在基本信息页，修改所属区域"
      	            }
      	        },
      	        callback: {
      	            beforeClick: function(treeId, treeNode) {
      	                var zTree = $.fn.zTree.getZTreeObj("choiceTree");      	                
      	                if (treeNode.isParent) {
      	                    zTree.expandNode(treeNode);    	                        
      	                }       	              
      	            },
      	            onCheck:function(event, treeId, treeNode){      	            
      	            	$("#orgCodeId").val(treeNode.id);
      	            	$("#orgNameId").val(treeNode.name);
      	            	$("#orgLeap").val(treeNode.leap);
      	            	$("#choiceTree").hide();
      	            }
      	        }
      	 };
    	  
    	 var reqparam=  "orgCode="+orgCode;
    	 if($("#queryStoreName").val()!=null&&$("#queryStoreName").val()!=""){
    		 reqparam += "&storeName="+$("#queryStoreName").val();
    	 }    	 
    	 addrManage.getOrganize(reqparam, function(data){
 	    	if(data!=null && data.resultMessage.messageCode =='0000' ){
     			var obj=data.resultData.body.result;
     			var zNodes =[];
     			for(var i=0;i<obj.length;i++){
     				if(obj[i].parentCode == 0){
     					orgCode = obj[i].orgCode;
     					gloabObj.selCode = orgCode;
     					gloabObj.selName = obj[i].orgName;
     					gloabObj.last = obj[i].last;
     					gloabObj.leap = obj[i].leap;
    	                gloabObj.parentCode = obj[i].parentCode;
     				}
     				if(obj[i].last==0){
     					zNodes.push({id:obj[i].orgCode,pId:obj[i].parentCode,name:obj[i].orgName,last:obj[i].last,leap:obj[i].leap,title:"在基本信息页，修改所属区域",icon:"media/image/tree_org.png"});
     				}else{
     					zNodes.push({id:obj[i].orgCode,pId:obj[i].parentCode,name:obj[i].orgName,last:obj[i].last,leap:-1,title:"在基本信息页，修改所属区域",icon:"media/image/tree_store.png"});
     				}
     				
     			}
     			//左边树形机构
     			var t = $("#tree");
      	        t = $.fn.zTree.init(t, setting, zNodes);             	       
      	        zTree = $.fn.zTree.getZTreeObj("tree");
      	        zTree.selectNode(zTree.getNodeByParam("pId", 0));
      	        zTree.expandAll(true);
      	        //
      	        selStoreManager();
      	        
      	        //可选择机构树形初始化
      	        zNodes =[];
	   			for(var i=0;i<obj.length;i++){
	   				if(obj[i].last ==0){
	   					zNodes.push({id:obj[i].orgCode,pId:obj[i].parentCode,name:obj[i].orgName,last:obj[i].last,leap:obj[i].leap,icon:"media/image/tree_org.png"});
	   				}
	   			}
	   			var t = $("#choiceTree");
      	        t=$.fn.zTree.init(t, choicSetting, zNodes);             	       
      	        t.expandAll(true);
	   			
 	    	}
 	    });
    };
    
    //搜索树列表店铺
    $("#queryOrg").click(function(){
    	initOrganize();
    });
    
    //初始化赏金分配模板
    (function(){
    	addrManage.getClassType("codeClass=System.wish.template", function(data){
			  if(data&&data.resultMessage.messageCode=="0000"){
				  var list = data.resultData.body.result;
				  var strHtml=[];
				  var strValHtml=[];
				  for(var i=0;i<list.length;i++){					 
					 strHtml.push('<li><a  data-name="'+list[i].name+'" data-value="'+list[i].value+'">'+list[i].value+'('+list[i].name+')</a></li>');
					 strValHtml.push('<li><a  data-name="'+list[i].name+'" data-value="'+list[i].value+'">'+list[i].name+'</a></li>');
				  }
				  
				  $("#rewardModel").html(strHtml.join(""));
				  $("#rewardModel").find("a").click(function(){
					  $("input[name='rewardMoney']").val($(this).attr("data-value"));
					  $("input[name='wish']").val($(this).attr("data-name"));
					  //触发改变事件
					  $("input[name='rewardMoney']").trigger("change");
				  });
				  
				  $("#wishModel").html(strValHtml.join(""));
				  $("#wishModel").find("a").click(function(){
					  $("input[name='wish']").val($(this).attr("data-name"));
				  });
				  
			  }  			  
			  
		});
    })();
    
    //查询店长信息
    var selStoreManager=function(){
    	//加载商户的打赏信息  这个接口之后要调整,按部门结构一级一级搜索
		var getReward = function(tempOrgCode){
			//判断是否为店铺
			var isStore = tempOrgCode == gloabObj.parentCode ? 1 : 0 ;
			//
			var tranOrgCode = tempOrgCode||(gloabObj.parentCode==0?gloabObj.selCode:gloabObj.parentCode);
			
			addrManage.getMechantsRewards("orgCode="+tranOrgCode,function(data){
				  try{
					  if(data!=null && data.resultMessage.messageCode =='0000' &&data.resultData!=null){
						    var list = data.resultData.body.result;
						    var allot="";
						    $("#template-allot-content").html("");
						    //存储原始分配信息
						    StoreInfo.allot = [];
						    if(list == null)return;
						    //展示分配信息列表
						    for(var i=0;i<list.length;i++){
						    	allot = list[i].allotPlan;
						    	StoreInfo.allot.push(list[i].percent);
						    	var id = isStore?0:list[i].id ;
						    	var parentId = isStore?(list[i].id?list[i].id:list[i].parentId):list[i].parentId;
							    var vo={money:list[i].money,percent:(list[i].percent*100).toFixed(2),id:id,parentId:parentId,remark:list[i].remark};
							    var template = document.getElementById('template-allot').innerHTML;
								var compiled = Template7(template).compile();
								var compiledRendered = compiled(vo);
								$("#template-allot-content").append(compiledRendered);	
						    }
						    if(allot!=""){
						    	$("#allotPlan").val(allot);
						    	$("#allotPlan").trigger("change");
						    }
						    
						    if(gloabObj.last==0){
						    	$(".rewardaccount").remove();
						    }
						    
						    //下级是否可编辑
	  					    //注解后台管理权限 try{initEditAuth(StoreInfo.mechants.iseditVal1)}catch(e){};
						    
						    //初始化选择人员下拉
						    $(".selEmp").html(gloabObj.empList.join(""));           		    
						    App.initChosenSelect(".selEmp");
						    $(".selEmp").trigger("liszt:updated");
						    
						    
						  
			    	  }
				  }finally{
		    		  calcRewardPerson();
		    	  }
			    });
		};
		
		var initEditAuth=function(code){
			//按最后修改纪录
			/**
			if(code==2){ 
		    	$(".iseditVal1").attr("readonly",true);
		    	$(".iseditVal1-dis").hide();
		    	$(".iseditVal1-sel").children("option").hide();
		    	$(".iseditVal1-sel").children("option:selected").show();  
		    }else{
		    	$(".iseditVal1").attr("readonly",false);
		    	$(".iseditVal1-dis").show();
		    	$(".iseditVal1-sel").children("option").show();
		    }
		    **/
		};
		//加载所有人员
		addrManage.getAllEmployee("orgCode="+orgCode+"&storeId="+(gloabObj.last==1?gloabObj.selCode:"0"), function(data){
 	    	if(data!=null && data.resultMessage.messageCode =='0000' ){
 	    		var htmlObj=['<option value="0">暂无分成人(此分成由被打赏人获得)</option>'];
     			var obj=data.resultData.body.result;
     			var merchants = obj.merchants;
     			var manager = obj.manager;
     			var employee = obj .empployee ; 
     			var divided = obj.divided;
     			if(merchants!=null && merchants.length>0){
     				var group = ['<optgroup label="管理人员">'];
     				for(var i=0;i<merchants.length;i++){
     					group.push('<option value="'+merchants[i].empId+'">'+merchants[i].empName+'</option>');
     				}
     				group.push("</optgroup>");
     				htmlObj.push(group.join(""));
     			}
     			
     			if(manager!=null && manager.length>0){
     				var group = ['<optgroup label="店长">'];
     				for(var i=0;i<manager.length;i++){
     					group.push('<option value="'+manager[i].empId+'">'+manager[i].empName+'</option>');
     				}
     				group.push("</optgroup>");
     				htmlObj.push(group.join(""));
     			}
     			
     			if(divided!=null && divided.length>0){
     				var group = ['<optgroup label="分成人员">'];
     				for(var i=0;i<divided.length;i++){
     					group.push('<option value="'+divided[i].empId+'">'+divided[i].empName+"("+divided[i].jobTitle+")"+'</option>');
     				}
     				group.push("</optgroup>");
     				htmlObj.push(group.join(""));
     			}
     			
     			if(employee!=null && employee.length>0){
     				var group = ['<optgroup label="店员">'];
     				for(var i=0;i<employee.length;i++){
     					group.push('<option value="'+employee[i].empId+'">'+employee[i].empName+'</option>');
     				}
     				group.push("</optgroup>");
     				htmlObj.push(group.join(""));
     			}
     			
     			gloabObj.empList = htmlObj;
     			
     			$(".selEmp").html(gloabObj.empList.join(""));	
     			for(var i=0;i<gloabObj.rewardList.length;i++){ 	         							
					$("select[name='extRewardlist.empId']").eq(i).val(gloabObj.rewardList[i].empId);          							
				}
           		App.initChosenSelect(".selEmp");
           		$(".selEmp").trigger("liszt:updated");
 	    	}
 	    }); 
		// 是否显示上级机构
		if(gloabObj.parentCode==0){
			$("#orgCodeInfoId").hide();
			$("#delStore").hide();
		}else{
			$("#orgCodeInfoId").show();
			$("#delStore").show();
			//上级机构
		    var choiceTree = $.fn.zTree.getZTreeObj("choiceTree");
		    var nodeItem=choiceTree.getNodeByParam("id", gloabObj.parentCode);
		    $("#orgCodeId").val(nodeItem.id);
	      	$("#orgNameId").val(nodeItem.name);
	      	$("#orgLeap").val(nodeItem.leap);
	      	choiceTree.checkNode(nodeItem,true);
		}
		//显示详情
		if(gloabObj.parentCode!=0 && gloabObj.last == 0){
			$(".orgManagerDis").hide();
		}else{
			$(".orgManagerDis").show();
		}
		
      	
		//店铺
    	if(gloabObj.last==1){
        	$(".managerdis").show();
        	$(".merchantsdis").hide();
        	
        	$("#orgManagerId").html("服务员管理");
        	//店长
    		addrManage.getManagerListByStoreId("storeId="+gloabObj.selCode,function(data){
    			if(data!=null && data.resultMessage.messageCode =='0000' ){
    				var json=data.resultData.body.result;
    				var strHtml=[];
    				for(var i =0;i<json.length;i++){
    					strHtml.push("<tr id='l"+json[i].empId+"'><td>"+(i+1)+"</td><td>"+json[i].empName+"</td><td><a href='javascript:$.confirm(\"提示\",\"确认要移除?\",StoreInfo.delManager,"+json[i].empId+");'  class='btn red' title='移除后，该店长将没有权限管理本店铺'> 移除</a></td></tr>");
    				}
    				$("#managerItem").html(strHtml.join(""));
    			}
    		});
    		
    		//分成人员
    		getJson(addrManage.divided.getDivEmpListByStoreId,"storeId="+gloabObj.selCode,function(data){
    			if(data!=null && data.resultMessage.messageCode =='0000' ){
    				var json=data.resultData.body.result;
    				var strHtml=[];
    				for(var i =0;i<json.length;i++){
    					strHtml.push("<tr id='divided"+json[i].empId+"'><td>"+(i+1)+"</td><td>"+json[i].empName+"</td><td><input value='"+(json[i].jobTitle == null?"":$.trim(json[i].jobTitle))+"' id='dividedInput"+json[i].empId+"'  onblur='StoreInfo.editDivided("+json[i].empId+")' style='width:230px;' placeholder='请输入分成人的职位信息，如：店长'></td><td><a href='javascript:$.confirm(\"提示\",\"确认要移除?\",StoreInfo.delDivided,"+json[i].empId+");' class='btn red' title='移除后，该分成人与绑定的二维码解绑，不再参与分成'> 移除</a></td></tr>");
    				}
    				$("#dividedItem").html(strHtml.join(""));
    			}
    		});
    		
    		//获取店铺信息
    		addrManage.getStore("storeId="+gloabObj.selCode, function(data){
    			if(data!=null && data.resultMessage.messageCode =='0000' ){
    				var json=data.resultData.body.result;
    				//$("#qrcode").parent().parent().parent().show();
    				$("#qrcode").attr("src","/rs/qrcode?text="+App.path+"/rs/wechat/goBaseLogin?state=6%26data="+gloabObj.selCode);
    				$("#dividedQrcode").attr("src","/rs/qrcode?text="+App.path+"/rs/wechat/goBaseLogin?state=61%26data="+gloabObj.selCode);
    				
    				$("input[name='orgName']").val(json.storeName);
    				$("input[name='address']").val(json.address);
    				$("input[name='telphone']").val(json.telphone);
    				
    				$("input[name='rewardMoney']").val(json.rewardMoney);
    				$("input[name='wish']").val(json.wish);
    				
    				if(json.isUpdate == 1){
    					$("input[name='isUpdateMoney']").parent().addClass("checked");
    					$("input[name='isUpdateMoney']").attr("checked",true);
    				}else{
    					$("input[name='isUpdateMoney']").parent().removeClass("checked");
    					$("input[name='isUpdateMoney']").attr("checked",false);
    				}
    				//权限列表
    			    authcode=(json.authlist!=null&&json.authlist.length!=0)?json.authlist[0].authCode:"0";
    			    
    			    //加载地区
    			    Area.init("storInfoForm",json.provinceId,json.cityId,json.areaId,function(){    	    			
    	    		});
    			    
    			    //加载店铺分类
    			    new Data({
    			    	parentId:"infotypes",
    			    	data:[{
    			    		name:"storeType",
    			    		//defaultText:"一级类型",
    			    		//defaultValue:'recreation',
    			    		defaultValue:json.storeType
    			    	},{
    			    		name:"codeType",
    			    		//defaultText:"二级类型",
    			    		defaultValue:json.codeType
    			    	}],
    			    	parentCode:'System.store.type'
    			    }).init();
    			    
    			    
        		}
        		
        		addrManage.getStoreAuth("groupCode=001",function(data){
	  				  var list = data.resultData.body.result;
	  				  var strHtml=[];
	  				  for(var i=0;i<list.length;i++){
	  					  if(list[i].authCode == authcode){
	  						  strHtml.push("<option value='"+list[i].authCode+"' selected='selected'>"+list[i].authName+"</option>");
	  					  }else{
	  					  	  strHtml.push("<option value='"+list[i].authCode+"'>"+list[i].authName+"</option>");
	  					  }
	  				  }
	  				  $("#authType").html("");
	  				  $("#authType").html(strHtml.join(""));
	  			    });
        		
    		});
    		
    		//获取商户赏金分配
//    		addrManage.getRewardStoreList("orgCode="+orgCode,function(){
//    			
//    		}); 	
    		
    		//获取商户信息 //注解后台管理权限
//    		addrManage.getMechants(JSON.stringify({orgCode:gloabObj.parentCode}),function(data){
//        		if(data!=null && data.resultMessage.messageCode =='0000' ){
//        			var obj=data.resultData.body.result; 
//        			StoreInfo.mechants = obj;
        			//if(obj.isedit==1){ 
        				//获取门店赏金分配
        				addrManage.getStoreExtReward("storeId="+gloabObj.selCode,function(data){
                			if(data!=null && data.resultMessage.messageCode =='0000' &&data.resultData!=null){
          					    var list = data.resultData.body.result.extRewardlist;
          					    if(list != null && list.length>0){
              					    var allot="";
              					    $("#template-allot-content").html("");
              					    
              					    for(var i=0;i<list.length;i++){
              					    	allot = list[i].allotPlan;

              						    var vo={money:list[i].money,percent:(list[i].percent*100).toFixed(2),id:list[i].id,parentId:list[i].parentId,remark:list[i].remark};
              						    var template = document.getElementById('template-allot').innerHTML;
              							var compiled = Template7(template).compile();
              							var compiledRendered = compiled(vo);
              							$("#template-allot-content").append(compiledRendered);								
              							
              							
              					    }
              					    //下级是否可编辑
              					    ////注解后台管理权限  initEditAuth(obj.iseditVal1);
              					    
              					    //分配方案
              					    if(allot!=""){
              					    	$("#allotPlan").val(allot);
              					    	$("#allotPlan").trigger("change");
              					    }
              					    
              					    //员工列表
              					    gloabObj.rewardList = list;
                					$(".selEmp").html(gloabObj.empList.join(""));	
	          	               		for(var i=0;i<list.length;i++){ 
	          	               			if(allot == "allot1002"){
	          	               				$("select[id='allotEmpId']").eq(i).val(list[i].empId);        
	          	               				break;
	          	               			}
	          							$("select[name='extRewardlist.empId']").eq(i).val(list[i].empId);          							
	          					    }
          	               			App.initChosenSelect(".selEmp"); 
          	               		    $(".selEmp").trigger("liszt:updated");
          	               			
          	               		    calcRewardPerson();
          					    }else{
          					    	getReward(gloabObj.parentCode);
          					    }
          		    	    }
                		});
//        			}else{
//        				getReward();
//        			}
        				
        	/*	//注解后台管理权限	
        		}
        	});*/
    		
    		
    	//部门	
    	}else{
        	$(".managerdis").hide();
        	$(".merchantsdis").show();
        	
        	//获取商户信息
        	addrManage.getMechants(JSON.stringify({orgCode:gloabObj.selCode}),function(data){
        		var authcode = 0;
        		if(data!=null && data.resultMessage.messageCode =='0000' ){
        			//$("#qrcode").attr("src","/rs/qrcode?text="+App.path+"/rs/wechat/goBaseLogin?state=8%26data="+gloabObj.selCode);
        			//$("#qrcode").parent().parent().parent().hide();
        			$("#managerQrcode").attr("src","/rs/qrcode?text="+App.path+"/rs/wechat/goBaseLogin?state=62%26data="+gloabObj.selCode);
        			if(gloabObj.parentCode==0){
        				$(".personTitle").html("商户管理员");
        				$("#orgManagerId").html("商户人员管理");
        			}else{
        				$(".personTitle").html("区域管理员");
        				$("#orgManagerId").html("区域经理管理");
        			}
        			var obj=data.resultData.body.result;
        			$("input[name='orgCode']").val(obj.orgCode);
        			$("input[name='orgName']").val(obj.orgName);
        			$("input[name='rewardMoney']").val(obj.rewardMoney);   
        			$("input[name='wish']").val(obj.wish);
        			if(obj.isUpdateMoney==1){
        				$("input[name='isUpdateMoney']").parent().addClass("checked");
        				$("input[name='isUpdateMoney']").attr("checked",true);
        			}else{
        				$("input[name='isUpdateMoney']").parent().removeClass("checked");
        				$("input[name='isUpdateMoney']").attr("checked",false);
        			}
        			
        			if(obj.isedit==1){    				
    					$("input[name='isedit']").parent().addClass("checked");
    					$("input[name='isedit']").attr("checked",true);
    				}else{
    					$("input[name='isedit']").parent().removeClass("checked");
    					$("input[name='isedit']").attr("checked",false);
    				}
        			
        			for(var i=1;i<3;i++){
	        			if(obj["iseditVal"+i]==1){    				
	    					$("input[name='iseditVal"+i+"']").parent().addClass("checked");
	    					$("input[name='iseditVal"+i+"']").attr("checked",true);
	    				}else{
	    					$("input[name='iseditVal"+i+"']").parent().removeClass("checked");
	    					$("input[name='iseditVal"+i+"']").attr("checked",false);
	    				}
        			}
        			
        			$("input[name='address']").val(obj.address); 			
        			$("input[name='telphone']").val(obj.telphone);
        			
        			authcode = obj.authType;
        			
        			 //加载地区
    	    		Area.init("storInfoForm",obj.provinceId,obj.cityId,obj.areaId,function(){
    	    			
    	    		});
        			
    	    		//加载店铺分类
    			    new Data({
    			    	parentId:"infotypes",
    			    	data:[{
    			    		name:"store_type",
    			    		//defaultText:"一级类型",
    			    		//defaultValue:'recreation',
    			    		defaultValue:obj.store_type
    			    	},{
    			    		name:"codeType",
    			    		//defaultText:"二级类型",
    			    		defaultValue:obj.codeType
    			    	}],
    			    	parentCode:'System.store.type'
    			    }).init();
    			    
    			    calcRewardPerson();
        		}
        		
        		addrManage.getStoreAuth("groupCode=001",function(data){
  				  var list = data.resultData.body.result;
  				  var strHtml=[];
  				  for(var i=0;i<list.length;i++){
  					  if(list[i].authCode == authcode){
  						  strHtml.push("<option value='"+list[i].authCode+"' selected='selected'>"+list[i].authName+"</option>");
  					  }else{
  					  	  strHtml.push("<option value='"+list[i].authCode+"'>"+list[i].authName+"</option>");
  					  }
  				  }
  				  $("#authType").html("");
  				  $("#authType").html(strHtml.join(""));
  			    });
        	});
        	
        	//获取赏金分配 
        	getReward(gloabObj.selCode);
    	}
    };
    
    var initData = function(){
    	//初始化
    	addrManage.getClassType("codeClass=System.allot.type", function(data){
			  if(data&&data.resultMessage.messageCode=="0000"){
				  var list = data.resultData.body.result;
				  var strHtml=[];
				  for(var i=0;i<list.length;i++){					 
					 strHtml.push("<option value='"+list[i].value+"'>"+list[i].name+"</option>");
				  }
				  $("select[name='allotPlan']").html(strHtml.join(""));
			  }  			  
			  
		});
    	
    	 //店员信息
    	var zcCols=[{
		        	"mDataProp":"id","sClass":"center","sTitle":"序号",
		        		"fnRender": function(data, type, full){
		        			return data.iDataRow+1;
		        		}
		            },
    	            {"mDataProp":"empName","sTitle":"员工姓名"},
    	            {"mDataProp":"jobNumber","sTitle":"工号"}	, 
    	            {"mDataProp":"empId","sClass":"center","sTitle":"操作","fnRender": function(data, type, full){
    	                var html='<a href="javascript:;"  class="btn yellow" style="display:none"> 查看</a>&nbsp;&nbsp;'
    	                        +'<a href="javascript:$.confirm(\'提示\',\'确认要移除?\',StoreInfo.delEmployee,'+type+');"  class="btn red"  title="'+(gloabObj.last==1?'移除后，该员工与绑定的二维码解绑，不再参与打赏':'移除后，该管理员与绑定的二维码解绑，不再参与管理')+'"> 移除</a>';
    	                return html;
    	            }
    	            }
    		];
    	 StoreInfo.empTable = Dtable.init("empList",
			//"chartForm",
			dataTableParam,
			zcCols,
			App.path+'/rs/external/merchantsEmployee/getPage',
			function(b){		
		  });
		
    };
    
    //被打赏人分成计算
	var calcRewardPerson = function(){
		  var rewardPersonAmountId = $("#rewardPersonAmountId");
		  var rewardPersonPercentId = $("#rewardPersonPercentId");
		  var rewardMoney = $("input[name='rewardMoney']").val();
		  var totalPercent = 100;
		  var totalMoney = rewardMoney ;
		  $("input[name='extRewardlist.percent']").each(function(i){
			  var percent = $(this).val();
			  if(percent!=""){
				  var money=(parseFloat(percent)/100*parseFloat(rewardMoney) ).toFixed(2);
				  totalPercent -= percent;
				  totalMoney -= money;
			  }
		  });
		  totalMoney = totalMoney==0?rewardMoney:parseFloat(totalMoney).toFixed(2);
		  totalPercent = totalPercent==0?totalPercent:parseFloat(totalPercent).toFixed(2);
		  rewardPersonAmountId.html(totalMoney?totalMoney:"");
		  rewardPersonPercentId.html(totalPercent?totalPercent:"");
		  
		  if(totalMoney>=0){			  
			  return true;
		  }else{
			  return false;
		  }
	  };
    
    var initHandler = function(){
    	//赏金分配方案改变
    	$("#allotPlan").on("change",function(){
    		  $(".allot").hide();
    		  if(this.value != "" && this.value != null){
    			  $("."+this.value).show();
    		  }		

    	  });
    	
    	  //点击新增分配方案
    	  $("#add").on('click',function(e){
    		    var vo={};
    		    vo.money=0;
    		    vo.percent=0;
    		    vo.selId="selId"+($(".selEmp").length+1);
    		    var template = document.getElementById('template-allot').innerHTML;
    			var compiled = Template7(template).compile();
    			var compiledRendered = compiled(vo);
    			$("#template-allot-content").append(compiledRendered);				
    			if(gloabObj.last==0){       
    				$(".rewardaccount").hide();
    			}else{
    				 //初始化选择人员下拉
				    $("#"+vo.selId).html(gloabObj.empList.join(""));           		    
				    App.initChosenSelect("#"+vo.selId);

    			}
    	  });
    	  
    	  //点击删除新增的分配方案
    	  $("#template-allot-content").on("click",".delreward",function(i){	
    		  var index = $(".delreward").index($(this));
    		  $(".rewardList").eq(index).remove();
    	  });
    	  
    	  //奖金计算
    	  $("#template-allot-content").on("change",".rewardmoney",function(i){
    		  var _name = $(this).attr("name");
    		  var index = $("input[name='"+_name+"']").index($(this) ) ;	
    		  var value = $(this).val();
    		  var obj = null;
    		  if(_name == "extRewardlist.money"){				 
    			  obj = $("input[name='extRewardlist.percent']").eq(index);
    			  if(!App.is_money(value)){
    				  obj.val("0");
    				  $(this).val("0");
    				  return;
    			  }
    			  var percent=(parseFloat(value)*100/parseFloat($("input[name='rewardMoney']").val() ) ).toFixed(2);
    			  obj.val(percent);
    		  }else{
    			  obj = $("input[name='extRewardlist.money']").eq(index);
    			  if(!App.is_money(value)){
    				  obj.val("0");
    				  $(this).val("0");
    				  return;
    			  }
    			  var money=(parseFloat(value)/100*parseFloat($("input[name='rewardMoney']").val() ) ).toFixed(2);
    			  obj.val(money);
    		  }
    		  var bool = calcRewardPerson();
    		  if(!bool){
    			  $(".errorMoney").eq(index).show();
    			  //obj.val("0");
				  //$(this).val("0");
    		  }else{
    			  $(".errorMoney").eq(index).hide();
    		  }
    	  });
    	  //打赏金额计算
    	  $("input[name='rewardMoney']").on("change",function(){
			  var _this = this;
			  if(!App.is_money($(_this).val())){
				  $("input[name='extRewardlist.money']").val("0");
				  return;
			  }
			  $("input[name='extRewardlist.money']").each(function(i){
				   var obj = $("input[name='extRewardlist.percent']").eq(i);
				   var money=(parseFloat(obj.val())/100*parseFloat($(_this).val() ) ).toFixed(2);
				   $(this).val(money);
			  });
			  calcRewardPerson();
		  });
    	      	  
    	  //删除店铺和机构
          $("#delStore").click(function(){
          	if(gloabObj.last==0){  
          		$.confirm("提示","确认要删除区域吗？此操作不可恢复。并且同时删除下级店铺",function(){
	          		addrManage.removeOrganize("orgCode="+gloabObj.selCode,function(data){
	              		if(data!=null && data.resultMessage.messageCode =='0000' ){
	              			 //$.alert("提示","删除成功");
	              			 //window.location.reload();
	              			initOrganize();
	              		}else{
	              			$.alert("提示","删除失败");
	              		}
	              	});
	          		
	          	});
          		
          	}else{
	          	$.confirm("提示","确认要删除店铺吗？此操作不可恢复。",function(){
	          		var json={"storeIds":gloabObj.selCode,"status":3};
	          		addrManage.removeStore(JSON.stringify(json),function(data){
	              		if(data!=null && data.resultMessage.messageCode =='0000' ){
	              			 //$.alert("提示","删除成功");
	              			 //window.location.reload();
	              			initOrganize();
	              		}else{
	              			$.alert("提示","删除失败");
	              		}
	              	});
	          		
	          	});
          	}
          	
          });
         
        
          //生成下载链接
          $("#saveQrcode").click(function(){
          	var num = parseInt($("input[name='qrcodeNum']").val());
          	if(isNaN(num)){
          		$.alert("提示","请输入数量。");
          		return;
          	}
          	if(num>100||num<0){
          		$.alert("提示","请输入小于100且大于0的数。");
          		return;
          	}
          	var tranData={storeId:gloabObj.selCode,gnum:num};
          	addrManage.saveFileInfo(JSON.stringify(tranData),function(resData){
          		if(resData!=null&&resData.resultMessage.messageCode=="0000"){
				  		var gurl=resData.resultData.body.result;
				  	    window.open(gurl, "_blank");
				  	}
          	});
          });
          
          //保存基础信息
          $("#saveInfo").click(function(){
          	var obj=$("#storInfoForm").serializeJson();
          	if(obj.isedit==null){
          		obj.isedit = 2;
          	}
          	if(obj.iseditVal1==null){
          		obj.iseditVal1 = 2;
          	}          	
          	
          	if(gloabObj.last == 0){
          		obj.orgCode = gloabObj.selCode;
          		obj.leap = parseInt(obj.leap)+1;
          		
          		if(gloabObj.parentCode != 0){
          			obj.parentCode = obj.orgCodeId;//上级机构
          		}          		
          		delete obj.orgCodeId;
          		addrManage.updateMechants(JSON.stringify(obj),function(data){
          			if(data!=null && data.resultMessage.messageCode =='0000' ){
          				$.alert("提示","保存成功");
          				initOrganize();
          			}else{
          				$.alert("错误","保存失败");
          			}
          	
          		});
          	}else{
          		obj.storeId = gloabObj.selCode;
          		obj.storeName = obj.orgName;
          		obj.storeType = obj.store_type;
          		obj.orgCode = obj.orgCodeId;//上级机构
          		delete obj.orgName;
          		delete obj.isedit;
          		delete obj.iseditVal1;
          		delete obj.iseditVal2;
          		delete obj.store_type;
          		delete obj.orgCodeId;
          		delete obj.leap;
          		
          		addrManage.editStore(JSON.stringify(obj),function(data){
          			if(data!=null && data.resultMessage.messageCode =='0000' ){
          				$.alert("提示","保存成功");
          				initOrganize();
          			}else{
          				$.alert("错误","保存失败");
          			}
          		});
          		
          	}
          	
          	
          });
          
          //保存赏金分配信息
          $('#saveRewardInfo').click(function(){        
              	var obj=$("#rewardForm").serializeJson();    
              	if(obj.isUpdateMoney == null){
	  			    	obj.isUpdateMoney = 2;
	  			}
              	if($("#rewardPersonAmountId").html()!=""&&parseFloat($("#rewardPersonAmountId").html())<0){
              		$.alert("错误提示",'分成金额不能大于100%');
  			    	return;
              	}
                //验证输入
  			    if(!App.is_money($("input[name='rewardMoney']"))){
  			    	$.alert("错误提示",'金额输入不正确');
  			    	return;
  			    }
  			    //处理祝福语为空的情况
  			    if(obj.wish==""){
            		obj.wish=" ";
            	}
  			    
  			    var loading = App.loading();
  			    
              	//部门
              	if(gloabObj.last == 0){
	                	obj.orgCode = gloabObj.selCode;
	                	obj.merchantsExtReward = obj.extRewardlist;
	                	delete obj.extRewardlist;
	                	//
	                	if(obj.allotPlan != "allot1003"){
	                		obj.merchantsExtReward = [{}];
	                	}	                	
	                	//设置方案内容
	                	for(var i=0;i<obj.merchantsExtReward.length;i++){
	                		obj.merchantsExtReward[i].orgCode = obj.orgCode;    
	                		obj.merchantsExtReward[i].allotPlan = obj.allotPlan;
	                		obj.merchantsExtReward[i].percent=obj.merchantsExtReward[i].percent/100;	                		
	                	}	                	
	                	
	            		addrManage.saveMechantsRewards(JSON.stringify(obj),function(data){
	            			loading.close();
	            			if(data!=null && data.resultMessage.messageCode =='0000' ){
	            				$.alert("提示","保存成功");
	            			}else{
	            				$.alert("错误","保存失败");
	            			}
	            	
	            		});
	            //店铺
              	}else{              	   
              	    if(obj.allotPlan == "allot1002"){
              	    	obj.extRewardlist=[{}];
              	    	obj.extRewardlist[0].empId = $("#allotEmpId").val();
              	    	obj.extRewardlist[0].money= 0;
              	    	obj.extRewardlist[0].percent = 0;
              	    }
              	    if(obj.allotPlan == "allot1001"){
              	    	obj.extRewardlist=[];
              	    }
              	    
              	    obj.isUpdate = obj.isUpdateMoney;
              	    delete obj.isUpdateMoney;
              	   
              	    
              	    obj.storeId = gloabObj.selCode;     	    
              	    
              	    addrManage.editStoreExtReward(JSON.stringify(obj),function(resdata){
              	    	loading.close();
              	    	if(resdata!=null && resdata.resultMessage.messageCode =='0000' ){
            				$.alert("提示","保存成功");
            			}else{
            				$.alert("错误","保存失败");
            			}
            	
	  				});
              	}
              
          });
          
          //创建店铺
          $("#createStore").click(function(){
        	  var template = document.getElementById('template-store').innerHTML;
			
        	  $.dataWindow("创建店铺",template,function(){
        		    var obj=$("#storeForm").serializeJson();
        		    if(obj.storeName == ""){        		    	
        		    	//$("input[name='storeName']").focus();
        		    	$(".alert").show();
        		    	$("input[name='storeName']").parent().parent().addClass("error");
        		    	return;
        		    }
        		    if(obj.orgCode==""){
        		    	obj.orgCode=orgCode;
        		    }
        		    obj.schema=orgCode;
	        		addrManage.saveStore(JSON.stringify(obj),function(resdata){
	            	    if(resdata!=null && resdata.resultMessage.messageCode =='0000' ){
	            	    	$.alert("提示","保存成功");
	            	    	/**
	            	    	var json=resdata.resultData.body.result;	          				
	          				var newNode = {id:json.storeId+"",pId:obj.orgCode,name:json.storeName,last:1,title:"在基本信息页，修改所属区域",icon:"media/image/tree_store.png"};
	          				zTree=$.fn.zTree.getZTreeObj("tree");
	          				var node=zTree.getNodeByParam("id", obj.orgCode);
	          				zTree.addNodes(node, newNode);
	          				**/
	            	    	initOrganize();
	          			}else{
	          				$.alert("错误","保存失败");
	          			}
          	
	  				});
        	  });
        	  
        	  //设置上级机构
        	  if(gloabObj.last == 0){
	        	  $("#createOrgName").val(gloabObj.selName);
	        	  $("#createOrgCode").val(gloabObj.selCode);
        	  }else{
        		  var zTree = $.fn.zTree.getZTreeObj("tree");
        		  var nodeItem=zTree.getNodeByParam("id", gloabObj.parentCode);
        		  $("#createOrgName").val(nodeItem.name);
	        	  $("#createOrgCode").val(nodeItem.id);
        	  }
        	  //加载地区
        	  Area.init("storeForm",null,null,null,function(){
    			
        	  });
			  
        	  //加载分类
			  new Data({
			    	parentId:"types",
			    	data:[{
			    		name:"storeType",
			    		//defaultText:"一级类型",
			    		//defaultValue:'recreation',
			    		defaultValue:null
			    	},{
			    		name:"codeType",
			    		//defaultText:"二级类型",
			    		defaultValue:null
			    	}],
			    	parentCode:'System.store.type'
			  }).init();

			  //加载
			  initWinOrgTree();
	
          });
          
          //创建区域
          $("#createOrgnize").click(function(){
        	  var template = document.getElementById('template-orgnize').innerHTML;
			
        	  $.dataWindow("创建区域<font style='color:#e02222; font-size:14px; margin-left:5px;'>可在区域配置中统一管理其下辖店铺的设置权限和赏金分配方案</font>",template,function(){
        		    var obj=$("#orgnizeForm").serializeJson();
        		    if(obj.orgName == ""){
        		    	$(".alert").show();
        		    	$("input[name='orgName']").parent().parent().addClass("error");
        		    	return;
        		    }
        		    if(obj.parentCode==""){
        		    	obj.parentCode=orgCode;
        		    }
        		    obj.schema=orgCode;    
        		    obj.leap = parseInt(obj.leap)+1;
	        		addrManage.saveMechants(JSON.stringify(obj),function(resdata){
	            	    if(resdata!=null && resdata.resultMessage.messageCode =='0000' ){
	            	    	$.alert("提示","保存成功");
	            	    	/**
	            	    	var json=resdata.resultData.body.result;	          				
	          				var newNode = {id:json.orgCode,pId:gloabObj.selCode,name:json.orgName,last:0};
	          				zTree=$.fn.zTree.getZTreeObj("tree");
	          				var node=zTree.getNodeByParam("id", gloabObj.selCode);
	          				zTree.addNodes(node, newNode);
	          				**/
	            	    	initOrganize();
	          			}else{
	          				$.alert("错误","保存失败");
	          			}
          	
	  				});
        	  });
        	  
        	  //设置上级机构
        	  if(gloabObj.last == 0){
	        	  $("#createOrgName").val(gloabObj.selName);
	        	  $("#createOrgCode").val(gloabObj.selCode);
	        	  $("#createOrgLeap").val(gloabObj.leap);
        	  }else{
        		  var zTree = $.fn.zTree.getZTreeObj("tree");
        		  var nodeItem=zTree.getNodeByParam("id", gloabObj.parentCode);
        		  $("#createOrgName").val(nodeItem.name);
	        	  $("#createOrgCode").val(nodeItem.id);
	        	  $("#createOrgLeap").val(nodeItem.leap);
        	  }
        	  
        	  initWinOrgTree();
        	  
          });
          
          
          //
          /**
          $("input[name='isRewardOption']").click(function(){
        	  if($(this).attr("checked")==true){
        		  $("#isRewardOptionContains").show();
        	  }else{
        		  $("#isRewardOptionContains").hide();
        	  }
          });
          **/
          
          //选择机构
          $("#choiceOrg").click(function(){
        	  if($("#choiceTree").css("display")=="none"){
        		  $("#choiceTree").show();
        	  }else{
        		  $("#choiceTree").hide();
        	  }
          });
          
          //窗口选择机构
          var initWinOrgTree=function(){
        	  var zTree = $.fn.zTree.getZTreeObj("choiceTree");
        	  zTree.setting.callback={
      	            beforeClick: function(treeId, treeNode) {
      	                var zTree = $.fn.zTree.getZTreeObj("winTree");
      	                if (treeNode.isParent) {
      	                    zTree.expandNode(treeNode);    	                        
      	                }       	              
      	            },
      	            onCheck:function(event, treeId, treeNode){      	            
      	            	$("#createOrgCode").val(treeNode.id);
      	            	$("#createOrgName").val(treeNode.name);
      	            	$("#createOrgLeap").val(treeNode.leap);
      	            	$("#winTree").hide();
      	            }
      	      };
        	  var t = $("#winTree");
    	      $.fn.zTree.init(t, zTree.setting, zTree.getNodes()); 
    	      
    	      $("#choiceWinOrg").click(function(){
    	    	  if($("#winTree").css("display")=="none"){
            		  $("#winTree").show();
            	  }else{
            		  $("#winTree").hide();
            	  }
    	      });
          };
    };
    
	
    return {
        init: function () {   
        	initOrganize();
        	initData();
        	
        	initHandler();
        },
        //删除店员
        delEmployee:function(id){
        	if(gloabObj.last==1){
            	addrManage.removeEmployee("empId="+id,function(data){
            		if(data!=null && data.resultMessage.messageCode =='0000' ){
            			 //$.alert("提示","删除成功");
            			 StoreInfo.empTable.fnDraw();
            		}else{
            			$.alert("提示","移除失败");
            		}
            	});
        	}else{
        		addrManage.deleteMerchantsEmployee("empId="+id+"&orgCode="+gloabObj.selCode,function(data){
            		if(data!=null && data.resultMessage.messageCode =='0000' ){
            			 //$.alert("提示","删除成功");
            			 StoreInfo.empTable.fnDraw();
            		}else{
            			$.alert("提示","移除失败");
            		}
            	});
        	}
        },
        //删除店长
        delManager : function(id){
        	addrManage.removeManager("empId="+id+"&storeId="+gloabObj.selCode,function(data){
        		if(data!=null && data.resultMessage.messageCode =='0000' ){
        			 //$.alert("提示","删除成功");
        			 $("#l"+id).remove();
        		}else{
        			$.alert("提示","移除失败");
        		}
        	});
        },
        //删除分成人员
        delDivided : function(id){
        	getJson(addrManage.divided.deleteDivManager,"storeId="+gloabObj.selCode+"&empId="+id,function(data){
    			if(data!=null && data.resultMessage.messageCode =='0000' ){
           			 //$.alert("提示","删除成功");
           			 $("#divided"+id).remove();
           		}else{
           			$.alert("提示","移除失败");
           		}
    		});
        },
        //修改分成人员
        editDivided : function(id){
        	var jobTitle = $("#dividedInput"+id).val()==""?" ":$("#dividedInput"+id).val();
        	var json = {"jobTitle":jobTitle,"empId":id};
        	ajaxJson(addrManage.divided.edit,JSON.stringify(json),function(data){
    			if(data!=null && data.resultMessage.messageCode =='0000' ){
           			 //$.alert("提示","删除成功");
           			 //$("#divide"+id).remove();
           		}else{
           			$.alert("提示","修改失败");
           		}
    		});
        }

    };

}();

var dataTableParam = function(){
	return {orgCode:gloabObj.selCode,last:gloabObj.last};
};





