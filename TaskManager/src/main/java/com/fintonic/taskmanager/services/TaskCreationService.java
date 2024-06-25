package com.fintonic.taskmanager.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintonic.taskmanager.models.dtos.TaskDTO;
import com.fintonic.taskmanager.models.entities.Task;
import com.fintonic.taskmanager.repositories.TaskRepository;

@Service
public class TaskCreationService {

	private final TaskRepository repository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public TaskCreationService(TaskRepository taskRepository) {
		this.repository = taskRepository;
	}
	
	public Optional<TaskDTO> saveTask(TaskDTO taskDTO){
		Task task = modelMapper.map(taskDTO, Task.class);
		
		Task savedTask = repository.save(task);
		
		return Optional.of(modelMapper.map(savedTask, TaskDTO.class));
	}
	
}
