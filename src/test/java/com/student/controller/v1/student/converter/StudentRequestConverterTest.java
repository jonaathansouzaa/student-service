	package com.student.controller.v1.student.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.student.controller.v1.student.constant.Messages;
import com.student.controller.v1.student.request.StudentRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.student.dto.StudentDTO;

	@RunWith(MockitoJUnitRunner.class)
public class StudentRequestConverterTest {

	@InjectMocks
	private StudentRequestConverter studentRequestConverter;
	
	@Test
	public void shouldReturnStudentDTOWhenIReceiveACorrectStudentRequest() {
		final StudentRequest studentRequest = new StudentRequest(RandomStringUtils.randomAlphabetic(10));
		final StudentDTO expected = new StudentDTO(null, studentRequest.getName());

		StudentDTO actual = studentRequestConverter.dtoFromRequest(studentRequest);
		assertThat(expected).isEqualToComparingFieldByFieldRecursively(actual);
	}
	
	@Test
	public void shouldReturnAnExceptionWhenIReceiveAStudentRequestWithoutDescription() {
		final StudentRequest studentRequest = new StudentRequest(null);
		
		assertThatThrownBy(() -> studentRequestConverter.dtoFromRequest(studentRequest))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(Messages.THE_FIELD_DESCRIPTION_IS_REQUIRED);
	}
	
	@Test
	public void shouldReturnAnExceptionWhenIReceiveAStudentRequestWithEmptyDescription() {
		final StudentRequest studentRequest = new StudentRequest("");
		
		assertThatThrownBy(() -> studentRequestConverter.dtoFromRequest(studentRequest))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(Messages.THE_FIELD_DESCRIPTION_IS_REQUIRED);
	}
	
}
