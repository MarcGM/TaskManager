package com.fintonic.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fintonic.taskmanager.models.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
