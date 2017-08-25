var RegisterMerchants = {
	init:function(){
		RegisterMerchants.initParams();
		RegisterMerchants.showDatas();
		RegisterMerchants.initListenter();
	},
	initParams:function(){
		var $$ = require("jquery");
		var addr = require("addr");
		var oc = RegisterMerchants.orgCode = localStorage.getItem("orgCode");
		var iu = RegisterMerchants.isUpdate = localStorage.getItem("isUpdate");
		var ic = RegisterMerchants.isCreateNew = localStorage.getItem("isCreateNew");
		//只需要在本页面使用
		localStorage.removeItem("orgCode");
		localStorage.removeItem("isUpdate");
		localStorage.removeItem("isCreateNew");
		//跳转过来的页面不进行处理
		if(oc == null && ic==null){

			$$("#regbnt").show();
			//获取商户信息，如果存在跳转至状态页面
			addr.findMerchantsInfoEntity(
				{},
				function(datas) {
					datas = datas.resultData.body.result;
					var len = datas.length;
					//未注册
					if(len != 0){
						for (var i = 0; i < datas.length; i++) {
							var da = datas[i];
							if(da.schema == null){		
								window.location.href = "tips_tenant_auditing.xhtml";	
							}
						}
					}
				});
		}else{
			//如果存在orgCode修改信息
			if(oc != null){
				$$("#regbnt").html("修改");
			}
			//审核中，开始不可以编辑
			if(iu =="1"){
				$$("input").attr("disabled","disabled");
				$$("#updateInfo").show();	
			//审核不通过，直接编辑
			}else if(iu =="3"){
				$$("#regbnt").show();	
			//审核通过,不可编辑
			}else if(iu==2){
				$$("input").attr("disabled","disabled");		
			}else{
				$$("#regbnt").show();	
			}
			
			$("#agreementDiv").hide();
		}
		
	},
	/**
	 * 父编码
	 */
/*	buildCodeInfo:function(pCode,defuVal){
		var addr = require("addr");
		var url = addr.sys.getCodeInfoList;
		addr.getJson(url,"pCode=" + pCode,function(data){
			if(data!=null&&data.resultMessage.messageCode=="0000"){
				  var list = data.resultData.body.result;
				  var strHtml=[];
				  for(var i=0;i<list.length;i++){
					  if(list[i].value == defuVal){
						  strHtml.push("<option selected='selected' value='"+list[i].value+"'>"+list[i].name+"</option>");					  
					  }else{
						  strHtml.push("<option value='"+list[i].value+"'>"+list[i].name+"</option>");					  
					  }
				  }
				  $("#codeInfo").html(strHtml.join(""));
			}
		});
	},*/
	//显示数据
	showDatas:function(){
		var addr = require("addr");
		var $$ = require("jquery");
		//状态列表
		/*addr.getClassType("codeClass=System.store.type", function(data){
				  if(data&&data.resultMessage.messageCode=="0000"){
					  var list = data.resultData.body.result;
					  var strHtml=[];
					  for(var i=0;i<list.length;i++){
						  strHtml.push("<option value='"+list[i].value+"' data='"+list[i].id+"'>"+list[i].name+"</option>");
					  }
					  $("#tenantType").html("");
					  $("#tenantType").html(strHtml.join(""));
					  getRegisterMerchantsEntity();
					  RegisterMerchants.buildCodeInfo(list[0].value);
				  }
		});	*/
		//function getRegisterMerchantsEntity(){
			var delVal  = null;
			//不是从状态页面进入本页面
			if(RegisterMerchants.orgCode!=null){
				//移除店铺信息
				$("#sliderSegmentedControl").children();
				//回显信息
				var param = "";
				if(RegisterMerchants.orgCode != null){
					param = "orgCode="+RegisterMerchants.orgCode;
				}
				addr.getRegisterMerchantsEntity(
					//JSON.stringify(data),,
					param,
					function(datas){
						var status = datas.resultMessage.status;
						if(status == 0){
							var da = datas.resultData.body.result[0];
							if(da != null){
								for(key in da){
									var input = $$("input[name = "+key+"]");
									if(input.length == 0){
										//处理select 
										var sel = $$("select[name = "+key+"]");
										if(sel.length == 1){
											sel.children().each(function(){
												var me = $$(this);
												if(me.val()==da[key]){
													me.attr("selected","selected");
												}
											});
										}else{
											console.log("属性【"+key+" = "+da[key]+"]没有元素");
										}
									//处理input
									}else if(input.length == 1){
										input.val(da[key]);
									}else{
										console.log("属性【"+key+" = "+da[key]+"] 元素数量为 ："+input.length);
									}
								}

								//RegisterMerchants.buildCodeInfo(da.type,da.codeType);
								RegisterMerchants.initSelect(da);
								function getVal(name){
									return name==null?"":name;
								}
								$("#showCityPicker").html(getVal(da.typeName) + " " + getVal(da.codeTypeName));
								$("#showCityPicker3").html(getVal(da.provinceName) + " " + getVal(da.cityName)+ " " +  getVal(da.areaName));
							}
							var filePath = $("#filePath").val();
							if(filePath != ""){
								//$("#photoImg").attr("src",filePath);

						        $$(".header").children(".img").css("background-image","url('"+filePath+"')");
								//$("#photoDiv").hide();
							}
						}
					});
			}else{
				//RegisterMerchants.buildCodeInfo(list[0].value,null);	
				RegisterMerchants.initSelect({});
			}
			//getRegisterMerchantsEntity();
		//}
	},
	initSelect:function(da){
		//地区
		AreaWap.init("storeInfo","#showCityPicker3",da.provinceId,da.cityId,da.areaId,function(items,me){
			var t3 = (items[2] || {});
			var text = t3.text==null?"":t3.text;
			$("#showCityPicker3").html((items[0] || {}).text + " " + (items[1] || {}).text + " " +text);
			$("#storeInfo").find("input[name=areaId]").val(t3==null?"":t3.value);
			$("#storeInfo").find("input[name=cityId]").val((items[1] || {}).value);
			$("#storeInfo").find("input[name=provinceId]").val((items[0] || {}).value);

		});
		//类型
		DateWap.init("storeInfo","System.store.type","#showCityPicker",2,[da.type,da.codeType],function(items,me){
			$("#showCityPicker").html((items[0] || {}).text + " " + (items[1] || {}).text);
			$("#storeInfo").find("input[name=type]").val((items[0] || {}).value);
			$("#storeInfo").find("input[name=codeType]").val((items[1] || {}).value);				
		});
	},
	lock:false,
	sendPhone:function(params,verificationCodeBnt){
		var addr = require("addr");
		var jquery = require("jquery");
		var tools = require("common");
        var $ = jQuery;
		if(RegisterMerchants.lock){
			return ;
		}
		RegisterMerchants.lock = true;
		if(params.account==null){
			params.account = params.phone;
		}
		if(params.account == ""){
			 tools.toastTip(null, "请输入手机号!");
		}
		if(params.account.length<11){
		     tools.toastTip(null, "请正确输入手机号码");
		     RegisterMerchants.lock = false;
		     return ;
		}
		var i = 60;
		function verificationCode(){
			setTimeout(function(){
				$("#"+verificationCodeBnt).html(i--+"秒");
				if(i>=0){
					console.log(i);
					verificationCode();
				}else{
					end();
				}
			}, 1000);
		}
		verificationCode();
		function end(){
			i = 0;
			console.log('end');
			RegisterMerchants.lock = false;
			$("#"+verificationCodeBnt).html("重新获取");
		}
		var url = addr.mer.checkAccount;
		var phone = params.account;
		var merId = $("#merId").val();
		addr.getJson(url,"account="+phone+"&merId="+merId,function(data){
			if(data!=null&&data.resultMessage.messageCode=="0000"){

				//debugger;
				addr.gainCode(params,function(datas){
					//成功、失败处理

					var status = datas.resultMessage.status;
					if(status == 0){			
						var res = datas.resultData.body.result;
						if(res.statusCode == 1){
							//发送成功，加上锁定
							RegisterMerchants.accountLock = true;
							 tools.toastTip(null, "发送成功,请注意查收!");
						}else{
							end();
							tools.toast(null,res.description);	
						}
							
					}else{			
						end();
						tools.toast(null,datas.resultMessage.messageText);
					}
				});
			}else{
				$("input[name='account']").val("");
				 alert(data.resultMessage.messageText);
			}
		});
	
	},
	initListenter:function(){

		var addr = require("addr");
		var tools = require("common");
		//校验手机，品牌名称唯一
		RegisterMerchants.valdate();
		//修改信息
		mui('body').on('change','#tenantType',
			function(e) {
				var me = $(this);
				var opt = me.children('option:selected');
				var pid = opt.val();
				RegisterMerchants.buildCodeInfo(pid);
			});

		//提交按钮
		mui('body').on('tap','#regbnt',
			function(e) {
					if(!RegisterMerchants.checkSubmin()){
						return ;
					}
					//提交
					var data = $("#storeInfo").serializeJson();
					console.log("data:"+data);
					data.phone = data.mobile;
					addr.submitRegisterMerchants(
					JSON.stringify(data),
					function(datas){
						var status = datas.resultMessage.status;
						if(status == 0){			
							window.location.href = "tips_tenant_auditing.xhtml";		
						}else{
							if(datas!=null&&datas.resultMessage.messageCode=="0000"){
								var number=datas.resultData.body.result;
								if(number==1){
									alert("该用户已经存在,不能注册");
								}else{
									tools.toast(null,datas.resultMessage.messageText);
								}
							}else{
								tools.toast(null,datas.resultMessage.messageText);
							}
							//tools.toast(null,datas.resultData.body.result);
						}
					});
				});

		//var lock = false;
		//发送验证码
		$('#verificationCode').on('click',function(e){
			var params = $("#storeInfo").serializeJson();
			params.phone = params.account;
			RegisterMerchants.sendPhone(params,"verificationCode");
		});
		//修改信息
		mui('body').on('tap','#updateInfo',
			function(e) {
				$("input").attr("disabled",null);
				$("#regbnt").show();
				$("#updateInfo").removeClass("mui-btn-red");
				$("#updateInfo").addClass("mui-btn-gray");
			});

		//提交按钮
		mui('body').on('tap','#agreement',
			function(e) {
				window.location.href = "tips_tenant_agreement.xhtml";		
			});
	},
	/**
	 * 校验提交
	 */
	checkSubmin:function(){
		var tools = require("common");
		var $$ = require("jquery");
		var data = $("#storeInfo").serializeJson();
		//新创建商户界面校验
		var agreementBox = $("#agreementBox")[0];
		if(RegisterMerchants.orgCode==null && RegisterMerchants.isCreateNew == null && !agreementBox.checked){
			 tools.toastTip(null, "请阅读并同意众赏商户协议");
			 return false;
		}
		function checkEmail(str){
			var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
			if(myReg.test(str)){ 
				return true;
			}else{
				tools.toastTip(null, "请正确输入邮箱");
				return false;
			}
		}
		function checkNotNul(value,tip){
			if (value == "") {
				tools.toastTip(null, tip);
				return false;
			}else{
				return true;
			}
		}
		//正则验证数字
		function figure(expression){
			var reg = new RegExp("^\\d+(\\.\\d+)?$");
			return reg.test(new String(expression));
		}
		//验证码 验证必须是数字，位数为四位
		function checkVerificationCode(code){
			if(!figure(code) && code.length!=4){
				 tools.toastTip(null, "请输入正确的验证码");
				return false;
			}
			return true;
		}
		if(!checkNotNul(data.orgName,"请输入品牌名称")){
			return false;
		}
		if(!checkNotNul(data.merName,"请输入公司名称")){
			return false;
		}
//		if(!checkNotNul(data.filePath,"请上传图片")){
//			return false;
//		}
		if(!checkNotNul(data.type,"请选择分类")){
			return false;
		};
		if(!checkNotNul(data.cityId,"请选择所在地区")){
			return false;
		};
		if(!checkNotNul(data.address,"请填写详细地址")){
			return false;
		}
		if(!checkNotNul(data.legalName,"请填写商户负责人姓名")){
			return false;
		};
		if(!checkEmail(data.email)){
			return false;
		};
		//登录密码验证 6-12 位
		if(RegisterMerchants.orgCode==null){
			var pwd=data.password.trim();
			if(pwd.length<6 || pwd.length>20){
				tools.toastTip(null, "登录密码为6-20位");
				return false;
			}

			//验证手机号
			function checkMobile(s){
				if(s.length<11){
				     tools.toastTip(null, "请输入正确的手机号码");
				     return false;
				}
				return true;
			}

			if(!checkMobile(data.account)){
				return false;
			};
			//验证码
			if(!checkVerificationCode(data.code)){
				return false;
			}
		}
		/*if(!checkNotNul(data.storeName,"请输入店铺名称")){
			return false;
		}
		if(!checkNotNul(data.storeMobile,"请输入店铺电话")){
			return false;
		}else if(data.storeMobile.length<11){
			tools.toastTip(null,"请输入正确输入电话");
			return;
		}*/
		return true;
	},
	accountLock:true,
	valdate:function(){
		var addr = require("addr");
		/*$("input[name='account']").change(function(){
			var url = addr.mer.checkAccount;
			var phone = $(this).val();
			var merId = $("#merId").val();
			addr.getJson(url,"account="+phone+"&merId="+merId,function(data){
				if(data!=null&&data.resultMessage.messageCode=="0000"){
					RegisterMerchants.accountLock = false;
				}else{
					RegisterMerchants.accountLock = true;
					$("input[name='account']").val("");
					 alert(data.resultMessage.messageText);
				}
			});
		});
		$("input[name='telphone']").change(function(){
			var url = addr.mer.checkAccount;
			var phone = $(this).val();
			var merId = $("#merId").val();
			addr.getJson(url,"account="+phone+"&merId="+merId,function(data){
				if(data!=null&&data.resultMessage.messageCode=="0000"){
					RegisterMerchants.accountLock = false;
				}else{
					RegisterMerchants.accountLock = true;
					$("input[name='telphone']").val("");
					 alert(data.resultMessage.messageText);
				}
			});
		});*/
		$("input[name='orgName']").change(function(){
			var url = addr.mer.checkOrgName;
			var orgName = $(this).val();
			var merId = $("#merId").val();
			var datastr=JSON.stringify({orgName:orgName,merId:merId});
			addr.ajaxJson(url,datastr,function(data){
				if(data!=null&&data.resultMessage.messageCode=="0000"){
					
				}else{
					$("input[name='orgName']").val("");
					alert(data.resultMessage.messageText);
				}
			});
		});
	}
};
