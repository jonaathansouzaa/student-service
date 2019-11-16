package com.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.converter.StudentConverter;
import com.student.dto.StudentDTO;
import com.student.model.Student;
import com.student.repository.StudentRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	private StudentConverter studentConverter;

	@Autowired
	public StudentService(StudentRepository studentRepository, StudentConverter studentConverter) {
		this.studentRepository = studentRepository;
		this.studentConverter = studentConverter;
	}
	
	public StudentDTO save(StudentDTO studentDTO) {
		Student studentToInsert = studentConverter.entityFromDTO(studentDTO);
		Student studentInserted = studentRepository.save(studentToInsert);
		return studentConverter.dtoFromEntiy(studentInserted);
	}

	public Optional<Student> findById(Long studentId) {
		return studentRepository.findById(studentId);
	}

}
