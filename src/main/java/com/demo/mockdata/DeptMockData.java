package com.demo.mockdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.mockdata.vo.DeptInfoOutput;

@Component
public class DeptMockData {
	
	static public DeptInfoOutput getDeptInfo() {
		return new DeptInfoOutput("資訊部", "台北");
	}
	
	static public List<DeptInfoOutput> getDeptInfoList() {
		List<DeptInfoOutput> rtnList = new ArrayList<>();
		rtnList.add(new DeptInfoOutput("資訊部", "台北內湖"));
		rtnList.add(new DeptInfoOutput("會計部", "台北松山"));
		rtnList.add(new DeptInfoOutput("財務部", "台北中山"));
		return rtnList;
	}
}
