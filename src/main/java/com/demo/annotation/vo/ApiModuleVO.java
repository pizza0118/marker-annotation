package com.demo.annotation.vo;

import java.util.ArrayList;
import java.util.List;

public class ApiModuleVO {
	
	private String value;
	private List<ApiInfoVO> apiInfoVOList;

	public List<ApiInfoVO> getApiInfoVOList() {
		if(null == apiInfoVOList) {
			apiInfoVOList = new ArrayList<>();
		}
		return apiInfoVOList;
	}

	public void setApiInfoVOList(List<ApiInfoVO> apiInfoVOList) {
		this.apiInfoVOList = apiInfoVOList;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
