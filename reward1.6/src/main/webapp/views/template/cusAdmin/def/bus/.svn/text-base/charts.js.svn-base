var Charts = function () {
	var storeMap = {0:'全部店铺'},selTab = 1;
    return {
        //main function to initiate the module

        init: function () {
        	Charts.initArea();
        	Charts.initListenter();
        	//默认本月
        	//$("#thisMon").click();
        	//默认本周
        	$("#thisWeek").click();
            //店铺
        	Charts.initStore();
        	
        },
        initArea:function(){ 
        	//加载前调用
        	/*Area.provinceChange = function(){
        		Charts.initStore();
        	}*/
        	//区域加载后调用
        	Area.cityChange = function(){
        		Charts.initStore();
        	}
        	//区域改变时调用
        	Area.areaChange = function(){
        		Charts.initStore();       		
        	}
    		//地区
    		Area.init("chartsSubimt",null,null,null,function(){
    			
    		});
        },
        seacher:function(){
        	//日志和店铺汇总查总数
        	if(selTab == 4 || selTab == 5){
            	var paramdata = Charts.getSeacherData();
            	if(paramdata == null){
            		return ;
            	}
            	//数据概况
            	Charts.seacherAllData(paramdata);
        	}
        	
    		if(selTab == 1){
        		Charts.initCharts();  			
    		}else if(selTab == 2){  		
    			Charts.initStoreRanking();
    		}else if(selTab == 3){     	
    			Charts.initStaffRanking();		
    		}else if(selTab == 4){     	
    			Charts.initLog(paramdata);	
    		}else if(selTab == 5){     	
    			Charts.initStoreSummary(paramdata);		
    		}
        },
        initListenter:function(){
        	function changeCustomData(){
    			var selStartTime = $("#selStartTime_show").val();
    			var selEndTime = $("#selEndTime_show").val();
    			 $("#selStartTime").val(selStartTime);
    			 $("#selEndTime_show").val(selEndTime);
    			Charts.dataType.startDate =selStartTime;
    			Charts.dataType.endDate =selEndTime;
    			if(selStartTime.indexOf("-")>0 && selEndTime.indexOf("-")>0){
            		Charts.seacher();
    			}
        	}
        	$("#seacher").click(function(){

        		document.getElementById('chartsForm').style.display='none';
        		$(document.getElementById("hiddform")).removeClass("collapse").addClass('expand');
        		Charts.seacher();
        	});
        	$("#customseacher").click(function(){

        		document.getElementById('chartsForm').style.display='none';
        		$(document.getElementById("hiddform")).removeClass("collapse").addClass('expand');
        		Charts.seacher();
        	});
        	//本月本周点击 
        	$(".screening—time").click(function(){
        		$(".screening—time").removeClass("active");
        		var me = $(this);
        		me.addClass("active");
        		var tag = me.attr("tag");
        		Charts.dataType.type = parseInt(tag);
        		$("#screeningTimeVal").val(me.val());
        		//自定义
        		if(tag=='4'){
        			changeCustomData();
        			$("#selectTime").show();
        		}else{
        			Charts.dataType.startDate =null;
        			Charts.dataType.endDate =null;
            		$("#selectTime").hide();
            		Charts.seacher();
        		}
        	});
        	$("#selStartTime_show").change(changeCustomData);
        	$("#selEndTime_show").change(changeCustomData);

        	//导出
        	$(".chart_button").click(function(){
        		if(selTab == 1){
            		//Charts.initCharts();  			
        		}else if(selTab == 2){  		
        			Charts.exportRanking("findStoreRankingPageEx");
        		}else if(selTab == 3){     	
        			Charts.exportRanking("findStaffRankingPageEx");//.initStaffRanking();		
        		}else if(selTab == 4){     	
        			Charts.exportRanking("findLogsByDataEx");//.initLog();	
        		}else if(selTab == 5){     	
        			Charts.exportRanking("findStoreSummaryPageEx");//.initStoreSummary();		
        		}
        	});
        	//一级tab点击事件
        	$(".statistics_tab").click(function(){
        		var me = $(this);
        		var href = me.attr("href");
        		if('#tab_2' == href){
            		$("#export").show();
        			Charts.initStoreRanking();
        			selTab = 2;
        		}else if('#tab_3' == href){
            		$("#export").show();
        			Charts.initStaffRanking();		
        			selTab = 3;
        		}else{
        			$("#charts_tab_1").click();
        			Charts.initCharts();	
            		//$("#export").hide();
        			selTab = 1;
        		}
        	});
        	//二级TAB点击事件
        	$(".charts_tab_tow").click(function(){
        		var me = $(this);
        		$(".charts_tab_tow").removeClass("active");
        		me.addClass("active");
        		var id  = me.attr('id');
        		$(".charts_tabs_tow").hide();
        		var showId = "#"+id.replace("tab","tabs");
        		$(showId).show();

        		if(id=="charts_tab_1"){
            		$("#export").hide();
        			Charts.initCharts();
        			selTab = 1;
        		//日志
        		}else if(id == "charts_tab_2"){
            		$("#export").show();
        			Charts.initLog();
        			selTab = 4;	
        		//门店汇总
        		}else if(id == "charts_tab_3"){
            		$("#export").show();
        			Charts.initStoreSummary();
        			selTab = 5;
        		}
        	});
        },
        addSelectListenter:function(datas){

        	//店铺选择事件
        	$(".select_store_checkbox").on('click',function(){
        		var me = $(this);
        		var tag = me.attr("tag");
        		//全选
        		if(tag==null || tag == ''){
        			$("#selectStoreVal").html("");
	        		if(me.attr("checked") == 'checked'){
	        			$(".select_store_checkbox").attr('checked','checked');
	        			
	        			for (var i = 0; i < datas.length; i++) {
							var da = datas[i];
	        				storeMap[da.storeId]=da.storeName;
						}
	        			selCount = datas.length;
        				/*$("#selectStoreVal").append(datas.length+'个店铺');*/
	        			$("#selectStoreVal").append('全部店铺');
	        		}else{
	        			$(".select_store_checkbox").attr('checked',null);
	        			storeMap = {0:'全部店铺'};
	        			selCount = 0;
	        			$("#selectStoreVal").append(0+'个店铺');
	        		}
        		}else{
	        		var storeval = $("#selectStoreVal").html();
	        		//选择
	        		if(me.attr("checked") == 'checked'){
		        		storeMap[tag] = me.attr("tagname");
		        	//	$("#selectStoreVal").html(storeval+ me.attr("tagname")+",");
		        		$("#selectStoreVal").html(++selCount+'个店铺');
		        		//处理全选如果选择选择了，全勾上
		        		var b = true;
		        		$(".select_store_checkbox").each(function(){
		        			var me2= $(this);
		        			if(me2.attr("id") != "selectAllStore" && me2.attr("checked") != 'checked'){
		        				b = false;
		        			}
		        		});
		        		if(b){
		        			$("#selectAllStore").attr('checked','checked');
		        		}
	        		}else{
	        			delete storeMap[tag] ;
		        		//$("#selectStoreVal").html(storeval.replace( me.attr("tagname")+",",""));
		        		$("#selectStoreVal").html(--selCount+'个店铺');

		        		//处理全选如果选择选择了，全勾上
		        		var b = false;
		        		$(".select_store_checkbox").each(function(){
		        			var me2= $(this);
		        			if(me2.attr("id") != "selectAllStore" && me2.attr("checked") != 'checked'){
		        				b = true;
		        			}
		        		});
		        		if(b){
		        			$("#selectAllStore").attr('checked',null);
		        		}
	        		}
        		}
        	});	
        },
        //所有店铺
        initStore:function(){
        	var orgCode = window.localStorage.getItem("orgCode");
        	//测试
        	if(orgCode==null){
        		//orgCode = 1001;
        	}
        	var proId = $("#provinceId").val();
        	var cityId = $("#cityId").val();
        	var areaId = $("#areaId").val();
        	var url = App.path+"/rs/external/merchants/getStoreList?orgCode="+orgCode;
        	function add(id){
            	var val = $("#"+id).val();
            	if(val!=null){
            		url += "&"+id+"="+val;
            	}
        	}
        	add("provinceId");
        	add("cityId");
        	add("areaId");
        	$.ajax( {
			        dataType: 'json',
			        type: "GET",
			        url: url,
			        contentType: "application/json; charset=utf-8",
			        success: function(data){
			        	var datas = data.resultData.body.result;
			        	var sel = $("#selectStore");
			        	sel.html("");
			        	sel.append('<input type="checkbox" class="select_store_checkbox" id="selectAllStore"><span class="select_store_title">全选</span>');

			        	for (var i = 0; i < datas.length; i++) {
							var da = datas[i];
							//storeMap[da.storeId] = da.storeName;
							sel.append('<input type="checkbox" class="select_store_checkbox" tag="'+da.storeId+'" tagname="'+
									 da.storeName+'"><span class="select_store_title">'+ da.storeName+'</span>');
						}
			        	storeMap = {0:'全部店铺'}
			        	Charts.addSelectListenter(datas);
			        	$("#selectAllStore").click();
			        	
			        	Charts.initCharts();
			        }
		        } );
        },
        //数据统计
        initCharts:function(){

        	var paramdata = Charts.getSeacherData();
        	if(paramdata == null){
        		return ;
        	}
        	//线型报表
        	Charts.seacherChart("/rs/external/statistics/comprehensive/findCharStatistcs",paramdata);
        	//数据概况
        	Charts.seacherAllData(paramdata);
        },
        seacherAllData:function(paramdata){
        	var url = App.path+"/rs/external/statistics/comprehensive/findCharStatistcsAll";
        	 $.ajax( {
			        dataType: 'json',
			        type: "POST",
			        url: url,
			        contentType: "application/json; charset=utf-8",
			        data: JSON.stringify(paramdata),
			        success: function(datas){
			        	var amountCount = datas.amountCount==null?0:datas.amountCount;
			        	var count = datas.count==null?0:datas.count;
			        	var cusCount = datas.cusCount==null?0:datas.cusCount;
			        	var shopownerDivided = datas.shopownerDivided==null?0:datas.shopownerDivided;
			        	$("#amountAllCount").html(amountCount);
			        	$("#allCount").html(count);
			        	$("#empAllCount").html(datas.empCount==null?0:datas.empCount);
			        	$("#cusAllCount").html(cusCount);
			        	$("#shopownerAllDivided").html(shopownerDivided);
			        	$("#areaManageDivided").html(datas.merDivided==null?0:datas.merDivided);
			        	$("#empDivided").html(datas.empDivided);
			        	
			        	//平均打赏金额
			        	var avgCount = 0;
			        	if(count > 0){
			        		avgCount = amountCount/cusCount;
			        	}
			        	$("#amountAvgCount").html(avgCount.toFixed(2));
			        	if(cusCount > 0){
			        		avgCount = count/cusCount;
			        	}
			        	$("#avgCount").html(avgCount.toFixed(2));
			        }
		       });
        },
        
        seacherChart:function(url,paramdata){
        	//var d = Charts.getDatas();
        	//return;
        	url = App.path+url;
        	 $.ajax( {
			        dataType: 'json',
			        type: "POST",
			        url: url,
			        contentType: "application/json; charset=utf-8",
			        data: JSON.stringify(paramdata),
			        success: function(datas){
			        	if(datas.length==0){
			        		var w = "150px",show = $("#screeningTimeVal").val()+"查询无数据";
			        		$("#amountCountTrend").css("height",w);
			        		$("#empCountTrend").css("height",w);
			        		$("#countTrend").css("height",w);
			        		
			        		$("#amountCountTrend").html(show);
			        		$("#empCountTrend").html(show);
			        		$("#countTrend").html(show);
			        	}else{
			        		$("#amountCountTrend").css("height","300px");
			        		$("#empCountTrend").css("height","300px");
			        		$("#countTrend").css("height","300px");
				        	//金额
				        	var opt = Charts.buildChartsData(datas,"amountCount");
				        	Charts.buildCharts("amountCountTrend",opt);
				        	//次数
				        	var opt2 = Charts.buildChartsData(datas,"count");
				        	Charts.buildCharts("countTrend",opt2);
				        	//人数
				        	var opt3 = Charts.buildChartsData(datas,"empCount");
				        	Charts.buildCharts("empCountTrend",opt3);
			        	}
			        }
		        } );

        },
        //选择的范围
        dataType:{
        	type:4,				//0今天  1本周，2本月，3本季度，  4自定义
        	//startDate:'2016-02-15',		//自定义开始时间
        	//endDate:'2016-03-03'		//自定义结束时间
            startDate:null,		//自定义开始时间
            endDate:null		//自定义结束时间
        },
        getSeacherData:function(){
        	var d = Charts.getFormData();
        	
        	if(d.startDate == null || d.startDate == ""){
        		$.alert("警告!","开始时间不能为空");
        		return null;
        	}
        	if(d.endDate == null||d.endDate == null){
        		$.alert("警告!","结束时间不能为空!");
        		return null;     		
        	}
			console.log("start:"+d.startDate+" end:"+d.endDate);
			var storeIds = '';
			for(key in storeMap){
				storeIds+=key+',';
			}
			if(storeIds == ""){
        		$.alert("警告!","请选择店铺！");
				return null;
			}else{
				storeIds = storeIds.substring(0, storeIds.length-1).replace("0,","");
			}
			var sd = d.startDate.replace("-","").replace("-","")
			var ed = d.endDate.replace("-","").replace("-","")
			$("#selStartTime").val(sd);
			$("#selEndTime").val(ed);
			$("#storeIds").val(storeIds);
			return	{
	        	storeIds:storeIds,
	        	//startTime:'2015-01-12',
	        	//endTime:'2016-09-12'
		        startTime:sd,
		        endTime:ed
	        		
	        }
        },
        /**
         * 获取日期
         */
        buildData:function(da){
        	if(da.indexOf("-")>0){
    			var das = da.split("-");
        		var by = parseInt(das[0]);
        		var bm = parseInt(das[1]);;//获取当前月份的日期
        		var bd = parseInt(das[2]);;
        		if(bm<10){
        			bm = "0"+bm;
        		}
        		if(bd<10){
        			bd = "0"+bd;
        		}
        		return by+"-"+bm+"-"+bd;
        	}
        	return da;
        },
        getFormData:function(){
        	var data = {};
    		var dd = new Date(); //当前日期 
    		var y = dd.getFullYear();
    		var m = dd.getMonth()+1;//获取当前月份的日期
    		var d = dd.getDate();
    		if(m<10){
    			m = "0"+m;
    		}
    		if(d<10){
    			d = "0"+d;
    		}
        	if(Charts.dataType.type==0) {
        		data.startDate = y+"-"+m+"-"+d;
        		data.endDate = y+"-"+m+"-"+d;
        	//本周
        	}else if(Charts.dataType.type==1){
        		data.endDate = y+"-"+m+"-"+d;
        		var day = dd.getDay();
        		if(day==0){
        			day = 7;
        		}
        		
        		dd.setDate(dd.getDate() - day  + 1);
        		y = dd.getFullYear();
        		m = dd.getMonth()+1;	
        		d = dd.getDate();
        		if(m<10){
        			m = "0"+m;
        		}
        		if(d<10){
        			d = "0"+d;
        		}
        		data.startDate = y+"-"+m+"-"+d;       		
        	}else if(Charts.dataType.type==2){
        		data.startDate = y+"-"+m+"-0"+1;
        		data.endDate = y+"-"+m+"-"+d;
        	}else if(Charts.dataType.type==3){
        		var sm = Charts.getQuarterStartMonth();
        		if(sm<10){
        			sm = "0"+sm;
        		}
        		data.startDate = y+"-"+sm+"-0"+1;
        		data.endDate = y+"-"+m+"-"+d;
        	}else if(Charts.dataType.type==4){
        		data.startDate = Charts.buildData(Charts.dataType.startDate);
        		data.endDate = Charts.buildData(Charts.dataType.endDate);
        	}else{
    			console.error("不存在的类型"+Charts.dataType.type);
        	}
        	
        	return data;
        },
    	//获得本季度的开端月份 
        getQuarterStartMonth:function(){
    		var now = new Date(); 				//当前日期 
    		var nowMonth = now.getMonth(); 		//当前月 
    		var quarterStartMonth = 0;
    		if(nowMonth<3){
    			quarterStartMonth = 0;
    		}
    		if(2<nowMonth && nowMonth<6){
    			quarterStartMonth = 3;
    		}
    		if(5<nowMonth && nowMonth<9){
    			quarterStartMonth = 6;
    		}
    		if(nowMonth>8){
    			quarterStartMonth = 9;
    		}
    		return quarterStartMonth;
        },
        /**
         * 根据选择的类型返回所有的日期
         */
        getDatas:function(){
        	var dd,datas = [],index = 0;;
    		var now = new Date(); 				//当前日期 
        	var qm = Charts.getQuarterStartMonth();
        	if(Charts.dataType.type==4){
        		dd = new Date(Charts.buildData(Charts.dataType.endDate));
        		if(dd==null){
        			console.error(Charts.dataType.endDate);
        			return;
        		}
        	}else{
        		dd = now;
        	}
        	var count = 0;
        	//获取前一天
        	function GetDateStr(i,isend) {
        		if(!isend){
        			//防止太多
        			if(count>1500){
            			console.error("太多日期"+Charts.dataType.endDate);
        			}
	        		//dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	        		dd.setDate(dd.getDate()+i);//获取AddDayCount天后的日期
	        		var y = dd.getFullYear();
	        		var m = dd.getMonth()+1;//获取当前月份的日期
	        		var d = dd.getDate();
        			//console.log( y+"-"+m+"-"+d);
	        		if(m<10){
	        			m = "0"+m;
	        		}
	        		if(d<10){
	        			d = "0"+d;
	        		}
	
					datas[index++] =  m+"-"+d;	
	        		//0今天  1本周，2本月，3本季度，  4自定义

		        	if(Charts.dataType.type==0) {
		        		isend = true;
		        	}
		        	else if(Charts.dataType.type==1) {
	            		var w = dd.getDay();	//获取当前星期X(0-6,0代表星期天)
            			//console.log( m+"-"+d+"   i="+i+"  type="+Charts.dataType.type);
	            		if(w==1){
	            			isend = true;
	            		}
	            	}else if(Charts.dataType.type==2) {
	            		//本月
            			//console.log( m+"-"+d+"   i="+i+"  type="+Charts.dataType.type);
	            		if(d==1){
	            			isend = true;
	            		}
	            	}else if(Charts.dataType.type==3) {
            			//console.log( m+"-"+d+"   i="+i+"  type="+Charts.dataType.type);
	            		//本季度一号
	            		if(qm==m && d==1){
	        				datas[i] =  y+"-"+m;	
	            			isend = true;
	            		}
	            	}else if(Charts.dataType.type==4){
            			//console.log( m+"-"+d+"   i="+i+"  type="+Charts.dataType.type);
	            		var en = new Date(Charts.buildData(Charts.dataType.startDate));
	            		var ey = en.getFullYear();
	            		var em = en.getMonth()+1;//获取当前月份的日期
	            		var ed = en.getDate();
	            		if(ey==y && em==m && ed==d){
	            			isend = true;
	            		}
	            	}

        			GetDateStr(-1,isend);
        		}
        	} 
        	//if(Charts.dataType.type==0) {
        		GetDateStr(0,false);
        	//}else{
        	//	GetDateStr(0,false);
        	//}
        	//倒序
        	return datas.reverse();
        },
        /**
         * 构建报表数据
         */
        buildChartsData:function(datas,type){
        	var opt = {};
        	opt.datas = Charts.getDatas();
        	
        	var charData = {};
        	for (var i = 0; i < datas.length; i++) {
        		//
        		var das = datas[i];
        		var da = charData[das.storeId];
        		if(charData[das.storeId]==null){
        			//店铺ID为KEY
        			da = charData[das.storeId] = {};
        		}
    			da[das.dateVal] = das;
			}
        //	var retData = [];
        	opt.series = [];
    		opt.legend = [];
        	var index = 0;
        	for (key in charData) {
        		opt.legend[index] = storeMap[key];;
        		opt.series[index] = {};
        		opt.series[index].name = storeMap[key];
        		opt.series[index].type = 'line';
        		opt.series[index].data = [];
        		opt.series[index].markPoint = {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            };
		        opt.series[index]. markLine = {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            };
        		//所有数据
        		var cdas =  charData[key];
        		for (var j = 0; j < opt.datas.length; j++) {
					var oda = opt.datas[j];
					if(cdas[oda]==null){
						opt.series[index].data[j] = 0;
					}else{
						opt.series[index].data[j] =cdas[oda][type]//amountCount;
					}
				}
        		index++;
        		
			}
        	return opt;
        },
        /**
         * 构建报表
         */
        buildCharts:function(id,chartData){

        	var opt = {
        		   /* title : {
        		        text: '未来一周气温变化',
        		        subtext: '纯属虚构'
        		    },*/
        		    tooltip : {
        		        trigger: 'axis'
        		    },
        		    legend: {
        		        data:chartData.legend  //['最高气温','最低气温']
        		    },
        		    toolbox: {
        		        show : true,
        		        feature : {
        		            mark : {show: true},
        		            dataView : {show: true, readOnly: false},
        		            //magicType : {show: true, type: ['line', 'bar']},
        		           // restore : {show: true},
        		            saveAsImage : {show: true}
        		        }
        		    },
        		    calculable : true,
        		    xAxis : [
        		        {
        		            type : 'category',
        		            boundaryGap : false,
        		        	data :  chartData.datas
        		        }
        		    ],
        		    yAxis : [
        		        {
        		            type : 'value',
        		            axisLabel : {
        		                formatter: '{value}'
        		            }
        		        }
        		    ],
        		    series :chartData.series
        		};
        	  var myChart = echarts.init(document.getElementById(id));
              myChart.setOption(opt);
        },
        exportRanking:function(url){
        	var url = App.path+'/rs/external/statistics/export/'+url;
        	//window.location.href = url;
        	this.getSeacherData();
        	$("#chartsSubimt").attr("action",url);
        	$("#chartsSubimt").submit();
        },
        //店铺排名
        initStoreRanking:function(){
        	if(Charts.getSeacherData()==null){
        		return;
        	}
			if(Charts.otable==null){
	        	var zcCols=[
						{
							"mDataProp":"storeId","sClass":"center","sTitle":"排行",
								"fnRender": function(data, type, full){
									return data.iDataRow+1;
								}
						    },
	        	            {"mDataProp":"storeName","sTitle":"店名"},
	        	            {"mDataProp":"amountCount","sTitle":"打赏金额"},
	        	            {"mDataProp":"shopownerDivided","sTitle":"分成金额"},
	        	            {"mDataProp":"empCount","sTitle":"收到打赏员工数"},
	        	            {"mDataProp":"cusCount","sTitle":"打赏人数"},
	        	            {"mDataProp":"count","sTitle":"打赏次数"}
	        		];
				Charts.otable = Dtable.init("storeRanking",
					//"chartForm",
					Charts.getSeacherData,
					zcCols,
					App.path+'/rs/external/statistics/comprehensive/findStoreRankingPage',
					function(b){
				
					});
			}else{
				Charts.otable.fnDraw();
			}
        },
        //店员排名
        initStaffRanking:function(){

        	if(Charts.getSeacherData()==null){
        		return;
        	}
			if(Charts.StaffRankTable==null){
	        	var zcCols=[{
				        	"mDataProp":"storeId","sClass":"center","sTitle":"排行",
				        		"fnRender": function(data, type, full){
				        			return data.iDataRow+1;
				        		}
				            },
	        	            {"mDataProp":"empName","sTitle":"店员名称"},
	        	            {"mDataProp":"storeName","sTitle":"店名"},
	        	            {"mDataProp":"amountCount","sTitle":"打赏金额"},
	        	            {"mDataProp":"cusCount","sTitle":"打赏人数"},
	        	            {"mDataProp":"count","sTitle":"打赏次数"}
	        		];
				Charts.StaffRankTable = Dtable.init("staffRanking",
					//"chartForm",
					Charts.getSeacherData,
					zcCols,
					App.path+'/rs/external/statistics/comprehensive/findStaffRankingPage',
					function(b){
				
					});
			}else{
				Charts.StaffRankTable.fnDraw();
			}
        },
        //日志
        initLog:function(){
        	if(Charts.getSeacherData()==null){
        		return;
        	}
			if(Charts.logsTable==null){
	        	var zcCols=[
	        	            //{"bVisible": false, "aTargets": [ 0 ] },
	        	            {"mDataProp":"dateVal","sTitle":"日期"},
	        	            {"mDataProp":"amountCount","sTitle":"打赏金额"},
	        	            {"mDataProp":"shopownerDivided","sTitle":"分成金额"},
	        	            {"mDataProp":"empCount","sTitle":"收到打赏员工数"},
	        	            {"mDataProp":"cusCount","sTitle":"打赏人数"},
	        	            {"mDataProp":"count","sTitle":"打赏次数"}
	        		];
				Charts.logsTable = Dtable.init("storeLogsTable",
					//"chartForm",
					Charts.getSeacherData,
					zcCols,
					App.path+'/rs/external/statistics/comprehensive/findLogsByData',
					function(b){
						var nCloneTh = document.createElement( 'th' );
						var nCloneTd = document.createElement( 'td' );
						nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';

						if(Charts.logsTable.isDe == null){

					        $('#storeLogsTable thead tr').each( function () {
					            this.insertBefore( nCloneTh, this.childNodes[0] );
					        } );
							Charts.logsTable.isDe  = true;
						}

				        $('#storeLogsTable tbody tr').each( function () {
				            this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
				        });

					});

		        $('#storeLogsTable').on('click', ' tbody td .row-details', function () {
		            var nTr = $(this).parents('tr')[0];
		            if ( Charts.logsTable.fnIsOpen(nTr) )
		            {
		                /* This row is already open - close it */
		                $(this).addClass("row-details-close").removeClass("row-details-open");
		                Charts.logsTable.fnClose( nTr );
		            }
		            else
		            {
		                /* Open this row */                
		                $(this).addClass("row-details-open").removeClass("row-details-close");
		                //Charts.logsTable.fnOpen( nTr, fnFormatDetails(Charts.logsTable, nTr), 'details' );
		                Charts.initLogDetail(nTr,'logsTable');
		            }
		        });
			}else{
				Charts.logsTable.fnDraw();
			}
        },
        initLogDetail:function(nTr,type){
            var aData =  Charts[type].fnGetData( nTr );
            var obj= Charts.getSeacherData();
            if(type=='logsTable'){
        		obj['busdata'] = aData['dateVal'].replace("-","").replace("-","");
            }else{
        		obj['storeId'] = aData['storeId'];
            }
        	var objstr=(JSON.stringify(obj));
        	var filed = type=='logsTable'?'findLogsByDataDetail':'findStoreSummaryDetail';
	        $.ajax( {
		        dataType: 'json',
		        type: "POST",
		        contentType: "application/json; charset=utf-8",
		        //findStoreSummaryDetail
		        url: App.path+'/rs/external/statistics/comprehensive/'+filed,
		        data: objstr,
		        success: function(data){
		        	var name = type=='logsTable'?'店名':'日期';
		            var sOut = '<table width="100%">';
		            
		            sOut += '<tr><td>'+name+'</td><td>打赏金额</td><td>分成金额</td><td>收到打赏员工数</td><td>打赏人数</td><td>打赏次数</td></tr>';
		            for (var i = 0; i < data.length; i++) {
		            	var d = data[i];
		            	var n = type=='logsTable'?d.storeName:d.dateVal;
		            	sOut +='<tr><td>'+n+'</td><td>'+d.amountCount+'</td><td>'+d.shopownerDivided+'</td>'
		            		+'<td>'+d.empCount+'</td><td>'+d.cusCount+'</td><td>'+d.count+'</td></tr>';
					}
		            
		            sOut += '</table>';
		             
		            
		        	 Charts[type].fnOpen( nTr, sOut, 'details' );
		        	 var len =  $(nTr).children().length;
		        	 $(nTr).next().children().attr("colspan",len+1);
		        }
	        } );
        },
        //门店排行
        initStoreSummary:function(){

        	if(Charts.getSeacherData()==null){
        		return;
        	}
			if(Charts.storeSummary==null){		 
	        	var zcCols=[
	        	            //{"bVisible": false, "aTargets": [ 0 ] },
	        	            {"mDataProp":"storeName","sTitle":"店铺"},
	        	            {"mDataProp":"amountCount","sTitle":"打赏金额"},
	        	            {"mDataProp":"shopownerDivided","sTitle":"分成金额"},
	        	            {"mDataProp":"empCount","sTitle":"收到打赏员工数"},
	        	            {"mDataProp":"cusCount","sTitle":"打赏人数"},
	        	            {"mDataProp":"count","sTitle":"打赏次数"}
	        		];
				Charts.storeSummary = Dtable.init(
					{id:"storeSummaryTable",
					//"chartForm",
					params:Charts.getSeacherData,
					extendColspanLen:1,
					zcCols:zcCols,
					url:App.path+'/rs/external/statistics/comprehensive/findStoreSummaryPage',
					back:function(b){
						var nCloneTh = document.createElement( 'th' );
						var nCloneTd = document.createElement( 'td' );
						nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';

						if(Charts.storeSummary.isDe == null){

					        $('#storeSummaryTable thead tr').each( function () {
					            this.insertBefore( nCloneTh, this.childNodes[0] );
					        } );
							Charts.storeSummary.isDe  = true;
						}

				        $('#storeSummaryTable tbody tr').each( function () {
				            this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
				        });
				        jQuery('#storeSummaryTable_wrapper .dataTables_filter input').addClass("m-wrap small"); // modify table search input
				        jQuery('#storeSummaryTable_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
				        jQuery('#storeSummaryTable_wrapper .dataTables_length select').select2(); // initialzie select2 dropdown
				    
					}});

		        $('#storeSummaryTable').on('click', ' tbody td .row-details', function () {
		            var nTr = $(this).parents('tr')[0];
		            if ( Charts.storeSummary.fnIsOpen(nTr) )
		            {
		                /* This row is already open - close it */
		                $(this).addClass("row-details-close").removeClass("row-details-open");
		                Charts.storeSummary.fnClose( nTr );
		            }
		            else
		            {
		                /* Open this row */                
		                $(this).addClass("row-details-open").removeClass("row-details-close");
		                Charts.initLogDetail(nTr,'storeSummary');
		            }
		        });
			}else{
				Charts.storeSummary.fnDraw();
			}
        }

    };

}();