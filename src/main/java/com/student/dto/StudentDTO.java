package com.student.dto;

public class StudentDTO {

	private Long studentId;
	private String description;

	public StudentDTO() {
	}
	
	public StudentDTO(String description) {
		this.description = description;
	}
	
	public StudentDTO(Long studentId, String description) {
		this.studentId = studentId;
		this.description = description;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
