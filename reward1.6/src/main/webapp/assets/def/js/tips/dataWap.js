/**
 * 数据字典构建
 */
var DateWap  = {
		/**
		 * 
		 * @param listenterIds		事件ID 暂时无用
		 * @param code				根级编码
		 * @param level				层级
		 * @param defu				默认值 数组['foot','huoguo']
		 * @param back
		 */
		init:function(listenterIds,code,sel,level,defVals,back){
			this.loadList(listenterIds,code,sel,level,defVals,back);
		},
		buildDefaluVal:function(cityPicker,defVals,list){
			var sels = list;
			for (var i = 0; i < defVals.length; i++) {
				for (var j = 0; j < sels.length; j++) {
					var sel = sels[j];
					if(sel.value == defVals[i]){
						cityPicker.pickers[i].setSelectedIndex(j);
						sels = sel.children;
						break;
					}
				}
				//cityPicker.pickers[i].setSelectedValue(defVals[i]);
			}					
		},
		/**
		 * 加载所有数据
		 */
		loadList:function(listenterId,code,sel,level,defVals,back){
			var url = webserver+"/rs/external/syscode/getAllList?v="+new Date().getTime();
			this.getJson(url,"pCode="+code,function(data){
				  if(data&&data.resultMessage.messageCode=="0000"){
					  var list = data.resultData.body.result;
					  //return ;
						(function($, doc) {
							//$.init();
							$.ready(function() {
								//级联示例
								var cityPicker = new $.PopPicker({
									layer: level
								});
								cityPicker.setData(list);
								if(defVals!=null && defVals[0] !=null){
									DateWap.buildDefaluVal(cityPicker, defVals, list);
								}
								mui('#'+listenterId).off('tap');
								mui('#'+listenterId).on('tap',sel,
										function(e) {
											cityPicker.show(function(items) {
												if(back){
													back(items,this);
												}
												//return false;
											});
										});
							});
						})(mui, document);
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