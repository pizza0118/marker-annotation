package com.demo.mockdata.vo;

public class DeptInfoOutput {
	
	private String name;
	private String location;
	
	public DeptInfoOutput() {}
	
	public DeptInfoOutput(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
