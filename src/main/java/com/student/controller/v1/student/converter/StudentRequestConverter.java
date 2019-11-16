package com.student.controller.v1.student.converter;

import com.student.controller.v1.student.constant.Messages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.student.controller.v1.student.request.StudentRequest;
import com.student.dto.StudentDTO;

@Component
public class StudentRequestConverter {

	public StudentDTO dtoFromRequest(StudentRequest studentRequest) {
		if (StringUtils.isEmpty(studentRequest.getName())) {
			throw new IllegalArgumentException(Messages.THE_FIELD_DESCRIPTION_IS_REQUIRED);
		}
		
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setDescription(studentRequest.getName());
		return studentDTO;
	}

}
