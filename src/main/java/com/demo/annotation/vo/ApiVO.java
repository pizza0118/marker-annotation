package com.demo.annotation.vo;

import java.util.ArrayList;
import java.util.List;

public class ApiVO {
	private List<ApiModuleVO> apiModuleVOList;

	public List<ApiModuleVO> getApiModuleVOList() {
		if(null == apiModuleVOList) {
			apiModuleVOList = new ArrayList<>();
		}
		return apiModuleVOList;
	}

	public void setApiModuleVOList(List<ApiModuleVO> apiModuleVOList) {
		this.apiModuleVOList = apiModuleVOList;
	}
}
