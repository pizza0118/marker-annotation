package com.demo.annotation.vo;

import java.util.Map;

public class ApiInfoVO {
	
	private String value;
	private String desc;
	private String url;
	private Map<String, Object> inputs;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, Object> getInputs() {
		return inputs;
	}
	public void setInputs(Map<String, Object> inputs) {
		this.inputs = inputs;
	}
	
}
