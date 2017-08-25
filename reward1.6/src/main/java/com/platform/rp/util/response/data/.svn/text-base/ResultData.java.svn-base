package com.platform.rp.util.response.data;

import java.util.HashMap;
import java.util.Map;

public class ResultData implements RawData{
	private Map<String, Object> body;

	public ResultData() {
		this.body = new HashMap<String, Object>();
	}
	
	public ResultData(Object o) {
		this.putItem("result", o);
	}

	public ResultData(Map<String, Object> body) {
		//this.body = body;
		this.putItem("result", body);
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

	public Map<String, Object> putItem(String key, Object value) {
		if (this.body == null)
			this.body = new HashMap<String, Object>();

		this.body.put(key, value);

		return this.body;
	}

	public Map<String, Object> putAll(Map<String, Object> map) {
		if (this.body == null)
			this.body = new HashMap<String, Object>();

		this.body.putAll(map);

		return this.body;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pushData(Object o) {
		if(o instanceof Map){
			this.putAll((Map<String, Object>) o);
		}else{
			this.putItem("result", o);
		}
		
	}

	
	@Override
	public Object data() {
		return getBody();		
	}
}