package com.cg.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Project_Appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Appointment_id")
	private int appid;
	
	@Column(name="doctor_id")
	private int did;
	
	@Column(name="patient_id")
	private int pid;
	
	@Column(name="Appointment_time")
	private LocalDateTime time;
	
	@Column(name="Appointment_date")
	private LocalDate date;
	
	@Column(name="Issue")
	private String issue;
	
	@Column(name="Appointment_status")
	private String status;
	
	@Column(name="Appointment_fees")
	private double fees;

}
