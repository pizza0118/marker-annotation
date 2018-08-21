package com.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.ApiInfo;
import com.demo.annotation.ApiModule;
import com.demo.mockdata.DeptMockData;
import com.demo.mockdata.vo.DeptInfoInput;
import com.demo.mockdata.vo.DeptInfoOutput;

@RestController
@ApiModule(value = "deptWebservice")
@RequestMapping("/api/dept")
public class DeptController {
	
	@PostMapping("/getDeptInfo")
	@ApiInfo(desc = "取得部門資訊", value = "getDeptInfo")
	public DeptInfoOutput getDeptInfo(@RequestBody DeptInfoInput input) {
		return DeptMockData.getDeptInfo();
	}
	
	@PostMapping("/getDeptInfoList")
	@ApiInfo(desc = "取得所有部門資訊", value = "getDeptInfoList")
	public List<DeptInfoOutput> getDeptInfoList() {
		return DeptMockData.getDeptInfoList();
	}
}
