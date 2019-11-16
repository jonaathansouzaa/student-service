package com.student.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.student.converter.StudentConverter;
import com.student.dto.StudentDTO;
import com.student.model.Student;
import com.student.repository.StudentRepository;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

	@Mock 
	private StudentConverter studentConverter;
	
	@Mock
	private StudentRepository studentRepository;
	
	@InjectMocks
	private StudentService studentService;
	
	@Test
	public void shouldReturnAStudentWhenISaveWithSuccess() {
		final StudentDTO studentDTO = new StudentDTO(RandomStringUtils.randomAlphabetic(10));
		final StudentDTO expected = new StudentDTO(studentDTO.getDescription());
		final Student student = new Student(studentDTO.getDescription());
		
		when(studentConverter.entityFromDTO(studentDTO)).thenReturn(student);
		when(studentRepository.save(student)).thenReturn(student);
		when(studentConverter.dtoFromEntiy(student)).thenReturn(expected);
		
		StudentDTO actual = studentService.save(studentDTO);
		
		assertEquals(expected, actual);
		
		verify(studentConverter).entityFromDTO(studentDTO);
		verify(studentRepository).save(student);
		verify(studentConverter).dtoFromEntiy(student);
	}
	
	@Test
	public void shouldReturnAStudentWhenIsFound() {
		Long studentId = RandomUtils.nextLong();
		Optional<Student> expected = Optional.of(new Student());

		when(studentRepository.findById(studentId)).thenReturn(expected);
		
		Optional<Student> actual = studentService.findById(studentId);
		assertEquals(expected, actual);
	}
	
}
