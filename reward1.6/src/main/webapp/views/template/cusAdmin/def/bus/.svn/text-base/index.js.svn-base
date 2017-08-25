var IndexInfo = function () {
	//var orgCode = window.sessionStorage.getItem("orgCode");
	var tables = {},amountCount=0;
    return {
        init: function () {   
        	//初始化table
        	IndexInfo.initTable();
        	//初始化计算数据
        	IndexInfo.initDatas();
        	//定时刷新 
        	IndexInfo.publish();
        },

        publish:function(){
        	setInterval(function(){    		 
            	//初始化table
            	IndexInfo.initDatas();
        	}, 10000);
        },
        initDatas:function(){
        	function putData(id,data){
        		$("#"+id).html(data[id]);
        	}
        	$.ajax( {
		        dataType: 'json',
		        type: "POST",
		        url:App.path+'/rs/external/statistics/comprehensive/findRealTimeDynamicAll',
		        contentType: "application/json; charset=utf-8",
		        success: function(data){
		        	//如果数量没有变化不发请求
		        	if(amountCount != data.amountCount){
			        	putData("cusCount",data);
			        	putData("empCount",data);
			        	putData("shopownerDivided",data);
			        	putData("amountCount",data);
		            	//初始化计算数据
		            	tables.all.fnDraw();
		            	amountCount = data.amountCount;
		        	
		        	/* 当数字太大掉下字体变小  开始 */
		    			var idwidth = document.getElementById('amountCount');
		    			var idwidth1 = document.getElementById('shopownerDivided');
		           		if((idwidth.innerHTML.length) > 7){
		            		idwidth.style.fontSize = '28px';
		             	}
		            	else{
		            		idwidth.style.fontSize = '34px';
		            	}
		           		if((idwidth1.innerHTML.length) > 7){
		            		idwidth1.style.fontSize = '28px';
		             	}
		            	else{
		            		idwidth1.style.fontSize = '34px';
		            	}
		        	}
		        }
	        } );
        },
        initTable:function(){   	    
            //店员信息            
        	var zcCols=[/*{
			        	"mDataProp":"storeId","sClass":"center","sTitle":"序号",
			        		"fnRender": function(data, type, full){
			        			return data.iDataRow+1;
			        		}
			            },*/
        	            {"mDataProp":"storeName","sTitle":"店铺名称"},
        	            {"mDataProp":"empName","sTitle":"员工姓名"},
        	            {"mDataProp":"receivable","sTitle":"应收"},
        	            {"mDataProp":"amountCount","sTitle":"实收"},
        	            {"mDataProp":"type","sTitle":"类型"}	,
        	            {"mDataProp":"remark","sTitle":"备注"},
        	            {"mDataProp":"dateVal","sTitle":"时间"}
        		];
        	tables.all  = Dtable.init("empList",
				//"chartForm",
				{},
				zcCols,
				App.path+'/rs/external/statistics/comprehensive/findRealTimeDynamic',
				function(b){
			
				});			
         
        }

    };

}();

