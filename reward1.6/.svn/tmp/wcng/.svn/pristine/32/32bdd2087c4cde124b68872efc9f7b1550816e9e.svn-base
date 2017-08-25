

//修改跳转
function updateInfo(){
	window.location.href="regist.xhtml";
};
$(function(){
	var url = addrManage.mer.findMerchantsInfoByOrgCode;
	getJson(url,"",function(data){
		if(data!=null&&data.resultMessage.messageCode=="0000"){			
			var status = data.resultMessage.status;
			//alert(status)
			if(status == 0){
				var da = data.resultData.body.result[0];
				//alert(da)
				if(da != null){
					for(key in da){
						var input = $("input[name = "+key+"]");
						if(input.length == 0){
						//处理input
						}else if(input.length == 1){
							input.val(da[key]);
						}else{
							console.log("属性【"+key+" = "+da[key]+"] 元素数量为 ："+input.length);
						}
					}

					$("#remark").html(da.remark);
				}
				var filePath = $("#filePath").val();
				if(filePath != ""){
					//$("#photoImg").attr("src",filePath);

			        $(".header").css("background-image","url('"+filePath+"')");
					//$("#photoDiv").hide();
				}
			}
			//RegisterMerchants.buildCodeInfo(da.type,da.codeType);
			//RegisterMerchants.initSelect(da);
			function getVal(name){
				return name==null?"":name;
			}
			$("#showCityPicker").html(getVal(da.typeName) + " " + getVal(da.codeTypeName));
			$("#showCityPicker3").html(getVal(da.provinceName) + " " + getVal(da.cityName)+ " " +  getVal(da.areaName));
		
			
			
		}else{
			 alert(data.resultMessage.messageText);
		}
	});
	
});