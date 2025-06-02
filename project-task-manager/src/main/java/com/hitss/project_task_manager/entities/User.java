package com.hitss.project_task_manager.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotBlank
//	@Size(max = 15)
	private String userName;
	
//	@NotBlank
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
//	@NotBlank
	private String passwd;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer idUserCreated;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer isActive;
	
	@OneToOne
	@JoinColumn(name = "person_id")
	private Person person;
}
