hojo.provide("icallcenter.logon");
icallcenter.logon.startLogon = function (loginName, password, extenType) {
    var config = {
        Monitor: true,
        proxy_url: proxyUrl,
        hollyPhone_url: "http://127.0.0.1:8099/command",
        extenType: extenType,
        password: password,
        User: loginName,
        busyType: "0"
    };
    icallcenter.logon.initPhone(config);
};

icallcenter.logon.initPhone = function (config) {
    hojo.require("icallcenter.Phone");
    icallcenter.Phone.registerEvent(config);
//    icallcenter.Phone.registerHollyPhoneEvent(config);
};

icallcenter.logon.afterPhone = function (phone) {
    if (phone) {
        hojo.require("icallcenter.SoftphoneBar");
        softphoneBar = new icallcenter.SoftphoneBar(phone, "softphonebar");
        hojo.require("icallcenter.callProcessor");
        callProcessor = new icallcenter.callProcessor(phone);
    }
};

icallcenter.logon.getUrlValue = function (param) {
    var query = window.location.search;
    var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1)
        return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1)
        return query.substring(iStart);
    return query.substring(iStart, iEnd);
};

icallcenter.logon.logout = function () {

};