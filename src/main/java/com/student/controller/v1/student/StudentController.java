package com.student.controller.v1.student;

import com.student.controller.v1.student.request.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.controller.v1.student.adapter.StudentAdapter;
import com.student.controller.v1.student.response.StudentResponse;

@RestController
@RequestMapping(value = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {
	
	private StudentAdapter studentAdapter;

	@Autowired
	public StudentController(StudentAdapter studentAdapter) {
		this.studentAdapter = studentAdapter;
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public ResponseEntity<StudentResponse> postStudent(@RequestBody StudentRequest studentRequest) {
		return ResponseEntity.ok(studentAdapter.handleRequest(studentRequest));
	}

}
