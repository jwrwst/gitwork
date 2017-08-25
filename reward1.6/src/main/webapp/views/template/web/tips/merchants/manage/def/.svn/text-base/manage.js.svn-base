var navType,parentCode;
var Manage = {
		isMerManage:null,
		orgCode:null,
		schema:null,
		init:function(){
			console.debug("manage.init");
			require([ "jquery", "mui", "mui_view", "addr", "common", "ftscroller" ],
					function($$, mui, mui_view, addr, tools) {
				console.debug(navType);
				//区域经理直接进来移除
				if(parentCode == ""){
					localStorage.removeItem("isMerManage");
				}
				Manage.isMerManage = localStorage.getItem("isMerManage"),
				
				//alert(1)
				Manage.initCommontListenters(addr,$$);
				$(function(){
					Manage.buildMerchantsInfoSel(addr);
					if(navType == 'list'){
						DataList.init(addr,$$, tools);
					}else if(navType == 'ranking'){
						Ranking.init(addr,$$, tools);
					}else if(navType == 'statistics'){
						Ranking.unLoad = true;
						Ranking.init(addr,$$, tools);
					}else if(navType == 'merinfo'){
						MerInfo.init(addr,$$, tools);
					}
				});
				
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
		//公共事件
		initCommontListenters:function(addr,$$){
			//选择商户
			mui("body").on('change', '#merchantsInfo', function() {
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
				//记录选择商户
				localStorage.setItem("selMerchants",Manage.orgCode);
				Manage.buildMerSelAfter(addr);
			});
			
			//店铺管理
			mui("body").on('tap', '#dynamic', function() {
				window.location.href = basePath+"tips_merchants_manage_list.xhtml?param.data="+parentCode;
			});
			//商户管理
			mui("body").on('tap', '#range', function() {
				window.location.href = basePath+"tips_merchants_manage_ranking.xhtml?param.data="+parentCode;
			});
			//商户管理
			mui("body").on('tap', '#statistics', function() {
				window.location.href =basePath+ "tips_merchants_manage_statistics.xhtml?param.data="+parentCode;
			});
			//商户管理
			mui("body").on('tap', '#merchantsnotes', function() {
				window.location.href = basePath+"tips_merchants_manage_merInfo.xhtml?param.data="+parentCode;
			});

			//账户与安全
			mui("body").on('tap', '#userInfo', function() {
				localStorage.setItem("_user_orgCode",Manage.orgCode);
				window.location.href = basePath+"tips_merchants_account.xhtml";	
			});
			if(navType == 'list'){
				$("#dynamic").addClass('mui-active');
			}else if(navType == 'ranking'){
				$("#range").addClass('mui-active');
			}else if(navType == 'statistics'){
				$("#statistics").addClass('mui-active');
			}else if(navType == 'merinfo'){
				$("#merchantsnotes").addClass('mui-active');
			}
		},
		//加载完商户后重新初始化数据
		buildMerSelAfter:function(addr){
			if(navType == 'list'){
				DataList.buildStore(addr);
				DataList.buildOrg(addr);
			}else if(navType == 'ranking'){
				//更新状态
				Ranking.buildList();		
			}else if(navType == 'statistics'){
				$("#statistics").addClass('mui-active');
			}else if(navType == 'merinfo'){
				MerInfo.buildMerDatas();	
			}
			if(Manage.MerSelLitenter){
				Manage.MerSelLitenter();
			}
		},
		/**
		 * 构建商户列表
		 */
		buildMerchantsInfoSel:function(addr){
			//选择的
			var def = localStorage.getItem("selMerchants");
			//从商户管理页面进入下级区域页面
			if(Manage.isMerManage != null){
				$('body').show();
				//$("#merchantsInfo").prev().remove();
				//$("#merchantsInfo").html("<option >" + Manage.isMerManage+ "</option>");
				//$("#merchantsInfo").attr("disabled","disabled");
				$("#merchantsInfo").parent().append("<span class='org_name_title' style='padding:0 10px; line-height:45px;'>"+Manage.isMerManage+"</span>");
				$("#merchantsInfo").prev().remove();
				$("#merchantsInfo").remove();
				Manage.schema = parentCode.split("|")[0];
				Manage.orgCode = parentCode.split("|")[1];
				DataList.buildStore(addr);
				DataList.buildOrg(addr);

				Manage.buildMerSelAfter(addr);
			}else{
				// 初始化单页view1
				addr.findMerchantsInfoEntity(
						"status=2",
						function(datas) {
							datas = datas.resultData.body.result;
							var len = datas.length;
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
								//console.debug( Manage.type+"  schema:"+ da.orgCode+"  orgCode:"+ da.orgCode+"  b:"+b);
								//0:商户  1 区域经理
								b = Manage.type == 1?b:!b;
								//console.debug( Manage.type+"  b:"+b);
								if(b){
									////记录所有的商户
									if(def == da.orgCode){
										$("#merchantsInfo").append('<option selected="selected" value="' + da.orgCode + '" data-schema="'+da.schema+
												'"  data-leap="'+da.leap+'">' + da.orgName+ '</option>');
										Manage.schema = da.schema;
										Manage.orgCode = da.orgCode;
									}else{
										$("#merchantsInfo").append('<option value="' + da.orgCode + '" data-schema="'+da.schema+
												'"  data-leap="'+da.leap+'">' + da.orgName+ '</option>');
										if(Manage.orgCode == null){ 								
											//构建门店、区域列表
											Manage.schema = da.schema;
											Manage.orgCode = da.orgCode;
											Manage.leap = parseInt(da.leap);
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
							
							//if(parentCode==''){
							//	parentCode = Manage.schema+"|"+Manage.orgCode;
							//}
							Manage.buildMerSelAfter(addr);
							
					});	
	
				
				}
		}
};
/**
 * 排行榜
 */
var Ranking = {
	data:{
		type:0,				//0 次数	1金额
		groupType:0,		//0门店  	1员工
		searchType:0,		//0:打赏	1评分	2：店长分成  3 总监分成
		storeType:'2',		//0:本人所在门店；1 商家  2 区域经理 3区域经理全国门店
		pageNo:0,			//起始页
		startdata:null,
		enddata:null,
		countData:null,		//次数数据
		moneyData:null		//金额数据
	},
	init:function(addr,$$, tools){
		Ranking.initListenter(addr);
		Ranking.initList(addr);
		//默认设置显示每天
		$(function(){
			Ranking.initData(0);
		});
	},
	initList:function(addr){
		var now =  Ranking.DateFormat(new Date(), Ranking.date.format);
		Ranking.data.startdata = now;
		Ranking.data.enddata = now;
		//Ranking.buildList(addr);
	},
	initListenter:function(addr){	
			
		//次数榜、金额榜切换
		mui("body").on('tap', '#countTab', function() {
			Ranking.data.type = 0;
			Ranking.buildList();		
		});
		mui("body").on('tap', '#moneyTab', function() {
			Ranking.data.type = 1;
			Ranking.buildList();	
		});
		//日期切换
		mui("body").on('change', '#dateType', function() {
			var val = $(this).val();
			Ranking.initData(parseInt(val));
			isLoad1 = false;
			//Ranking.buildList();			
		});
		//类型切换
		mui("body").on('change', '#searchType', function() {
			var val = $(this).val();
			if(val=='1' || val == '3'){
				$("#countTab").html("评价次数榜 ");
				$("#moneyTab").html("好评榜 ");
			}else{
				$("#countTab").html("打赏次数榜 ");
				$("#moneyTab").html("打赏金额榜 ");
			}
			isLoad1 = false;
			Ranking.buildList();			
		});
		//显示自定义时间
		mui("body").on('tap', '.custom-date-val', function() {
			Ranking.showCusterDate();
		});
		var tools
		if(!Ranking.unLoad){
			tools = require("common");
		}
		//选择时间确认
		mui("body").on('tap', '#customDateSubmit', function() {
			var sd = $("#customStartTime").val();
			var ed = $("#customEndTime").val();		
			if(sd==""){
				if(!Ranking.unLoad){
					tools.toastTip(null,"请选择开始时间");
				}
				return;
			}
			if(ed==""){
				if(!Ranking.unLoad){
					tools.toastTip(null,"请选择结束时间");
				}
				return;
			}
			console.debug(sd+" end:"+ed);
			Ranking.initCustomDate(Ranking.date.toDate(sd),Ranking.date.toDate(ed));
			$("#customDate").hide();
			isLoad1 = false;
			Ranking.buildList();
		});//
		//全国、下辖门店
		mui("body").on('change', '#storeType', function() {
			var val = $(this).val();
			Ranking.data.storeType = val;
			console.debug(val);
			isLoad1 = false;
			Ranking.buildList();
		});//searchType

		var isLoad1 = false;
		var sli =  document.getElementById('slider');
		if(sli != null){
		  document.getElementById('slider').addEventListener('slide', function(e) {
			if (e.detail.slideNumber === 1) {
				if(!isLoad1){
					Ranking.data.type = 1;
					Ranking.buildList();
					isLoad1 = true;
				}		
			}else{
			
			} 
		  });
		}
	},
	//显示自定义时间
	showCusterDate:function(){
		$("#customDate").show();
		var sd = $("#disStartTime").html();
		var ed = $("#disEndTime").html();
		
		sd = sd.replace("年","-").replace("月","-").replace("日","");
		ed = ed.replace("年","-").replace("月","-").replace("日","");
		$("#customStartTime").val(sd);
		$("#customEndTime").val(ed);
	},
	//构建初始化时间
	initCustomDate:function(startDate,endDate){
		var sd1 = Ranking.DateFormat(startDate, Ranking.date.format);
		var sd2 = Ranking.DateFormat(startDate, "yyyy年MM月dd日");
		var ed1 = Ranking.DateFormat(endDate, Ranking.date.format,1);
		var ed2 = Ranking.DateFormat(endDate, "yyyy年MM月dd日");
		$("#disStartTime").attr("data-val",sd1);
		$("#disEndTime").attr("data-val",ed1);
		$("#disStartTime").html(sd2);
		$("#disEndTime").html(ed2);
		Ranking.data.startdata = sd1;
		Ranking.data.enddata = ed1;
		console.debug("ed1:"+ed1);
	},
	//切换日期
	onSlideChangeEnd:function(){
		var addr = require("addr");
		var index = swiper.activeIndex;
		var selDiv = $("#showDataView").children('div:eq('+index+')');
		var sd = Ranking.data.startdata = selDiv.attr("data-startdata");
		var ed = Ranking.data.enddata = selDiv.attr("data-enddata");

		Ranking.buildList(addr);
		//初始化自定义时间
		if(sd!='' && ed!=''){
			var sm = sd.substring(4,6);
			sm = parseInt(sm);
			var em = ed.substring(4,6);
			em = parseInt(em);
			Ranking.initCustomDate(new Date(sd.substring(0,4),sm-1,sd.substring(6,8)),
					new Date(ed.substring(0,4),em -1,ed.substring(6,8)));
		}
		//console.debug(index+'  startdata= '+Ranking.data.startdata+"  enddata:"+Ranking.data.enddata);
		//console.debug(sd.substring(0,4)+" 年"+sd.substring(4,6)+"月"+sd.substring(6,8));
	},
	DateFormat:function(dd,fmt,off){
		var m = dd.getMonth() + 1;
		var d = dd.getDate();
		if(off){
			d +=1;
		}
	    var o = {
	            "M+": m, //月份 
	            "d+": d, //日 
	            "h+": dd.getHours(), //小时 
	            "m+": dd.getMinutes(), //分 
	            "s+": dd.getSeconds(), //秒 
	            "q+": Math.floor((dd.getMonth() + 3) / 3), //季度 
	            "S": dd.getMilliseconds() //毫秒 
	        };
	        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (dd.getFullYear() + "").substr(4 - RegExp.$1.length));
	        for (var k in o)
	        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	        return fmt;
	},
	date:{
		format:"yyyyMMdd",
		toDate:function(str){
			var newstr = str.replace(/-/g,'/'); 
		    return new Date(newstr); 
		},
		//天
		buildDay:function(data){
			var da = Ranking.DateFormat(data, "MM月dd日");
			var daval = Ranking.DateFormat(data, this.format);
			var daval2 = Ranking.DateFormat(data, this.format,1);
			return '<div class="swiper-slide" data-startdata="'+daval+'" data-enddata="'+daval+'"><p ><font>'+da+'</font><br/>'+data.getFullYear()+'</p></div>';
		},
		//周
		buildWeek:function(startDay,endDate){
			var	startData = Ranking.DateFormat(startDay, "MM月dd日");
			var	startval = Ranking.DateFormat(startDay, this.format);
			var endDay = Ranking.DateFormat(endDate, "MM月dd日");
			var	endval = Ranking.DateFormat(endDate, this.format,1);
			return '<div class="swiper-slide"  data-startdata="'+startval+'" data-enddata="'+endval+'"><p  class="zhou"><font>'
						+startData+'-'+endDay+'</font><br/>'+startDay.getFullYear()+'</p></div>';
		},
		//月
		buildMonth:function(endDate,off){
			//off = off*7;
		    var nowMonth = endDate.getMonth()+1+off;          		//当前月
		    var nowYear = endDate.getFullYear();        			//当前年
		    
			console.debug("nowYear:"+nowYear+"  nowMonth:"+nowMonth+" off:"+off);
		    //小于1 取前一年
		    if(nowMonth<=0){
		    	nowMonth = 12+nowMonth;
		    	if(nowMonth == 0){
		    		nowMonth = 12;
		    	}
		    	nowYear -= 1;
		    }
		    if(nowMonth<=0){
		    	nowMonth = 12+nowMonth;
		    	if(nowMonth == 0){
		    		nowMonth = 12;
		    	}
		    	nowYear -= 1;
		    }
		    if(nowMonth<=0){
		    	nowMonth = 12+nowMonth;
		    	if(nowMonth == 0){
		    		nowMonth = 12;
		    	}
		    	nowYear -= 1;
		    }
		    if(nowMonth<10){
		    	nowMonth = "0"+nowMonth;
		    }

			console.debug("nowYear:"+nowYear+"  nowMonth:"+nowMonth);
			var da = Ranking.DateFormat(endDate, "MM月dd日");
		    endval = Ranking.DateFormat(endDate, this.format,1);
			var startData = nowMonth+"月0"+1+"日";
			//var startval = nowYear+"-"+nowMonth+"-01";
			var startval = nowYear+""+nowMonth+"01";
			
			//console.debug("startval:"+startval);
			//不是本月
			if (off != 0) {
				function getMonthDays(myMonth) {
					var monthStartDate = new Date(nowYear, myMonth, 1);
					var monthEndDate = new Date(nowYear, myMonth + 1, 1);
					var days = (monthEndDate - monthStartDate)
							/ (1000 * 60 * 60 * 24);
					return days;
				}
			    var lastMonthDate = endDate;  //上月日期
			    lastMonthDate.setDate(1);
			    lastMonthDate.setMonth(lastMonthDate.getMonth()+off);
			    var lastYear = lastMonthDate.getYear();
			    var lastMonth = lastMonthDate.getMonth();
				var enda = new Date(nowYear, lastMonth, getMonthDays(lastMonth));
				da = Ranking.DateFormat(enda, "MM月dd日");
				endval = Ranking.DateFormat(enda, this.format,1);
			}
			return '<div class="swiper-slide"  data-startdata="'+startval+'" data-enddata="'+endval+'"><p  class="zhou"><font>'
						+startData+'-'+da+'</font><br/>'+endDate.getFullYear()+'</p></div>';
		},
		buildYear:function(endDate,off){
			var da =  endDate.getFullYear()+off;        			//当前年
			//var startval = da + "-01-01";
			//var endval = da + "-12-31";
			var startval = da + "0101";
			var endval = da + "1232";
			if(off==0){
				endval = Ranking.DateFormat(endDate, this.format,1);
			}
			return '<div class="swiper-slide"  data-startdata="'+startval+'" data-enddata="'+endval+'"><p ><font>'
					+da+'</font></p></div>';
		}
	},
	initData:function(val){
		var showDataView  = $("#showDataView");
		var html = "";
		var data = new Date();
		var iToDay=data.getDate();
		var iToMon=data.getMonth();
		var iToYear=data.getFullYear();	
		//显示日期
		$("#dateWindow-disTime").hide();
		$("#showDataView").show();
		switch (val) {
			//天
			case 0:		
				//修改自定义时间
				Ranking.initCustomDate(data,data);
				//
				for (var i = -30; i <= 0; i++) {
					var newDay = new Date(iToYear,iToMon,(iToDay+i)); 
					html += Ranking.date.buildDay(newDay);
				}
				showDataView.html(html);
				swiper.slideTo(30);
				swiper.update(true);
				break;
			//周
			case 1:		
				//修改自定义时间
			    var nowDayOfWeek = data.getDay();         //今天本周的第几天
				Ranking.initCustomDate(new Date(iToYear,iToMon,iToDay-nowDayOfWeek+1),data);
				for (var i = -30; i <= 0; i++) {
					var off = i*7;
					var startDay = new Date(iToYear,iToMon,(iToDay+off-nowDayOfWeek+1)); 
					var endDay;
					//当天 
					if(i==0){
						endDay = new Date(iToYear,iToMon,iToDay); 
					}else{
						endDay = new Date(iToYear,iToMon,(iToDay+off-nowDayOfWeek+7)); 
					}
					html += Ranking.date.buildWeek(startDay,endDay);
				}
				showDataView.html(html);
				swiper.slideTo(30);
				swiper.update(true);
				break;
			//月
			case 2:		
				//修改自定义时间
				Ranking.initCustomDate(new Date(iToYear,iToMon,1),data);
				//这里不能用da
				for (var i = -30; i <= 0; i++) {
					html += Ranking.date.buildMonth(new Date(),i);
				}
				showDataView.html(html);
				swiper.slideTo(30);
				swiper.update(true);
				break;
			//年
			case 3:		
				//修改自定义时间
				Ranking.initCustomDate(new Date(iToYear,0,1),data);
				for (var i = -30; i <= 0; i++) {
					html += Ranking.date.buildYear(new Date(),i);
				}
				showDataView.html(html);
				swiper.slideTo(30);
				swiper.update(true);
				break;
			//自定义
			default:
				$("#dateWindow-disTime").show();
				$("#showDataView").hide();
				Ranking.showCusterDate();
				break;
		}
		//记录时间数据
		Ranking.onSlideChangeEnd();
	},
	buildParam:function(){
		var st = $("#searchType").val();
		//员工打赏榜
		if(st==0){
			Ranking.data.searchType = 0;
			Ranking.data.groupType = 1;
		//员工评分榜
		}else if(st == 1){
			Ranking.data.searchType = 1;
			Ranking.data.groupType = 1;			
		//门店打赏榜
		}else if(st == 2){
			Ranking.data.searchType = 0;
			Ranking.data.groupType = 0;			
		//门店评分榜
		}else if(st == 3){
			Ranking.data.searchType = 1;
			Ranking.data.groupType = 0;			
		}
	},
	buildList : function(addr) {
		console.debug(Ranking.data.startdata+" enddata:"+Ranking.data.enddata);
		//统计页面补丁
		if(Ranking.unLoad){
			Ranking.reLoad();
			return;
		}
		if(Manage.orgCode == null){
			console.error("null orgCode");
			return;
		}
		//console.debug(Ranking.data.storeType);
		var addr = require("addr");
		var tools = require("common");
		var oC = Manage.orgCode;
		if(Ranking.data.storeType == 3){
			oC = Manage.schema;
		}
		
		Ranking.buildParam();
		var json = {
			"searchType" : Ranking.data.searchType,		//0:打赏；1：评分 2：店长分成  3 总监分成
			"type" : Ranking.data.type,					//0 次数	1金额
			"dateType" : 6,								//0:今天，1昨天，2：本周，3：上周，4：本月，5上月 6时间 startTime="20160425",endTime="20160425"
			"orgCode" : oC,			//
			"storeType" : Ranking.data.storeType,		//0:本人所在门店；1 商家  2 区域经理 3区域经理全国门店
			"groupType" : Ranking.data.groupType,		//
			"numPerPage" : "50",
			"pageNum" : Ranking.data.pageNo + "",		//
			"startTime" : Ranking.data.startdata,
			"endTime" :  Ranking.data.enddata
		};
		//console.debug(json.storeType);
		var loadbar = tools.loading();
		window.setTimeout(function() {
			loadbar.close();
		}, 2000);
		addr.findStorManagerRanking(JSON.stringify(json), function(data) {
			loadbar.close();
			if (data != null && data.resultMessage.messageCode == "0000"
					&& data.resultData.body.result != null) {
				if (Ranking.data.type == 0) {
					Ranking.data.countData = data.resultData.body.result.result;
				} else {
					 Ranking.data.moneyData = data.resultData.body.result.result;
				}
				Ranking.buildView();
			}
		});
	},
	//构建页面
	buildView:function(){
		var tools = require("common");
		if(Ranking.data.type == 0){
			$("#template-page-content-my0").html("");
		}else{
			$("#template-page-content-my1").html("");
		}
		var data;
		if(Ranking.data.type == 0){
			data = Ranking.data.countData;
		}else{
			data = Ranking.data.moneyData;
		}
		for (var i = 0; i < data.length; i++) {
			var da = data[i];
			var name = da.empName;
			var val = da.amountCount;
			//0门店  	1员工
			if(Ranking.data.groupType == 0){
				name = da.storeName;
			}
			//0:打赏	1评分	2：店长分成  3 总监分成
			if(Ranking.data.searchType == 1){
				val = da.score;
			}else if(Ranking.data.searchType == 2 || Ranking.data.searchType == 3){
				val = da.shopownerDivided;
			}
			var li = $('<li class="mui-table-view-cell mui-media data-list"></li>');
			var a = $('<a data-id="{{empId}}" data-count={{count}}></a>');
			var span1 = $('<span class="mui-media-object mui-pull-left"></span> ');
			var sqi = $('<i class="mui-icon iconfont icon-order" style="font-size: 30px !important; color: #d9d8d8;"></i>');
			span1.append(sqi);
			var label = $('<label class="em" style="color: #fff;">'+(i+1)+'</label> ');
			if(i<3){
				sqi.css('color','#EA4F16');
				label.css('color','#fff');
			}
			var span2 = ' <span class="mui-media-body"><span style="font-size: 15px; color: #666;"><span class="item-name">'+name+'</span></span></span>';
			var div = $('<div style="float: right; font-size: 14px;"></div>');
			li.append(a);
			a.append(span1);
			a.append(label);
			a.append(span2);
			a.append(div);
			function buildScore(color,count,val,div){
				var span = $('<span style="float: left; text-align:center; line-height: 20px;'+color+' min-width: 3.0em;">'+val+'</span>');
				var ratingstars = $('<div class="ratingstars"></div>');
				span.append(ratingstars);
				ratingstars.append('<i class="mui-icon iconfont icon-star" style="font-size:14px !important;" data-num=1></i>');
				ratingstars.append('<i class="mui-icon iconfont icon-star" style="font-size:14px !important;" data-num=1></i>');
				ratingstars.append('<i class="mui-icon iconfont icon-star" style="font-size:14px !important;" data-num=1></i>');
				ratingstars.append('<i class="mui-icon iconfont icon-star" style="font-size:14px !important;" data-num=1></i>');
				ratingstars.append('<i class="mui-icon iconfont icon-star" style="font-size:14px !important;" data-num=1></i>');
				div.append(span);
			}
			//0 次数 1 金额
			if(Ranking.data.type == 0){
				div.append('<span style="float: left; padding-right: 10px; line-height: 40px;">'+da.count+'次</span>');
				//0:打赏	1评分	2：店长分成  3 总监分成
				if(Ranking.data.searchType == 1){
					buildScore("color: #b3b3b3;",da.count,val,div);
				}else{
					div.append('<span style="float: left; line-height: 40px;color: #b3b3b3; min-width: 3.0em;">'+val+'</span>');
				}
				$("#template-page-content-my0").append(li);
			}else{
				div.append('<span style="float: left; padding-right: 10px;color: #b3b3b3; line-height: 40px;">'+da.count+'次</span>');
				//0:打赏	1评分	2：店长分成  3 总监分成
				if(Ranking.data.searchType == 1){
					buildScore("",da.count,val,div);
				}else{
					div.append('<span style="float: left; line-height: 40px; min-width: 3.0em;">'+val+'</span>');
				}
				$("#template-page-content-my1").append(li);
			}
			tools.setStar(val+"",li.find(".icon-star"),"#F3D958");
		}
	}
};
/*
function Statistics(){
	var ec,addr,tools;
	this.init = function(addr,$$, tools){
		var storeId = window.localStorage.getItem("storeId");
		// 使用
        require(
            [
                'echarts','addr',
                'echarts/chart/line' ,// 使用柱状图就加载bar模块，按需加载
                'mui','mui_view','jquery','template7'
            ],
            function (e,ad,to) {
    	require([ "jquery", "mui", "mui_view", "addr", "common",
    				function($$, mui, mui_view, ad, to,e) {
            	ec =e;
            	addr = ad;
            	tools = to;

				Manage.initCommontListenters(addr,$$);
				Manage.buildMerchantsInfoSel(addr);
				initDate();
            }
        );
		
	};
	this.onLoad = function(){
		initDate();
	};
	function initDate(){
    	var viewApi = mui('#center').view({
	  		defaultPage: '#regpage'
	    });
    	
    	//图形定义参数                    				
		var option = {
				backgroundColor:"#ffffff",
				title:{
					show:true,
					padding:10,
					textStyle:{
						fontSize:15
					}
				},
			    tooltip : {
			    	show:true,
			        trigger: 'item',
			        formatter:"{b} : {c}",
			        backgroundColor: 'rgba(255,78, 0,0.7)',
			        borderColor: '#333',
			        borderRadius: 4,
			        borderWidth: 0
			    },					   
			    toolbox: {
			        show : false,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : false,
			    xAxis : [
			        {
			            type : 'category',
//				            boundaryGap : false,
			            data:[]
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {	
			        	name:"",
			            type:'line',
//				            markPoint : {
//				                data : [
//				                    {type : 'max', name: '最大值'},
//				                    {type : 'min', name: '最小值'}
//				                ]
//				            },
			            itemStyle: {
			                normal: {
			                    color: 'tomato',
			                    barBorderColor: 'tomato',
			                    barBorderWidth: 6,
			                    barBorderRadius:0,
			                    label : {
			                        show: true, position: 'top',textStyle:{color:"#000"}
			                    }
			                }
			            },
			            data:[]
			        }
			    ]
		};
		
		var chartArr=[];
        addr.getStoreStatisticInfoByStoreID("storeId="+storeId+"&filter="+$("#dateType").val(),function(data){
        	 if(data!=null&&data.resultMessage.messageCode=="0000"){
        		 var jsondata= data.resultData.body.result;
        		 var response ={data:jsondata};
        		 
        		//添加容器            	
             	var template = document.getElementById('template-charts').innerHTML;
   			    var compiled = Template7(template).compile();
   			    var compiledRendered = compiled(response);
   			    $("#template-charts-content").append(compiledRendered);
   			    $(".statis-placeholder").width($("body").width()); 
   			    
   			    // 基于准备好的dom，初始化echarts图表        				
				for(var i=0;i<response.data.length;i++){					
					var tempObj = response.data[i];        					
	                var charts = ec.init(document.getElementById('chartsObj'+i)); 
	                option.title.text=tempObj.title;
	                option.xAxis[0].data=tempObj.rows;
	                option.series[0].data=tempObj.data;
	                
	                charts.setOption(option);
	                chartArr.push(charts);
				}
        	 }
        });			
        
		*//**
		var response1 ={data:[{title:"服务评级",rows:['3/25','3/26','3/27','3/28','3/29','3/30','3/31'],data:[120, 132, 101, 108, 1000, 210, 230]},
        	                     {title:"评价人数",rows:['周一','周二','周三','周四','周五','周六','周日'],data:[120, 132, 101, 108, 100, 210, 230]},
        	                     {title:"评价分布",rows:['3/25','3/26','3/27','3/28','3/29','3/30','3/31'],data:[120, 132, 101, 134, 90, 230, 210]},
        	                     {title:"打赏次数",rows:['周一','周二','周三','周四','周五','周六','周日'],data:[120, 132, 101, 108, 1000, 210, 230]},
        	                     {title:"打赏金额",rows:['3/25','3/26','3/27','3/28','3/29','3/30','3/31'],data:[120, 132, 101, 134, 90, 230, 210]},
        	                     {title:"收到打赏店员数",rows:['周一','周二','周三','周四','周五','周六','周日'],data:[120, 132, 101, 134, 90, 230, 210]}]};
		**//*
		//改变统计周期
		$("#dateType").on("change",function(t){
			addr.getStoreStatisticInfoByStoreID("storeId="+storeId+"&filter="+$("#dateType").val(),function(data){
            	 if(data!=null&&data.resultMessage.messageCode=="0000"){
            		 var jsondata= data.resultData.body.result;
            		 var response1 ={data:jsondata};
			
					for(var i=0;i<response1.data.length;i++){
						var tempObj = response1.data[i];
						var charts = ec.init(document.getElementById('chartsObj'+i));
//							charts.showLoading();
//							setTimeout(function(){charts.hideLoading();}, 500);
						option.title.text=tempObj.title;
		                option.xAxis[0].data=tempObj.rows;
		                option.series[0].data=tempObj.data;
		                charts.setOption(option);
					}
            	 }
           	 
           });		
			
		});
	}
	this.onSlideChangeEnd = function(){

		var addr = require("addr");
		var index = swiper.activeIndex;
		var selDiv = $("#showDataView").children('div:eq('+index+')');
		var sd = Ranking.data.startdata = selDiv.attr("data-startdata");
		var ed = Ranking.data.enddata = selDiv.attr("data-enddata");


		initDate();
		//初始化自定义时间
		if(sd!='' && ed!=''){
			var sm = sd.substring(4,6);
			sm = parseInt(sm);
			var em = sd.substring(4,6);
			em = parseInt(em);
			Ranking.initCustomDate(new Date(sd.substring(0,4),sm-1,sd.substring(6,8)),
					new Date(ed.substring(0,4),em -1,ed.substring(6,8)));
		}
		//console.debug(index+'  startdata= '+Ranking.data.startdata+"  enddata:"+Ranking.data.enddata);
		console.debug(sd.substring(0,4)+" 年"+sd.substring(4,6)+"月"+sd.substring(6,8));
	};
}*/
//区域、门店列表 
var DataList = {
		init:function(addr,$$, tools){

			//区域经理直接进来移除
			if(parentCode == ""){
				localStorage.removeItem("isMerManage");
			}
			Manage.isMerManage = localStorage.getItem("isMerManage"),
			//Manage.type = 0;
			DataList.initStoreLitenters(addr,$$,tools);
			//Manage.initMerLitenters(addr,$$,tools);			
			//Manage.initMer(addr,$$);
		},
		initStoreLitenters:function(addr,$$,tools){
			/*//删除
			mui("body").on('tap', '#confirmdelete', function() {
				tools.confirm("删除店铺后","此店铺的信息无法找回哦",function(back){
					if(back.index==1){
						updateStatus(3, Manage.getStoreIds(),"删除");
					}
				});	
			});*/

			//点击节点事件
			mui("body").on('tap', 'li.data-list a', function() {
				var li = $(this).parent();
				var type = $(li).attr("data-type");
				var code = $(li).attr("data-code");
				localStorage.setItem("_parent_code",code);
				var hasDelete = $(event.target).hasClass("delete-store");
				if(hasDelete){
					return;
				}
				/*if(Manage.isMerManage == null){
					var selName =  $('#merchantsInfo').children('option:selected').text();
				}*/
				//区域
				if(type == '1'){
					localStorage.setItem("isMerManage",li.attr("data-name"));
					window.location.href = basePath+"tips_merchants_manage_list.xhtml?param.data="+Manage.schema+"|"+code;	
				//店铺
				}else{
					localStorage.setItem("empId", empId);
					localStorage.setItem("storeId", DataList.getStoreId(li));
					localStorage.setItem("tip_orgCode",Manage.orgCode);
					//alert(Manage.orgCode);
					window.location.href = basePath+"tips_store_sub_storeinfo.xhtml";	
				}
			});
			//搜索店铺
			mui("body").on('tap', '#search', function() {
				DataList.buildStore(addr);
				DataList.buildOrg(addr);
			});

			//添加门店		
			mui("body").on('tap', '#addStore', function() {
				DataList.toAddStore();
			});
			//添加区域	
			mui("body").on('tap', '#addMer', function() {
				$("#topPopover").hide();
				
				var btnArray = ['取消', '创建'];
				mui.prompt('',"添加区域", '请输入区域名称', btnArray, function(e) {
					if (e.index == 1 && e.value != '') {
						//info.innerText = '谢谢你的评语：' + e.value;
						var url = addr.mercants.addArea;
						//var leap = leap
						var params = {orgName:e.value,parentCode:Manage.orgCode,schema:Manage.schema,leap:Manage.leap+1};
						addr.ajaxJson(url,JSON.stringify(params),function(data){
							if(data!=null&&data.resultMessage.messageCode=="0000"){
								//initDatas(addr,tools);
								tools.toastTip(null,"添加成功");	
								DataList.buildOrg(addr);						
							}else{
								 tools.toastTip(null,"添加失败");
							}
						});
					} else {
						//info.innerText = '你点了取消按钮';
					}
				})
			});
			//删除店铺
			mui("body").on('tap', '.delete-store', function() {
				var ids = $(this).attr('data-id');
				tools.confirm("删除店铺后","此店铺的信息无法找回哦",function(back){
					if(back.index==1){
					    var data = {status:3,storeIds:ids};		
						var datastr=JSON.stringify(data);
						var url = addr.store.updateStatus;
						addr.ajaxJson(url,datastr,function(data){
							if(data!=null&&data.resultMessage.messageCode=="0000"){
								tools.toastTip(null,"删除成功");
								DataList.buildStore(addr);
							}else{
								tools.toast("错误","删除失败,请直接联系众赏或再试一次.");
							}
						});
					}
				});	
			});
			//删除区域	
			mui("body").on('tap', '.delete-mer', function() {
				var orgCode = $(this).parent().attr("data-code");
				tools.confirm("删除区域后","所属店铺一起被删除!",function(back){
					if(back.index==1){
						var url = addr.mercants.delArea;
						addr.getJson(url,"orgCode="+orgCode,function(data){
							if(data!=null&&data.resultMessage.messageCode=="0000"){
								//initDatas(addr,tools);
								tools.toastTip(null,"删除成功");	
								DataList.buildOrg(addr);
							}else{
								 tools.toastTip(null,"删除失败");
							}
						});
					}
				});	
			});
		},
		//店铺功能相关事件
		toAddStore:function(){
			localStorage.setItem("_orgCode",Manage.orgCode);
			window.location.href = basePath+"tips_store_addStore.xhtml?param.data="+parentCode;		
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
		/**
		 * 构建下级区域
		 */
		buildOrg:function(addr){
			$("#storeLists").children("li[data-type='1']").remove();
			var url = addr.mercants.getListByParentCode;	
			var datastr=JSON.stringify({parentCode:Manage.orgCode,schema:Manage.schema,orgName:$("#searchVal").val()});
			addr.ajaxJson(url,datastr,function(data){
				if(data!=null&&data.resultMessage.messageCode=="0000"){
					data = data.resultData.body.result;	
					DataList.buildDatas(data);
				}				
			});
		},
		/**
		 * 构建门店
		 */
		buildStore:function(addr){
			//var orgcode = Manage.orgCode;
			$("#storeLists").children("li[data-type='0']").remove();
			addr.searchStoreListByOpenId(
					JSON.stringify({orgCode:Manage.orgCode,storeName:$("#searchVal").val()}),
					function(datas) {
						datas = datas.resultData.body.result;
						DataList.buildDatas(datas);				
				});
			
		},
		buildDatas : function(datas) {
			for (var i = 0; i < datas.length; i++) {
				var da = datas[i];
				var status = da.storeStatus;
				var name = da.storeName ? da.storeName : da.orgName;
				var id = da.storeId ? da.storeId : da.orgId;
				var code = da.orgCode ? da.orgCode : da.orgId;
				//0：门店   1 区域
				var type = da.storeId ? 0 : 1;
				if (status == 3) {
					continue;
				}

				if (da.storeStatus == 2) {
					showVal += "<span class='store_name_status'>已停用</span>";
				}
				var li = $('<li class="mui-table-view-cell  data-list" data-type="'+type+'" data-code="'+code+
						'" data-name="'+name+'"></li>');
				var a = $('<a data="'+id+'"></a>');
				var isign = $('<i class="mui-icon iconfont icon-storemanage" style="margin-right:5px;"></i>');
				if (type == 0) {
					li.append('<span class="mui-icon iconfont icon-delete delete-store" data-id="'+id+'"></span>');
					isign.addClass("icon-storemanage");
					$("#storeLists").append(li);
				} else {
					li.append('<span class="mui-icon iconfont icon-delete delete-mer"></span>');
					isign.addClass("icon-quyu");
					$("#storeLists").prepend(li);
				}
				li.append(a);
				a.append(isign);
				a.append(name);
			}

		}			
};
//商户信息
var MerInfo = {
	init:function(addr,$$, tools){
		MerInfo.initListenter(addr,$$, tools);
		MerInfo.buildMerDatas(addr,$$, tools);
	},
	initListenter:function(addr,$$, tools){
		function update(back){
			if(!MerInfo.checkSubmin()){
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
						$("#updateInfo").html("修改信息");
					});
					b = !b;
				}else{
					$("input").attr("disabled",null);
					$("select").attr("disabled",null);
					$("input[name='merName']").attr("disabled","disabled");
					$("#updateInfo").html("保存");
					b = !b;
				}
			});
	},
	//显示数据
	buildMerDatas:function(){
		var addr = require("addr");
		var $$ = require("jquery");
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
						        $$(".header").children(".img").css("background-image","url('"+filePath+"')");
							}
							//类型，地区
							MerInfo.initSelect(da);
							function getVal(name){
								return name==null?"":name;
							}
							$("#showCityPicker").html(getVal(da.typeName) + " " + getVal(da.codeTypeName));
							$("#showCityPicker3").html(getVal(da.provinceName) + " " + getVal(da.cityName)+ " " +  getVal(da.areaName));
							
						}
					}
					
				});
		}
	},
	checkSubmin:function(){
		var tools = require("common");
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
	}
};


var loaddata = function(){}
