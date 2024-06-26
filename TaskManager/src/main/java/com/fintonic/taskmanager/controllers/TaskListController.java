package com.fintonic.taskmanager.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintonic.taskmanager.models.dtos.TaskDTO;
import com.fintonic.taskmanager.services.TaskListService;

@RestController
@Validated
public class TaskListController {

	private final TaskListService service;

	public TaskListController(TaskListService taskListService) {
		this.service = taskListService;
	}
	
	@GetMapping(path = "/listTasks")
	public ResponseEntity<List<TaskDTO>> listTasks() {
		List<TaskDTO> listTasks = service.listTasks();
	
		return new ResponseEntity<>(listTasks, HttpStatus.OK);
		
	}
	
}
