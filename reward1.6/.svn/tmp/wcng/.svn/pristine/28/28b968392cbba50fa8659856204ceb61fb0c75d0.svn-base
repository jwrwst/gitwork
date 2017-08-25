hojo.provide("icallcenter.callProcessor");
hojo.require("hojo.io.script");

hojo.declare("icallcenter.callProcessor", null, {
    _phone: null,
    constructor: function (phone) {
        this._phone = phone;
        var evtHandle = this._phone.register("EvtRing", this, "onRing");
        this._phone._handles.push(evtHandle);
        var evtHandle = this._phone.register("EvtHangup", this, "onHangup");
        this._phone._handles.push(evtHandle);
		evtHandle = this._phone.register("EvtDialing", this, "onDialing");
		this._phone._handles.push(evtHandle);
		evtHandle = this._phone.register("EvtPeerStatusChanged", this, "peerStatusChanged"); 
		this._phone._handles.push(evtHandle);
		evtHandle = this._phone.register("EvtCallStatusChanged", this, "callStatusChanged"); 
		this._phone._handles.push(evtHandle);
		evtHandle = this._phone.register("EvtConnected", this, "EvtConnected"); 
		this._phone._handles.push(evtHandle);
		evtHandle = this._phone.register("EvtLogon", this, "EvtLogon"); 
		this._phone._handles.push(evtHandle);
		evtHandle = this._phone.register("EvtMonitorQueue" , this, "EvtMonitorQueue");
        this._phone._handles.push(evtHandle);
    },
    onRing: function (data) {
    	var callsheetId = data.callSheetId;
        var agent = data.agent;
        var originCallNo = data.originCallNo;
        if(hojo.byId("icallcenter.dialout.input"))
            hojo.byId("icallcenter.dialout.input").value = originCallNo;
        var originCalledNo = data.originCalledNo;
        var callType = data.callType;
        var status = data.status;
        var offeringTime = data.offeringTime;
        var ivrkey = data.ivrkey;
        var province = data.province;
        var city = data.city;
        //window.open('www.baidu.com?callNo=' + originCallNo);
        showCustomer(originCallNo);
		var phoneJson = {
	    		Command: "Action",
	    		Action: "Ring",
	    		ActionID: "Ring"+Math.random(),
	    		CallsheetId: callsheetId,
	    		CallNo: originCallNo,
	    		CalledNo: originCalledNo,
	    		CallType: callType,
	    		RingTime: offeringTime,
	    		Agent: agent,
	    		Status: status
		};
	   	this.sendAction(phoneJson);
    },
    onHangup: function(data) {
        console.dir(data);
		var callsheetId = data.callSheetId;
		var agent = data.agent;
		var callNo = data.originCallNo;
		var calledNo = data.originCalledNo;
		var callType = data.callType;
		var status = data.status;
		var ringTime= data.ringTime;
		var beginTime= data.beginTime;
		var endTime= data.endTime;
		var monitorFilename= data.data.MonitorFilename;
		//alert("agent:" + agent +";callNo:" + callNo+";calledNo:"+calledNo+";callType:"+callType+";status:"+status+";ringTime:"+ringTime+";beginTime:"+beginTime+";endTime:"+endTime+";monitorFilename:"+monitorFilename);
	   	var phoneJson = {
	    		Command: "Action",
	    		Action: "Hangup",
	    		ActionID: "Hangup"+Math.random(),
	    		CallsheetId: callsheetId,
	    		CallNo: callNo,
	    		CalledNo: calledNo,
	    		CallType: callType,
	    		RingTime: ringTime,
	    		Agent: agent,
	    		Status: status,
	    		BeginTime: beginTime,
	    		EndTime: endTime,
	    		MonitorFilename: monitorFilename
		};
	   	this.sendAction(phoneJson);
    },
    onDialing: function(data) {
		var callsheetId = data.callSheetId;
		var agent = "";
		var callNo = data.originCallNo;
		var calledNo = data.originCalledNo;
		var callType = data.callType;
		var status = data.status;
		var ringTime= data.offeringTime;
		var beginTime= "";
		var endTime= "";
		var monitorFilename= "";
	   	var phoneJson = {
	    		Command: "Action",
	    		Action: "Dialing",
	    		ActionID: "Dialing"+Math.random(),
	    		CallsheetId: callsheetId,
	    		CallNo: callNo,
	    		CalledNo: calledNo,
	    		CallType: callType,
	    		RingTime: ringTime,
	    		Agent: agent,
	    		Status: status,
	    		BeginTime: beginTime,
	    		EndTime: endTime,
	    		MonitorFilename: monitorFilename
		};
	   	
	   	this.sendAction(phoneJson);
    },
    EvtConnected: function(data) {
		var callsheetId = data.callSheetId;
		var agent = "";
		var callNo = data.originCallNo;
		var calledNo = data.originCalledNo;
		var callType = data.callType;
		var status = data.status;
		var ringTime= data.offeringTime;
		var beginTime= data.beginTime;
		var endTime= "";
		var monitorFilename= "";
	   	var phoneJson = {
	    		Command: "Action",
	    		Action: "Connected",
	    		ActionID: "Connected"+Math.random(),
	    		CallsheetId: callsheetId,
	    		CallNo: callNo,
	    		CalledNo: calledNo,
	    		CallType: callType,
	    		RingTime: ringTime,
	    		Agent: agent,
	    		Status: status,
	    		BeginTime: beginTime,
	    		EndTime: endTime,
	    		MonitorFilename: monitorFilename
		};
	   	this.sendAction(phoneJson);
    },
    EvtLogon: function(data) {
    	var status = data; 
	   	var phoneJson = {
	    		Command: "Action",
	    		Action: "Logon",
	    		ActionID: "Logon" + Math.random(),
	    		Status: status
		};
	   	this.sendAction(phoneJson);
    },
    peerStatusChanged: function(data) {
//    	var peerStatus = data; 
//	   	var phoneJson = {
//	    		Command: "Action",
//	    		Action: "Peer",
//	    		ActionID: "Peer" + Math.random(),
//	    		Status: peerStatus
//		};
//	   	this.sendAction(phoneJson);
    },
    callStatusChanged: function(data) {
//    	var peerStatus = data; 
//	   	var phoneJson = {
//	    		Command: "Action",
//	    		Action: "Call",
//	    		ActionID: "Call" + Math.random(),
//	    		Status: peerStatus
//		};
//	   	this.sendAction(phoneJson);
    },
    EvtMonitorQueue: function (queueItem) {
//        console.log(queueItem);
    	//var isMySelfQueue = false;
       // for(var i in queueItem.members){
       // 	var member = queueItem.members[i];
       // 	if(member == this._phone.sipNo) {
      //          isMySelfQueue = true;
       // 	}
      //  }
      //  if(isMySelfQueue == true) {
      //  	alert(queueItem.queueName); 
      //  	alert(queueItem.idleAgentCount);
      //  	alert(queueItem.queueWaitCount);
      //  }
//        if(this._phone && this._phone.monitorQueues) {
//            var totalQueueWait = 0;
//            for(var j in this._phone.monitorQueues)	{
//                var item = this._phone.monitorQueues[j];
//                if(!item || !item.queueId)
//                    continue;
//                var tempArray=[item.members, item.queueName,item.idleAgentCount,item.totalAgentCount,
//                    item.queueWaitCount,(item.totalCalls-item.abadonedCalls)];
//                totalQueueWait = totalQueueWait + item.queueWaitCount;
//            }
//            alert('==========================' + totalQueueWait);
//        }
    },
    sendAction: function(json) {
    	//hojo.byId("icallcenter.iframe").src="http://localhost:15062/?json=" + hojo.toJson(json) + "&random=" + Math.floor(Math.random()*100000);
    }
});