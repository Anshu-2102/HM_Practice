package com.cg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Project_doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Doctor_Id")
	private int id;
	@Column(name="Doctor_Name")
	@NotBlank(message = "The name must not be null and must contain at least one non-whitespace character")
	private String name;
	@Column(name="Doctor_Qualification")
	private String qualification;
	
}
