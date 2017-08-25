var MerchantsInfo = function () {

	var orgCode = "0";
	
    var handleTimePickers = function () {
        
        if (jQuery().timepicker) {
            $('.timepicker-default').timepicker();
            $('.timepicker-24').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            });

        }
    };
    
    
    
	
    	
    	//初始化店铺
    	var initRewardInfo = function(){
    		addrManage.getMechants(JSON.stringify({orgCode:orgCode,orgId:0}),function(data){
    			
    			if(data!=null && data.resultMessage.messageCode =='0000' ){
    				var obj=data.resultData.body.result;
    				orgCode = obj.orgCode;
    				$("#qrcode").attr("src","/rs/qrcode?text="+obj.orgCode);
    				$("input[name='orgCode']").val(obj.orgCode);
    				$("input[name='orgName']").val(obj.orgName);
    				$("input[name='rewardMoney']").val(obj.rewardMoney);
    				$("input[name='busStartTime']").val(obj.busStartTime);
    				$("input[name='busEndTime']").val(obj.busEndTime);
    				if(obj.isUpdateMoney==1){
    					$("input[name='isUpdateMoney']").parent().addClass("checked");
    					$("input[name='isUpdateMoney']").attr("checked",true);
    				}
    				if(obj.isedit==1){    				
    					$("input[name='isedit']").parent().addClass("checked");
    					$("input[name='isedit']").attr("checked",true);
    				}
    				$("input[name='email']").val(obj.email);
    				$("input[name='account']").val(obj.account);
    				$("input[name='password']").val(obj.password);
    				
    				//$("input[name='store_type']").val(obj.store_type);
    				$("input[name='address']").val(obj.address);
    				$("input[name='telphone']").val(obj.telphone);
    				$("#photo").attr("src",obj.photo);
    				
    				 new Data({
    				    	parentId:"types",
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
    				
    			}
    			
	    		addrManage.getMechantsStores("orgCode="+orgCode+"&status=1",function(data){
		    		if(data&&data.resultMessage.messageCode=="0000"){
		    			  var list = data.resultData.body.result;
		    			  addrManage.getRewardStoreList("orgCode="+orgCode,function(data){
		    				  
		    				  var storeRewardArr=[];
		    				  if(data&&data.resultMessage.messageCode=="0000"&&data.resultData!=null){
		    					  var storeRewardList=data.resultData.body.result;    					  
		    					  for(var j=0;j<storeRewardList.length;j++){
		    						  storeRewardArr.push(storeRewardList[j].storeId);
								  }
								 
		    				  }
		    				  
		    				  var strHtml=[];
							  for(var i=0;i<list.length;i++){
								 if(storeRewardArr.indexOf(list[i].storeId)>-1){
									 strHtml.push("<option value='"+list[i].storeId+"' selected>"+list[i].storeName+"</option>");
								 }else{
									 strHtml.push("<option value='"+list[i].storeId+"'>"+list[i].storeName+"</option>");
								 }
								 
							  }
							  
							  $('#my_multi_select2').html(strHtml.join(""));
							  
							  $('#my_multi_select2').multiSelect({
						            selectableOptgroup: true,
						            
						      }); 
						  
		    			  });
		    		}
		    	});
		    	
		    	
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
					  
					  addrManage.getMechantsRewards("orgCode="+orgCode,function(data){
						  console.log(data)
						  if(data!=null && data.resultMessage.messageCode =='0000' &&data.resultData!=null){
							    var list = data.resultData.body.result;
							    var allot="";
							    for(var i=0;i<list.length;i++){
							    	allot = list[i].allotPlan;
								    var vo={money:list[i].money,percent:list[i].percent};
								    var template = document.getElementById('template-allot').innerHTML;
									var compiled = Template7(template).compile();
									var compiledRendered = compiled(vo);
									$("#template-allot-content").append(compiledRendered);	
							    }
							    if(allot!=""){
							    	$("#allotPlan").val(allot);
							    	$("#allotPlan").trigger("change");
							    }
				    	  }
					  });
					  
					  
					  
				  });
	    	
    		});
	    };
	    

    
    
	  $("#allotPlan").on("change",function(){
		  $(".allot1003").hide();
		  if(this.value != "" && this.value != null){
			  $("."+this.value).show();
		  }		

	  });
	
	  //点击新增分配方案
	  $("#add").on('click',function(e){
		    var vo={};
		    vo.money=0;
		    vo.percent=0;
		    var template = document.getElementById('template-allot').innerHTML;
			var compiled = Template7(template).compile();
			var compiledRendered = compiled(vo);
			$("#template-allot-content").append(compiledRendered);				
			
	  });
	  
	  //点击删除新增的分配方案
	  $("#template-allot-content").on("click","a",function(i){		 
		  $(this).parent().parent().remove();
	  });
	  
	  //奖金计算
	  $("#template-allot-content").on("change",".rewardmoney",function(i){
		  var _name = $(this).attr("name");
		  var index = $("input[name='"+_name+"']").index($(this) ) ;	
		  var value = $(this).val();
		  if(_name == "merchantsExtReward.money"){				 
			  var obj = $("input[name='merchantsExtReward.percent']").eq(index);
			  if(!App.is_money(value)){
				  obj.val("0");
				  $(this).val("0");
				  return;
			  }
			  var percent=(parseFloat(value)*100/parseFloat($("input[name='rewardMoney']").val() ) ).toFixed(2);
			  obj.val(percent);
		  }else{
			  var obj = $("input[name='merchantsExtReward.money']").eq(index);
			  if(!App.is_money(value)){
				  obj.val("0");
				  $(this).val("0");
				  return;
			  }
			  var money=(parseFloat(value)/100*parseFloat($("input[name='rewardMoney']").val() ) ).toFixed(2);
			  obj.val(money);
		  }
	  });
   
    
    return {
        init: function () {         
            handleTimePickers(); 
            initRewardInfo();
           
            
            $('#mechantsForm').validate({
                errorElement: 'label', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                	orgName: {
                        required: true
                    },
                    rewardMoney: {
                        required: true
                    },
                    account: {
                        required: true
                    }
                },

                messages: {
                	orgName: {
                        required: "商户名称不能为空."
                    },
                    rewardMoney: {
                        required: "赏金不能为空."
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    $('.alert-error', $('#mechantsForm')).show();
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.control-group').addClass('error'); // set error class to the control group
                },

                success: function (label) {
                    label.closest('.control-group').removeClass('error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                },

                submitHandler: function (form) {
                	var obj=$("#mechantsForm").serializeJson();
                	if(obj.isUpdateMoney == null){
                		obj.isUpdateMoney="2";
                	}
                	if(obj.isedit == null){
                		obj.isedit="2";
                	}
                	
            		addrManage.updateMechants(JSON.stringify(obj),function(data){
            			if(data!=null && data.resultMessage.messageCode =='0000' ){
            				$.alert("提示","保存成功");
            			}else{
            				$.alert("错误","保存失败");
            			}
            	
            		});
                }
            });
            
            $('#rewardForm').validate({
                errorElement: 'label', //
                errorClass: 'help-inline', // 
                focusInvalid: false, //
                invalidHandler: function (event, validator) { //
                    $('.alert-error', $('#rewardForm')).show();
                },

                highlight: function (element) { // 
                    $(element)
                        .closest('.control-group').addClass('error'); // 
                },

                success: function (label) {
                    label.closest('.control-group').removeClass('error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                },

                submitHandler: function (form) {
                	var obj=$("#rewardForm").serializeJson();
                	obj.orgCode = orgCode;
                	for(var i=0;i<obj.merchantsExtReward.length;i++){
                		obj.merchantsExtReward[i].orgCode = orgCode;    
                		obj.merchantsExtReward[i].allotPlan = obj.allotPlan;
                	}
                	for(var i=0;i<obj.merchantsRewardStoreEntity.length;i++){
                		obj.merchantsRewardStoreEntity[i].orgCode = orgCode;                		
                	}

            		addrManage.saveMechantsRewards(JSON.stringify(obj),function(data){
            			if(data!=null && data.resultMessage.messageCode =='0000' ){
            				$.alert("提示","保存成功");
            			}else{
            				$.alert("错误","保存失败");
            			}
            	
            		});
                }
            });
        }

    };

}();