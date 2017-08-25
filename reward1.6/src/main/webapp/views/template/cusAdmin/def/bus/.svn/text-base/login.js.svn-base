var Login = function () {
    
    return {
        //main function to initiate the module
        init: function () {
           //设置用户名
           $("input[name='account']").val($.cookie("account"));
           if($.cookie("account")){
        	   $("input[name='remember']").parent().addClass("checked");
           }
           $("input[name='remember']").attr("checked",true);
        		   
           $('.login-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	            	account: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	            	account: {
	                    required: "用户名不能为空."
	                },
	                password: {
	                    required: "密码不能为空."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                $('.alert-error', $('.login-form')).show();
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
	            	var obj=$(".login-form").serializeJson();
	            	obj.remember?$.cookie("account", obj.account):$.cookie("account", "");	            	
	            	delete obj.remember;
	        		addrManage.login(JSON.stringify(obj),function(data){
	        			if(data.resultData==null){
	        				$('.alert-error-pwd', $('.login-form')).show();
	        			}else{
	        				var json = data.resultData.body.result;
	        				if(json.status == 2){		        				
	        					var orgCode=json.orgCode;
	        					var orgList=[json];
	        					window.localStorage.setItem("orgCode", orgCode);
	        					window.sessionStorage.setItem("orgList", JSON.stringify(orgList ) );
	        					window.location.href = "index.xhtml";        					
	        				}else{
	        					window.sessionStorage.setItem("status", json.status );
	        					window.location.href = "regist2.xhtml"; 
	        				}
	        			}
	        	
	        		});
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                	var obj=$(".login-form").serializeJson();
	                	delete obj.remember;
		        		addrManage.login(JSON.stringify(obj),function(data){
		        			if(data.resultData==null){
		        				$('.alert-error-pwd', $('.login-form')).show();
		        			}else{
		        				var json = data.resultData.body.result;
		        				if(json.status == 2){		        				
		        					var orgCode=json.orgCode;
		        					var orgList=[json];
		        					window.localStorage.setItem("orgCode", orgCode);
		        					window.sessionStorage.setItem("orgList", JSON.stringify(orgList ) );
		        					window.location.href = "index.xhtml";        					
		        				}else{
		        					window.sessionStorage.setItem("status", json.status );
		        					window.location.href = "regist2.xhtml"; 
		        				}
		        			}
		        	
		        		});
	                }
	                return false;
	            }
	        });	        
	            

	        $('.forget-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                email: {
	                    required: true,
	                    email: true
	                }
	            },

	            messages: {
	                email: {
	                    required: "Email is required."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   

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
	                window.location.href = "index.xhtml";
	            }
	        });

	        $('.forget-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.forget-form').validate().form()) {
	                    window.location.href = "index.xhtml";
	                }
	                return false;
	            }
	        });

	        jQuery('#forget-password').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.forget-form').show();
	        });

	        jQuery('#back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.forget-form').hide();
	        });

	        $('.register-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                rpassword: {
	                    equalTo: "#register_password"
	                },
	                email: {
	                    required: true,
	                    email: true
	                },
	                tnc: {
	                    required: true
	                }
	            },

	            messages: { // custom messages for radio buttons and checkboxes
	                tnc: {
	                    required: "Please accept TNC first."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   

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
	                if (element.attr("name") == "tnc") { // insert checkbox errors after the container                  
	                    error.addClass('help-small no-left-padding').insertAfter($('#register_tnc_error'));
	                } else {
	                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	                }
	            },

	            submitHandler: function (form) {
	                window.location.href = "index.xhtml";
	            }
	        });

	        jQuery('#register-btn').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.register-form').show();
	        });

	        jQuery('#register-back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.register-form').hide();
	        });
	        
	        //获取商户列表
	        var queryLogin = function(uid){	
	        	   $("#bgload").show();
	        	   setTimeout(function(){
	        		   $("#bgload").hide();
	        	   }, 3000);
				   addrManage.loginMerchantInfoList(uid,function(data){
						 if(data!=null && data.resultMessage.messageCode =='0000' ){
						    var orgCode=data.resultData.body.result[0].orgCode;
						    window.sessionStorage.setItem("orgList", JSON.stringify(data.resultData.body.result ) );
						    window.localStorage.setItem('orgCode', orgCode);  
						    window.location.href = 'index.xhtml';
						 } 
				   });
			};
	        
	        //扫码登录;初始化时间戳
	        addrManage.getRandId(null,function(data){
	        	$("#bgload").hide();
				if(data!=null && data.resultMessage.messageCode =='0000' ){
					var obj=data.resultData.body.result;
					var url = encodeURIComponent(App.path + "/rs/wechat/goBaseLogin?state=9&data="+obj);//扫码后跳转页面					
					$("#loginQrcode").attr("src","/rs/qrcode?text="+url);					
					setInterval(function(){
						queryLogin(obj);
					},10000);
				}
			});     
        }
	        
	    
    }; 
   
    
}();