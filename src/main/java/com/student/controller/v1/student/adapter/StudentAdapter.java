package com.student.controller.v1.student.adapter;

import com.student.controller.v1.student.converter.StudentRequestConverter;
import com.student.controller.v1.student.converter.StudentResponseConverter;
import com.student.controller.v1.student.request.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.student.controller.v1.student.response.StudentResponse;
import com.student.dto.StudentDTO;
import com.student.service.StudentService;

@Component
public class StudentAdapter {

	private StudentRequestConverter studentRequestConverter;
	private StudentService studentService;
	private StudentResponseConverter studentResponseConverter;

	@Autowired
	public StudentAdapter(StudentRequestConverter studentRequestConverter, StudentService studentService, StudentResponseConverter studentResponseConverter) {
		this.studentRequestConverter = studentRequestConverter;
		this.studentService = studentService;
		this.studentResponseConverter = studentResponseConverter;
	}

	public StudentResponse handleRequest(StudentRequest studentRequest) {
		StudentDTO studentDTOFromRequest = studentRequestConverter.dtoFromRequest(studentRequest);
		StudentDTO studentDTOFromService = studentService.save(studentDTOFromRequest);
		return studentResponseConverter.responseFromDto(studentDTOFromService);
	}

}
