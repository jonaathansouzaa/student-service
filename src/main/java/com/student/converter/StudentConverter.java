package com.student.converter;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.student.dto.StudentDTO;
import com.student.model.Student;

@Component
public class StudentConverter {

	public StudentDTO dtoFromEntiy(Student entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setStudentId(entity.getStudentId());
		studentDTO.setDescription(entity.getName());
		return studentDTO;
	}

	public Student entityFromDTO(StudentDTO dto) {
		Student student = new Student();
		student.setName(dto.getDescription());
		return student;
	}

}
