package com.demo.mockdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.mockdata.vo.CustomerInfoOutput;


@Component
public class CustomerMockData {
	
	static public CustomerInfoOutput getCustomerInfo() {
		return new CustomerInfoOutput("peter", "123@gmail.com", "0911222333", "台北市市民大道1號");
	}
	
	static public List<CustomerInfoOutput> getCustomerInfoList() {
		List<CustomerInfoOutput> rtnList = new ArrayList<>();
		
		rtnList.add(new CustomerInfoOutput("peter", "123@gmail.com", "0911222333", "台北市市民大道1號"));
		rtnList.add(new CustomerInfoOutput("mary", "456@gmail.com", "0922222333", "台北市市民大道2號"));
		rtnList.add(new CustomerInfoOutput("sam", "789@gmail.com", "0933222333", "台北市市民大道3號"));
		return rtnList;
	}
}
