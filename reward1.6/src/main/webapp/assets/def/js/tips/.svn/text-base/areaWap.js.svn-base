/**
 * 构建区域
 */
var AreaWap  = {
		
		init:function(ids,sel,defPro,defCity,defArea,back){
			this.loadList(ids,sel,defPro,defCity,defArea,back);
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
		loadList:function(id,sel,defPro,defCity,defArea,back){
			//var url = webserver+"/rs/external/sysArea/getAllList?v="+new Date().getTime();
			var url = webserver+"/assets/def/js/tips/areadata.json?v="+new Date().getTime();
			this.getJson(url,"",function(data){
				  if(data&&data.resultMessage.messageCode=="0000"){
					  var list = data.resultData.body.result;
					  //return ;
						(function($, doc) {
							//$.init();
							$.ready(function() {
								//级联示例
								var cityPicker = new $.PopPicker({
									layer: 3
								});
								cityPicker.setData(list);
								//设置默认值
								/*if(defPro){
									cityPicker.pickers[0].setSelectedValue(defPro);
								}
								if(defCity){
									cityPicker.pickers[1].setSelectedIndex(1);
									cityPicker.pickers[1].setSelectedValue(defCity);
								}
								if(defArea){
									cityPicker.pickers[2].setSelectedIndex(2);
									cityPicker.pickers[2].setSelectedValue(defArea);
								}*/
								AreaWap.buildDefaluVal(cityPicker, [defPro,defCity,defArea], list)
								//var citypicke = doc.getElementById('showCityPicker3');
								//citypicke.addEventListener('tap', function(event) {
								mui('#'+id).off('click');
								mui('#'+id).on('click',sel,function(e) {
									cityPicker.show(function(items) {
										if(back){
											back(items);
										}
										//返回 false 可以阻止选择框的关闭
										//return false;
									});
								}, false);
								//-----------------------------------------
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