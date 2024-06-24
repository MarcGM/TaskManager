package com.fintonic.taskmanager.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskServiceTest {
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	void createTask_OK() {
		
	}
	
}
