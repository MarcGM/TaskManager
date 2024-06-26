package com.fintonic.taskmanager.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintonic.taskmanager.models.dtos.TaskDTO;
import com.fintonic.taskmanager.models.entities.Task;
import com.fintonic.taskmanager.repositories.TaskRepository;

@Service
public class TaskListService {
	
	private final TaskRepository repository;
	
	@Autowired
    private ModelMapper modelMapper;

	public TaskListService(TaskRepository taskRepository) {
		this.repository = taskRepository;
	}
	
	public List<TaskDTO> listTasks() {
		List<Task> tasks = repository.findAll();
		
		return modelMapper.map(tasks, List.class);
	}

}
