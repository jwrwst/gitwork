/**
 * 数据字典web版本
 * @returns
 */
var datasObj = {};
var datasParam = {};
function Data(options){
	var pid=null,pCode,max,params,sels = [],datas;
	pid = options.parentId;
	params = options.data;
	max = params.length;
	pCode = options.parentCode;
	if(datasParam[pid] ==null)
	datasParam[pid]={};
	/**
	 * 
	 */
	this.init = function(){
		loadList();
	};
	function setListenter(sel){
		sel.change(function(){
			var me = $(this);
			var nexSel = me.next();
			var level = parseInt(me.attr('data-level'));
			var index = 0;
			nexSel.html("");
			//暂时只处理一级
			for (var i = 0; i < datas.length; i++) {
				var data = datas[i];
				if(data.value == me.val()){
					buildOption(nexSel,data.children,level+1);
				}			
			}
		});
	}
	function buildOption(sel,datas,count){//最后一个 不添加事件
		sel.html('<option value="">--请选择--</option>');
		if(count<max-1){
			setListenter(sel);
		}
		var defText = params[count].defaultText;
		if(defText != null){
			sel.append('<option value="food">'+defText+'</option>');
		}
		sels[count] = sel;
		for (var i = 0; i < datas.length; i++) {
			var da = datas[i];
			var opt = $('<option value="'+da.value+'">'+da.text+'</option>');
			sel.append(opt);
			var children = da.children;
			var defVal = params[count].defaultValue;
			if(defVal != null ){
				if(defVal ==da.value){
					opt.attr("selected","selected");
					if(children != null && children.length>0){
						count++;
						buildSelect(children,count);
					}
				}
			}else if(i==0){
				if(children != null && children.length>0){
					count++;
					buildSelect(children,count);
				}
			}
		}
	}
	//加载数据
	function loadList(){
		//var hasc = $.trim($("#"+pid).html())=="";
		var olen = $("#"+pid).children('select').length;
		var hasc = (olen ==0);
		//if(hasc && (datasParam[pid].isload=='undefined' || datasParam[pid].isload==null)){
		if(hasc){
			//console.debug(datasParam[pid].isload+"  hasc:"+hasc);
			datasParam[pid].isload = true;
			//console.debug(datasParam[pid].isload);
			var url = App.path+"/rs/external/syscode/wap/getAllList?v="+new Date().getTime();
			getJson(url,"pCode="+pCode,function(data){
				  if(data&&data.resultMessage.messageCode=="0000"){
					  var list = data.resultData.body.result;
					  datasObj[pid] = datas = list;
					  $("#"+pid).html("");
					  buildSelect(list,0);
				  }
			});
		}else{
			console.debug(222222222);
			if(datasObj[pid] == null) return;
			var chils = $("#"+pid).children();
			for (var i = 0; i < params.length; i++) {
				$(chils[i]).val(params[i].defaultValue);
				if(i==0){
					//暂时只处理一级
					for (var j = 0; j < datasObj[pid].length; j++) {
						var data = datasObj[pid][j];
						if(data.value == params[i].defaultValue){
							buildOption($(chils[i+1]),data.children,i+1);
						}			
					}
						
				}
			}
		}
	}
	function buildSelect(datas,count){
		//if($("#"+params[count].name).length==0){
			var sel = $('<select class="mui-btn mui-btn-block" style="width:40%;margin-right:1%;background: transparent;padding-right:20px;text-align: center;" data-level="'
					+count+'" name="'+params[count].name+'" id="'+params[count].name+'"></select>');
			$("#"+pid).append(sel);
			buildOption(sel,datas,count);
		//}
	}
}

