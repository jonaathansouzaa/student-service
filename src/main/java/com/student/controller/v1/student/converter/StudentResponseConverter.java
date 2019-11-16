package com.student.controller.v1.student.converter;

import org.springframework.stereotype.Component;

import com.student.controller.v1.student.response.StudentResponse;
import com.student.dto.StudentDTO;

@Component
public class StudentResponseConverter {

	public StudentResponse responseFromDto(StudentDTO studentDto) {
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setName(studentDto.getDescription());
		return studentResponse;
	}

}
