package com.fintonic.taskmanager.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import com.fintonic.taskmanager.models.dtos.TaskDTO;
import com.fintonic.taskmanager.services.TaskCreationService;

@RestController
@Validated
public class TaskCreationController {
	
	private final TaskCreationService service;

	public TaskCreationController(TaskCreationService service, TaskCreationService taskCreationService) {
		this.service = taskCreationService;
		
	}
	
	@PostMapping(path = "/createTask")
	public ResponseEntity<TaskDTO> createTask(TaskDTO taskDto) {
		Optional<TaskDTO> returnedTask = service.saveTask(taskDto);
		
		if(returnedTask.isPresent())
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
