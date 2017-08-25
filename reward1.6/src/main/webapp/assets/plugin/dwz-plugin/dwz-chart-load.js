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

var bootPATH = __CreateJSPath("dwz-chart-load.js");

document.write('<script type="text/javascript" src="' + bootPATH + 'chart/raphael.js"></script>');
document.write('<script type="text/javascript" src="' + bootPATH + 'chart/g.raphael.js"></script>');
document.write('<script type="text/javascript" src="' + bootPATH + 'chart/g.bar.js"></script>');
document.write('<script type="text/javascript" src="' + bootPATH + 'chart/g.line.js"></script>');
document.write('<script type="text/javascript" src="' + bootPATH + 'chart/g.pie.js"></script>');
document.write('<script type="text/javascript" src="' + bootPATH + 'chart/g.dot.js"></script>');

