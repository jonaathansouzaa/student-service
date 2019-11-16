package com.student.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.student.dto.StudentDTO;
import com.student.model.Student;

@RunWith(MockitoJUnitRunner.class)
public class StudentConverterTest {

	@InjectMocks
	private StudentConverter studentConverter;
	
	@Test
	public void shouldReturnAStudentConvertedFromStudentDTO() {
		final StudentDTO studentDto = new StudentDTO(null, RandomStringUtils.randomAlphabetic(10));
		final Student expected = new Student(studentDto.getDescription());

		Student actual = studentConverter.entityFromDTO(studentDto);
		assertThat(expected).isEqualToComparingFieldByFieldRecursively(actual);
	}
	
	@Test
	public void shouldReturnAStudentDTOConvertedFromStudent() {
		final Student student = new Student(RandomStringUtils.randomAlphabetic(10));
		final StudentDTO expected = new StudentDTO(student.getName());

		StudentDTO actual = studentConverter.dtoFromEntiy(student);
		assertThat(expected).isEqualToComparingFieldByFieldRecursively(actual);
	}
	
	@Test
	public void shouldReturnAStudentDTOConvertedFromANullStudent() {
		final Student student = null;
		final StudentDTO expected = null;

		StudentDTO actual = studentConverter.dtoFromEntiy(student);
		assertEquals(expected, actual);
	}
	
}
