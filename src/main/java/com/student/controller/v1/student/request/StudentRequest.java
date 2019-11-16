package com.student.controller.v1.student.request;

public class StudentRequest {

	private String name;
	
	public StudentRequest() {
	}
	
	public StudentRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
