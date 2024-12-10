package com.cg.controller;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.AppointmentDTO;
import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientDTO;
import com.cg.entity.Appointment;
import com.cg.feign.DoctorFeignClient;
import com.cg.feign.PatientFeignClient;
import com.cg.service.IAppointmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RequestMapping("/api")
@RestController
public class AppointmentController {
	@Autowired
	IAppointmentService appointmentservice;
	
	@Autowired
	PatientFeignClient patientFeign;

	@Autowired
	DoctorFeignClient doctorFeign;
	
	 private static final String DOCTOR_SERVICE = "DoctorService";
	    private int attempt = 1;
	
	@GetMapping("/appointment")
	public List<Appointment> getAppointments()
	{
		return appointmentservice.findAll();
	}
	
	@GetMapping("/appointment/{id}")
	public Optional<Appointment> findAppointmentById(@PathVariable int id){
		return appointmentservice.findAppointmentById(id);
	}	
	
	@GetMapping("/appointmentdto/{id}")
	public AppointmentDTO getAppointmentwithPatientandDoctor(@PathVariable int id) {
		return appointmentservice.getAppointmentwithPatientandDoctor(id);
		
	}
	
	//http://localhost:8802/api/doctor/152
	//http://localhost:8802/api/appointmentdto/2
	@GetMapping(path="/doctor/{id}",produces= {MediaType.APPLICATION_JSON_VALUE}) 
	@CircuitBreaker(name = DOCTOR_SERVICE, fallbackMethod = "getDoctorFallback")
    @Retry(name = DOCTOR_SERVICE, fallbackMethod = "getDoctorFallback")
	public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable int id) {
		DoctorDTO doctor=doctorFeign.getDoctorById(id);
	        return ResponseEntity.ok().body(doctor);
    }
	
	public ResponseEntity<DoctorDTO> getDoctorFallback(int id, Throwable throwable) {
		DoctorDTO fallback=doctorFeign.getDoctorById(id);
        return ResponseEntity.ok().body(fallback);
    }
	

	@GetMapping(path="/patient/{id}",produces= {MediaType.APPLICATION_JSON_VALUE}) 
	 public PatientDTO getPatientById(@PathVariable int id) {
		PatientDTO patient=patientFeign.getPatientById(id);
		return patient;
	}
		
	@GetMapping("/appointment/myappointment")
	public Optional<Appointment> getAppointmentById(@RequestParam int id){
		return appointmentservice.findAppointmentById(id);
	}
	
	@PostMapping("/appointment")
	public Appointment createAppointment(@Validated @RequestBody Appointment a) {
		return appointmentservice.CreateAppointment(a);
	}
	
	@DeleteMapping("/appointment/{id}")
	public String deleteAppointmentById(@PathVariable int id) {
		String r=appointmentservice.DeleteAppointmentbyId(id);
		return r;
	}
	
	@PutMapping("/appointment")
	public Appointment updateAppointment(@RequestBody Appointment a) {
		return appointmentservice.UpdateData(a);
	}
}