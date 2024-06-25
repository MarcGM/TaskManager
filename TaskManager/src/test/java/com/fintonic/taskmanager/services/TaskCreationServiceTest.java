package com.fintonic.taskmanager.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fintonic.taskmanager.models.entities.Task;
import com.fintonic.taskmanager.repositories.TaskRepository;

@SpringBootTest
class TaskCreationServiceTest {

	TaskCreationService service;
	@Mock
	TaskRepository repository;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = Mockito.mock(TaskRepository.class);
        service = new TaskCreationService(repository);
    }
	
	@Test
	public void saveTaskWithoutTags_callMethod_OK() {
		LocalDate localDate = LocalDate.of(2024, 06, 26);
		
		Task task = new Task();
		task.setTitle("Reply email");
		task.setDescription("Reply customer email");
		task.setExpirationDate(localDate);
		task.setTags(null);
		
		when(repository.save(any(Task.class))).thenReturn(task);
		repository.save(task);
		verify(repository, times(1)).save(task);
	}
	
	@Test
	public void saveTaskWithTags_callMethod_OK() {
		LocalDate localDate = LocalDate.of(2024, 06, 26);
		
		Task task = new Task();
		task.setTitle("Reply email");
		task.setDescription("Reply customer email");
		task.setExpirationDate(localDate);
		
		ArrayList<String> tags = new ArrayList<>();
		tags.add("finances");
		tags.add("inform");
		task.setTags(tags);
		
		when(repository.save(any(Task.class))).thenReturn(task);
		repository.save(task);
		verify(repository, times(1)).save(task);
	}
	
}
