__CreatePath = function (js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }

    path = path.substring(0, path.indexOf("/assets"));
   
    return path;
};

var bootPATH = __CreatePath("tips.js");

var webhost="http://"+window.location.host ;
var webserver=bootPATH;
var pageUrl = webserver+"/views/template/web";

//日期格式化
Date.prototype.Format = function (fmt) {
	try{
	     var o = {
	         "M+": this.getMonth() + 1, //月份
	         "d+": this.getDate(), //日
	         "h+": this.getHours(), //小时
	         "m+": this.getMinutes(), //分
	         "s+": this.getSeconds(), //秒
	         "q+": Math.floor((this.getMonth() + 3) / 3), //季度
	         "S": this.getMilliseconds() //毫秒
	     };
	     if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	     for (var k in o)
	         if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	     return fmt;
	}catch(e){
		 return "";
	}
 };

//转化日期
String.prototype.toDate = function () {
	try{
		 var DateStr=this;
		 var ymSplit=DateStr.split(" ");
		 var arys= ymSplit[0].split('-'); 
		 var times=ymSplit[1].split(':'); 
	     myDate = new Date(arys[0],--arys[1],arys[2],times[0],times[1],times[2]);  
	     return myDate;
	}catch(e){
		 console.log(e);
		 return "";
	}
};

require.config({
	baseUrl: webserver+'/assets',
    paths: {
    	"jquery":'plugin/jquery-1.9.1.min',
    	"mui":'plugin/mui/dist/js/mui.min',
    	"mui_view":"plugin/mui/js/mui.view",    	
    	"ftscroller":"plugin/ftscroller",
    	"iscroll":"plugin/iscroll",
    	"addr":"def/js/tips/addr",
    	"common":"def/js/tips/common",
    	"template7":"plugin/template/dist/template7.min",
    	"flot":"plugin/flot/jquery.flot.min",
    	"jweixin":"plugin/wx/jweixin-1.0.0",
    	"echarts": 'plugin/echarts-m/dist',
    	"swiper": 'plugin/swiper.min'
    	
    },
    shim: {
    	"mui":{exports:"mui"},
        "mui_view" :{deps:["mui"]},
        "jquery":{exports:"jQuery"},
        "ftscroller":{exports:"FTScroller"},
        "iscroll":{exports:"iScroll"},
        "template7":{exports:"Template7"},
        "jweixin":{exports:"wx"}
    }
});


