//定义模块
define(["jquery","mui","iscroll","jweixin"],function(a,b,c,wx){
	mui.init({
				swipeBack: true ,//启用右滑关闭功能
				keyEventBind:{
					backbutton:true,
				}
	});
	//初始化单页的区域滚动	
	mui('.mui-scroll-wrapper').scroll({deceleration: 0.0001});
	
	//加入页脚
	jQuery(".scroller").append('<div class="plat-title mui-text-center" style="width:50%; margin:0 auto;" ><img style="height:20px;" alt="logo" src="/assets/def/images/logo.png"><span style="float:right; line-height:20px;">众赏之下，人人勇夫<br /></span></div>');
	//jQuery(".scroller").append('<div class="plat-title mui-text-center" >众赏平台</div>');
	//设置高度
	jQuery(document).ready(function(){
		var tab = window.document.querySelector(".mui-bar-tab");
		var nav = (jQuery(".mui-navbar").css("display")=="none"?null:"1");
		if(tab==null && nav == null){
			jQuery(".plat-content").css("min-height", (jQuery(window).height()-50 )+"px" );
		}else if (tab==null && nav != null){
			jQuery(".plat-content").css("min-height", (jQuery(window).height()-30 )+"px" );
		}else if (tab!=null && nav == null){
			jQuery(".plat-content").css("min-height", (jQuery(window).height()-90 )+"px" );
			jQuery(".plat-title").css("min-height", "80px" );
		}else{
			jQuery(".plat-content").css("min-height", (jQuery(window).height()-130 )+"px" );
			jQuery(".plat-title").css("min-height", "150px" );
			
		}	
		
		//失去焦点
		jQuery("body").on("click",function(e){			
			var nodeType = e.explicitOriginalTarget ? e.explicitOriginalTarget.nodeName.toLowerCase():(e.target ? e.target.nodeName.toLowerCase():'');
			if(nodeType =='input'|| nodeType =='textarea' || nodeType =='select' || nodeType == 'option') return;
		
			jQuery("body").blur();
			document.activeElement.blur();
	    });
	
	});
	
	
	
	//判断权限	
	merchants= window.localStorage.getItem("merchants");
	var merchantFunc=function(){
		try{
		    merchants= window.localStorage.getItem("merchants");
			if(merchants!=null && merchants !=""){
				merchants = JSON.parse(merchants);
				//临时处理很久以前人员权限
				if(window.localStorage.getItem("empId")==58 || window.localStorage.getItem("empId")==53){
					return;
				}
				//店铺权限
				if(merchants.isedit == 2){
					jQuery(".isedit").attr("readonly",true);
					jQuery(".isedit-display").hide();
					jQuery(".isedit-sel").each(function(){
						if(jQuery(this).attr("disabled") == null || (jQuery(this).attr("disabled") != "disabled" && jQuery(this).attr("disabled") != true )){
							jQuery(this).after("<input type='hidden' name='"+jQuery(this).attr("name")+"' value='"+jQuery(this).val()+"'/>");
						}else{
							jQuery(this).next().val(jQuery(this).val());
						}
					});
					jQuery(".isedit-sel").attr("disabled",true);
				}
				//赏金分配权限
				if(merchants.iseditVal1 == 2){
					jQuery(".iseditVal1").attr("readonly",true);
					jQuery(".iseditVal1-display").hide();					
					jQuery(".iseditVal1-sel").each(function(){
						if(jQuery(this).attr("disabled") == null || (jQuery(this).attr("disabled") != "disabled" && jQuery(this).attr("disabled") != true )){
							jQuery(this).after("<input type='hidden' name='"+jQuery(this).attr("name")+"' value='"+jQuery(this).val()+"'/>");
						}else{
							jQuery(this).next().val(jQuery(this).val());
						}
					});
					jQuery(".iseditVal1-sel").attr("disabled",true);
				}
				//赏金分成人员权限
				if(merchants.iseditVal2 == 2){
					jQuery(".iseditVal2").attr("readonly",true);
					jQuery(".iseditVal2-display").hide();					
					jQuery(".iseditVal2-sel").each(function(){
						if(jQuery(this).attr("disabled")==null || (jQuery(this).attr("disabled") != "disabled" && jQuery(this).attr("disabled") != true ) ){
							jQuery(this).after("<input type='hidden' name='"+jQuery(this).attr("name")+"' value='"+jQuery(this).val()+"'/>");
						}else{
							jQuery(this).next().val(jQuery(this).val());
						}
					});
					jQuery(".iseditVal2-sel").attr("disabled",true);
				}
				
			}
		}catch(e){merchants={isedit:1,iseditVal1:1};}
	};merchantFunc();
	
	
    /*方法封装*/
	var tools={
		$:jQuery,
		global:{
			 storeId : window.localStorage.getItem("storeId"),
			 empId : window.localStorage.getItem("empId"),
			 page:{isDisplay:""},
			 merchants:merchants,
			 merchantFunc:merchantFunc
		},
	    //提交提示框
	    toast:function(title,content,_callback) {
	    	mui.alert(content, " ", _callback);
		},
	    toastTip:function(title,content,_callback) {
			mui.toast(content);			
		},
		confirm:function(title,content,_callback) {
			//mui.confirm(message,title,btnArray,callback)
			return mui.confirm(content,title,null,_callback);			
		},
		toastWindow:function(title,content,_callback){
			var makdrop=document.getElementById("makdrop");
			makdrop.style.display="block";
			var toast = document.createElement('div');
			toast.classList.add('mui-popup');
			toast.classList.add('mui-popup-in');
			toast.style.display = 'block';
			toast.style.zIndex = 10000000;
			toast.innerHTML = '<div class="mui-popup-inner" style="border-radius:13px;padding:0;">'+
			'<div class="mui-popup-title mui-self-tit" style="border-radius:13px 13px 0 0">'+str+'</div>'+
			'<span class="mui-icon mui-self-correct" style="margin-top:20px;"></span>'+
			'<div class="mui-popup-text" style="padding:15px;color:#616060">'+content+' </div>'+
			'</div>';
			document.body.appendChild(toast);
			setTimeout(function() {
				makdrop.style.display="none";
				document.body.removeChild(toast);
			}, 3500);
		},
		//loading加载动画
		loading:function(){
			//var mask = mui.createMask(function(){oImg.style.display='none';});//callback为用户点击蒙版时自动执行的回调；
			//mask.show();//显示遮罩
			if(document.getElementById('oMask') ){
				document.getElementById('oMask').style.display='block';
				document.getElementById('oImg').style.display='block';
			}else{
				var oMask=document.createElement('div');
				var oImg = document.createElement('img');			
				oMask.id='oMask';
				oMask.style.background='#000';
				oMask.style.opacity = "0.6";
				oMask.style.filter = "alpha(opacity=60%)";
				oMask.style.width=document.body.scrollWidth+'px';
				oMask.style.height=document.body.scrollHeight+'px';
				oMask.style.position="absolute";
				oMask.style.top=0;
				oMask.style.left=0;
				oMask.style.zIndex=999999999999;
				oImg.id="oImg";
				oImg.src= webhost+"/assets/def/images/load.gif";
				oImg.style.position="absolute";
				oImg.style.left = "50%";
				oImg.style.top = "50%"; 
				oImg.style.marginLeft = "-16px";
				oImg.style.marginTop = "-16px";
				oMask.style.zIndex=9999999999999;
				//oImg.innerHTML='<img src="'+webhost+'/assets/def/images/load.gif" style="position:absolute;left:50%;top:50%; margin-left:-16px;margin-top:-16px;" />'; 
				document.body.appendChild(oImg);
				document.body.appendChild(oMask);
			}
			return {
				close:function(){
					document.getElementById('oMask').style.display='none';
					document.getElementById('oImg').style.display='none';
				}
			};
		},
		mask:function(){
			var mask = mui.createMask(callback);//callback为用户点击蒙版时自动执行的回调；
			mask.show();//显示遮罩
			//mask.close();//关闭遮罩
			return mask;
		},
		setStar:function(index,listStar,color){
			//background-image: -webkit-gradient(linear, left 0, right 0, color-stop(0.50, #EA4F16),color-stop(0.5, #ccc));
			listStar.css("color","#ccc");
			listStar.removeClass("icon-bstar").css("background-image","");
			var len= parseInt(index);
			var lastItem=parseFloat(index)-len;
			if(lastItem>0){
				listStar.eq(len).addClass("icon-bstar").css("background-image","-webkit-gradient(linear, left 0, right 0, color-stop("+lastItem+", "+(color||"#EA4F16")+"),color-stop("+lastItem+", #ccc))");
			}
		    for(var i=0;i<len;i++){
		    	listStar.eq(i).css("color",color||"#EA4F16");		    	
		    }
		},
		is_money:function (list){
			var a=/^[0-9]*(\.[0-9]{1,2})?$/;
			if(list==""||list==null){
				return false;
			}
			if(typeof(list) == "string"){
				return a.test(list);
			}
			
			if(typeof(list) == "object"){
				list=[list];
			}
			for(var i=0;i<list.length;i++){
				var v= list[i].val();
				if(v=="" || v== null){
					return false;
				}
				if(!a.test(v)){
					return false;
				}				
			}
			return true;
		},
		is_weixin:function (){
			 //判断是否从微信浏览器打开
			var ua = navigator.userAgent.toLowerCase();
			if(ua.match(/MicroMessenger/i)=="micromessenger") {
				return true;
		 	} else {
				return false;
			}
	 	},
	 	getQueryString:	function(name) {
	 		//获取url中的参数
	 		var svalue = window.location.href.match(new RegExp("[\?\&]" + name + "=([^\&]*)(\&?)", "i"));
	 		return svalue ? svalue[1] : "";
	 	},		 	
	    payTo:function(addr,trandata,_callback,_cancelback){   
	    	 var _this=this;
	    	//支持微信支付
			 if(_this.is_weixin()){	
				addr.wxPayTo(trandata,function(datas){
					if(datas!=null&&datas.resultMessage.messageCode=="0000"){						
					   var payment = datas.resultData.body.result.json;
					   if(payment){ 							 
						  var urlArry =JSON.parse("{"+payment+"}");
						  wx.chooseWXPay({
							    "timestamp": urlArry.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
							    "nonceStr": urlArry.nonceStr, // 支付签名随机串，不长于 32 位
							    "package": urlArry.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
							    "signType" : urlArry.signType,        //微信签名方式:     
						    	"paySign" : urlArry.paySign, //微信签名 
						    	"success" : function(){
						    		if(_callback){
					    				_callback();
					    			}
						    	},
						    	"fail" : function(){
						    		if(_cancelback){
					            		var orderId = datas.resultData.body.result.orderId;
					            		_cancelback(orderId);
					            	}
						    	},
						    	"cancel" : function(){
						    		if(_cancelback){
					            		var orderId = datas.resultData.body.result.orderId;
					            		_cancelback(orderId);
					            	}
						    	}
						    	/**
						    	complete: function (res) {
						    		if(res.errMsg == "chooseWXPay:ok" ) {
						    			if(_callback){
						    				_callback();
						    			}
						            }else{
						            	if(_cancelback){
						            		var orderId = datas.resultData.body.result.orderId;
						            		_cancelback(orderId);
						            	}
						            }   
						    	}**/
							});			
					    }
					}else{
						mui.alert("初始化微信支付失败");
					}
				 });	
				}else{//支付宝支付					
					addr.aliPayto(trandata,function(datas){
						if(datas.statusCode=="0000"){
							if(_callback){
			    				_callback();
			    			}
						}
					});
					
				}
		},
		initWeiXin:function(addr){
			var url=encodeURIComponent(window.location.href);  
//			if(window.location.href.indexOf("?")>-1){
//				url=encodeURIComponent(window.location.href.substring(0,window.location.href.indexOf("?")));  
//			}
		    // 初始化微信JS
			addr.initWeiXin("url="+url,function(redata){
		        try {                	
		            wx.config({
		               debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		               appId : redata.appId, // 必填，公众号的唯一标识
		               timestamp : redata.timestamp, // 必填，生成签名的时间戳
		               nonceStr : redata.noncestr, // 必填，生成签名的随机串
		               signature : redata.signature,// 必填，签名，见附录1
		               jsApiList : ['chooseWXPay','previewImage','uploadImage','chooseImage','scanQRCode']
		                   // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		               });
		       } catch (e) {
		           console.log(e);
		       }
			});
	
		     
		},
		scrollBar:function(item,_callback){
			/**
			 var scroller= new FTScroller(item, {
				   scrollingX: !1,
				    bouncing: !1,
				    snapping: !1,
				    scrollbars: !0,
				    scrollBoundary: 8,
				    updateOnChanges: !0,
				    updateOnWindowResize: !0
			   });
			  //滚动底部触发事件
			   var scrollHeight=0;
			   scroller.addEventListener("reachedend",function(){
				    if(!(typeof _callback === "function"))return;
				   
				    scrollHeight=scroller.scrollHeight;

                    if(tools.global.isDisplay =="none")return;
                    
			        var html="<div id='loading_data' style='position:fixed;left:0px;bottom:0px;height:50px;width:100%;text-align: center;line-height: 50px;background-color: #ffffff;color:#444444;font-size:15px;z-index:60000;'><span class='mui-icon mui-icon-spinner-cycle'></span><span id='loading_data_text'>正在加载</span></div>";
			        $("#loading_data").remove();
			        $("body").append(html);
			        
			        _callback();
					
					setTimeout(function(){
						$("#loading_data").remove();
				    },3000);
			   });
			   scroller.addEventListener("segmentdidchange", function(){
				   if(scrollHeight!=0){
					   scroller.scrollTo(0,scrollHeight-10);
					   scrollHeight=0;
				   }
			   });
			   
			   return scroller;
			   **/
			   var flag=true;
			   myScroll = new iScroll(item, {
					checkDOMChanges: true,
					fixedScrollbar:true,
					hideScrollbar:true,					
//					onScrollEnd:function(){
//						if(!(typeof _callback === "function"))return;		
//				        _callback();					
//
//					},
					onScrollLimit: function(){
						if(flag==false)return;
						flag = false;
						window.setTimeout(function(){
							flag = true;
						}, 2000);
					    if(!(typeof _callback === "function"))return;		
				        _callback();
					}
			   });
			   document.addEventListener('touchmove', function (e) { 
				   e.preventDefault(); 
			   }, false);
			   
 			   
			   return myScroll;
		},
		scroller:null
		
	};
   
    window.onpopstate= function(obj){ 
	        //如何有div就用div切换
	    	if (mui('#center')&&mui('#center').view().canBack()) { //如果view可以后退，则执行view的后退
				mui('#center').view().back();
			}    	
	}; 
	
	//处理view的后退与webview后退
	(function($) {			
		$.back = function() {			
			window.history.back();
		};
	})(mui);
	
	//处理金额
	(function($){
		var math_tools={
				isNumber:function(key) {  
				    return key >= 48 && key <= 57  ;
				},
				isSpecialKey:function(key) {  
				    //8:backspace; 46:delete; 37-40:arrows; 36:home; 35:end; 9:tab; 13:enter  
				    return key == 8 || key == 46 || (key >= 37 && key <= 40) || key == 35 || key == 36 || key == 9 || key == 13  ;
				} ,
				isFullStop:function(key) {  
				    return key == 190 || key == 110;  
				} 
		};
		  
 
	    $("body").on("contextmenu","input.money", function(){  
	        return false;  
	    });  
	  
	    $("input.money").css('ime-mode', 'disabled');  
	      
	    $("body").on("keydown","input.money" ,function(e) {  
	        var key = window.event ? e.keyCode : e.which;  
	        if (math_tools.isFullStop(key)) {  
	            return $(this).val().indexOf('.') < 0;  
	        }  
	        return (math_tools.isSpecialKey(key)) || ((math_tools.isNumber(key) && !e.shiftKey));  
	    });  
 
	})(jQuery);
	
	//序列化json
    (function($){
		$.fn.serializeJson=function(){  
		    var serializeObj={};  
		    var array=this.serializeArray();  
		    //var str=this.serialize();
		    $(array).each(function(){
		    	if(this.name.indexOf(".")>-1){
		    		var sp=this.name.split(".");
		    		var a0=sp[0];
		    		var a1=sp[1];
		    		//判断对象是否存在
		    		if(serializeObj[a0]){
		    			var obj=serializeObj[a0];
		    			//判断集合是否存在
		    			if(obj[obj.length-1][a1]){
		    				var temp={};
		    				temp[a1]=this.value;
		    				obj.push(temp);
		    			}else{
		    				obj[obj.length-1][a1]=this.value;
		    			}
		             }else{
		            	 serializeObj[a0]=[]; 
		            	 
		            	 var temp={};
		 				 temp[a1]=this.value;
		                 serializeObj[a0].push(temp);
		             }    
		    	}else{
		    		if(serializeObj[this.name]){  
		                if($.isArray(serializeObj[this.name])){  
		                    serializeObj[this.name].push(this.value);  
		                }else{  
		                    serializeObj[this.name]=[serializeObj[this.name],this.value];  
		                }  
		            }else{  
		                serializeObj[this.name]=this.value;   
		            }
		    	}
		    	
		        
		    });  
		    return serializeObj;  
		};  
	})(jQuery);
	
    (function($) { 
        $.fn.imgcomplete = function(callback) {  
            return this.each(function() { 
                var self = this,  
                    $this = $(this);  
                if (!$this.is("img") && $this.css("background-image")==null) {  
                    return true;  
                }  
                var src=$this.attr("src");
                if($this.css("background-image")!=null){
                	src=$this.css("background-image").replace("url(\"","").replace("\")","").replace("url('","").replace("')","").replace("url(","").replace(")","");
                }
                
                var img = new Image();  
                img.src = src;  
                if (img.complete) { // 如果图片已经存在于浏览器缓存, 直接回调  
                    callback.call(self, img.width, img.height);  
                } else {  
                    img.onload = function () { // 经测试IE/FF都支持(测了IE8/FF10)  
                        if (!img.complete) return;  
                        callback.call(self, img.width, img.height);  
                    };  
                }  
                return true;  
            });  
        };  
    })(jQuery);
		
   //流动条插件加载
   (function($){
	   var containerElement = document.getElementById('scroll-content');
	   if(null==containerElement)return;
	   var func= !(typeof loaddata === "function")?null:loaddata;
	   tools.scroller=tools.scrollBar(containerElement,func);
   })(jQuery);

   
   
	
   return tools;
	
});