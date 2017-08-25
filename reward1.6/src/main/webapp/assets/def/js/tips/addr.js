/**定义接口地址模块**/
define(["jquery","mui"],function($$,mui){
	//防止重得添加记录
	var control = {};
	function getUrl(url){
		if(url.indexOf('save')){
			return url.substring(0,url.indexOf('?'));
		}		
		return false;
	}
	//获取json数据
	var  ajaxJson=function(url,transdata,callback){
		var u = getUrl(url);
		if(u!=null ){
			if(control[u]){
				return;
			}
			control[u] = true;
			//一秒后自动恢复
			setTimeout(function(){
				control[u] = false;
			}, 1000)
		}

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
				control[u] = false;
				//服务器返回响应，根据响应结果,具体分析； 
				if(data){	    			    		
	  				callback(data);
		    	}else{
		    		mui.alert("请求失败，请检查网络", '提示信息', function() {
							//确定之后需要执行的
					});
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
	};
	/*接口列表*/
	var tipManage={
		com:{
			randomCode:webserver+"/rs/external/merchants/wap/randomCode?v=" +new Date().getTime()
		},
		mer:{
			checkAccount:webserver+"/rs/external/merchants/wap/checkAccount?v="+new Date().getTime(),
			checkOrgName:webserver+"/rs/external/merchants/wap/checkOrgName?v="+new Date().getTime
		},
		user:{
			//校验手机验证码
			validateCode : webserver+"/rs/external/sms/wap/validateCode?v="+new Date().getTime(),
			//修改密码
			modifyPassword : webserver+"/rs/external/merchants/modifyPassword?v="+new Date().getTime(),
			//图片验证码和手机验证码的有效性
			validateInfo : webserver+"/rs/external/sms/wap/validateInfo?v="+new Date().getTime(),
			//更换绑定手机号
			updateBindMobile : webserver+"/rs/external/merchants/updateBindMobile?v="+new Date().getTime(),
			//更换绑定手机号
			updatePasswordByPhone :  webserver+"/rs/external/merchants/wap/updatePasswordByPhone?v="+new Date().getTime()
		},
		sys:{
			//get：根据父ID查询
			getCodeInfoList: webserver+"/rs/external/syscode/getCodeInfoLists?v="+new Date().getTime()
		},
		store:{
			//更新门店状态
			updateStatus: webserver+"/rs/external/storeInfo/updateStatus?v="+new Date().getTime(),
			//更新门店状态
			saveStore: webserver+"/rs/external/storeInfo/saveStore?v="+new Date().getTime(),
			//根据商户编码获取统计数据
			getStoreStatisticInfoByOrgCode:webserver+"/rs/external/storeStatistic/getStoreStatisticInfoByOrgCode?v="+new Date().getTime(),
			//获取可创建店铺数
			getCreateStoreNum:webserver+"/rs/external/storeInfo/getCreateStoreNum?v="+new Date().getTime()
		},	
		mercants:{
			//删除商户
			del: webserver+"/rs/external/merchants/delete?v="+new Date().getTime(),
			//删除商户
			getListByParentCode: webserver+"/rs/external/organize/getListByParentCode?v="+new Date().getTime(),
			//根据父编码查询区域或者商户下门店
			searchStoreByParentCode: webserver+"/rs/external/merchants/searchStoreByParentCode?v="+new Date().getTime(),
			//删除商户
			getInfo: webserver+"/rs/external/merchants/getInfo?v="+new Date().getTime(),
			//查询上级等级
			findParentOrg:webserver+"/rs/external/merchants/findParentOrg?status=2&v="+new Date().getTime(),
			//添加区域
			addArea:webserver+"/rs/external/organize/save?status=2&v="+new Date().getTime(),
			//删除区域
			delArea:webserver+"/rs/external/organize/remove?status=2&v="+new Date().getTime(),
			//修改密码下一步
			nextRefindPassword:webserver+"/rs/external/merchants/wap/nextRefindPassword?v="+new Date().getTime()
		},
		emp:{
			//商户管理人员
			getAreaManagerByStoreID:webserver+"/rs/external/storeEmp/getAreaManagerByStoreID?v="+new Date().getTime(),
			//获取分成人员列表
			getDivEmpListByStoreId:webserver+"/rs/external/storeEmpDiv/getDivEmpListByStoreId?v="+new Date().getTime(),
			//移除分成人员
			deleteDivManager:webserver+"/rs/external/storeEmpDiv/deleteDivManager?v="+new Date().getTime(),
			//edit
			edit:webserver+"/rs/external/empInfo/edit?v="+new Date().getTime()
		},
		statistics:{
			//统计商户数据
			findCharStatistcsAll:webserver+"/rs/external/statistics/comprehensive/findCharStatistcsAll?v="+new Date().getTime()
		},
		getJson:getJson,
		ajaxJson:ajaxJson,
		/**************************************************商户注册管理模式*******************************************************************/		
		/**
		 * 商户信列表
		 */
		findMerchantsInfoEntity:function(data,callback){
			var url = webserver+"/rs/external/merchants/findMerchantsInfoEntity?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/**
		 * 用户信息
		 */
		getUserInfo:function(data,callback){
			var url = webserver+"/rs/external/empInfo/getEmployeeInfo?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		updatePassword:function(data,callback){
			var url=webserver+"/rs/external/merchants/updatePassword?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/** 根据员工编号获取门店列表
		 * 参数
		 * storeInfo;// 主键id
		 * */
		getStoreListByOpenId:function(data,callback){
			var url=webserver+"/rs/external/merchants/getStoreList?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},	
		/** 根据员工编号获取门店列表
		 * 参数
		 * storeInfo;// 主键id
		 * */
		searchStoreListByOpenId:function(data,callback){
			var url=webserver+"/rs/external/merchants/searchStoreList?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},	
		/**
		 * 校验账号
		 */
		checkMobile:function(data,callback){
			var url = webserver+"/rs/external/merchants/checkMobile?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/**
		 * 更新商户账号
		 */
		createUser:function(data,callback){
			var url = webserver+"/rs/external/merchants/createUser?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
			
		},
		/**
		 * 根据orgCode查商户信息
		 */
		getRegisterMerchantsEntity:function(data,callback){
			var url = webserver+"/rs/external/merchants/getRegisterMerchantsEntity?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/**
		 * 提交注册商户
		 * 
		 */
		submitRegisterMerchants:function(data,callback){
			var url = webserver+"/rs/external/merchants/registerMerchants?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/************************************************************系统数据****************************************************/
		/*获取分类信息（如店铺类型）
		 *参数 
		 * codeClass  //分类名称
		 * */	
		 getClassType:function(data,callback){
			var url=webserver+"/rs/external/syscode/findByCodeClass?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		 },
		 /**********************************************************店铺模块*************************************************************/
		/* 保存店铺信息
		 * 参数
		 * storeInfo;// 主键id
		 * */
		saveStore:function(data,callback){
			var url=webserver+"/rs/external/storeInfo/save?v="+new Date().getTime();
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
			var url=webserver+"/rs/external/storeInfo/update?v="+new Date().getTime();
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
			var url=webserver+"/rs/external/storeInfo/getStoreInfo?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 根据员工编号获取门店列表
		 * 参数
		 * storeInfo;// 主键id
		 * */
		getStoreList:function(data,callback){
			var url=webserver+"/rs/external/storeDynamic/getStoreListByEmpId?v="+new Date().getTime();
			getJson(url,data,function(datas){
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
			var url=webserver+"/rs/external/storeAuth/getStoreAuthList?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 判断权限信息
		 * 参数
		 * storeInfo;// 主键id
		 * */
		storeAuthDetail:function(data,callback){
			var url=webserver+"/rs/external/storeAuthDetail/getStoreAuth?v="+new Date().getTime();
			getJson(url,data,function(datas){
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
			var url=webserver+"/rs/external/storeInfo/getStoreExtReward?v="+new Date().getTime();
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
			var url=webserver+"/rs/external/storeInfo/updateStoreExtReward?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 根据店铺编号获取门店数据
		 * 参数
		 * storeInfo;// 主键id
		 * */
		getDataByStoreId:function(data,callback){
			var url=webserver+"/rs/external/storeDynamic/getStoreListByEmpId?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/*****************************************************************************************************************/
		/* 根据店铺编号获取店长列表
		 * 参数
		 * storeId;// 主键id
		 * */
		getManagerListByStoreId:function(data,callback){
			var url=webserver+"/rs/external/storeEmp/getEmpListByStoreId?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 根据店铺编号获取分成列表
		 * 参数
		 * storeId;// 主键id
		 * */
		getDivEmpListByStoreId:function(data,callback){
			var url=webserver+"/rs/external/storeEmpDiv/getDivEmpListByStoreId?v="+new Date().getTime();
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
			var url=webserver+"/rs/external/fileInfo/save?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 获取权限
		 * 参数
		 * storeId;// 主键id
		 * */
		getMerchantsByStoreId:function(data,callback){
			var url=webserver+"/rs/external/merchants/getMerchantsByStoreId?v="+new Date().getTime();
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
			var url=webserver+"/rs/external/merchantsReward/getList?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		///////////////店铺动态///////////
		/* 今日数据
		 * 参数
		 * storeInfo;// 主键id
		 * */
		getStoreDynamicByStoreId:function(data,callback){
			var url=webserver+"/rs/external/storeDynamic/getStoreDynamicByStoreId?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 店员小费及评价列表
		 * 参数
		 * {"numPerPage":10,"storeid":9,"pageNum":1}
		 * */
		toDynamicListPage:function(data,callback){
			var url=webserver+"/rs/external/storeDynamic/toListPage?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/////////////店铺排行////////////
		/* 评价排行主页
		 * 参数
		 * {"querytype":"storeEvaluate","filter":"month","numPerPage":10,"storeid":9,"pageNum":1}
		 * */
		toRankListPage:function(data,callback){
			var url=webserver+"/rs/external/storeRank/toListPage?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 店员评价详情
		 * 参数
		 * storeid & empid
		 * */
		getEmployeeEvaluateRankDetail:function(data,callback){
			var url=webserver+"/rs/external/storeRank/getEmployeeEvaluateRankDetail?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 评价排行主页
		 * 参数
		 * {"querytype":"empEvaluate","filter":"month","numPerPage":10,"storeid":9,"empid":43,"pageNum":1}
		 * */
		toRankDetailListPage:function(data,callback){
			var url=webserver+"/rs/external/storeRank/toListPage?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 打赏排行主页
		 * 参数
		 * {"querytype":"storeReward","filter":"month","numPerPage":10,"storeid":9,"pageNum":1}
		 * */
		toRewardListPage:function(data,callback){
			var url=webserver+"/rs/external/storeRank/toListPage?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 店员打赏详情
		 * 参数
		 * storeid & empid
		 * */
		getEmployeeRewardRankDetail:function(data,callback){
			var url=webserver+"/rs/external/storeRank/getEmployeeRewardRankDetail?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 打赏排行主页
		 * 参数
		 * {"querytype":"empReward","filter":"month","numPerPage":10,"storeid":9,"empid":43,"pageNum":1}
		 * */
		toRewardDetailListPage:function(data,callback){
			var url=webserver+"/rs/external/storeRank/toListPage?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/////////////////店铺统计/////////////////
		/* 店铺统计
		 * 参数
		 * storeid & filter(day,yesterday,week,lastweek,month,lastmonth)
		 * */
		getStoreStatisticInfoByStoreID:function(data,callback){
			var url=webserver+"/rs/external/storeStatistic/getStoreStatisticInfoByStoreID?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/////////////////我的收入//////////////////
		/* 我的收入
		 * 参数
		 * empid
		 * */
		getEmployeeIncomeBaseInfo:function(data,callback){
			var url=webserver+"/rs/external/employeeIncome/getEmployeeIncomeBaseInfo?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 打赏及评价列表
		 * 参数
		 * {"numPerPage":10,"empid":43,"pageNum":1}
		 * */
		toEmployeeIncomeListPage:function(data,callback){
			var url=webserver+"/rs/external/employeeIncome/toListPage?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 提现
		 * 参数
		 * ItRecordCashLogEntity 实体
		 * */
		saveCash:function(data,callback){
			var url=webserver+"/rs/external/itRecordCashLog/save?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 提现纪录
		 * 参数
		 * {"numPerPage":10,"empid":43,"pageNum":1}
		 * */
		toCashListPage:function(data,callback){
			var url=webserver+"/rs/external/itRecordCashLog/toListPage?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 设置
		 * 参数
		 * BsEmployeeInfoEntity 实体
		 * */
		editEmployee:function(data,callback){
			var url=webserver+"/rs/external/empInfo/edit?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 获取员工全量信息
		 * 参数
		 * BsEmployeeInfoEntity 实体
		 * */
		getEmployeeInfo:function(data,callback){
			var url=webserver+"/rs/external/empInfo/getEmployeeInfo?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 获取员工排行列表
		 * 参数
		 * {"searchType":0,"type":0,"dateType":0,"storeId":9,"storeType":0} 实体
		 * */
		findStorManagerRanking:function(data,callback){
			var url=webserver+"/rs/external/statistics/wap/findStorManagerRanking?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		
		////////////////打赏///////////////
		/* 绑定员工信息
		 * 参数
		 * BsEmployeeInfoEntity 实体
		 * */
		saveEmployee:function(data,callback){
			var url=webserver+"/rs/external/empInfo/save?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 店员基本信息
		 * 参数
		 * empid 
		 * 返回
		 * {"resultMessage":{"status":0,"messageCode":"0000","messageText":"成功","servertime":"2016-04-01 16:31:50"},"resultData":{"body":{"empnickname":"哈哈","rewardmoney":4.56,"empid":43,"storename":"北京二店12","isupdate":1,"storeid":9,"headpic":""}}}
		 * */
		getEmpHomepageBaseInfoByEmpId:function(data,callback){
			var url=webserver+"/rs/external/employeeHomepage/getEmpHomepageBaseInfoByEmpId?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},		
		/* 保存评价
		 * 参数
		 * ItEvaluateInfoEntity 实体
		 * */
		saveEvaluate:function(data,callback){
			var url=webserver+"/rs/external/employeeHomepage/addEvaluate?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 保存打赏
		 * 参数
		 * ItRewardInfoEntity 实体
		 * */
		saveReward:function(data,callback){
			var url=webserver+"/rs/external/employeeHomepage/addReward?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 删除打赏预订单
		 * 参数
		 * orderNum 订单号
		 * */
		removeReward:function(data,callback){
			var url=webserver+"/rs/external/rewardInfo/deleteByOrderNum?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		////////////////人员管理///////////////////
		/* 人员管理主页
		 * 参数
		 * storeId 店铺编号
		 * */
		getStoreEmpCountByStoreID:function(data,callback){
			var url=webserver+"/rs/external/storeEmpManager/getStoreEmpCountByStoreID?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 店员列表
		 * 参数
		 * {"numPerPage":10,"storeId":9,"pageNum":1}
		 * */
		toEmpListPage:function(data,callback){
			var url=webserver+"/rs/external/empInfo/toListPage?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
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
			var url=webserver+"/rs/external/empInfo/removeEmployee?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		////////////绑定店铺/////////////
		/* 店长绑定
		 * 参数
		 * {"numPerPage":10,"storeId":9,"pageNum":1}
		 * */
		saveManagerStore:function(data,callback){
			var url=webserver+"/rs/external/storeInfo/saveManagerStore?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/* 分成人员绑定
		 * 参数
		 * {"numPerPage":10,"storeId":9,"pageNum":1}
		 * */
		saveDividedStore:function(data,callback){
			var url=webserver+"/rs/external/storeInfo/saveDividedStore?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},

		/* 区域经理绑定
		 * 参数
		 * {"numPerPage":10,"storeId":9,"pageNum":1}
		 * */
		saveMerManage:function(data,callback){
			var url=webserver+"/rs/external/storeInfo/saveMerManage?v="+new Date().getTime();
			ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		////////////////
		/*初始化微信参数
		 * 参数
		 * empId17
		 * 		*/
		initWeiXin:function(data,callback){
			var url=webserver+"/rs/wechat/getWxParam?v="+new Date().getTime();
			getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			});
		},
		/*微信支付接口
		 * 参数
		 * empId
		 * storeCode
		 * customerKey
		 * orderNum  uuid生成*/
		wxPayTo:function(data,callback){
			 var url=webserver+"/rs/wechat/getWxPayInit?v="+new Date().getTime();
			 ajaxJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			 });
	    },
		/*微信图片上传
		 * 参数
		 * serverId
		 * empId
		 * */
	    uploadImg:function(data,callback){
			 var url=webserver+"/rs/wechat/uploadImg?v="+new Date().getTime();
			 getJson(url,data,function(datas){
				if(callback){
					callback(datas);
				}
			 });
	    },
		 /*支付宝支付接口
		  * 参数
		 * empId
		 * storeCode
		 * customerKey
		 * qrCode */	
	 	aliPayto:function(data,callback){
			 var url=webserver+"/wechat/getAliPayInit?v="+new Date().getTime();
			 ajaxJson(url,data,function(datas){
					if(callback){
						callback(datas);
					}
			 });
		},
		/*获取验证码
		 *接口地址
		 *phone
		 *get
		 * */
		gainCode:function(data,callback){
			 var url=webserver+"/rs/external/sms/send?v="+new Date().getTime();
			 getJson(url,data,function(datas){
					if(callback){
						callback(datas);
					}
			 });
		},
		lock:false,
		/**
		 * 发短信
		 * params:phone手机号
		 * verificationCodeBnt：验证码元素ID
		 */
		sendSmsCode:function(params,verificationCodeBnt,_back){
				var addr = require("addr");
				var tools = require("common");
		        var $ = jQuery;
				if(addr.lock){
					return ;
				}
				addr.lock = true;
				if(params.account==null){
					params.account = params.phone;
				}
				if(params.account == ""){
					 tools.toastTip(null, "请输入手机号!");
				}
				if(params.account.length<11){
				     tools.toastTip(null, "请正确输入手机号码");
				     addr.lock = false;
				     return ;
				}
				var i = 60;
				function verificationCode(){
					setTimeout(function(){
						$("#"+verificationCodeBnt).html(i--+"秒");
						if(i>=0){
							console.log(i);
							verificationCode();
						}else{
							end();
						}
					}, 1000);
				}
				verificationCode();
				function end(){
					i = 0;
					console.log('end');
					addr.lock = false;
					$("#"+verificationCodeBnt).html("重新获取");
				}
				//debugger;
				addr.gainCode(params,function(datas){
					//成功、失败处理
					var status = datas.resultMessage.status;
					if(status == 0){			
						var res = datas.resultData.body.result;
						if(res.statusCode == 1){
							_back();
							//发送成功，加上锁定
							addr.accountLock = true;
							 tools.toastTip(null, "发送成功,请注意查收!");
						}else{
							end();
							tools.toast(null,res.description);	
						}
							
					}else{			
						end();
						tools.toast(null,datas.resultMessage.messageText);
					}
				});
			
		}
		
	};
	
	
	return tipManage;
});
