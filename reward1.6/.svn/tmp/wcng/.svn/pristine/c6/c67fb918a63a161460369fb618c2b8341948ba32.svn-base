
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
			Ranking.buildList();
		});//
		//全国、下辖门店
		mui("body").on('change', '#storeType', function() {
			var val = $(this).val();
			Ranking.data.storeType = val;
			console.debug(val);
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
