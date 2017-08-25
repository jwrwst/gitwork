var Auditing = {
	orgCode:0,
	/**
	 * 初始化页面元素
	 */
	initPage : function($$, mui,addr){
		
		Auditing.initMerchants($$, mui, addr);
	},
	initListenter : function($$, mui) {

		var addr = require("addr");
		var tools = require("common");
		//进入商户管理
	//	mui('body').on('tap','.to_merchant_manage',function(e) {
		mui("body").on('tap', '.to_merchant_manage', function() {
			window.location.href = "tips_merchants_manage.xhtml";
		});
		mui("body").on('tap', '#abox', function() {
			$$(".scroller").css({
				"display" : "block",
				"position" : "absolute",
				"width" : "100%",
				"background" : "#efeff4",
				"top" : "50px"
			});
		});
		//选择商户
		mui("body").on('change', '#merchantsInfo', function() {
			var me = $$(this);
			//记录店铺
			var oc = Auditing.orgCode = me.val();
			//更新状态
			Auditing.buildStatus(oc);
			
		});
		//重新提交审核
		mui("body").on('tap', '#regbnt', function() {
			localStorage.setItem("orgCode", Auditing.orgCode);
			localStorage.setItem("isUpdate", "3");
			window.location.href = "tips_tenant_updateMerchants.xhtml";			
		});
		//查看详情
		mui("body").on('tap', '#showDedial', function() {
			localStorage.setItem("orgCode", Auditing.orgCode);
			localStorage.setItem("isUpdate", "1");
			window.location.href = "tips_tenant_updateMerchants.xhtml";			
		});
		//查看详情
	/*	mui("body").on('tap', '#showDedial2', function() {
			localStorage.setItem("orgCode", Auditing.orgCode);
			localStorage.setItem("isUpdate", "2");
			window.location.href = "tips_tenant_updateMerchants.xhtml";			
		});*/
		// 创建账号
		mui("body").on('tap', '#createUser', function() {
			var checkNum=$("input[name='username']").val();
			var regu =/^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			var re = new RegExp(regu);
			if (!re.test(checkNum)) {
				tools.toastTip(null,"请输入正确的手机号");
				return;
		    }
			var p1 = $("input[name='password']").val();
			var p2 = $("input[name='password2']").val();
			if(p1!=p2){
				tools.toastTip(null,"密码不一致");		
				return;
			}
			if(p1=="" || p1== null){
				tools.toastTip(null,"密码不能为空");
				return;
			}
			var form = $("#createrUser").serializeJson();
			//return alert(Auditing.orgCode);
			function createUser(){
				addr.createUser(
						"username="+form.username+"&password="+form.password+"&orgCode="+Auditing.orgCode
						+"&verificationCode="+form.verificationCode,
						function(datas) {
							var status = datas.resultMessage.status;
							if(status == 0){
								$(".status").hide();
								$("#createrUserSucess").show();
							}else{
								tools.toastTip(null,datas.resultMessage.messageText);
							}
							
						});

			}
            addr.checkMobile(
				"mobile="+$("input[name='username']").val(),
				function(datas) {
					var status = datas.resultMessage.status;
					if(status==0){
						if(datas.resultData.body.result=='true'){
							createUser();
						}else{
							tools.toastTip("error","账号已存在!");						
						}
					}else{
						tools.toast("",datas.resultMessage.messageText);
					}
						
				});
					
		});
		//获取验证码
		mui("body").on('tap', '#code', function() {
			var checkNum=$("input[name='username']").val();
			var regu =/^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			var re = new RegExp(regu);
			if (!re.test(checkNum)) {
				tools.toastTip(null,"请输入正确的手机号");
				return;
		    }
			function gainCode(){
	            //验证码接口,获取验证码的值
	            addr.gainCode(
					"phone="+$("input[name='username']").val(),
					function(datas) {
						var status = datas.resultMessage.status;
						if(status==0){
							countdown();
						}else{
							tools.toast("发送失败",datas.resultMessage.messageText);
						}
							
					});
			}
			//
            addr.checkMobile(
				"mobile="+$("input[name='username']").val(),
				function(datas) {
					var status = datas.resultMessage.status;
					if(status==0){
						if(datas.resultData.body.result=='true'){
							gainCode();
						}else{
							tools.toastTip("error","账号已存在!");						
						}
					}else{
						tools.toast("",datas.resultMessage.messageText);
					}
						
				});
		});
		function countdown(){
			var step = 59;
            $$('#code').val('重新发送60s');
            var _res = setInterval(function()
            {   
                $$("#code").attr("disabled", true);//设置disabled属性
                $$('#code').val('重新发送'+step+'s');
                step-=1;
                if(step == -1){
	                $$("#code").removeAttr("disabled"); //移除disabled属性
	                $$('#code').val('获取验证码');
	                clearInterval(_res);//清除setInterval
                }
            },1000);
		}
	},
	showDedial:function(orgCode){
		var isfir  = localStorage.getItem("isFirst");
		if(isfir != null){
			var addr = require("addr");
			var status22 = $("#status22");
			$("#status2").hide();
			status22.show();
			var param = "orgCode="+orgCode;
			
			addr.getRegisterMerchantsEntity(
				//JSON.stringify(data),,
				param,
				function(datas){
					var status = datas.resultMessage.status;
					if(status == 0){
						var da = datas.resultData.body.result[0];
						if(da != null){
							for(key in da){
								var input = status22.find("input[name = "+key+"]");
								if(input.length == 0){
									console.log("属性【"+key+" = "+da[key]+"]没有元素");
								}else if(input.length == 1){
									input.val(da[key]);
								}else{
									console.war("属性【"+key+" = "+da[key]+"] 元素数量为 ："+input.length);
									
								}
							}
						}
						$('input[name="username"]').val(da.mobile);
					}
				});
		}else{
			localStorage.setItem("isFirst","false");
		}
	},
	buildStatus:function(orgCode){
		var md = Auditing.merchantsInfos[orgCode];
		var sta = md.status;//opt.attr("status");

		$(".status").hide();
		$("#status"+sta).show();
		
		//审核不通过原因
		if(sta==3){
			$("#remarkInfo").html(md.remark);
		}else if(sta == 2){
			if( md.account == null && md.status == 2){
				$(".status").hide();
				$("#status21").show();
			}
			Auditing.showDedial(orgCode);
		}
	},
	//添加商户
	addMerchantsInfoListenter:function(isAdd){
		mui("body").on('tap', '#addMerchantsInfo', function() {
			var tools = require("common");
			if(isAdd){
				tools.confirm("<h3>确认添加新的商户？</h3>","<br/>如果是在已注册商户下添加更多店铺请在PC端访问<br/>http://www.321tips.cn/merchants",function(back){
					if(back.index==1){
						console.log(back);
						localStorage.setItem("isCreateNew", true);
						window.location.href = "tips_tenant_registerMerchants.xhtml";
					};		
				});	
			}else{
				tools.toast(null, "你的审核流程没有结束,请审核完毕并创建账号之后再添加新的商户");
			}				
			/*if(confirm("确认添加新的商户？\n如果是在已注册商户下添加更多门店请在PC端访问\nhttp://www.321tips.cn/merchants")){		
				if(isAdd){
					localStorage.setItem("isCreateNew", true);
					window.location.href = "tips_tenant_registerMerchants.xhtml";		
				}else{
					tools.toast(null, "你的审核流程没有结束,请审核完毕并创建账号之后再添加新的商户");
				}
	
				
			}*/
		});
	},
	/**
	 * 初始化商户列表 
	 */
	merchantsInfos:{},		
	initMerchants : function($$, mui, addr) {
		// /findMerchantsInfoEntityByOpenId
		addr.findMerchantsInfoEntity(
				"",
				function(datas) {
					datas = datas.resultData.body.result;
					var isAdd = false;
					//var hasUsername = false;
					var len = 0;
					var da0;
					for (var i = 0; i < datas.length; i++) {
						var da = datas[i];
						if(da.schema == da.orgCode){		
							////记录所有的商户
							Auditing.merchantsInfos[da.orgCode] = da;
							$$("#merchantsInfo").append(
									'<option value="' + da.orgCode + '">' + da.orgName
											+ '</option>	');
							//只有存在已经审核通过的商户并已经注册才可以添加新的商户
							//考虑注册多个商户，某个商户修改审核还可以添加，所以分开
							if(da.status == 2 ){
								isAdd = true;
							}
							//if(da.account !=null){
							//	hasUsername = true;
							//}
							len++;
							if(Auditing.orgCode == 0){
								//显示第一个商户的状态
								try {
									da0 = da;
									Auditing.orgCode = da.orgCode;
								} catch (e) {
									// TODO: handle exception
								}	
							}
						}
					}
					//添加选择事件
					Auditing.addMerchantsInfoListenter(isAdd);
				/*	//审核通过的只有一条记录并没有用户
					if( da0.account == null && da0.status == 2){
						//$("#status2").show();
						Auditing.showDedial(Auditing.orgCode);
						//setTimeout(function(){
							$(".status").hide();
							$("#status21").show();
						//}, 5000);
					}else{*/
						Auditing.buildStatus(Auditing.orgCode);
					//}
					
				});
	}
};

require([ "jquery", "mui", "mui_view", "addr", "common", "ftscroller" ],
		function($$, mui, mui_view, addr, tools) {
			// 初始化单页view
	Auditing.initPage($$, mui,addr);
	Auditing.initListenter($$,mui);

	//setTimeout(function(){$(".status").hide();$("#status21").show();}, 500);
});