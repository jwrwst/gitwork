/*接口列表*/
var addrManage={
	mer:{
		checkAccount:App.path+"/rs/external/merchants/wap/checkAccount?v="+new Date().getTime(),
		checkOrgName:App.path+"/rs/external/merchants/wap/checkOrgName?v="+new Date().getTime(),
		findMerchantsInfoByOrgCode:App.path+"/rs/external/merchants/wap/findMerchantsInfoByOrgCode?v="+new Date().getTime(),
		//修改密码
		updatePassword:App.path+"/rs/external/merchants/updatePassword?v="+new Date().getTime(),
		//获取登录的用户信息
		getLoingInfo:App.path+"/rs/external/merchants/getLoingInfo?v="+new Date().getTime(),
		//更换绑定手机号
		updateBindMobile : App.path+"/rs/external/merchants/updateBindMobile?v="+new Date().getTime()
	},
	sms:{
		validateCode:App.path+"/rs/external/sms/wap/validateCode?v="+new Date().getTime()
	},
	//分成人员管理	
	divided:{	
		//获取分成人员列表
		getDivEmpListByStoreId:App.path+"/rs/external/storeEmpDiv/getDivEmpListByStoreId?v="+new Date().getTime(),
		//移除分成人员
		deleteDivManager:App.path+"/rs/external/storeEmpDiv/deleteDivManager?v="+new Date().getTime(),
		//edit
		edit:App.path+"/rs/external/empInfo/edit?v="+new Date().getTime()
	},
	gainCode:function(data,callback){
		 var url=App.path+"/rs/external/sms/wap/send?v="+new Date().getTime();
		 getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
		 });
	},
	//ajaxJson:ajaxJson,
	//getJson:getJson,
	/*获取分类信息（如店铺类型）
	 *参数 
	 * codeClass  //分类名称
	 * */
	getClassType:function(data,callback){
		var url=App.path+"/rs/external/sms/findByCodeClass?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	 },
	/*登陆
	 *参数 
	 * */	
	 login:function(data,callback){
		var url=App.path+"/rs/external/merchants/wap/login?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	 },
	/* 保存店铺信息
	 * 参数
	 * mechantsInfo;// 实体
	 * */
	saveMechants:function(data,callback){
		var url=App.path+"/rs/external/organize/save?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 修改商户信息
	 * 参数
	 * mechantsInfo;// 实体
	 * */
	updateMechants:function(data,callback){
		var url=App.path+"/rs/external/merchants/update?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 获取商户信息
	 * 参数
	 * orgCode;// 组织编号
	 * */
	getMechants:function(data,callback){
		var url=App.path+"/rs/external/merchants/getInfo?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 删除机构信息
	 * 参数
	 * orgCode;// 组织编号
	 * */
	removeOrganize:function(data,callback){
		var url=App.path+"/rs/external/organize/remove?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 获取商户赏金分配店铺列表
	 * 参数
	 * orgCode;// 组织编号
	 * */
	getRewardStoreList:function(data,callback){
		var url=App.path+"/rs/external/merchantsReward/getRewardStoreList?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 获取商户店铺列表
	 * 参数
	 * orgCode;// 组织编号
	 * */
	getMechantsStores:function(data,callback){
		var url=App.path+"/rs/external/merchants/getStoreList?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	
	/* 获取商户赏金分配方案列表
	 * 参数
	 * orgCode;// 组织编号
	 * */
	getMechantsRewards:function(data,callback){
		var url=App.path+"/rs/external/merchantsReward/getList?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	
	/* 保存商户赏金分配方案列表
	 * 参数
	 * mechantsRewards;// 实体
	 * */
	saveMechantsRewards:function(data,callback){
		var url=App.path+"/rs/external/merchantsReward/save?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	
	/* 获取商户列表及店铺列表
	 * 参数
	 * orgCode;// 组织编号
	 * */
	getOrganize:function(data,callback){
		var url=App.path+"/rs/external/organize/getList?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	
	/* 获取商户员工列表
	 * 参数
	 * orgCode;// 组织编号
	 * */
	getMerchantsEmployeePage:function(data,callback){
		var url=App.path+"/rs/external/merchantsEmployee/getPage?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 根据店铺编号获取店长列表
	 * 参数
	 * storeId;// 主键id
	 * */
	getManagerListByStoreId:function(data,callback){
		var url=App.path+"/rs/external/storeEmp/getEmpListByStoreId?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	
	/* 移除店员
	 * 参数
	 * empId
	 * */
	removeEmployee:function(data,callback){
		var url=App.path+"/rs/external/empInfo/removeEmployee?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 移除店长
	 * 参数
	 * empId,storeId
	 * */
	removeManager:function(data,callback){
		var url=App.path+"/rs/external/storeEmp/deleteManager?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	
	/* 移除店铺
	 * 参数
	 * storeId
	 * */
	removeStore:function(data,callback){
		var url=App.path+"/rs/external/storeInfo/updateStatus?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 移除商户人员
	 * 参数
	 * orgCode,empId
	 * */
	deleteMerchantsEmployee:function(data,callback){
		var url=App.path+"/rs/external/merchantsEmployee/deleteMerchantsEmployee?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 生成二维码下载地址
	 * 参数
	 * entity;// 实体
	 * */
	saveFileInfo:function(data,callback){
		var url=App.path+"/rs/external/fileInfo/save?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 修改店铺信息
	 * 参数
	 * storeInfo;// 主键id
	 * */
	editStore:function(data,callback){
		var url=App.path+"/rs/external/storeInfo/update?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 获取店铺信息
	 * 参数
	 * storeInfo;// 主键id
	 * */
	getStore:function(data,callback){
		var url=App.path+"/rs/external/storeInfo/getStoreInfo?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 修改店铺信息
	 * 参数
	 * storeInfo;// 主键id
	 * */
	saveStore:function(data,callback){
		var url=App.path+"/rs/external/storeInfo/saveStore?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 获取店铺赏金分配方案
	 * 参数
	 * storeId;// 店铺编号
	 * */
	getStoreExtReward:function(data,callback){
		var url=App.path+"/rs/external/storeInfo/getStoreExtReward?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 修改店铺赏金分配方案
	 * 参数
	 * storeExtRewardEntity;// 店铺扩展表实体
	 * */
	editStoreExtReward:function(data,callback){
		var url=App.path+"/rs/external/storeInfo/updateStoreExtReward?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 获取功能列表
	 * 参数
	 * storeInfo;// 主键id
	 * */
	getStoreAuth:function(data,callback){
		var url=App.path+"/rs/external/storeAuth/getStoreAuthList?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/* 获取所有人员列表
	 * 参数
	 * storeId,orgCode;// 主键id
	 * */
	getAllEmployee:function(data,callback){
		var url=App.path+"/rs/external/empInfo/getAllEmployee?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	/**
	 * 
	 */
	submitRegisterMerchants:function(){
		var url = App.path+"/rs/external/empInfo/getAllEmployee?v="+new Date().getTime();
		getJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
	
	/* 获取时间戳
	 **/
	getRandId:function(data,callback){
		var url=App.path+"/rs/external/merchants/wap/getRandId?v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	},
		

	
	/** 获取商户信息
	 **/
	loginMerchantInfoList:function(data,callback){
		var url=App.path+"/rs/external/merchants/wap/getMerchantInfo?uid="+data+"&v="+new Date().getTime();
		ajaxJson(url,data,function(datas){
			if(callback){
				callback(datas);
			}
		});
	}
};


//获取json数据
var  ajaxJson=function(url,transdata,callback){
	jQuery.ajax(url,{
		data:transdata,
		dataType:'json',//服务器返回json格式数据
		type:'post',//HTTP请求类型
		contentType: "application/json; charset=utf-8",
		timeout:10000,//超时时间设置为10秒；
		beforeSend: function(request) {
            request.setRequestHeader("nocross", "nocross");
        },
		success:function(data){
			//服务器返回响应，根据响应结果,具体分析； 
			if(data){	    			    		
  				callback(data);
	    	}else{
	    		
	    	}	
		},
		error:function(xhr,type,errorThrown){
			//异常处理；
			console.log(type);
		}
	});
};


var  getJson=function(url,transdata,callback){
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
};
	
	