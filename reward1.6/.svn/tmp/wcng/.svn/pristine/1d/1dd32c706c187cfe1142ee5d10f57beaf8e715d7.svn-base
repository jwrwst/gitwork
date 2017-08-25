var Area  = {
    	//加载前调用
		provinceChange:null,
    	//区域加载后调用
		cityChange:null,
    	//区域改变时调用
		areaChange:null,
		//parendIds:null,
		init:function(parendId,defPro,defCity,defArea,back){
			//Area.parendIds = parendIds;
			this.loadProvince(defPro,defCity,defArea,back,parendId);
			this.addChangeListenter(parendId);
		},
		/**
		 * 添加change事件
		 */
		addChangeListenter : function(parendId) {
			var pro = parendId==null?$(".auto-select-province"):$("#"+parendId).find(".auto-select-province");
			pro.change(function() {
				var code = $(this).val();
				Area.loadCity(code,null,null, null,parendId);
				if (Area.provinceChange) {
					Area.provinceChange(code, parendId);
				}
			});

			var city = parendId==null?$(".auto-select-city"):$("#"+parendId).find(".auto-select-city");
			city.change(function() {
				var code = $(this).val();
				Area.loadArea(code,null,null,null,parendId);
			});
			var area = parendId==null?$(".auto-select-area"):$("#"+parendId).find(".auto-select-area");
			area.change(function() {
				var code = $(this).val();
				if (Area.areaChange) {
					Area.areaChange(code, parendId);
				}
			});
		},
		loadArea:function(cityCode,defArea,back,parendId){
			var url = App.path+"/rs/external/sysArea/wap/getAreaInfoList?v="+new Date().getTime();
			this.getJson(url,"pCode="+cityCode,function(data){
				  if(data&&data.resultMessage.messageCode=="0000"){
					  var list = data.resultData.body.result;
					  var strHtml=[];
					  strHtml.push("<option value='' data=''>全部区县</option>");	
					  for(var i=0;i<list.length;i++){
						  var da = list[i];
						  if(da.code == defArea){
							  strHtml.push("<option selected='selected' value='"+da.code+"' data='"+da.id+"''>"+da.name+"</option>");				  
						  }else{
							  strHtml.push("<option value='"+da.code+"' data='"+da.id+"''>"+da.name+"</option>");			  
						  }
					  }
					  //如果存在父id只修改父id下面的页面
					  if(parendId){
						  $("#"+parendId).find(".auto-select-area").html(strHtml.join(""));
					  }else{
						  $(".auto-select-area").html(strHtml.join(""));
					  }
					  if(back){
						  back();	  
					  }
					  if (Area.cityChange) {
						Area.cityChange(cityCode, parendId);
					  }
				  }
			});
		},
		/**
		 * 加载城市
		 */
		loadCity:function(proCode,defCity,defArea,back,parendId){
			var url =  App.path+"/rs/external/sysArea/wap/getAreaInfoList?v="+new Date().getTime();
			this.getJson(url,"pCode="+proCode,function(data){
				  if(data&&data.resultMessage.messageCode=="0000"){
					  var list = data.resultData.body.result;
					  var strHtml=[];
					  strHtml.push('<option value="" data="">全部城市</option>');	
					  for(var i=0;i<list.length;i++){
						  var da = list[i];
						  if(da.code == defCity){
							  strHtml.push("<option selected='selected' value='"+da.code+"' data='"+da.id+"''>"+da.name+"</option>");				  
						  }else{
							  strHtml.push("<option value='"+da.code+"' data='"+da.id+"''>"+da.name+"</option>");			  
						  }
					  }
					  //如果存在父id只修改父id下面的页面
					  if(parendId){
						  $("#"+parendId).find(".auto-select-city").html(strHtml.join(""));
					  }else{
						  $(".auto-select-city").html(strHtml.join(""));
					  }
					  if(defCity == null && list[0] != null){
						  defCity = list[0].code;
					  }
					  //城市
					  Area.loadArea(defCity,defArea,back,parendId);
				  }
			});
		},
		/**
		 * 加载省份
		 */
		loadProvince:function(defPro,defCity,defArea,back,parendId){
			var url =  App.path+"/rs/external/sysArea/wap/getAreaInfoList?v="+new Date().getTime();
			this.getJson(url,"pCode=000",function(data){
				  if(data&&data.resultMessage.messageCode=="0000"){
					  var list = data.resultData.body.result;
					  var strHtml=[];
					  strHtml.push("<option value='' data=''>全部省份</option>");	
					  for(var i=0;i<list.length;i++){
						  var da = list[i];
						  //默认值
						  if(da.code == defPro){
							  strHtml.push("<option selected='selected' value='"+da.code+"' data='"+da.id+"'>"+da.name+"</option>");	
							  defCode = da.code;
						  }else{
							  strHtml.push("<option value='"+da.code+"' data='"+da.id+"''>"+da.name+"</option>");			  
						  }
					  }
					  //如果存在父id只修改父id下面的页面
					  if(parendId){
						  $("#"+parendId).find(".auto-select-province").html(strHtml.join(""));
					  }else{
						  $(".auto-select-province").html(strHtml.join(""));
					  }

					  if(defPro == null && list[0] != null){
						  defPro = list[0].code;
					  }
					  //城市
					  Area.loadCity(defPro,defCity,defArea,back,parendId);
				  }
			});
		},
		getJson:function(url,transdata,callback){
			jQuery.ajax(url,{
				data:transdata,
				dataType:'json',//服务器返回json格式数据
				type:'get',//HTTP请求类型
				timeout:10000,//超时时间设置为10秒；
				beforeSend: function(request) {
		            request.setRequestHeader("nocross", "nocross");
		        },
				success:function(data){
					//服务器返回响应，根据响应结果,具体分析； 
					if(data){	    		
				    	  if(data.statusCode && data.statusCode == 9991 ){
				    		  mui.alert(data.message?data.message:"请求失败，请检查网络", '提示信息', function() {
									//确定之后需要执行的
								});
				    	  }else{
				    		  if(data){
				  				callback(data);
				  			} 
				    	  }	
				    }	
				},
				error:function(xhr,type,errorThrown){
					//异常处理；
					console.log(type);
				}
			});
		}
};