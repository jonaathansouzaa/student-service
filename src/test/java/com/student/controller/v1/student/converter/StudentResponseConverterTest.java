package com.student.controller.v1.student.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.student.controller.v1.student.response.StudentResponse;
import com.student.dto.StudentDTO;

@RunWith(MockitoJUnitRunner.class)
public class StudentResponseConverterTest {

	@InjectMocks
	private StudentResponseConverter studentResponseConverter;

	@Test
	public void shouldReturnAStudentResponseWhenIReceiveAStudentDTO() {
		final StudentDTO studentDto = new StudentDTO(null, RandomStringUtils.randomAlphabetic(10));
		final StudentResponse expected = new StudentResponse(studentDto.getDescription());
				
		StudentResponse actual = studentResponseConverter.responseFromDto(studentDto);
		assertThat(expected).isEqualToComparingFieldByFieldRecursively(actual);
	}
	
}
