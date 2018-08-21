package com.demo.mockdata.vo;

import java.util.List;

import com.demo.vo.TestVO;

public class CustomerInfoInput {
	
	private String id;
	private Integer num;
	private TestVO testVO;
	private List<String> testList;
	private List<TestVO> testVOList;
	private TestVO[] voArray;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public TestVO getTestVO() {
		return testVO;
	}

	public void setTestVO(TestVO testVO) {
		this.testVO = testVO;
	}

	public List<String> getTestList() {
		return testList;
	}

	public void setTestList(List<String> testList) {
		this.testList = testList;
	}

	public List<TestVO> getTestVOList() {
		return testVOList;
	}

	public void setTestVOList(List<TestVO> testVOList) {
		this.testVOList = testVOList;
	}

	public TestVO[] getVoArray() {
		return voArray;
	}

	public void setVoArray(TestVO[] voArray) {
		this.voArray = voArray;
	}
	
}
