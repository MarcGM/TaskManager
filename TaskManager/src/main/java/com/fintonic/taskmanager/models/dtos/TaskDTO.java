package com.fintonic.taskmanager.models.dtos;

import java.sql.Date;
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
	
	public TaskDTO() {
		super();
	}

	public TaskDTO(@NotBlank String title, @NotBlank String description, @NotNull LocalDate expirationDate) {
		super();
		this.title = title;
		this.description = description;
		this.expirationDate = expirationDate;
	}
	
	
	public TaskDTO(@NotBlank String title, @NotBlank String description, @NotNull LocalDate expirationDate,
			List<String> tags) {
		super();
		this.title = title;
		this.description = description;
		this.expirationDate = expirationDate;
		this.tags = tags;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
