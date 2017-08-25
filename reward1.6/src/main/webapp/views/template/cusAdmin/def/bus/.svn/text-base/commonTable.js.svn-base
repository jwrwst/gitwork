var Dtable = function() {

	return {
		init : function(id,fid,zcCols,url,back) {
			var opt = id;
			if(typeof opt =='string'){
				opt = {
					id:id,
					fromId:fid,
					zcCols:zcCols,
					url:url,
					back:back
				};
			}
			var table = $('#' + opt.id).dataTable({
				"zcCols":opt.zcCols,
				"aoColumns" : [ {
					"bSortable" : false
				}, null, {
					"bSortable" : false
				}, null, {
					"bSortable" : false
				}, {
					"bSortable" : false
				} ],
				"aLengthMenu" : [ [ 10, 20, 50 ], [ 10, 20, 50  ] // change per
																// page values
																// here
				],
		        "aaSorting":[],
		        "extendColspanLen":opt.extendColspanLen,
	            "bSort":false,
				"iDisplayLength" : 10,
				"bRetrieve":"aa",
				"sDefaultContent":"de",
				"bServerSide" : true,
				"sDom" : "t<'row-fluid'<'span6'li><'span6'p>>",
				"sPaginationType" : "bootstrap",
				"oLanguage" : {
					"sLengthMenu" : "_MENU_",
					"oPaginate" : {
						"sPrevious" : "上一页",
						"sNext" : "下一页"
					},
			       "sZeroRecords": "没有检索到数据"
				},
		        //如果加上下面这段内容，则使用post方式传递数据
		        "fnServerData": function ( sSource, aoData, fnCallback ) {
		        	var fromId = opt.fromId;
		        	var obj= fromId;
		        	if(fromId==null){
		        		obj = opt.params.call();
		        	}else{
			        	var obj = fromId;
			        	if(typeof fromId == 'string'){
			        		var obj= $("#"+fromId).serializeJson();;
			        	}else if (typeof fromId == 'object'){
			        		obj= fromId;
			        	}else{
			        		obj = fromId();
			        	}
			        }
		        	
		        	for ( var i in aoData) {
		        		var el=aoData[i];
		        		obj[el.name]=el.value;
					}
		        	var objstr=(JSON.stringify(obj));
			        $.ajax( {
				        dataType: 'json',
				        type: "POST",
				        contentType: "application/json; charset=utf-8",
				        url: sSource,
				        data: objstr,
				        success: function(data){
				        	var b = data.resultData.body.result;
				        	b.iTotalRecords=b.totalCount;
				        	b.iTotalDisplayRecords=b.totalCount;
				        	//b.sAjaxDataProp = b.result;
				        	//b.iTotalDisplayRecords=b.pageSize;
				        	fnCallback(b);
				        }
			        } );
		        },
				"aoColumnDefs" : [ {
					'bSortable' : false,
					'aTargets' : [ 0 ]
				} ],
				"sAjaxSource" : opt.url,
				"sAjaxDataProp" : "result",
				'bStateSave' : true,
		        //"fnRowCallback":gloabVars.zcRowCallback,
		        "fnDrawCallback":function( nRow, aData, iDisplayIndex){
		        	opt.back(nRow, aData, iDisplayIndex);	        	
			    },
		        "aoColumns": opt.zcCols
			});

			return table;
		}
	};
}();