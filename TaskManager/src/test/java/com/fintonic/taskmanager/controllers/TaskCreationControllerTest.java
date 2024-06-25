package com.fintonic.taskmanager.controllers;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintonic.taskmanager.models.dtos.TaskDTO;
import com.fintonic.taskmanager.services.TaskCreationService;

@SpringBootTest
class TaskCreationControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TaskCreationService taskCreationService;
	@InjectMocks
	private TaskCreationController taskCreationController;
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void createTaskWithoutTags_ReturnOK() throws Exception {
		TaskDTO taskDtoRequest = new TaskDTO();
		taskDtoRequest.setTitle("Reply email");
		taskDtoRequest.setDescription("Reply customer email");
		taskDtoRequest.setExpirationDate(LocalDate.of(2024, 06, 26));
		
		Optional<TaskDTO> taskDtoResponse = Optional.of(new TaskDTO());
		taskDtoResponse.get().setTitle("Reply email");
		taskDtoResponse.get().setDescription("Reply customer email");
		taskDtoResponse.get().setExpirationDate(LocalDate.of(2024, 06, 26));
		
		when(taskCreationService.saveTask(any(TaskDTO.class))).thenReturn(taskDtoResponse);
		
		Optional<String> taskDtoJson = dtoToJSON(taskDtoResponse.get());
		if(taskDtoJson.isEmpty()) {
			fail("JSON conversion failed.");
		}else{
			mockMvc.perform(post("/createTask")
					.contentType(MediaType.APPLICATION_JSON)
					.content(taskDtoJson.get()))
					.andExpect(status().isCreated())
					.andExpect(content().contentType("application/json"))
	                .andExpect(jsonPath("$.title").value("Reply email"))
	                .andExpect(jsonPath("$.description").value("Reply customer email"))
	                .andExpect(jsonPath("$.expirationDate").value(taskDtoRequest.getExpirationDate().toString()));
		}
			
	}
	@Test
	void createTaskWithTags_ReturnOK() throws Exception{
		TaskDTO taskDtoRequest = new TaskDTO();
		taskDtoRequest.setTitle("Write inform");
		taskDtoRequest.setDescription("Write inform about finances.");
		taskDtoRequest.setExpirationDate(LocalDate.of(2024, 06, 26));
		List<String> tags = new ArrayList<>();
		tags.add("finances");
		tags.add("inform");
		taskDtoRequest.setTags(tags);
		
		Optional<TaskDTO> taskDtoResponse = Optional.of(new TaskDTO());
		taskDtoResponse.get().setTitle("Write inform");
		taskDtoResponse.get().setDescription("Write inform about finances.");
		taskDtoResponse.get().setExpirationDate(LocalDate.of(2024, 06, 26));
		List<String> tagsResponse = new ArrayList<>();
		tagsResponse.add("finances");
		tagsResponse.add("inform");
		taskDtoResponse.get().setTags(tagsResponse);
		
		when(taskCreationService.saveTask(any(TaskDTO.class))).thenReturn(taskDtoResponse);
		
		Optional<String> taskDtoJson = dtoToJSON(taskDtoResponse.get());
		if(taskDtoJson.isEmpty()) {
			fail("JSON conversion failed.");
		}else{
			mockMvc.perform(post("/createTask")
					.contentType(MediaType.APPLICATION_JSON)
					.content(taskDtoJson.get()))
					.andExpect(status().isCreated())
					.andExpect(content().contentType("application/json"))
	                .andExpect(jsonPath("$.title").value("Write inform"))
	                .andExpect(jsonPath("$.description").value("Write inform about finances."))
	                .andExpect(jsonPath("$.expirationDate").value(taskDtoRequest.getExpirationDate().toString()))
	                .andExpect(jsonPath("$.tags").isArray())
	                .andExpect(jsonPath("$.tags").value(hasItem("finances")))
	                .andExpect(jsonPath("$.tags").value(hasItem("inform")));
		}
	}
	@Test
	void createTaskWithoutTitle_ReturnError() throws Exception{
		TaskDTO taskDtoRequest = new TaskDTO();
		taskDtoRequest.setDescription("Reply customer email");
		taskDtoRequest.setExpirationDate(LocalDate.of(2024, 06, 26));
		
		Optional<TaskDTO> taskDtoResponse = Optional.of(new TaskDTO());
		taskDtoResponse.get().setDescription("Reply customer email");
		taskDtoResponse.get().setExpirationDate(LocalDate.of(2024, 06, 26));
		
		when(taskCreationService.saveTask(any(TaskDTO.class))).thenReturn(taskDtoResponse);
		
		Optional<String> taskDtoJson = dtoToJSON(taskDtoResponse.get());
		if(taskDtoJson.isEmpty()) {
			fail("JSON conversion failed.");
		}else{
			mockMvc.perform(post("/createTask")
					.contentType(MediaType.APPLICATION_JSON)
					.content(taskDtoJson.get()))
					.andExpect(status().isInternalServerError());
		}

	}
	@Test
	void createTaskWithoutDescription_ReturnError() throws Exception{
		TaskDTO taskDtoRequest = new TaskDTO();
		taskDtoRequest.setTitle("Write inform");
		taskDtoRequest.setExpirationDate(LocalDate.of(2024, 06, 26));
		
		Optional<TaskDTO> taskDtoResponse = Optional.of(new TaskDTO());
		taskDtoResponse.get().setTitle("Write inform");
		taskDtoResponse.get().setExpirationDate(LocalDate.of(2024, 06, 26));
		
		when(taskCreationService.saveTask(any(TaskDTO.class))).thenReturn(taskDtoResponse);
		
		Optional<String> taskDtoJson = dtoToJSON(taskDtoResponse.get());
		if(taskDtoJson.isEmpty()) {
			fail("JSON conversion failed.");
		}else{
			mockMvc.perform(post("/createTask")
					.contentType(MediaType.APPLICATION_JSON)
					.content(taskDtoJson.get()))
					.andExpect(status().isInternalServerError());
		}
		
	}
	@Test
	void createTaskWithoutExpirationDate_ReturnError() throws Exception{
		TaskDTO taskDtoRequest = new TaskDTO();
		taskDtoRequest.setTitle("Write inform");
		taskDtoRequest.setDescription("Reply customer email");
		
		Optional<TaskDTO> taskDtoResponse = Optional.of(new TaskDTO());
		taskDtoResponse.get().setTitle("Write inform");
		taskDtoResponse.get().setDescription("Reply customer email");
		
		when(taskCreationService.saveTask(any(TaskDTO.class))).thenReturn(taskDtoResponse);
		
		Optional<String> taskDtoJson = dtoToJSON(taskDtoResponse.get());
		if(taskDtoJson.isEmpty()) {
			fail("JSON conversion failed.");
		}else{
			mockMvc.perform(post("/createTask")
					.contentType(MediaType.APPLICATION_JSON)
					.content(taskDtoJson.get()))
					.andExpect(status().isInternalServerError());
		}
		
	}

	private Optional<String> dtoToJSON(TaskDTO dto) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		Optional<String> taskDTOJson = Optional.empty();
		
		try {
			taskDTOJson = Optional.of(objectMapper.writeValueAsString(dto));
		} catch (JsonProcessingException e) {
			System.out.println("It has not been possible to convert the object to JSON.");
			e.printStackTrace();
		}
		
		return taskDTOJson;
	}
}
