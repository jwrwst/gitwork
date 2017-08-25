function Regist(){
	this.init = function(){
		initData();
		initListenter();
		validate();
		getMessageCode();
	};
	getMessageCode=function(){
		var lock = false;
		function sendCode(){
			if(lock){
				return ;
			}
			lock = true;
			var phone=$("#phone").val();
			if(phone.length<11){
			     alert("请正确输入手机号码");
			     lock = false;
			     return ;
			}
			var i = 60;
			function verificationCode(){
				setTimeout(function(){
					$("#messageCode").val(i--+"s");
					console.log(i);
					if(i>=0){
						verificationCode();
					}else{
						end();
					}
				}, 1000);
			}
			verificationCode();
			function end(){
				i = 0;
				lock = false;
				$("#messageCode").val("获取验证码");
			}
			var params="phone="+phone;
			addrManage.gainCode(params,function(datas){
				//成功、失败处理
				var status = datas.resultMessage.status;
				if(status == 0){			
					var res = datas.resultData.body.result;
					if(res.statusCode == 1){
						 alert("发送成功,请注意查收!");
					}else{
						end();
						alert(res.description);	
					}
						
				}else{			
					end();
					alert(datas.resultMessage.messageText);
				}
			});
		
		}
		//发送验证码
		$("#messageCode").click(function(){

			var url = addrManage.mer.checkAccount;
			var phone = $(this).val();
			var merId = $("#merId").val();
			getJson(url,"account="+phone+"&merId="+merId,function(data){
				if(data!=null&&data.resultMessage.messageCode=="0000"){
					sendCode();
				}else{
					$("input[name='phone']").val("");
					 alert(data.resultMessage.messageText);
				}
			});
		});
	};
	function initListenter(){
		$("#toLogin").click(function(){
			window.location.href="login.html";
		});
		$("input[name='phone']").change(function(){
			//addrManage.getJson();
			var url = addrManage.mer.checkAccount;
			var phone = $(this).val();
			var merId = $("#merId").val();
			getJson(url,"account="+phone+"&merId="+merId,function(data){
				if(data!=null&&data.resultMessage.messageCode=="0000"){
					
				}else{
					$("input[name='phone']").val("");
					 alert(data.resultMessage.messageText);
				}
			});
		});
		$("input[name='orgName']").change(function(){
			//addrManage.getJson();
			var url = addrManage.mer.checkOrgName;
			var orgName = $(this).val();
			var merId = $("#merId").val();
			var datastr=JSON.stringify({orgName:orgName,merId:merId});
			ajaxJson(url,datastr,function(data){
				if(data!=null&&data.resultMessage.messageCode=="0000"){
					
				}else{
					$("input[name='orgName']").val("");
					 alert(data.resultMessage.messageText);
				}
			});
		});
		/**$("input[name='code']").change(function(){
			//addrManage.getJson();
			var url = addrManage.sms.validateCode;
			var code = $(this).val();
			var phone = $("input[name='phone']").val();
			var datastr=JSON.stringify({msgCode:code,phone:phone});
			ajaxJson(url,datastr,function(data){
				if(data!=null&&data.resultMessage.messageCode=="0000"){

					 alert(data.resultMessage.messageText);
				}else{
					 alert(data.resultMessage.messageText);
				}
			});
		});*/
	}
	function sumbmit(form){
		//判断是否有营业执照
		var filePath = $("#filePath").val();
		if(filePath==""){
			var upload = $("input[name='upload']").val();
			if(upload==""){
				alert("请选择图片");
			}
		}
		//所有name加 entity.
		var inputs =  $("#registForm").find("input");
		inputs.each(function(){
			var name = $(this).attr('name');
			if(name != "code" && name != 'confirm_password' && name != 'upload' && name != 'rootCode'){
				$(this).attr("name","entity."+name);
			}
		});
		var select =  $("#registForm").find("select");
		select.each(function(){
			var name = $(this).attr('name');
			if(name != "code" && name != 'confirm_password' && name != 'upload'){
				$(this).attr("name","entity."+name);
			}
		});
		//
		form.submit();   //提交表单   
	}
	function validate(){
		
		 var validate = $("#registForm").validate({
             debug: true, //调试模式取消submit的默认提交功能   
             //errorClass: "label.error", //默认为错误的样式类为：error   
             focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
             onkeyup: false,   
             submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
                 //alert("提交表单");   
               	 var iseditVal1 = $("#iseditVal1")[0];
               	 var val = 	iseditVal1.checked;
            	 if(val){
            		sumbmit(form);
            	 }else{
            		 alert("请查看众赏商户协议");
            	 }
                 
             },   
             
             rules:{
            	 orgName:{
                     required:true
                 },
                 //upload:{
                //     required:true
                // },
                 merName:{
                     required:true
                 },
                 cityId:{
                     required:true
                 },
                 codeType:{
                     required:true
                 },
                 legalName:{
                     required:true
                 },
                 email:{
                     required:true,
                     email:true
                 },
                 phone:{
                     required:true,
                     rangelength:[11,11]
                 },
                 code:{
                     required:true
                 },
                 password:{
                     required:true,
                     rangelength:[6,10]
                 },
                 confirm_password:{
                     equalTo:"#password"
                 }                    
             },
             messages:{
            	 orgName:{
                     required:"必填"
                 },
                // upload:{
                 //    required:"必填"
                // },
                 merName:{
                     required:"必填"
                 },
                 provinceId:{
                     required:"必填"
                 },
                 cityId:{
                     required:"必填"
                 },
                 type:{
                     required:"必填"
                 },
                 codeType:{
                     required:"必填"
                 },
                 legalName:{
                     required:"必填"
                 },
                 phone:{
                     required:"必填",
                     rangelength: $.format("手机号格式不正确")
                 },
                 code:{
                     required:"必填"
                 },
                 email:{
                     required:"必填",
                     email:"E-Mail格式不正确"
                 },
                 password:{
                     required: "不能为空",
                     rangelength: $.format("密码最小长度:{0}, 最大长度:{1}。")
                 },
                 confirm_password:{
                     equalTo:"两次密码输入不一致"
                 }                                    
             }
                       
         });    
	};
	function initData(){

		function initSelect(da){
			//新增
			if(da==null){
				da = {};
			}
		    //加载地区
		    Area.init("registForm",da.provinceId,da.cityId,da.areaId);
		    
		    //加载店铺分类
		    new Data({
		    	parentId:"registData",
		    	data:[{
		    		name:"type",
		    		//defaultText:"一级类型",
		    		//defaultValue:'recreation',
		    		defaultValue:da.type
		    	},{
		    		name:"codeType",
		    		//defaultText:"二级类型",
		    		defaultValue:da.codeType
		    	}],
		    	parentCode:'System.store.type'
		    }).init();
		}
		var url = addrManage.mer.findMerchantsInfoByOrgCode;
		if(GetQueryString("type") == 3){
			initSelect();
		}else{
			getJson(url,"",function(data){
				if(data!=null&&data.resultMessage.messageCode=="0000"){			
					var status = data.resultMessage.status;
					//alert(status)
					if(status == 0){
						var da = data.resultData.body.result[0];
						//alert(da)
						if(da != null){
							for(key in da){
								var input = $("input[name = "+key+"]");
								if(input.length == 0){
								//处理input
								}else if(input.length == 1){
									input.val(da[key]);
								}else{
									console.log("属性【"+key+" = "+da[key]+"] 元素数量为 ："+input.length);
								}
							}
							
							var filePath = $("#filePath").val();
							if(filePath != ""){
								 $(".header").show();
						        $(".header").css("background-image","url('"+filePath+"')");
							}
							function getVal(name){
								return name==null?"":name;
							}	
							$("#title").html("修改信息");
							//$("input[name='upload']").val("un_change");
							$("input[name='password']").parent().parent().remove();
							$("input[name='confirm_password']").parent().parent().remove();
							$("input[name='phone']").parent().parent().remove();
							$("input[name='code']").parent().parent().remove();
							initSelect(da);
						}else{
							initSelect();
						}
						//$("#showCityPicker").html(getVal(da.typeName) + " " + getVal(da.codeTypeName));
						//$("#showCityPicker3").html(getVal(da.provinceName) + " " + getVal(da.cityName)+ " " +  getVal(da.areaName));
					}else{
						initSelect();
					}
				
					
					
				}else{
					 alert(data.resultMessage.messageText);
				}
			});
		}

	}
}