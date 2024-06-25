package com.fintonic.taskmanager.models.dtos;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskDTO {
	
	@JsonProperty
	@NotBlank()
	private String title;
	@JsonProperty
	@NotBlank()
	private String description;
	@JsonProperty
	@NotNull()
	private LocalDate expirationDate;
	@JsonProperty
	private List<String> tags;
	
	
}
