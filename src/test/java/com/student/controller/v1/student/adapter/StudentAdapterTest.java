package com.student.controller.v1.student.adapter;

import com.student.controller.v1.student.constant.Messages;
import com.student.controller.v1.student.converter.StudentRequestConverter;
import com.student.controller.v1.student.converter.StudentResponseConverter;
import com.student.controller.v1.student.request.StudentRequest;
import com.student.controller.v1.student.response.StudentResponse;
import com.student.dto.StudentDTO;
import com.student.service.StudentService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentAdapterTest {

	@Mock
	private StudentRequestConverter studentRequestConverter;

	@Mock
	private StudentResponseConverter studentResponseConverter;
	
	@Mock
	private StudentService studentService;
	
	@InjectMocks
	private StudentAdapter studentAdapter;
	
	@Test
	public void shouldReturnAResponseWhenARequestToInsertStudentIsCorrect() {
		final StudentRequest studentRequest = new StudentRequest(RandomStringUtils.randomAlphabetic(10));
		final StudentDTO studentDTO = new StudentDTO(null, studentRequest.getName());
		final StudentResponse expected = new StudentResponse(studentRequest.getName());
		
		when(studentRequestConverter.dtoFromRequest(studentRequest)).thenReturn(studentDTO);
		when(studentService.save(studentDTO)).thenReturn(studentDTO);
		when(studentResponseConverter.responseFromDto(studentDTO)).thenReturn(expected);
		
		StudentResponse actual = studentAdapter.handleRequest(studentRequest);
		assertEquals(expected, actual);
		
		verify(studentRequestConverter).dtoFromRequest(studentRequest);
		verify(studentService).save(studentDTO);
		verify(studentResponseConverter).responseFromDto(studentDTO);
	}
	
	@Test
	public void shouldReturnAnExceptionWhenIReceiveARequestWithoutDescription() {
		final StudentRequest studentRequest = new StudentRequest(null);
		
		when(studentRequestConverter.dtoFromRequest(studentRequest)).thenThrow(new IllegalArgumentException(Messages.THE_FIELD_DESCRIPTION_IS_REQUIRED));
		
		assertThatThrownBy(() -> studentAdapter.handleRequest(studentRequest))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(Messages.THE_FIELD_DESCRIPTION_IS_REQUIRED);
		
		verify(studentRequestConverter).dtoFromRequest(studentRequest);
	}

}
