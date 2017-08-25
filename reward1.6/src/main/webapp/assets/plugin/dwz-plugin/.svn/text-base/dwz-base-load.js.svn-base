__CreateJSPath = function (js) {
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
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    return path.replace("/js","");
};

var bootPATH = __CreateJSPath("dwz-base-load.js");

//js加载
document.write('<script src="' + bootPATH + 'js/jquery-1.7.2.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/jquery.cookie.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/jquery.validate.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/jquery.bgiframe.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'uploadify/scripts/swfobject.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.core.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.util.date.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.validate.method.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.regional.zh.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.barDrag.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.drag.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.tree.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.accordion.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.ui.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.theme.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.switchEnv.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.alertMsg.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.contextmenu.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.navTab.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.tab.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.resize.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.dialog.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.dialogDrag.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.sortDrag.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.cssTable.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.stable.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.taskBar.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.ajax.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.pagination.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.database.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.datepicker.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.effects.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.panel.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.checkbox.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.history.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.combox.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.print.js" type="text/javascript"></script>');
document.write('<script src="' + bootPATH + 'js/dwz.regional.zh.js" type="text/javascript"></script>');

//样式表加载
document.write('<link href="' + bootPATH + 'themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>');
document.write('<link href="' + bootPATH + 'themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>');
document.write('<link href="' + bootPATH + 'themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>');
document.write('<link href="' + bootPATH + 'uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>');
