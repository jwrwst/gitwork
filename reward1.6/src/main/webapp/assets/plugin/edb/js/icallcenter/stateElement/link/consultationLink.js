hojo.provide("icallcenter.stateElement.link.consultationLink");

hojo.declare("icallcenter.stateElement.link.consultationLink", null, {
    constructor: function (base) {
        this._base = base;
    },

    _base: null,

    _changeState: function () {
    },

    _switchState: function (evtJson) {
        if (evtJson.Event == "ChannelStatus") {
            if (evtJson.ChannelStatus == "Hangup") {
                this._base._curCallState = this._base._getInvalid();
            } else if (evtJson.ChannelStatus == "Link") {
                if (evtJson.LinkedChannel.ChannelType == "threeWayCall") {
                    this._base._curCallState = this._base._getThreeWayCallLink();
                } else if (evtJson.LinkedChannel.ChannelType == "normal") {
                    this._base._curCallState = this._base._getNormalLink();
                } else if (evtJson.LinkedChannel.ChannelType == "dialout") {
                    this._base._curCallState = this._base._getDialoutLink();
                } else if (evtJson.LinkedChannel.ChannelType == "inner") {
                    this._base._curCallState = this._base._getInnerLink();
                } else if (evtJson.LinkedChannel.ChannelType == "transfer") {
                    this._base._bussiness();
                }
            } else if (evtJson.ChannelStatus == "hold") {
                this._base._curCallState = this._base._getHold();
            }
        }
    },
    _publish: function () {
    }
});