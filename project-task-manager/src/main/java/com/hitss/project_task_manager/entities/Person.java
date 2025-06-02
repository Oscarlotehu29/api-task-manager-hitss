package com.hitss.project_task_manager.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "people")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotBlank
//	@Size(max = 25)
	private String name;
	
//	@Size(max = 25)
//	@NotBlank
	private String father_last_name;
	
//	@Size(max = 25)
	private String mother_last_name;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@DateTimeFormat(iso = ISO.DATE)
	@CreatedDate
	private LocalDate createAt;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@LastModifiedBy
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate updateAt;
	
	private Boolean isActive;
	
}
