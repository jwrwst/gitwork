var Manage = {

		orgCode:null,
		init:function(){
			require([ "jquery", "mui", "mui_view", "addr", "common", "ftscroller" ],
					function($$, mui, mui_view, addr, tools) {
				//区域经理直接进来移除
				if(parentCode == ""){
					localStorage.removeItem("isMerManage");
				}
				Manage.isMerManage = localStorage.getItem("isMerManage"),
				Manage.type = 0;
				Manage.buildMerchantsInfoSel(addr);

				Manage.initCommontListenters(addr,$$);
				Manage.initStoreLitenters(addr,$$,tools);
				Manage.initMerLitenters(addr,$$,tools);			

				Manage.initMer(addr,$$);
				
			});
		},
		initAreaMer:function(){
			require([ "jquery", "mui", "mui_view", "addr", "common", "ftscroller" ],
					function($$, mui, mui_view, addr, tools) {
				//区域经理直接进来移除
				if(parentCode == ""){
					localStorage.removeItem("isMerManage");
				}
				Manage.isMerManage = localStorage.getItem("isMerManage"),
				Manage.type = 1;
				Manage.buildMerchantsInfoSel(addr);
				Manage.initStoreLitenters(addr,$$);		
				Manage.initAreaMerLitenters(addr,$$,tools);				
			});
		},

		/**
		 * 区域管理添加事件
		 */
		initAreaMerLitenters:function(addr,$$,tools){
			//搜索店铺
			mui("body").on('tap', '#search', function() {
				//更新状态
				Manage.buildStore(addr,Manage.orgCode);
				Manage.buildOrg(addr,Manage.orgCode,Manage.schema);
			});
			//选择商户
			mui("body").on('change', '#merchantsInfo', function() {
				var me = $(this);
				//记录店铺
				Manage.orgCode = me.val();
				var me = $(this);
				//记录店铺
				var parentCode = me.val();
				//记录店铺
				var schema = Manage.schema = me.children('option:selected').attr("data-schema");
				//更新状态
				Manage.buildStore(addr,parentCode);
				Manage.buildOrg(addr,parentCode,schema);
				
			});

			//单选按钮被覆盖，所以事件添加到父级节点		
			mui("body").on('tap', 'li.mui-table-view-cell', function() {
				var type = $(this).attr("data-type");
				var code = $(this).attr("data-code");
				localStorage.setItem("_parent_code",code);
				//区域
				if(type == '1'){
					window.location.href = basePath+"tips_merchants_orgmanage.xhtml?param.data="+Manage.schema+"|"+code;	
				//店铺
				}else{
					localStorage.setItem("empId", empId);
					localStorage.setItem("storeId", Manage.getStoreId($(this)));
					localStorage.setItem("tip_orgCode",Manage.orgCode);
					//alert(Manage.orgCode);
					window.location.href = basePath+"tips_store_sub_storeinfo.xhtml";	
				}
			});
		},
		getStoreId:function(li){
			var a = li.children("a")[0];
			var storeId = $(a).attr("data");
			return storeId;
		},
		getStoreIds:function(li){
			var ids = "";
			$(".check_box_checked").each(function(){
				var me = $(this);
				var storId = Manage.getStoreId(me.parent());
				if(storId!=null){
					ids += storId + ",";			
				}
			});
			if(ids.length>0){
				ids = ids.substring(0, ids.length-1);
			}
			return ids;
		},
		checkSubmin:function(){
			var tools = require("common");
			var $$ = require("jquery");
			var data = $("#storeInfo").serializeJson();

			
			//验证手机号
			function checkMobile(s){
				if(s.length<11){
				     tools.toastTip(null, "请正确输入手机号码");
				     return false;
				}
				return true;
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
			//定义获取的输入值
			var merName=data.merName;

			if(!checkNotNul(data.name,"请输入全名")){
				return false;
			}
			if(!checkMobile(data.mobile)){
				return false;
			};
			if(!checkNotNul(merName,"请输入商户名称")){
				return false;
			};

			if(!checkEmail(data.email)){
				return false;
			};
			if(!checkNotNul(data.filePath,"请上传图片")){
				return false;
			}
			if(!checkNotNul(data.storeName,"请输入店铺名称")){
				return false;
			}
			return true;
		},
		/**
		 * 初始化商户管理
		 */
		initMer:function(){
			$("#storeInfo").find("input").attr('disabled','disabled');		
			$("#storeInfo").find("select").attr('disabled','disabled');
		},
		//公共事件
		initCommontListenters:function(addr,$$){

			//选择商户
			mui("body").on('change', '#merchantsInfo', function() {
				var me = $(this);
				var me = $(this);
				//记录店铺
				Manage.schema = me.children('option:selected').attr("data-schema");

				if(parentCode!='' && parentCode.split("|")[0] == Manage.schema){
					Manage.schema = parentCode.split("|")[0];
					Manage.orgCode = parentCode.split("|")[1];
				}else{
					//记录店铺					
					Manage.orgCode = me.val();				
					
				}
					
				//更新状态
				Manage.buildStore(addr);
				Manage.buildOrg(addr);
				//更新商户信息
				Manage.showMerDatas();
				
			});
			//店铺管理
			mui("body").on('tap', '#storeManage', function() {
				$("#merInfo").removeClass("active");
				$(this).addClass("active");
				$("#tab1").show();
				$("#tab2").hide();
			});
			//商户管理
			mui("body").on('tap', '#merInfo', function() {
				$("#storeManage").removeClass("active");
				$(this).addClass("active");
				$("#tab2").show();
				$("#tab1").hide();
			});

			//账户与安全
			mui("body").on('tap', '#userInfo', function() {
				localStorage.setItem("_user_orgCode",Manage.orgCode);
				window.location.href = basePath+"tips_merchants_account.xhtml";	
			});
		},
		//商户相关事件
		initMerLitenters:function(addr,$$,tools){
			//删除商户
			mui("body").on('tap', '#deleteMer', function() {
				tools.confirm("删除商户后","此商户的信息无法找回哦",function(back){
					if(back.index==1){
						var url = addr.mercants.del;
						addr.getJson(url,"orgCode=" + Manage.orgCode,function(data){
							if(data!=null&&data.resultMessage.messageCode=="0000"){
								tools.toastTip(null,"删除成功");
								Manage.buildMerchantsInfoSel(addr);
							}else{
								tools.toast("错误","删除失败,请直接联系众赏或再试一次.");
							}
						});
					}
				});
			});
			function update(back){
				if(!Manage.checkSubmin()){
					return ;
				}
				//提交
				var data = $("#storeInfo").serializeJson();
				addr.submitRegisterMerchants(
				JSON.stringify(data),
				function(datas){
					var status = datas.resultMessage.status;
					if(status == 0){			
						tools.toast(null,"修改成功");	
						back();
					}else{
						tools.toast(null,datas.resultMessage.messageText);
					}
				});
			}
			//提交按钮
			mui('.mui-content-padded').on('tap','#regbnt',
				function(e) {
				update();
			});
			var b = false;
			//修改信息
			mui('body').on('tap','#updateInfo',
				function(e) {
					if(b){
						update(function(){
							$("input").attr("disabled","disabled");
							$("input").attr("disabled","disabled");
							$("input[name='merName']").attr("disabled",null);
							//$("#regbnt").hide();
							//$("#deleteMer").show();
							//$("#updateInfo").addClass("mui-btn-red");
							//$("#updateInfo").removeClass("mui-btn-gray");
							$("#updateInfo").html("修改信息");
						});
						b = !b;
					}else{
						$("input").attr("disabled",null);
						$("select").attr("disabled",null);
						$("input[name='merName']").attr("disabled","disabled");
						$("#updateInfo").html("保存");
						//$("#regbnt").show();
						//$("#deleteMer").hide();
						//$("#updateInfo").removeClass("mui-btn-red");
						//$("#updateInfo").addClass("mui-btn-gray");
						b = !b;
					}
				});
		},
		//店铺功能相关事件
		initStoreLitenters:function(addr,$$,tools){

			function updateStatus(status,ids,info){
			    var data = {status:status,storeIds:ids};		
				var datastr=JSON.stringify(data);
				var url = addr.store.updateStatus;
				addr.ajaxJson(url,datastr,function(data){
					if(data!=null&&data.resultMessage.messageCode=="0000"){
						tools.toastTip(null,info+"成功");
						Manage.buildStore(addr);
					}else{
						tools.toast("错误",info+"失败,请直接联系众赏或再试一次.");
					}
				});
			}
			//启用
			mui("body").on('tap', '.store_name_status_edit', function() {
				var ids = Manage.getStoreId($(this).parent());
				updateStatus(1, ids,"启用");
			});
			//停用
			mui("body").on('tap', '#confirmdisabled', function() {
				tools.confirm("确认停用所选店铺","",function(back){
					if(back.index==1){
						updateStatus(2, Manage.getStoreIds(),"停用");
					}
				});	
			});
			//删除
			mui("body").on('tap', '#confirmdelete', function() {
				tools.confirm("删除店铺后","此店铺的信息无法找回哦",function(back){
					if(back.index==1){
						updateStatus(3, Manage.getStoreIds(),"删除");
					}
				});	
			});
			
			//搜索店铺
			mui("body").on('tap', '#search', function() {
				Manage.buildStore(addr);
				Manage.buildOrg(addr);
			});
			//编辑
			mui("body").on('tap', '#touchchange', function() {
				$$(".normal").css("display","none");
				$$(".changepage").css("display","block");
				/*$$(".mui-navigate-right").css("display","none");*/ 
				$$(".mui-table-view>li>a").removeClass("mui-navigate-right");
				$$(".mui-table-view>li>a:not(.mui-btn)").css("margin","-11px -50px");
				$$(".mui-table-view>li").addClass("mui-checkbox");
				$$(".mui-table-view>li").addClass("mui-left");
				$$(".mui-table-view>li").attr("name","checkbox");
				$$(".mui-table-view>li>input").show();
				$$(".alltouch").show();
				Manage.status = "edit";

				$$(".store_name_status").html("启用");
				$$(".store_name_status").addClass("store_name_status_edit");
				$$(".store_name_status").removeClass("store_name_status");
				/*$$(".mui-left").css({"name":"checkbox","type":"checkbox"});*/
			});
			//完成
			mui("body").on('tap', '#successchange', function() {
				$$(".normal").css("display","block");
				$$(".changepage").css("display","none");
				$$("mui-navigate-right").css("display","block");
				$$(".mui-table-view>li>input").hide();
				$$(".alltouch").hide();
				$$(".mui-table-view>li>a").addClass("mui-navigate-right");
				$$(".mui-table-view>li>a:not(.mui-btn)").css("margin","-11px 15px");
				$$(".mui-table-view>li").removeClass("mui-checkbox");
				$$(".mui-table-view>li").removeClass("mui-left");
				Manage.status = "show";
				$$(".store_name_status_edit").html("已停用");
				$$(".store_name_status_edit").addClass("store_name_status");
				$$(".store_name_status_edit").removeClass("store_name_status_edit");
			});
			
			//全选
			mui("body").on('tap', '#all-check', function() {
				var input = $$(this).children("input")[0];
				 if(input.checked){    
				        $$(".mui-table-view>li>input").attr("checked", false); 
				        $$(".mui-table-view>li>input").removeClass("check_box_checked"); 
				        $$(input).removeClass("check_box_checked"); 
				        
				    }else{    
				    	$$(".mui-table-view>li>input").attr("checked", "checked"); 
					    $$(".mui-table-view>li>input").addClass("check_box_checked"); 
					    $$(input).addClass("check_box_checked"); 
				    } 
			});

			//单选按钮被覆盖，所以事件添加到父级节点		
			mui("body").on('tap', 'li.mui-table-view-cell', function() {
				var type = $(this).attr("data-type");
				var code = $(this).attr("data-code");
				localStorage.setItem("_parent_code",code);
				if(Manage.isMerManage == null){
					var selName =  $('#merchantsInfo').children('option:selected').text();
					localStorage.setItem("isMerManage",selName);
				}
				//区域
				if(type == '1'){
					window.location.href = basePath+"tips_merchants_orgmanage.xhtml?param.data="+Manage.schema+"|"+code;	
				//店铺
				}else{
					localStorage.setItem("empId", empId);
					localStorage.setItem("storeId", Manage.getStoreId($(this)));
					localStorage.setItem("tip_orgCode",Manage.orgCode);
					//alert(Manage.orgCode);
					window.location.href = basePath+"tips_store_sub_storeinfo.xhtml";	
				}
			});
			//单选按钮被覆盖，所以事件添加到父级节点		
			/*mui("body").on('tap', 'li.mui-table-view-cell', function() {

				if(Manage.status=='edit'){
					var all = $$('#all-check').children("input")[0];
					var input = $(this).children("input")[0];
					//不选
					if($$(input).hasClass('check_box_checked')){    		 
						$$(input).removeClass("check_box_checked");	
						$$(all).removeClass("check_box_checked");	
				        $$(all).attr("checked", false);
				        $$(input).attr("checked", false);
				     //选中
					}else{    
						$$(input).addClass("check_box_checked");	
						var b = true;
						$("li.mui-table-view-cell>input").each(function(){
							if(!$(this).hasClass('check_box_checked')){ 
								b = false;
							}
						});
						if(b){
							$$(all).addClass("check_box_checked");	
					        $$(all).attr("checked", "checked");
						}
					} 
				//添加门店页面
				}else{
					localStorage.setItem("_storeId",Manage.getStoreId($(this)));
					
					Manage.toAddStore();
				}
			});*/
			//单选			
			mui("body").on('tap', '#addStore', function() {
				Manage.toAddStore();
			});
		},
		toAddStore:function(){
			localStorage.setItem("_orgCode",Manage.orgCode);
			window.location.href = basePath+"tips_store_addStore.xhtml";		
		},
		//显示数据
		showMerDatas:function(){
			var addr = require("addr");
			var $$ = require("jquery");
			//状态列表
			/*addr.getClassType("codeClass=System.store.type", function(data){
					  if(data&&data.resultMessage.messageCode=="0000"){
						  var list = data.resultData.body.result;
						  var strHtml=[];
						  for(var i=0;i<list.length;i++){
							  strHtml.push("<option value='"+list[i].value+"'>"+list[i].name+"</option>");
						  }
						  $$("#tenantType").html("");
						  $$("#tenantType").html(strHtml.join(""));
					  }
			});	*/
			//不是从状态页面进入本页面
			if(Manage.orgCode!=null){
			//回显信息
				var param = "";
				if(Manage.orgCode != null){
					param = "orgCode="+Manage.orgCode;
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
										console.war("属性【"+key+" = "+da[key]+"] 元素数量为 ："+input.length);
									}
								}

								var filePath = $("#filePath").val();
								if(filePath != ""){
									//$("#photoImg").attr("src",filePath);

							        $$(".header").children(".img").css("background-image","url('"+filePath+"')");
									//$("#photoDiv").hide();
								}
								//类型，地区
								Manage.initSelect(da);
								function getVal(name){
									return name==null?"":name;
								}
								$("#showCityPicker").html(getVal(da.typeName) + " " + getVal(da.codeTypeName));
								$("#showCityPicker3").html(getVal(da.provinceName) + " " + getVal(da.cityName)+ " " +  getVal(da.areaName));
								
							}
							//地区
							//Area.init("storeInfo",da.provinceId,da.cityId,da.areaId);
						}
						
					});
			}
		},

		/**
		 * 构建商户
		 */
		buildMerchantsInfoSel:function(addr){
			//选择的
			var def = localStorage.getItem("selMerchants");

			//从商户管理页面进入
			if(Manage.isMerManage != null){
				$('body').show();
				$("#merchantsInfo").prev().remove();
				$("#merchantsInfo").html("<option >" + Manage.isMerManage+ "</option>");
				$("#merchantsInfo").attr("readonly","readonly");
				Manage.schema = parentCode.split("|")[0];
				Manage.orgCode = parentCode.split("|")[1];
				Manage.buildStore(addr);
				Manage.buildOrg(addr);
			}else{
				// 初始化单页view1
				addr.findMerchantsInfoEntity(
						"status=2",
						function(datas) {
							datas = datas.resultData.body.result;
							len = datas.length;
							var url = basePath+'tips_merchants_assistant.xhtml?param.data='+Manage.type;
							//不是区域经理
							var isShow = false;
							if(len == 0){
								window.location.href = url;
							}
							$("#merchantsInfo").html();
							//跳转的页面
							if(parentCode!=''){
								Manage.schema = parentCode.split("|")[0];
								Manage.orgCode = parentCode.split("|")[1];
							}
							for (var i = 0; i < len; i++) {
								var da = datas[i];
								var b = da.schema != da.orgCode;
								//0:商户  1 区域经理
								b = Manage.type == 1?b:!b;
								if(b){
									////记录所有的商户
									if(def == da.orgCode){
										$("#merchantsInfo").append('<option selected="selected" value="' + da.orgCode + '" data-schema="'+da.schema+'">' + da.orgName+ '</option>');
										Manage.schema = da.schema;
										Manage.orgCode = da.orgCode;
									}else{
										$("#merchantsInfo").append('<option value="' + da.orgCode + '" data-schema="'+da.schema+'">' + da.orgName+ '</option>');
										if(Manage.orgCode == null){ 								
											//构建门店、区域列表
											Manage.schema = da.schema;
											Manage.orgCode = da.orgCode;
										}
									}
								}
							}
							//只有商户
							if(Manage.schema == null || Manage.schema == 'undefined'){
								window.location.href = url;		
							}else if(len != 0){
								isShow = true;
							}
							if(isShow){
								$('body').show();
							}
							
							if(parentCode==''){
								parentCode = Manage.schema+"|"+Manage.orgCode;
							}
							Manage.buildStore(addr);
							Manage.buildOrg(addr);
							//加载完成后加载商户信息
							if(Manage.type==0){
								Manage.showMerDatas();							
							}
					});	
	
				
				}
		},
		initSelect:function(da){
			//地区
			AreaWap.init("storeInfo","#showCityPicker3",da.provinceId,da.cityId,da.areaId,function(items,me){
				var t3 = (items[2] || {});
				var text = t3.text==null?"":t3.text;
				$("#showCityPicker3").html((items[0] || {}).text + " " + (items[1] || {}).text + " " +text);
				$("input[name=areaId]").val(t3==null?"":t3.value);
				$("input[name=cityId]").val((items[1] || {}).value);
				$("input[name=provinceId]").val((items[0] || {}).value);

			});
			//类型
			DateWap.init("storeInfo","System.store.type","#showCityPicker",2,[da.type,da.codeType],function(items,me){
				$("#showCityPicker").html((items[0] || {}).text + " " + (items[1] || {}).text);
				$("input[name=type]").val((items[0] || {}).value);
				$("input[name=codeType]").val((items[1] || {}).value);				
			});
		},
		/**
		 * 构建下级区域
		 */
		buildOrg:function(addr){
			var url = addr.mercants.getListByParentCode;	
			var datastr=JSON.stringify({parentCode:Manage.orgCode,schema:Manage.schema,orgName:$("#searchVal").val()});
			addr.ajaxJson(url,datastr,function(data){
				if(data!=null&&data.resultMessage.messageCode=="0000"){
					data = data.resultData.body.result;	
					Manage.buildDatas(data);
				}				
			});
		},
		/**
		 * 构建门店
		 */
		buildStore:function(addr){
			//var orgcode = Manage.orgCode;
			$("#storeLists").html("");
			addr.searchStoreListByOpenId(
					JSON.stringify({orgCode:Manage.orgCode,storeName:$("#searchVal").val()}),
					function(datas) {
						datas = datas.resultData.body.result;
						Manage.buildDatas(datas);				
				});
			
		},
		buildDatas:function(datas){
			//var isAdd = false;
			for (var i = 0; i < datas.length; i++) {
				var da = datas[i];
				var status = da.storeStatus ;
				var name = da.storeName?da.storeName:da.orgName;
				var id = da.storeId?da.storeId:da.orgId;
				var code = da.orgCode?da.orgCode:da.orgId;
				//0：门店   1 区域
				var type = da.storeId?0:1;
				if(status == 3){
					continue;
				}
				var showVal="",storeName;
				if(da.storeStatus == 2){
					storeName ="<span style='color: lightgray;'>"+name+"</span>" ;
				}else{
					storeName ="<span >"+name+"</span>" ;
				}
				////记录所有的商户
				if(Manage.status=='edit'){
					if(da.storeStatus == 2){
						showVal += "<span class='store_name_status_edit'>启用</span>";
					}
//					<li class="mui-table-view-cell mui-checkbox mui-left" name="checkbox"><input name="checkbox" type="checkbox" style=""><a class="store_li" data="32" style="margin: -11px -50px;"> 1111 </a></li>
					var li = $('<li class="mui-table-view-cell mui-checkbox mui-left"  name="checkbox"></li>');
					var a = $('<a style="margin: -11px -50px;" class="store_li" data="'+id+'">'+storeName+' </a>');
					li.append(showVal);
					li.append(a);
					if(type==0){
						li.append('<input name="checkbox" type="checkbox" class="manage_checkbox" data-type="'+type+'" data-code="'+code+'"/>');
						$("#storeLists").append(li);
					}else{
						$("#storeLists").prepend(li);
					}
				}else{

					if(da.storeStatus == 2){
						showVal += "<span class='store_name_status'>已停用</span>";
					}
					var li = $('<li class="mui-table-view-cell" data-type="'+type+'" data-code="'+code+'"></li>');
					var a = $('<a class="mui-navigate-right store_li" data="'+id+'">'+storeName+'</a>');
					li.append(showVal);
					li.append(a);
					if(type==0){
						li.append('<input name="checkbox" type="checkbox" class="manage_checkbox" style="display: none"/>');
						$("#storeLists").append(li);
					}else{
						$("#storeLists").prepend(li);
					}
				}			
			}
			
		}
}