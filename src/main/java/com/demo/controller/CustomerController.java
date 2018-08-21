package com.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.ApiInfo;
import com.demo.annotation.ApiModule;
import com.demo.mockdata.CustomerMockData;
import com.demo.mockdata.vo.CustomerInfoInput;
import com.demo.mockdata.vo.CustomerInfoOutput;

@RestController
@ApiModule(value = "customerWebservice")
@RequestMapping("/api/customer")
public class CustomerController {
	
	@PostMapping("/getCustomerInfo")
	@ApiInfo(desc = "取得客戶資訊", value = "getCustomerInfo")
	public CustomerInfoOutput getCustomerInfo(@RequestBody CustomerInfoInput input) {
		return CustomerMockData.getCustomerInfo();
	}
	
	@PostMapping("/getCustomerInfoList")
	@ApiInfo(desc = "取得所有客戶資訊", value = "getCustomerInfoList")
	public List<CustomerInfoOutput> getCustomerInfoList() {
		return CustomerMockData.getCustomerInfoList();
	}
}
