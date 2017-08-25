hojo.provide("icallcenter.stateElement.link.listenLink");

hojo.declare("icallcenter.stateElement.link.listenLink", null, {
	constructor: function(base) {
		this._base = base;
	},
	
	_base: null,
	
	_callState: "stListened",
	
	_changeToolBarState: function(obj) {
		hojo.publish("EvtCallToolBarChange",[obj._callState]);
	},
	
	_switchCallState:function(evtJson) {
		if(evtJson.Event == "ChannelStatus") {
			if(evtJson.Exten == this._base._phone.sipNo) {
				if(evtJson.ChannelStatus == "Hangup") {
					this._base._curCallState = this._base._getInvalid();
					this._changeToolBarState(this._base._curCallState);
					hojo.publish("EvtEndListen", []);
				}
			}
		} 
	},

	_publish:function() {
		//console.debug("开始抛事件");
	}

});