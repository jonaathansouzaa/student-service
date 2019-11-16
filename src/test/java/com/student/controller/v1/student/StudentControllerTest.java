package com.student.controller.v1.student;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.student.controller.v1.student.adapter.StudentAdapter;
import com.student.controller.v1.student.request.StudentRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.ContextsLoads;
import com.student.base.controller.BaseControllerTest;
import com.student.controller.v1.student.response.StudentResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@ActiveProfiles("test")
@Import(ContextsLoads.class)
public class StudentControllerTest extends BaseControllerTest {

	private static final String STUDENT_INSERT = "/v1/student";
	
	private ObjectMapper mapper;
	
	@MockBean
	private StudentAdapter studentAdapter;
	
	@Autowired
    protected MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mapper = new ObjectMapper();
	}
	
	@Test
	public void shouldReturnSuccessWhenIReceiveACorrectlyRequestToInsertStudent() throws JsonProcessingException, Exception {
		final JsonNode requestJson = loadAsJsonFromResource("json/student/student-request.json");
		final String expectedResponse = loadResourceAsString("json/student/student-response.json");
		
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setName("Jonathan Santos");
		
		when(studentAdapter.handleRequest(any(StudentRequest.class))).thenReturn(studentResponse);
		
		mockMvc.perform(post(STUDENT_INSERT)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(requestJson)))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedResponse));
		
		verify(studentAdapter).handleRequest(any(StudentRequest.class));
	}

}
